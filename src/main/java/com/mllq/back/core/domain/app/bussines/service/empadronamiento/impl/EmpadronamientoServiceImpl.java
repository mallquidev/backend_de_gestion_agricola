package com.mllq.back.core.domain.app.bussines.service.empadronamiento.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.commons.exception.NotFound;
import com.mllq.back.core.domain.app.bussines.service.empadronamiento.EmpadronamientoService;
import com.mllq.back.core.domain.core.dto.request.empadronamiento.EmpadronamientoRequest;
import com.mllq.back.core.domain.core.dto.response.empadronamiento.EmpadronamientoResponse;
import com.mllq.back.core.domain.core.entities.Empadronamiento;
import com.mllq.back.core.domain.core.entities.Persona;
import com.mllq.back.core.domain.core.entities.User;
import com.mllq.back.core.domain.core.repo.empadronamiento.EmpadronamientoRepository;
import com.mllq.back.core.domain.core.repo.persona.PersonaRepository;
import com.mllq.back.core.domain.core.repo.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpadronamientoServiceImpl implements EmpadronamientoService {
    
    private final EmpadronamientoRepository empadronamientoRepository;
    private final PersonaRepository personaRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ApiResponse createEmpadronamiento(EmpadronamientoRequest request) {
        
        Empadronamiento empadronamiento = new Empadronamiento();
        empadronamiento.setIdPersona(request.getIdPersona());
        empadronamiento.setIdUsuario(request.getIdUsuario());
        empadronamiento.setFechaRegistro(request.getFechaRegistro());
        
        empadronamientoRepository.save(empadronamiento);

        return ApiResponse.builder()
            .success(true)
            .message("Empadronamiento registrado correctamente")
            .build();
    }


    @Override
    @Transactional
    public ApiResponse<List<EmpadronamientoResponse>> getAllEmpadronamiento(){
        List<Empadronamiento> empadronamientos = empadronamientoRepository.findAll();

        List<EmpadronamientoResponse> responseList = empadronamientos.stream()
            .map(e-> {
                Persona persona = personaRepository.findById(e.getIdPersona())
                    .orElseThrow(() -> new NotFound("Persona no encontrada", ErrorCode.USER_NOT_FOUND));
                
                User user = userRepository.findById(e.getIdUsuario())
                    .orElseThrow(() -> new NotFound("usuario no encontrada", ErrorCode.USER_NOT_FOUND));

                return EmpadronamientoResponse.builder()
                    .idEmpadronamiento(e.getIdEmpadronamiento())
                    .nombrePersona(persona.getNombre())
                    .dniPersona(persona.getDni())
                    .nombreUsuario(user.getEmail())
                    .fechaRegistro(e.getFechaRegistro())
                    .build();
        }).toList();

        return ApiResponse.<List<EmpadronamientoResponse>>builder()
            .success(true)
            .message("Lista de empradonamiento")
            .data(responseList)
            .build();
    }

    @Transactional
    @Override
    public ApiResponse<Void> deleteEmpadronamiento(Long id){
        Empadronamiento empadronamiento = empadronamientoRepository.findById(id)
            .orElseThrow(() -> new BadRequest("Empadronamiento no encontrado", ErrorCode.of("empadro-001", "Empradronamiento no encontrada en la BD")));
        
        empadronamientoRepository.delete(empadronamiento);

        return ApiResponse.<Void>builder()
            .success(true)
            .message("Persona Eliminada correctamente")
            .build();
    }
}
