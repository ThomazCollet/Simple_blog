package com.thomazcollet.simple_blog.services;

import com.thomazcollet.simple_blog.repositories.UserRepository;
import com.thomazcollet.simple_blog.dtos.UpdatePasswordDTO;
import com.thomazcollet.simple_blog.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(User user) {

        user.setId(null);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Transactional
    public User updatePassword(Long userId, UpdatePasswordDTO dto) {

        // Buscando o usuario pelo id passado.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Testando se a senha atual confere
        if (!user.getPassword().equals(dto.getOldPassword())) {
            throw new RuntimeException("Senha atual não confere");
        }

        // Atualizar a senha
        user.setPassword(dto.getNewPassword());

        // Salvar no banco
        User updatedUser = userRepository.save(user);
        
        return updatedUser;
    }

    public void delete(Long id){

        //Testando se o usuario existe
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Excluindo o usuário
        userRepository.deleteById(id);

    }

    public User findById(Long id){
        
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return user;
    }

}
