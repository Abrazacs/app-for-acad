package ru.sergeysemenov.appforacad.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeysemenov.appforacad.converter.ItemsConverter;
import ru.sergeysemenov.appforacad.dtos.SystemItemExport;
import ru.sergeysemenov.appforacad.dtos.SystemItemHistoryUnit;
import ru.sergeysemenov.appforacad.dtos.SystemItemImportRequest;
import ru.sergeysemenov.appforacad.entity.SystemItem;
import ru.sergeysemenov.appforacad.error.ValidationException;
import ru.sergeysemenov.appforacad.repository.ItemsRepository;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemsService {

    private final ItemsRepository repository;
    private final ItemsConverter converter;
    private final RequestsValidator validator;

    public SystemItemExport findItemById(String id){
        if(!validator.validateId(id))throw new ValidationException();
        return converter.convertItemToExportItem(repository.getReferenceById(id));
    }

    @Transactional
    public void saveOrUpdate(SystemItemImportRequest importRequest){
        if(!validator.validateImportRequest(importRequest))throw new ValidationException();
        OffsetDateTime date = OffsetDateTime.parse(importRequest.getUpdateDate());
        List<SystemItem> systemItemList = importRequest.getItems().stream()
                .map(systemItemImport -> converter.convertImportItem(systemItemImport, date))
                .toList();
        repository.saveAll(systemItemList);
    }

    public void deleteById(String id){
        if(!validator.validateId(id))throw new ValidationException();
        try{
            repository.deleteById(id);
        }catch (IllegalArgumentException | EmptyResultDataAccessException e ){
            throw new EntityNotFoundException();
        }

    }

    public List<SystemItemHistoryUnit> getUpdatedFiles(OffsetDateTime requestDate) {
        OffsetDateTime startDate = requestDate.minusHours(24);
        List<SystemItem> items = repository.getSystemItemsByTypeEqualsAndDateBetween("FILE", startDate, requestDate);
        return items.stream().map(converter::covertItemToHistoryUnit).toList();
    }
}
