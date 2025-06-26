package com.thomazcollet.simple_blog.controllers;

import com.thomazcollet.simple_blog.services.UserService;

import jakarta.validation.Valid;

import com.thomazcollet.simple_blog.dtos.UpdatePasswordDTO;
import com.thomazcollet.simple_blog.models.User;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {

        User user = this.userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping()
    @Validated
    public ResponseEntity<Void> create(@RequestBody User user) {
        User savedUser = userService.create(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(
            @PathVariable Long id, // Captura o ID do usuário pela URL (ex: /user/3/password)
            @Valid @RequestBody UpdatePasswordDTO dto // Recebe os dados (senha antiga e nova) no corpo da requisição e
                                                      // valida com base nas anotações da DTO
    ) {
        // Chama o método da service que faz a lógica de verificação e atualização da
        // senha
        User updatedUser = userService.updatePassword(id, dto);

        // Retorna 200 OK com o usuário atualizado no corpo da resposta
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Chama o método delete do serviço, que:
        // - Verifica se o usuário existe
        // - Impede exclusão de administradores
        userService.delete(id);

        // Retorna status 204 No Content (sucesso sem corpo de resposta)
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/switch-role/{targetUserId}")
public ResponseEntity<User> switchRole(
        @PathVariable Long targetUserId,
        @RequestParam Long authenticatedUserId) {

    // Buscar usuário autenticado
    User authenticatedUser = userService.findById(authenticatedUserId);

    // Buscar usuário que terá o papel alterado
    User targetUser = userService.findById(targetUserId);

    // Realiza a troca de papel (role) com base nas regras de segurança
    User updatedUser = userService.switchRole(authenticatedUser, targetUser);

    // Retorna o usuário atualizado com status 200 OK
    return ResponseEntity.ok(updatedUser);
}


}
