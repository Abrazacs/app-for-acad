package ru.sergeysemenov.appforacad.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
public class SystemItemImport {

    private String id;
    private Optional<String> url;
    private Optional<String> parentId;
    private String type;
    private Optional<Long> size;

}
