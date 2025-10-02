package com.mllq.back.core.domain.app.bussines.service.zonas;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.others.ZonasRequest;
import com.mllq.back.core.domain.core.dto.response.others.ZonasResponse;

public interface ZonasService {
    ApiResponse<ZonasResponse> createZona(ZonasRequest request);

    ApiResponse<ZonasResponse> updateZona(Long id, ZonasRequest request);
    ApiResponse<Void> deleteZona(Long id);
    ApiResponse<List<ZonasResponse>> getAllZonas();
}
