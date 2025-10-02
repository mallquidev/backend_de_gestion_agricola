package com.mllq.back.core.domain.app.bussines.service.fertilizante.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.domain.app.bussines.service.fertilizante.FertilizanteService;
import com.mllq.back.core.domain.core.dto.request.others.FertilizanteRequest;
import com.mllq.back.core.domain.core.dto.response.others.FertilizanteResponse;
import com.mllq.back.core.domain.core.entities.Fertilizante;
import com.mllq.back.core.domain.core.repo.fertilizante.FertilizanteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FertilizanteServiceImpl implements FertilizanteService{
    private final FertilizanteRepository fertilizanteRepository;

    @Override
    @Transactional
    public ApiResponse<FertilizanteResponse> createFertilizante(FertilizanteRequest request) {

        if (fertilizanteRepository.existsByTipoDeFertilizante(request.getTipoDeFertilizante())) {
            throw new BadRequest("Ya existe este fertilizanet");
        }

        Fertilizante fertilizante = new Fertilizante();
        fertilizante.setTipoDeFertilizante(request.getTipoDeFertilizante());

        Fertilizante savedFertilizante = fertilizanteRepository.save(fertilizante);

        FertilizanteResponse fertilizanteResponse = FertilizanteResponse.builder()
            .idFertilizante(savedFertilizante.getIdFertilizante())
            .tipoDeFertilizante(savedFertilizante.getTipoDeFertilizante())
            .build();

        return ApiResponse.<FertilizanteResponse>builder()
            .success(true)
            .message("Persona registrado correctamente")
            .data(fertilizanteResponse)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<FertilizanteResponse> updateFertilizante(Long id, FertilizanteRequest request) {

        Fertilizante fertilizante = fertilizanteRepository.findById(id)
            .orElseThrow(() -> new BadRequest("Fertilizante no encontrado ", ErrorCode.of("ferti-001", "Fertilizante no encontrado en la bd")));
        
        fertilizante.setTipoDeFertilizante(request.getTipoDeFertilizante());

        Fertilizante updFertilizante = fertilizanteRepository.save(fertilizante);

        FertilizanteResponse response = FertilizanteResponse.builder()
            .tipoDeFertilizante(updFertilizante.getTipoDeFertilizante())
            .build();

        return ApiResponse.<FertilizanteResponse>builder()
            .success(true)
            .message("Persona registrado correctamente")
            .data(response)
            .build();
    }


    @Override
    @Transactional
    public ApiResponse<Void> deleteFertilizante(Long id) {
        Fertilizante fertilizante = fertilizanteRepository.findById(id)
                .orElseThrow(() -> new BadRequest("Fertilizante no encontrado", ErrorCode.of("ferti-002", "No se puede eliminar un fertilizante que no existe")));
        fertilizanteRepository.delete(fertilizante);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Fertilizante eliminado correctamente")
                .build();
    }

    @Override
    public ApiResponse<List<FertilizanteResponse>> getAllFertilizantes() {
        List<FertilizanteResponse> list = fertilizanteRepository.findAll()
                .stream()
                .map(f -> FertilizanteResponse.builder()
                        .idFertilizante(f.getIdFertilizante())
                        .tipoDeFertilizante(f.getTipoDeFertilizante())
                        .build())
                .collect(Collectors.toList());

        return ApiResponse.<List<FertilizanteResponse>>builder()
                .success(true)
                .message("Lista de fertilizantes")
                .data(list)
                .build();
    }

}
