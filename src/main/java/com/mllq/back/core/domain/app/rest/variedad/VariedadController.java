package com.mllq.back.core.domain.app.rest.variedad;

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
import com.mllq.back.core.domain.app.bussines.service.variedad.VariedadService;
import com.mllq.back.core.domain.core.dto.request.others.VariedadRequest;
import com.mllq.back.core.domain.core.dto.response.others.VariedadResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/variedad")
@RequiredArgsConstructor
public class VariedadController {
    private final VariedadService variedadService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<VariedadResponse>> createVariedad(@RequestBody VariedadRequest request) {
        ApiResponse<VariedadResponse> response = variedadService.createVariedad(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<VariedadResponse>> updateVariedad(
            @PathVariable Long id,
            @RequestBody VariedadRequest request) {
        ApiResponse<VariedadResponse> response = variedadService.updateVariedad(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVariedad(@PathVariable Long id) {
        ApiResponse<Void> response = variedadService.deleteVariedad(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<VariedadResponse>>> getAllVariedades() {
        ApiResponse<List<VariedadResponse>> response = variedadService.getAllVariedades();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
