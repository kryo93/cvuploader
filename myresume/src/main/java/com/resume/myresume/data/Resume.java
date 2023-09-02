package com.resume.myresume.data;

import jakarta.persistence.*;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] resumeContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getResumeContent() {
        return resumeContent;
    }

    public void setResumeContent(byte[] resumeContent) {
        this.resumeContent = resumeContent;
    }
}
