package com.thomazcollet.simple_blog.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configuração auto incremento no mySQL
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id; // Chave primária

    @Column(name = "username", length = 30, unique = true, nullable = false)
    @NotBlank
    @Size(min = 5, max = 30)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp // Define que o valor será preenchido automaticamente quando o user é criado
    private LocalDateTime createdAt; // Data de criação

    @Column(name = "role", nullable = false)
    private String role; // Ex: "ADMIN" ou "USER"

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogPost> posts = new ArrayList<>();

    // Métodos Especiais:

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("USER")) {
            throw new IllegalArgumentException("Role inválido. Use apenas 'ADMIN' ou 'USER'.");
        }
        this.role = role.toUpperCase(); // padroniza em maiúsculo
    }

    // Contrutores

    public User() {
    } // Contrutor vazio obrigatório em classes models

    public User(Long id, String userName, String email, String password, LocalDateTime createdAt) {
        this.id = id;
        this.username = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    // HashCode and Equals

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
