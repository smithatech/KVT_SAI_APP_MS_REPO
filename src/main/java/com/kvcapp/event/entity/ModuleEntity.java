package com.kvcapp.event.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "module")
public class ModuleEntity {
    @Id
    private String moduleId;
    private String name;
    @Column(length = 2000)
    private String description;
    private String assignedTeamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Stall> stalls = new ArrayList<>();

    public ModuleEntity() {}
    public ModuleEntity(String id) { this.moduleId = id; }

    public String getModuleId() { return moduleId; }
    public void setModuleId(String moduleId) { this.moduleId = moduleId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAssignedTeamId() { return assignedTeamId; }
    public void setAssignedTeamId(String assignedTeamId) { this.assignedTeamId = assignedTeamId; }
    public Exhibition getExhibition() { return exhibition; }
    public void setExhibition(Exhibition exhibition) { this.exhibition = exhibition; }
    public List<Stall> getStalls() { return stalls; }
    public void setStalls(List<Stall> stalls) { this.stalls = stalls; }
}
