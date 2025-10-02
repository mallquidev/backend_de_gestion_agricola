package com.mllq.back.core.domain.app.bussines.service.usuariozona.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.commons.exception.NotFound;
import com.mllq.back.core.domain.app.bussines.service.usuariozona.UsuarioZonaService;
import com.mllq.back.core.domain.core.dto.request.others.UsuarioZonaRequest;
import com.mllq.back.core.domain.core.dto.response.others.UsuarioZonaResponse;
import com.mllq.back.core.domain.core.entities.User;
import com.mllq.back.core.domain.core.entities.UsuarioZona;
import com.mllq.back.core.domain.core.entities.Zonas;
import com.mllq.back.core.domain.core.repo.user.UserRepository;
import com.mllq.back.core.domain.core.repo.usuariozona.UsuarioZonaRepository;
import com.mllq.back.core.domain.core.repo.zonas.ZonasRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioZonaServiceImpl implements UsuarioZonaService{
    private final UsuarioZonaRepository usuarioZonaRepository;
    private final UserRepository userRepository;
    private final ZonasRespository zonasRespository;

    @Override
    @Transactional
    public ApiResponse createUsuarioZona(UsuarioZonaRequest request) {
        // 🔹 Verificamos usuario
        User user = userRepository.findById(request.getIdUsuario())
            .orElseThrow(() -> new NotFound("Usuario no encontrado", ErrorCode.of("user-001", "Usuario no encontrado en la BD")));

        // 🔹 Verificamos zona
        Zonas zona = zonasRespository.findById(request.getIdZona())
            .orElseThrow(() -> new NotFound("Zona no encontrada", ErrorCode.of("zon-001", "Zona no encontrada en la BD")));

        UsuarioZona usuarioZona = new UsuarioZona();
        usuarioZona.setIdUsuario(user.getUserId());
        usuarioZona.setIdZona(zona.getIdZona());

        usuarioZonaRepository.save(usuarioZona);

        return ApiResponse.builder()
            .success(true)
            .message("UsuarioZona registrado correctamente")
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<List<UsuarioZonaResponse>> getAllUsuarioZona() {
        List<UsuarioZona> relaciones = usuarioZonaRepository.findAll();

        List<UsuarioZonaResponse> responseList = relaciones.stream().map(rel -> {
            User user = userRepository.findById(rel.getIdUsuario())
                .orElseThrow(() -> new NotFound("Usuario no encontrado", ErrorCode.of("user-001", "Usuario no encontrado en la BD")));

            Zonas zona = zonasRespository.findById(rel.getIdZona())
                .orElseThrow(() -> new NotFound("Zona no encontrada", ErrorCode.of("zon-001", "Zona no encontrada en la BD")));

            return UsuarioZonaResponse.builder()
                .idUsuariozona(rel.getIdUsuarioZona())
                .nombreUsuario(user.getEmail())
                .nombreZona(zona.getNombreZona())
                .build();
        }).toList();

        return ApiResponse.<List<UsuarioZonaResponse>>builder()
            .success(true)
            .message("Lista de UsuarioZona")
            .data(responseList)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteUsuarioZona(Long id) {
        UsuarioZona usuarioZona = usuarioZonaRepository.findById(id)
            .orElseThrow(() -> new BadRequest("UsuarioZona no encontrada", ErrorCode.of("uz-001", "UsuarioZona no encontrada en la BD")));

        usuarioZonaRepository.delete(usuarioZona);

        return ApiResponse.<Void>builder()
            .success(true)
            .message("UsuarioZona eliminada correctamente")
            .build();
    }
}
