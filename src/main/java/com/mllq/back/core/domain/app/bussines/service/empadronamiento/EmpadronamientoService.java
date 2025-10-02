package com.mllq.back.core.domain.app.bussines.service.empadronamiento;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.empadronamiento.EmpadronamientoRequest;
import com.mllq.back.core.domain.core.dto.response.empadronamiento.EmpadronamientoResponse;

public interface EmpadronamientoService{
    ApiResponse createEmpadronamiento(EmpadronamientoRequest request);
    ApiResponse<List<EmpadronamientoResponse>> getAllEmpadronamiento();
    ApiResponse<Void> deleteEmpadronamiento(Long id);

}
