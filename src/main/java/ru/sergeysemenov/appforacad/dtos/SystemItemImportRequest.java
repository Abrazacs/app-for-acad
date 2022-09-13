package ru.sergeysemenov.appforacad.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class SystemItemImportRequest {
    private List<SystemItemImport> items;
    private String updateDate;
}
