package com.mllq.back.core.domain.app.rest.empadronamiento;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.app.bussines.service.empadronamiento.EmpadronamientoService;
import com.mllq.back.core.domain.core.dto.request.empadronamiento.EmpadronamientoRequest;
import com.mllq.back.core.domain.core.dto.response.empadronamiento.EmpadronamientoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/empadronamiento")
@RequiredArgsConstructor
public class EmpadronamientoController {

    private final EmpadronamientoService empadronamientoService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody EmpadronamientoRequest request){
        ApiResponse response = empadronamientoService.createEmpadronamiento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ApiResponse<List<EmpadronamientoResponse>> getAllEmpadronamientos() {
        return empadronamientoService.getAllEmpadronamiento();
    }

    // Actualizar empadronamiento
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmpadronamiento(@PathVariable Long id) {
        ApiResponse<Void> response = empadronamientoService.deleteEmpadronamiento(id);
        return ResponseEntity.ok(response);
    }
    
}
