package com.kvcapp.event.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Stall {
    @Id
    private String stallId;
    private String name;
    @Column(length = 2000)
    private String description;
    private String stallNumber;
    private String layout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private ModuleEntity module;

    @OneToMany(mappedBy = "stall", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PosterContent> posterContent = new ArrayList<>();

    public Stall() {}
    public Stall(String id) { this.stallId = id; }

    public String getStallId() { return stallId; }
    public void setStallId(String stallId) { this.stallId = stallId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStallNumber() { return stallNumber; }
    public void setStallNumber(String stallNumber) { this.stallNumber = stallNumber; }
    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
    public ModuleEntity getModule() { return module; }
    public void setModule(ModuleEntity module) { this.module = module; }
    public List<PosterContent> getPosterContent() { return posterContent; }
    public void setPosterContent(List<PosterContent> posterContent) { this.posterContent = posterContent; }
}
