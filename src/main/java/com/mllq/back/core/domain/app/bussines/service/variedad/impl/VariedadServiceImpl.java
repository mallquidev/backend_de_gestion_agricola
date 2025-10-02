package com.mllq.back.core.domain.app.bussines.service.variedad.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.domain.app.bussines.service.variedad.VariedadService;
import com.mllq.back.core.domain.core.dto.request.others.VariedadRequest;
import com.mllq.back.core.domain.core.dto.response.others.VariedadResponse;
import com.mllq.back.core.domain.core.entities.Variedad;
import com.mllq.back.core.domain.core.repo.variedad.VariedadRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VariedadServiceImpl implements VariedadService{
    private final VariedadRepository variedadRepository;

    @Override
    @Transactional
    public ApiResponse<VariedadResponse> createVariedad(VariedadRequest request) {
        if (variedadRepository.existsByTipoDeVarieadad(request.getTipoDeVariedad())) {
            throw new BadRequest("Ya existe este fertilizanet");
        }

        Variedad variedad = new Variedad();
        variedad.setTipoDeVarieadad(request.getTipoDeVariedad());

        Variedad savedVariedad = variedadRepository.save(variedad);

        VariedadResponse variedadResponse = VariedadResponse.builder()
            .idVariedad(savedVariedad.getIdVariedad())
            .tipoDeVariedad(savedVariedad.getTipoDeVarieadad())
            .build();
        
        return ApiResponse.<VariedadResponse>builder()
            .success(true)
            .message("Persona registrado correctamente")
            .data(variedadResponse)
            .build();

    }

    @Override
    @Transactional
    public ApiResponse<VariedadResponse> updateVariedad(Long id, VariedadRequest request) {
        Variedad variedad = variedadRepository.findById(id)
                .orElseThrow(() -> new BadRequest("Variedad no encontrada",
                        ErrorCode.of("variedad-001", "Variedad no encontrada en la bd")));
        variedad.setTipoDeVarieadad(request.getTipoDeVariedad());

        Variedad updatedVariedad = variedadRepository.save(variedad);

        VariedadResponse response = VariedadResponse.builder()
                .idVariedad(updatedVariedad.getIdVariedad())
                .tipoDeVariedad(updatedVariedad.getTipoDeVarieadad())
                .build();

        return ApiResponse.<VariedadResponse>builder()
                .success(true)
                .message("Variedad actualizada correctamente")
                .data(response)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteVariedad(Long id) {
        Variedad variedad = variedadRepository.findById(id)
                .orElseThrow(() -> new BadRequest("Variedad no encontrada",
                        ErrorCode.of("variedad-002", "No se puede eliminar una variedad que no existe")));
        variedadRepository.delete(variedad);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Variedad eliminada correctamente")
                .build();
    }

    @Override
    public ApiResponse<List<VariedadResponse>> getAllVariedades() {
        List<VariedadResponse> list = variedadRepository.findAll()
                .stream()
                .map(v -> VariedadResponse.builder()
                        .idVariedad(v.getIdVariedad())
                        .tipoDeVariedad(v.getTipoDeVarieadad())
                        .build())
                .collect(Collectors.toList());

        return ApiResponse.<List<VariedadResponse>>builder()
                .success(true)
                .message("Lista de variedades")
                .data(list)
                .build();
    }
}
