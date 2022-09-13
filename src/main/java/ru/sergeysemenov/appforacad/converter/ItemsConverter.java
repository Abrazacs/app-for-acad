package ru.sergeysemenov.appforacad.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.sergeysemenov.appforacad.dtos.SystemItemExport;
import ru.sergeysemenov.appforacad.dtos.SystemItemHistoryUnit;
import ru.sergeysemenov.appforacad.dtos.SystemItemImport;
import ru.sergeysemenov.appforacad.entity.SystemItem;

import java.time.OffsetDateTime;
import java.util.List;


@Component
public class ItemsConverter {


    public SystemItem convertImportItem(SystemItemImport importItem, OffsetDateTime date) {
        SystemItem item = new SystemItem();
        item.setId(importItem.getId());
        if(importItem.getUrl().isPresent()){
            item.setUrl(importItem.getUrl().get());
        }
        item.setDate(date);
        if(importItem.getParentId().isPresent()){
            item.setParentId(importItem.getParentId().get());
        }
        item.setType(importItem.getType());
        if(importItem.getSize().isPresent()){
            item.setSize(importItem.getSize().get());
        }
        return item;
    }

    public SystemItemExport convertItemToExportItem(SystemItem item){
        SystemItemExport exportItem = new SystemItemExport();
        exportItem.setId(item.getId());
        exportItem.setUrl(item.getUrl());
        exportItem.setType(item.getType());
        exportItem.setParentId(item.getParentId());

        switch (exportItem.getType()){
            case "FILE" ->{
                exportItem.setDate(item.getDate());
                exportItem.setSize(item.getSize());
                exportItem.setChildren(null);
            }
            case "FOLDER" ->{
                List<SystemItem> children = item.getChildren();
                if(children.isEmpty()){
                    exportItem.setChildren(null);
                    exportItem.setDate(item.getDate());
                }else {
                    FolderDataCollector collector = new FolderDataCollector(item.getDate(), 0L);
                    collector = collect(children, collector);
                    exportItem.setSize(collector.getSize());
                    exportItem.setDate(collector.getDate());
                    exportItem.setChildren(children.stream().map(this::convertItemToExportItem).toList());
                }
            }
        }
        return exportItem;
    }

    private FolderDataCollector collect(List<SystemItem> items, FolderDataCollector collector){
        for (SystemItem item:items) {
            List<SystemItem> children = item.getChildren();
            if(!children.isEmpty()){
                collect(children, collector);
            }
            if(item.getType().equals("FILE")) {
                collector.setSize(collector.getSize() + item.getSize());
            }
            OffsetDateTime date = item.getDate();
            if(date.isAfter(collector.getDate())) {
                collector.setDate(date);
            }
        }
        return collector;
    }

    public SystemItemHistoryUnit covertItemToHistoryUnit(SystemItem item){
        SystemItemHistoryUnit historyUnit = new SystemItemHistoryUnit();
        historyUnit.setId(item.getId());
        historyUnit.setUrl(item.getUrl());
        historyUnit.setParentId(item.getParentId());
        historyUnit.setType(item.getType());
        historyUnit.setSize(item.getSize());
        historyUnit.setDate(item.getDate());
        return historyUnit;
    }

}
