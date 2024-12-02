/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JUDITH
 */

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_Gen")
    @SequenceGenerator(name = "Customer_Gen", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 50) // Nom d'usuari únic.
    private String username;

    @Column(name = "PASSWORD", nullable = false) // Contrasenya de l'usuari (ha de ser hasheada).
    private String password;

    @Column(name = "IS_AUTHOR", nullable = false) // Indica si l'usuari es autor.
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

    public boolean getIsAuthor() { return isAuthor; }
    public void setIsAuthor(boolean isAuthor) { this.isAuthor = isAuthor; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }
}