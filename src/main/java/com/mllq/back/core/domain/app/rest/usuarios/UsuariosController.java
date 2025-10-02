package com.mllq.back.core.domain.app.rest.usuarios;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.app.bussines.service.usuarios.UsuarioService;
import com.mllq.back.core.domain.core.dto.request.others.UsuuariosRequest;
import com.mllq.back.core.domain.core.dto.response.others.UsuariosResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuariosResponse>> createUsuarios(@RequestBody UsuuariosRequest request) {
        ApiResponse<UsuariosResponse> response = usuarioService.createUsuarios(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UsuariosResponse>> updateUsuario(
            @PathVariable Long id,
            @RequestBody UsuuariosRequest request) {
        ApiResponse<UsuariosResponse> response = usuarioService.updateUsuario(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUsuario(@PathVariable Long id) {
        ApiResponse<Void> response = usuarioService.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UsuariosResponse>>> getAllUsuarios() {
        ApiResponse<List<UsuariosResponse>> response = usuarioService.getAllUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
