package ru.sergeysemenov.appforacad.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sergeysemenov.appforacad.dtos.SystemItemExport;
import ru.sergeysemenov.appforacad.dtos.SystemItemHistoryUnit;
import ru.sergeysemenov.appforacad.dtos.SystemItemImportRequest;
import ru.sergeysemenov.appforacad.service.ItemsService;


import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class ItemController {

    private final ItemsService service;

    @GetMapping("/nodes/{id}")
    public SystemItemExport getItemById(@PathVariable String id){
        return service.findItemById(id);
    }

    @PostMapping("/imports")
    public void saveItem(@RequestBody SystemItemImportRequest request){
        service.saveOrUpdate(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        service.deleteById(id);
    }

    @GetMapping("/updates")
    public List<SystemItemHistoryUnit> getUpdatedFiles(){
        OffsetDateTime requestDate = OffsetDateTime.now();
        return service.getUpdatedFiles(requestDate);
    }

}
