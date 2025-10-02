package com.mllq.back.core.domain.app.bussines.service.variedad;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.others.VariedadRequest;
import com.mllq.back.core.domain.core.dto.response.others.VariedadResponse;

public interface VariedadService {
    ApiResponse<VariedadResponse> createVariedad(VariedadRequest request);

    ApiResponse<VariedadResponse> updateVariedad(Long id, VariedadRequest request);
    ApiResponse<Void> deleteVariedad(Long id);
    ApiResponse<List<VariedadResponse>> getAllVariedades();
}
