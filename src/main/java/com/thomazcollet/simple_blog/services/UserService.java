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

        // Adicionando Role ao user
        if (userRepository.countByRole("ADMIN") == 0) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        // Criando usuario
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

    public void delete(Long id) {

        // Testando se o usuario existe
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Excluindo o usuário
        userRepository.deleteById(id);

    }

    public User findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return user;
    }
    @Transactional
    public User switchRole(User authenticatedUser, User targetUser) {

        if (!authenticatedUser.getRole().equals("ADMIN")) {
            throw new RuntimeException("Apenas administradores podem realizar essa operação");
        }

        // Checando se o usuario ta alterando o role da propria conta
        if (authenticatedUser.getId().equals(targetUser.getId())) {

            throw new RuntimeException("Não é possivel alterar o status da prória conta.");
        }

        // Alternar entre USER e ADMIN
        if (targetUser.getRole().equals("USER")) {
            targetUser.setRole("ADMIN");
        } else {
            targetUser.setRole("USER");
        }

        return userRepository.save(targetUser);
    }

}
