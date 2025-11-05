package com.kvcapp.event.dto;

public class StallDtos {
    public static class NewStall {
        public String name;
        public String description;
        public String stallNumber;
        public String layout;
    }
    public static class UpdateStall extends NewStall {}
}
