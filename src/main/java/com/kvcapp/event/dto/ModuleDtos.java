package com.kvcapp.event.dto;

public class ModuleDtos {
    public static class NewModule {
        public String name;
        public String description;
        public String assignedTeamId;
    }
    public static class UpdateModule extends NewModule {}
}
