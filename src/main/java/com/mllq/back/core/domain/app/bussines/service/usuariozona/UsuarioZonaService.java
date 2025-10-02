package com.mllq.back.core.domain.app.bussines.service.usuariozona;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.others.UsuarioZonaRequest;
import com.mllq.back.core.domain.core.dto.response.others.UsuarioZonaResponse;

public interface UsuarioZonaService {
    ApiResponse createUsuarioZona(UsuarioZonaRequest request);
    ApiResponse<List<UsuarioZonaResponse>> getAllUsuarioZona();
    ApiResponse<Void> deleteUsuarioZona(Long id);
}
