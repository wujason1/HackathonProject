package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private byte[] file;
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "prize_id")
    private Prize prize;

    private boolean isClaimed;

    public Challenge() {}

    public Challenge(String type, byte[] file, String link) {
        super();
        this.type = type;
        this.file = file;
        this.link = link;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public void setPrize(Prize prize) {
        // TODO Auto-generated method stub
        this.prize = prize;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean isClaimed) {
        this.isClaimed = isClaimed;
    }

}
