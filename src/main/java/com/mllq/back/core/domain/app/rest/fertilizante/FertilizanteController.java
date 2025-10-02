package com.mllq.back.core.domain.app.rest.fertilizante;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.mllq.back.core.domain.app.bussines.service.fertilizante.FertilizanteService;
import com.mllq.back.core.domain.core.dto.request.others.FertilizanteRequest;
import com.mllq.back.core.domain.core.dto.response.others.FertilizanteResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fertilizante")
@RequiredArgsConstructor
public class FertilizanteController {
    private final FertilizanteService fertilizanteService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<FertilizanteResponse>> createFertilizante(@RequestBody FertilizanteRequest request) {
        ApiResponse<FertilizanteResponse> response = fertilizanteService.createFertilizante(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<FertilizanteResponse>> updateFertilizante(@PathVariable Long id, @RequestBody FertilizanteRequest request) {
        ApiResponse<FertilizanteResponse> response = fertilizanteService.updateFertilizante(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFertilizante(@PathVariable Long id) {
        ApiResponse<Void> response = fertilizanteService.deleteFertilizante(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<FertilizanteResponse>>> getAllFertilizantes() {
        ApiResponse<List<FertilizanteResponse>> response = fertilizanteService.getAllFertilizantes();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
