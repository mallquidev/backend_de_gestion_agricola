package com.mllq.back.core.domain.app.bussines.service.zonas.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.domain.app.bussines.service.zonas.ZonasService;
import com.mllq.back.core.domain.core.dto.request.others.ZonasRequest;
import com.mllq.back.core.domain.core.dto.response.others.ZonasResponse;
import com.mllq.back.core.domain.core.entities.Zonas;
import com.mllq.back.core.domain.core.repo.zonas.ZonasRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZonasServiceImpl implements ZonasService{
    private final ZonasRespository zonasRespository;

    @Override
    @Transactional
    public ApiResponse<ZonasResponse> createZona(ZonasRequest request){
        if (zonasRespository.existsByNombreZona(request.getNombreZona())) {
            throw new BadRequest("Ya existe este fertilizanet");
        }

        Zonas zonas = new Zonas();
        zonas.setNombreZona(request.getNombreZona());

        Zonas savedZonas = zonasRespository.save(zonas);

        ZonasResponse zonasResponse = ZonasResponse.builder()
            .idZona(savedZonas.getIdZona())
            .nombreZona(savedZonas.getNombreZona())
            .build();

        return ApiResponse.<ZonasResponse>builder()
            .success(true)
            .message("Persona registrado correctamente")
            .data(zonasResponse)
            .build();

    }

    @Override
    @Transactional
    public ApiResponse<ZonasResponse> updateZona(Long id, ZonasRequest request) {
        Zonas zona = zonasRespository.findById(id)
                .orElseThrow(() -> new BadRequest("Zona no encontrada",
                        ErrorCode.of("zona-001", "Zona no encontrada en la bd")));
        zona.setNombreZona(request.getNombreZona());

        Zonas updatedZona = zonasRespository.save(zona);

        ZonasResponse response = ZonasResponse.builder()
                .idZona(updatedZona.getIdZona())
                .nombreZona(updatedZona.getNombreZona())
                .build();

        return ApiResponse.<ZonasResponse>builder()
                .success(true)
                .message("Zona actualizada correctamente")
                .data(response)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteZona(Long id) {
        Zonas zona = zonasRespository.findById(id)
                .orElseThrow(() -> new BadRequest("Zona no encontrada",
                        ErrorCode.of("zona-002", "No se puede eliminar una zona que no existe")));
        zonasRespository.delete(zona);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Zona eliminada correctamente")
                .build();
    }

    @Override
    public ApiResponse<List<ZonasResponse>> getAllZonas() {
        List<ZonasResponse> list = zonasRespository.findAll()
                .stream()
                .map(z -> ZonasResponse.builder()
                        .idZona(z.getIdZona())
                        .nombreZona(z.getNombreZona())
                        .build())
                .collect(Collectors.toList());

        return ApiResponse.<List<ZonasResponse>>builder()
                .success(true)
                .message("Lista de zonas")
                .data(list)
                .build();
    }
}
