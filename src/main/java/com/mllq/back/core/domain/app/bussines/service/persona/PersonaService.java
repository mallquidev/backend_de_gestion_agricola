package com.mllq.back.core.domain.app.bussines.service.persona;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.persona.PersonaRequest;
import com.mllq.back.core.domain.core.dto.response.Persona.PersonaResponse;

public interface PersonaService {
    ApiResponse<PersonaResponse> createPersona(PersonaRequest request);
    ApiResponse<PersonaResponse> updatePersona(String dni, PersonaRequest request);
    
    ApiResponse<List<PersonaResponse>> getAllPersonas();
    ApiResponse<PersonaResponse> getPersonaByDni(String dni);
    ApiResponse<Void> deletePersona(String dni);
}
