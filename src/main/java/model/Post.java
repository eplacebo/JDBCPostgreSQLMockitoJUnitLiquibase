package model;
//Post (Long id, String content, Long created, Long updated)


import java.util.Date;

public class Post {
    private Long id;
    private String content;
    private Date created;
    private Date updated;


    public Post(Long id, String content, Date created, Date updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return null;
    }

    public Date getCreated() {
        return new Date();
    }

    public java.sql.Date setCreated(Date created) {
        this.created = created;
        return null;
    }

    public Date getUpdated() {
        return null;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return (content + " " + "(" + created + ")" + " " + "(" + updated + ")").replace("["," ");
    }
}
