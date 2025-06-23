package com.thomazcollet.simple_blog.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class BlogPost {
    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, unique = false, length = 200)
    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Size(min = 0, max = 20000)
    @Column(name = "body", nullable = false, unique = false, columnDefinition = "TEXT")
    private String body;

    @CreationTimestamp
    private LocalDateTime publishedAt;

    // Construtores

    public BlogPost() {
    }

    public BlogPost(Long blogPostId, String title, User author, String body, LocalDateTime publishedAt) {
        this.id = blogPostId;
        this.title = title;
        this.author = author;
        this.body = body;
        this.publishedAt = publishedAt;
    }

    // Métodos Especiais

    public Long getId() {
        return this.id;
    }

    public void setId(Long blogPostId) {
        this.id = blogPostId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getPublishedAt() {
        return this.publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BlogPost blogPost = (BlogPost) o;
        return id != null && id.equals(blogPost.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
