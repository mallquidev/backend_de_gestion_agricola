package com.mllq.back.core.domain.app.rest.tipoparcela;

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
import com.mllq.back.core.domain.app.bussines.service.tipoparcela.TipoParcelaService;
import com.mllq.back.core.domain.core.dto.request.others.TipoParcelaRequest;
import com.mllq.back.core.domain.core.dto.response.others.TipoParcelaResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tipoparcela")
@RequiredArgsConstructor
public class TipoParcelaController {
    private final TipoParcelaService tipoParcelaService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody TipoParcelaRequest request){
        ApiResponse response = tipoParcelaService.createTipoParcela(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TipoParcelaResponse>>> getAllTipoParcela() {
        ApiResponse<List<TipoParcelaResponse>> response = tipoParcelaService.getAllTipoParcela();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTipoParcela(@PathVariable Long id) {
        ApiResponse<Void> response = tipoParcelaService.deleteTipoParcela(id);
        return ResponseEntity.ok(response);
    }
}
