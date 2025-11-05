package com.kvcapp.event.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Exhibition {
    @Id
    private String exhibitionId;
    private String name;
    @Column(length = 2000)
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;

    @Enumerated(EnumType.STRING)
    private Status status = Status.Draft;

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ModuleEntity> modules = new ArrayList<>();

    public enum Status { Draft, Published }

    public Exhibition() {}
    public Exhibition(String id) { this.exhibitionId = id; }

    // getters and setters
    public String getExhibitionId() { return exhibitionId; }
    public void setExhibitionId(String exhibitionId) { this.exhibitionId = exhibitionId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public List<ModuleEntity> getModules() { return modules; }
    public void setModules(List<ModuleEntity> modules) { this.modules = modules; }
}
