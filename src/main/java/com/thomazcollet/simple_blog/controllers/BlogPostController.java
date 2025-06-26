package com.thomazcollet.simple_blog.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thomazcollet.simple_blog.models.BlogPost;
import com.thomazcollet.simple_blog.models.User;
import com.thomazcollet.simple_blog.services.BlogPostService;

@RestController
@RequestMapping("/post")
@Validated
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> findById(@PathVariable Long id) {
        BlogPost post = this.blogPostService.findById(id);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BlogPost> findByTitle(@PathVariable String title) {

        BlogPost post = this.blogPostService.findByTitle(title);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BlogPost>> findByAuthor(@PathVariable String author) {
        List<BlogPost> posts = this.blogPostService.findAllByAuthorUsername(author);

        return ResponseEntity.ok(posts);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody BlogPost blogPost) {
        BlogPost savedPost = this.blogPostService.create(blogPost);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody BlogPost blogPost) {
        this.blogPostService.update(blogPost);
        return ResponseEntity.noContent().build(); // 204 No Content, operação bem-sucedida sem corpo
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
            @RequestBody User authenticatedUser) {

        blogPostService.delete(id, authenticatedUser);

        return ResponseEntity.noContent().build();
    }

}
