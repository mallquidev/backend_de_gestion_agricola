package com.mllq.back.core.domain.app.bussines.service.usuarios;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.others.UsuuariosRequest;
import com.mllq.back.core.domain.core.dto.response.others.UsuariosResponse;

public interface UsuarioService {
    ApiResponse<UsuariosResponse> createUsuarios(UsuuariosRequest request);

    ApiResponse<UsuariosResponse> updateUsuario(Long id, UsuuariosRequest request);
    ApiResponse<Void> deleteUsuario(Long id);
    ApiResponse<List<UsuariosResponse>> getAllUsuarios();
}
