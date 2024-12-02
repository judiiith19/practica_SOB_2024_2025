/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author JUDITH
 */

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // Nom d'usuari únic.
    private String username;

    @Column(nullable = false) // Contrasenya de l'usuari (ha de ser hasheada).
    private String password;

    @Column(nullable = false) // Indica si l'usuari es autor.
    private boolean isAuthor;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL) // Relació amb artícles.
    private List<Article> articles;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAuthor() { return isAuthor; }
    public void setAuthor(boolean isAuthor) { this.isAuthor = isAuthor; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }
}
