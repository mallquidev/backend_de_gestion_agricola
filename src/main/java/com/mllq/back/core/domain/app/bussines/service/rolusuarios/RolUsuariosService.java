package com.mllq.back.core.domain.app.bussines.service.rolusuarios;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.others.RolUsuarioRequest;
import com.mllq.back.core.domain.core.dto.response.others.RolUsuarioResponse;

public interface RolUsuariosService {
    ApiResponse createRolusuario(RolUsuarioRequest request);

    ApiResponse<List<RolUsuarioResponse>> getAllRolusuarios();
    ApiResponse<Void> deleteRolusuario(Long id);
}
