package com.thomazcollet.simple_blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thomazcollet.simple_blog.models.BlogPost;
import com.thomazcollet.simple_blog.models.User;;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>{
    List<BlogPost> findByAuthor(User author);
}
