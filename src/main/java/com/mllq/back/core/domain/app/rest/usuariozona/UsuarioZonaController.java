package com.mllq.back.core.domain.app.rest.usuariozona;

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
import com.mllq.back.core.domain.app.bussines.service.usuariozona.UsuarioZonaService;
import com.mllq.back.core.domain.core.dto.request.others.UsuarioZonaRequest;
import com.mllq.back.core.domain.core.dto.response.others.UsuarioZonaResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuariozona")
@RequiredArgsConstructor
public class UsuarioZonaController {
    private final UsuarioZonaService usuarioZonaService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUsuarioZona(@RequestBody UsuarioZonaRequest request) {
        ApiResponse response = usuarioZonaService.createUsuarioZona(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UsuarioZonaResponse>>> getAllUsuarioZona() {
        ApiResponse<List<UsuarioZonaResponse>> response = usuarioZonaService.getAllUsuarioZona();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUsuarioZona(@PathVariable Long id) {
        ApiResponse<Void> response = usuarioZonaService.deleteUsuarioZona(id);
        return ResponseEntity.ok(response);
    }
}
