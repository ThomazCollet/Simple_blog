package com.thomazcollet.simple_blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomazcollet.simple_blog.models.*;
import com.thomazcollet.simple_blog.repositories.*;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private UserRepository userRepository;

    public BlogPost findById(Long id) {

        BlogPost post = blogPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Post não encontrado."));

        return post;
    }

    public List<BlogPost> findAllByAuthorUsername(String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        List<BlogPost> posts = blogPostRepository.findAllByAuthor(author);

        if (posts.isEmpty()) {
            throw new RuntimeException("Nenhum post encontrado para o autor: " + username);
        }

        return posts;
    }

    public BlogPost findByTitle(String title) {

        BlogPost post = blogPostRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Post não encontrado."));

        return post;
    }

    @Transactional
    public BlogPost create(BlogPost blogPost) {

        // Checkando se o autor do post é um admin
        if (!blogPost.getAuthor().getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Apenas administradores podem criar posts.");
        }

        // Garantindo que o id seja null
        blogPost.setId(null);

        // Salvando post
        BlogPost savedBlogPost = blogPostRepository.save(blogPost);
        return savedBlogPost;
    }

    @Transactional
    public BlogPost update(BlogPost blogPost) {

        // Buscar o post atual no banco de dados
        BlogPost existingPost = blogPostRepository.findById(blogPost.getId())
                .orElseThrow(() -> new RuntimeException("Post não encontrado."));

        // Verificar se o autor do post é ADMIN
        if (!existingPost.getAuthor().getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Apenas administradores podem atualizar posts.");
        }

        // Atualizar o post com os novos dados
        existingPost.setTitle(blogPost.getTitle());
        existingPost.setBody(blogPost.getBody());

        // Salvar no banco
        return blogPostRepository.save(existingPost);
    }

    public void delete(Long postId, User authenticatedUser) {

        // Buscar o post apenas uma vez
        BlogPost post = blogPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

        // Verificar se o autor do post é o usuário autenticado E se ele é admin
        if (post.getAuthor().getId().equals(authenticatedUser.getId())
                && authenticatedUser.getRole().equalsIgnoreCase("ADMIN")) {

            blogPostRepository.deleteById(postId);

        } else {
            throw new RuntimeException("Você não possui autorização para realizar essa operação");
        }
    }

}
