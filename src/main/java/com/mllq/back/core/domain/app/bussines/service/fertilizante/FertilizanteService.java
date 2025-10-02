package com.mllq.back.core.domain.app.bussines.service.fertilizante;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.others.FertilizanteRequest;
import com.mllq.back.core.domain.core.dto.response.others.FertilizanteResponse;

public interface FertilizanteService {
    ApiResponse<FertilizanteResponse> createFertilizante(FertilizanteRequest request);
    ApiResponse<FertilizanteResponse> updateFertilizante(Long id, FertilizanteRequest request);

    ApiResponse<Void> deleteFertilizante(Long id);
    ApiResponse<List<FertilizanteResponse>> getAllFertilizantes();
}
