package es.flaviojmend.fitbittracker.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
public class User {

    @Id
    private String userId;
    private String app;
    private Date dateCreated;
    private Date dateLastAccessed;


    public String getApp() {
        return app;
    }

    public User setApp(String app) {
        this.app = app;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastAccessed() {
        return dateLastAccessed;
    }

    public void setDateLastAccessed(Date dateLastAccessed) {
        this.dateLastAccessed = dateLastAccessed;
    }
}
