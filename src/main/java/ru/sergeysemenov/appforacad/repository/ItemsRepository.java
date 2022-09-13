package ru.sergeysemenov.appforacad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergeysemenov.appforacad.entity.SystemItem;

import java.time.OffsetDateTime;
import java.util.List;


@Repository
public interface ItemsRepository extends JpaRepository<SystemItem, String> {

    public List<SystemItem> getSystemItemsByTypeEqualsAndDateBetween(String type, OffsetDateTime startDate, OffsetDateTime endDate);

}
