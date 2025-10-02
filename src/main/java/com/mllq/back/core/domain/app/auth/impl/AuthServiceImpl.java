package com.mllq.back.core.domain.app.auth.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.Conflict;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.commons.exception.NotFound;
import com.mllq.back.core.commons.libs.auth.jwt.JwtUtil;
import com.mllq.back.core.commons.models.enums.RoleEnum;
import com.mllq.back.core.domain.app.auth.AuthService;
import com.mllq.back.core.domain.core.dto.request.user.LoginRequest;
import com.mllq.back.core.domain.core.dto.request.user.RegisterUserRequest;
import com.mllq.back.core.domain.core.dto.response.user.AuthResponse;
import com.mllq.back.core.domain.core.entities.Role;
import com.mllq.back.core.domain.core.entities.RoleUser;
import com.mllq.back.core.domain.core.entities.User;
import com.mllq.back.core.domain.core.repo.role.RoleRepository;
import com.mllq.back.core.domain.core.repo.role_user.RoleUserRepository;
import com.mllq.back.core.domain.core.repo.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleUserRepository roleUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public ApiResponse<AuthResponse> register(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequest(ErrorCode.USER_EMAIL_EXISTS.getMessage(), ErrorCode.USER_EMAIL_EXISTS);
        }

        //Buscamos rol USER
        Role userRole = roleRepository.findByName(RoleEnum.ADMIN)
            .orElseThrow(()->new Conflict("Rol ADMIN no encontrado"));

        User user = new User();
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        //guardamos el usuario
        User savedUser = userRepository.save(user);

        //Asignar rol
        RoleUser roleUser = new RoleUser();
        roleUser.setUser(savedUser.getUserId());
        roleUser.setRole(userRole.getId());
        roleUserRepository.save(roleUser);

        //Autenticar automaticamente
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtil.generateToken(authentication);

        //obtenemos todo los roles con la consulta personalizadares
        List<String> roles = roleUserRepository.findRoleNamesByUserId(savedUser.getUserId());

        AuthResponse authResponse = new AuthResponse(
            token,
            savedUser.getUserId(),
            savedUser.getEmail(),
            roles
        );

        return ApiResponse.<AuthResponse>builder()
            .success(true)
            .message("Usuario registrado correctamente")
            .data(authResponse)
            .build();

    }

    @Transactional
    public ApiResponse<AuthResponse> login(LoginRequest request) {
        // Buscar al usuario en la BD
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new NotFound(
                    ErrorCode.USER_NOT_FOUND.getMessage(),
                    ErrorCode.USER_NOT_FOUND
            ));

        try {
            // Intentar autenticación
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

        // Generar token
            String token = jwtUtil.generateToken(authentication);

        // Obtener roles
            List<String> roles = roleUserRepository.findRoleNamesByUserId(user.getUserId());

            AuthResponse authResponse = new AuthResponse(
                token,
                user.getUserId(),
                user.getEmail(),
                roles
            );

            return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Inicio de sesión exitoso")
                .data(authResponse)
                .build();

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
        // Contraseña incorrecta
            throw new BadRequest("Correo o contraseña incorrectos");
        }
    }


}
