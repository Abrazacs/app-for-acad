package ru.sergeysemenov.appforacad.converter;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@Setter
public class FolderDataCollector {
    private OffsetDateTime date;
    private Long size;

}
