package com.mllq.back.core.domain.app.bussines.service.rolusuarios.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.commons.exception.NotFound;
import com.mllq.back.core.domain.app.bussines.service.rolusuarios.RolUsuariosService;
import com.mllq.back.core.domain.core.dto.request.others.RolUsuarioRequest;
import com.mllq.back.core.domain.core.dto.response.others.RolUsuarioResponse;
import com.mllq.back.core.domain.core.entities.Role;
import com.mllq.back.core.domain.core.entities.RoleUser;
import com.mllq.back.core.domain.core.entities.User;
import com.mllq.back.core.domain.core.repo.role.RoleRepository;
import com.mllq.back.core.domain.core.repo.role_user.RoleUserRepository;
import com.mllq.back.core.domain.core.repo.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolusuariosServiceImpl implements RolUsuariosService{
    private final RoleUserRepository roleUserRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public ApiResponse createRolusuario(RolUsuarioRequest request) {
        RoleUser roleUser = new RoleUser();

        User user = userRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new NotFound("Usuario no encontrado", ErrorCode.USER_NOT_FOUND));

        Role role = roleRepository.findById(request.getIdRol())
                .orElseThrow(() -> new NotFound("Rol no encontrado", ErrorCode.of("role-001", "Rol no encontrado")));

        roleUser.setUser(user.getUserId());
        roleUser.setRole(role.getId());

        roleUserRepository.save(roleUser);

        return ApiResponse.builder()
                .success(true)
                .message("RolUsuario registrado correctamente")
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<List<RolUsuarioResponse>> getAllRolusuarios() {
        List<RoleUser> rolUsuarios = roleUserRepository.findAll();

        List<RolUsuarioResponse> responseList = rolUsuarios.stream()
            .map(e -> {
                User user = userRepository.findById(e.getUser())
                    .orElseThrow(() -> new NotFound("Usuario no encontrado", ErrorCode.USER_NOT_FOUND));
                
                Role role = roleRepository.findById(e.getRole())
                    .orElseThrow(() -> new NotFound("Rol no encontrado", ErrorCode.of("role-001", "Rol no encontrado")));

                return RolUsuarioResponse.builder()
                    .idRolusuario(e.getRoleUserId())
                    .nombreUsuario(user.getEmail())
                    .nombreRol(role.getName().name())
                    .build();
            }).toList();

        return ApiResponse.<List<RolUsuarioResponse>>builder()
            .success(true)
            .message("Lista de roles de usuarios obtenida correctamente")
            .data(responseList)
            .build();

        
    }


    @Override
    @Transactional
    public ApiResponse<Void> deleteRolusuario(Long id) {
        RoleUser roleUser = roleUserRepository.findById(id)
                .orElseThrow(() -> new BadRequest("RolUsuario no encontrado",
                        ErrorCode.of("roluser-001", "RolUsuario no encontrado en la BD")));

        roleUserRepository.delete(roleUser);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("RolUsuario eliminado correctamente")
                .build();
    }
}
