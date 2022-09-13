package ru.sergeysemenov.appforacad.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "system_items")

public class SystemItem {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "url")
    private String url;

    @Column (name = "date", nullable = false)
    private OffsetDateTime date;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private long size;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private List<SystemItem> children;

    @Override
    public String toString() {
        return "SystemItem{" +
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
