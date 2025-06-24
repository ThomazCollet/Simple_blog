package com.thomazcollet.simple_blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thomazcollet.simple_blog.models.*;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>{
    List<BlogPost> findAllByAuthor(User author);
    Optional<BlogPost> findByTitle(String title);
}
