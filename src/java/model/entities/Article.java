/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
/**
 *
 * @author JUDITH
 */
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) // Títol de l'artícle, máx 100 caracters.
    private String title;

    @Column(nullable = false) // Nom de l'autor.
    private String author;

    @Column(nullable = false, columnDefinition = "TEXT") // Contingut complet de l'artícle.
    private String content;

    @Temporal(TemporalType.TIMESTAMP) // Data de publicació amb l' hora.
    private Date publishedDate;

    @Column(nullable = false) // Num visualitzacions.
    private int views = 0;

    @ElementCollection // Llista de tópics asociats a l'artícle.
    @CollectionTable(name = "article_topics", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "topic")
    private List<String> topics;

    @Column(nullable = false) // Indica si l'artícle es públic.
    private boolean isPublic;
    
    //Getters i setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getPublishedDate() { return publishedDate; }
    public void setPublishedDate(Date publishedDate) { this.publishedDate = publishedDate; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public List<String> getTopics() { return topics; }
    public void setTopics(List<String> topics) { this.topics = topics; }

    public boolean isIsPublic() { return isPublic; }
    public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }
    
}
