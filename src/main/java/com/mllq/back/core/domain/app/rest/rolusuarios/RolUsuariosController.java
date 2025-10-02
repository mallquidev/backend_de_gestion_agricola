package com.mllq.back.core.domain.app.rest.rolusuarios;

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
import com.mllq.back.core.domain.app.bussines.service.rolusuarios.RolUsuariosService;
import com.mllq.back.core.domain.core.dto.request.others.RolUsuarioRequest;
import com.mllq.back.core.domain.core.dto.response.others.RolUsuarioResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rolusuarios")
@RequiredArgsConstructor
public class RolUsuariosController {

    private final RolUsuariosService rolUsuariosService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RolUsuarioRequest request){
        ApiResponse response = rolUsuariosService.createRolusuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Listar todos los RolUsuarios
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<RolUsuarioResponse>>> getAllRolusuarios() {
        ApiResponse<List<RolUsuarioResponse>> response = rolUsuariosService.getAllRolusuarios();
        return ResponseEntity.ok(response);
    }

    //Eliminar un RolUsuario por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRolusuario(@PathVariable Long id) {
        ApiResponse<Void> response = rolUsuariosService.deleteRolusuario(id);
        return ResponseEntity.ok(response);
    }
}
