package rs.raf.turisticki_vodic_be.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class Comment {

    private int id;
    private String author;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private int articleId;
    // u tabeli nije articleId nego article

    public Comment(int id, String author, String content, Date date, int articleId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.articleId = articleId;
    }

    public Comment(){


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
}
