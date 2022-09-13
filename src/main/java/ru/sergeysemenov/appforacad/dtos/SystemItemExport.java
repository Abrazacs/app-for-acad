package ru.sergeysemenov.appforacad.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.OffsetDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class SystemItemExport {

    private String id;
    private String url;
    private OffsetDateTime date;
    private String parentId;
    private String type;
    private long size;
    private List<SystemItemExport> children;

    @Override
    public String toString() {
        return "SystemItemExport{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", date=" + date +
                ", parentId='" + parentId + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", children=" + children +
                '}';
    }
}
