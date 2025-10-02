package com.mllq.back.core.domain.app.bussines.service.persona.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.domain.app.bussines.service.persona.PersonaService;
import com.mllq.back.core.domain.core.dto.request.persona.PersonaRequest;
import com.mllq.back.core.domain.core.dto.response.Persona.PersonaResponse;
import com.mllq.back.core.domain.core.entities.Persona;
import com.mllq.back.core.domain.core.repo.persona.PersonaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService{
    private final PersonaRepository personaRepository;

    @Override
    @Transactional
    public ApiResponse<PersonaResponse> createPersona(PersonaRequest request) {

        if(personaRepository.existsByDni(request.getDni())) {
            throw new BadRequest("Este usuadio ya existe", ErrorCode.of("pers-001", "Ya hay alguien registrado con ese dni"));
        }

        Persona persona = new Persona();
        persona.setNombre(request.getNombre());
        persona.setApellidoPaterno(request.getApellidoP());
        persona.setApellidoMaterno(request.getApellidoM());
        persona.setDni(request.getDni());
        persona.setStatus(1);

        Persona savedPersona = personaRepository.save(persona);

        PersonaResponse personaResponse = PersonaResponse.builder()
            .idPersona(savedPersona.getIdPersona())
            .nombre(savedPersona.getNombre())
            .apellidoPaterno(savedPersona.getApellidoPaterno())
            .apellidoMaterno(savedPersona.getApellidoMaterno())
            .dni(savedPersona.getDni())
            .build();
        
        return ApiResponse.<PersonaResponse>builder()
            .success(true)
            .message("Persona registrado correctamente")
            .data(personaResponse)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<PersonaResponse> updatePersona(String dni, PersonaRequest request) {
        
        //buscamos a la persona por su id
        Persona persona = personaRepository.findByDniAndStatus(dni, 1)
            .orElseThrow(() -> new BadRequest("Persona no encontrada con DNI: " + dni, ErrorCode.of("pers-002", "Persona no encontrada en la bd")));

        //actaulizamos los campos
        persona.setNombre(request.getNombre());
        persona.setApellidoPaterno(request.getApellidoP());
        persona.setApellidoMaterno(request.getApellidoM());
        persona.setDni(request.getDni());

        Persona updatPersona = personaRepository.save(persona);

        PersonaResponse personaResponse = PersonaResponse.builder()
            .idPersona(updatPersona.getIdPersona())
            .nombre(updatPersona.getNombre())
            .apellidoPaterno(updatPersona.getApellidoPaterno())
            .apellidoMaterno(updatPersona.getApellidoMaterno())
            .dni(updatPersona.getDni())
            .build();

        return ApiResponse.<PersonaResponse>builder()
            .success(true)
            .message("Persona actualizada correctamente")
            .data(personaResponse)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<List<PersonaResponse>> getAllPersonas() {
        List<Persona> personas = personaRepository.findAllByStatus(1);

        List<PersonaResponse> personaResponses = personas.stream()
            .map(p -> PersonaResponse.builder()
                .idPersona(p.getIdPersona())
                .nombre(p.getNombre())
                .apellidoPaterno(p.getApellidoPaterno())
                .apellidoMaterno(p.getApellidoMaterno())
                .dni(p.getDni())
                .build()
            ).toList();

        return ApiResponse.<List<PersonaResponse>>builder()
            .success(true)
            .message("Lista de persona obtenida correctamente")
            .data(personaResponses)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<PersonaResponse> getPersonaByDni(String dni){
        Persona persona = personaRepository.findByDniAndStatus(dni, 1)
            .orElseThrow(() -> new BadRequest("Persona no encontrada con DNI: "+ dni, ErrorCode.of("pers-004", "Persona no encontrada en la BD")));
        
        PersonaResponse personaResponse = PersonaResponse.builder()
            .idPersona(persona.getIdPersona())
            .nombre(persona.getNombre())
            .apellidoPaterno(persona.getApellidoPaterno())
            .apellidoMaterno(persona.getApellidoMaterno())
            .dni(persona.getDni())
            .build();

        return ApiResponse.<PersonaResponse>builder()
            .success(true)
            .message("Persona obtenida correctamente")
            .data(personaResponse)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deletePersona(String dni) {
        Persona persona = personaRepository.findByDniAndStatus(dni, 1)
            .orElseThrow(() -> new BadRequest("Persona no encontrada con DNI" + dni, ErrorCode.of("pers-004", "Persona no encontrada en la BD")));

        personaRepository.delete(persona);
        
        return ApiResponse.<Void>builder()
            .success(true)
            .message("Persona Eliminada correctamente")
            .build();
    }
}
