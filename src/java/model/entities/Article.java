/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author JUDITH
 */

@Entity
@Table(name = "ARTICLE")
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByTopicOrAuthor", query = "SELECT a FROM Article a WHERE (:topic IS NULL OR :topic MEMBER OF a.topics) AND (:author IS NULL OR a.author = :author)")
})
public class Article implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Article_Gen")
    @SequenceGenerator(name = "Article_Gen", sequenceName = "ARTICLE_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 100) // Títol de l'artícle, máx 100 caracters.
    private String title;
    
    @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT", length = 500) // Contingut complet de l'artícle.
    private String content;
    
    @Column(name = "PUBLISHED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // Data de publicació amb l' hora.
    private Date publishedDate;

    @Column(name = "AUTHOR", nullable = false) // Nom de l'autor.
    private String author;

    @Column(name = "VIEWS", nullable = false) // Num visualitzacions.
    private Integer views = 0;

    @ElementCollection
    @CollectionTable(name = "article_topics", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "TOPIC")
    private List<String> topics = new ArrayList<>();

    @Column(name = "IS_PUBLIC",nullable = false) // Indica si l'artícle es públic.
    private Boolean isPublic;
    
    //Getters i setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Date getPublishedDate() { return publishedDate; }
    public void setPublishedDate(Date publishedDate) { this.publishedDate = publishedDate; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public List<String> getTopics() { return topics; }
    public void setTopics(List<String> topics) { this.topics = topics; }

    public boolean isIsPublic() { return isPublic; }
    public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }
    
}
