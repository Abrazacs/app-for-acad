package ru.sergeysemenov.appforacad.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class SystemItemHistoryUnit {

    private String id;
    private String url;
    private String parentId;
    private String type;
    private long size;
    private OffsetDateTime date;


}
