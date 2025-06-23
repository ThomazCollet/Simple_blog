package com.thomazcollet.simple_blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomazcollet.simple_blog.models.BlogPost;
import com.thomazcollet.simple_blog.repositories.BlogPostRepository;
import com.thomazcollet.simple_blog.repositories.UserRepository;

@Service
public class BlogPostService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    public BlogPost findById(Long id){

        return post;
    }

    public List<BlogPost> findAllByAuthor(String Author){

        return posts[];
    }

    @Transactional
    public BlogPost create(BlogPost blogpost){


        return savedBlogPost;
    }

    @Transactional
    public BlogPost update(BlogPost blogPost){

        return updatedBlogPost;
    }

    public void delete(Long id){

        
    }

}
