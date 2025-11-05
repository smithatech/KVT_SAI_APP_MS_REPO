package com.kvcapp.event.entity;

import jakarta.persistence.*;

@Entity
public class PosterContent {
    @Id
    private String contentId;
    private String languageCode;
    private String posterMediaUrl;
    @Column(length = 5000)
    private String contentText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stall_id")
    private Stall stall;

    public PosterContent() {}
    public PosterContent(String id) { this.contentId = id; }

    public String getContentId() { return contentId; }
    public void setContentId(String contentId) { this.contentId = contentId; }
    public String getLanguageCode() { return languageCode; }
    public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }
    public String getPosterMediaUrl() { return posterMediaUrl; }
    public void setPosterMediaUrl(String posterMediaUrl) { this.posterMediaUrl = posterMediaUrl; }
    public String getContentText() { return contentText; }
    public void setContentText(String contentText) { this.contentText = contentText; }
    public Stall getStall() { return stall; }
    public void setStall(Stall stall) { this.stall = stall; }
}
