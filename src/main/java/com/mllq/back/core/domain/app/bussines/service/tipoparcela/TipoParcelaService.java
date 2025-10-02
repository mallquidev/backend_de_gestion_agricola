package com.mllq.back.core.domain.app.bussines.service.tipoparcela;

import java.util.List;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.domain.core.dto.request.others.TipoParcelaRequest;
import com.mllq.back.core.domain.core.dto.response.others.TipoParcelaResponse;

public interface TipoParcelaService {
    ApiResponse createTipoParcela(TipoParcelaRequest request);

    ApiResponse<List<TipoParcelaResponse>> getAllTipoParcela();
    ApiResponse<Void> deleteTipoParcela(Long id);
}
