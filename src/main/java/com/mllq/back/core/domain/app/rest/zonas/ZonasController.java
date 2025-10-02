package com.mllq.back.core.domain.app.rest.zonas;

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
import com.mllq.back.core.domain.app.bussines.service.zonas.ZonasService;
import com.mllq.back.core.domain.core.dto.request.others.ZonasRequest;
import com.mllq.back.core.domain.core.dto.response.others.ZonasResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/zonas")
@RequiredArgsConstructor
public class ZonasController {
    private final ZonasService zonasService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ZonasResponse>> createZonas(@RequestBody ZonasRequest request) {
        ApiResponse<ZonasResponse> response = zonasService.createZona(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ZonasResponse>> updateZona(
            @PathVariable Long id,
            @RequestBody ZonasRequest request) {
        ApiResponse<ZonasResponse> response = zonasService.updateZona(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteZona(@PathVariable Long id) {
        ApiResponse<Void> response = zonasService.deleteZona(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ZonasResponse>>> getAllZonas() {
        ApiResponse<List<ZonasResponse>> response = zonasService.getAllZonas();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
