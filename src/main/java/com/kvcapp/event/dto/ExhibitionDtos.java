package com.kvcapp.event.dto;

import java.time.LocalDate;

public class ExhibitionDtos {
    public static class NewExhibition {
        public String name;
        public String description;
        public LocalDate startDate;
        public LocalDate endDate;
        public String location;
        public String status; // Draft or Published
    }
    public static class UpdateExhibition extends NewExhibition {}
}
