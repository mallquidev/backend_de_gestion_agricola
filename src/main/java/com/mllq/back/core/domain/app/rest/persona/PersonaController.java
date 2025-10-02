package com.mllq.back.core.domain.app.rest.persona;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.app.bussines.service.persona.PersonaService;
import com.mllq.back.core.domain.core.dto.request.persona.PersonaRequest;
import com.mllq.back.core.domain.core.dto.response.Persona.PersonaResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/persona")
@RequiredArgsConstructor
public class PersonaController {
    
    private final PersonaService personaService;
    

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PersonaResponse>> register(@RequestBody PersonaRequest request) {
        ApiResponse<PersonaResponse> response = personaService.createPersona(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{dni}")
    public ResponseEntity<ApiResponse<PersonaResponse>> update(@PathVariable String dni, @RequestBody  PersonaRequest request) {
        ApiResponse<PersonaResponse> response = personaService.updatePersona(dni, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PersonaResponse>>> getAllPersonas() {
        ApiResponse<List<PersonaResponse>> response = personaService.getAllPersonas();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ApiResponse<PersonaResponse>> getPersonaByDni(@PathVariable String dni) {
        ApiResponse<PersonaResponse> response = personaService.getPersonaByDni(dni);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<ApiResponse<Void>> deletePersonaByDni(@PathVariable String dni) {
        ApiResponse<Void> response = personaService.deletePersona(dni);
        return ResponseEntity.ok(response);
    }
    

}
