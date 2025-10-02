package com.mllq.back.core.domain.app.bussines.service.usuarios.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.domain.app.bussines.service.usuarios.UsuarioService;
import com.mllq.back.core.domain.core.dto.request.others.UsuuariosRequest;
import com.mllq.back.core.domain.core.dto.response.others.UsuariosResponse;
import com.mllq.back.core.domain.core.entities.User;
import com.mllq.back.core.domain.core.repo.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements UsuarioService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ApiResponse<UsuariosResponse> createUsuarios(UsuuariosRequest request) {
        if (userRepository.existsByEmail(request.getCorreo())) {
            throw new BadRequest(ErrorCode.USER_EMAIL_EXISTS.getMessage(), ErrorCode.USER_EMAIL_EXISTS);
        }

        User user = new User();
        String hashedPassword = passwordEncoder.encode(request.getContrasena());
        user.setEmail(request.getCorreo());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        UsuariosResponse usuariosResponse = UsuariosResponse.builder()
            .idUsuario(savedUser.getUserId())
            .correo(hashedPassword)
            .build();

        return ApiResponse.<UsuariosResponse>builder()
            .success(true)
            .message("Persona registrado correctamente")
            .data(usuariosResponse)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<UsuariosResponse> updateUsuario(Long id, UsuuariosRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequest("Usuario no encontrado",
                        ErrorCode.of("user-001", "Usuario no encontrado en la bd")));

        if (!user.getEmail().equals(request.getCorreo()) && userRepository.existsByEmail(request.getCorreo())) {
            throw new BadRequest(ErrorCode.USER_EMAIL_EXISTS.getMessage(), ErrorCode.USER_EMAIL_EXISTS);
        }

        user.setEmail(request.getCorreo());
        if (request.getContrasena() != null && !request.getContrasena().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getContrasena()));
        }

        User updatedUser = userRepository.save(user);

        UsuariosResponse response = UsuariosResponse.builder()
                .idUsuario(updatedUser.getUserId())
                .correo(updatedUser.getEmail())
                .build();

        return ApiResponse.<UsuariosResponse>builder()
                .success(true)
                .message("Usuario actualizado correctamente")
                .data(response)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteUsuario(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequest("Usuario no encontrado",
                        ErrorCode.of("user-002", "No se puede eliminar un usuario que no existe")));

        userRepository.delete(user);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Usuario eliminado correctamente")
                .build();
    }

    @Override
    public ApiResponse<List<UsuariosResponse>> getAllUsuarios() {
        List<UsuariosResponse> list = userRepository.findAll()
                .stream()
                .map(u -> UsuariosResponse.builder()
                        .idUsuario(u.getUserId())
                        .correo(u.getEmail())
                        .build())
                .collect(Collectors.toList());

        return ApiResponse.<List<UsuariosResponse>>builder()
                .success(true)
                .message("Lista de usuarios")
                .data(list)
                .build();
    }
}
