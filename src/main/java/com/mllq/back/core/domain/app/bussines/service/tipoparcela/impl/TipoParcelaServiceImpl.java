package com.mllq.back.core.domain.app.bussines.service.tipoparcela.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.exception.BadRequest;
import com.mllq.back.core.commons.exception.ErrorCode;
import com.mllq.back.core.commons.exception.NotFound;
import com.mllq.back.core.domain.app.bussines.service.tipoparcela.TipoParcelaService;
import com.mllq.back.core.domain.core.dto.request.others.TipoParcelaRequest;
import com.mllq.back.core.domain.core.dto.response.others.TipoParcelaResponse;
import com.mllq.back.core.domain.core.entities.TipoParcela;
import com.mllq.back.core.domain.core.entities.Variedad;
import com.mllq.back.core.domain.core.entities.Zonas;
import com.mllq.back.core.domain.core.repo.tipoparcela.TipoParcelaRepository;
import com.mllq.back.core.domain.core.repo.variedad.VariedadRepository;
import com.mllq.back.core.domain.core.repo.zonas.ZonasRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoParcelaServiceImpl implements TipoParcelaService{

    private final TipoParcelaRepository tipoParcelaRepository;
    private final VariedadRepository variedadRepository;
    private final ZonasRespository zonasRespository;

    @Override
    @Transactional
    public ApiResponse createTipoParcela(TipoParcelaRequest request) {
        // 🔹 Verificamos que la Variedad exista
        Variedad variedad = variedadRepository.findById(request.getIdVariedad())
            .orElseThrow(() -> new NotFound("Variedad no encontrada", ErrorCode.of("var-001", "Variedad no encontrada en la BD")));

        // 🔹 Verificamos que la Zona exista
        Zonas zona = zonasRespository.findById(request.getIdZona())
            .orElseThrow(() -> new NotFound("Zona no encontrada", ErrorCode.of("zon-001", "Zona no encontrada en la BD")));

        TipoParcela tipoParcela = new TipoParcela();
        tipoParcela.setIdVariedad(variedad.getIdVariedad());
        tipoParcela.setIdZona(zona.getIdZona());
        tipoParcela.setTipoParcela(request.getTipoParcela());

        tipoParcelaRepository.save(tipoParcela);

        return ApiResponse.builder()
            .success(true)
            .message("TipoParcela registrada correctamente")
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<List<TipoParcelaResponse>> getAllTipoParcela() {
        List<TipoParcela> tipoParcelas = tipoParcelaRepository.findAll();

        List<TipoParcelaResponse> responseList = tipoParcelas.stream().map(tp -> {
            Variedad variedad = variedadRepository.findById(tp.getIdVariedad())
                .orElseThrow(() -> new NotFound("Variedad no encontrada", ErrorCode.of("var-001", "Variedad no encontrada en la BD")));

            Zonas zona = zonasRespository.findById(tp.getIdZona())
                .orElseThrow(() -> new NotFound("Zona no encontrada", ErrorCode.of("zon-001", "Zona no encontrada en la BD")));

            return TipoParcelaResponse.builder()
                .idParcela(tp.getIdParcela())
                .nombreVariedad(variedad.getTipoDeVarieadad())
                .nombreZona(zona.getNombreZona())
                .tipoParcela(tp.getTipoParcela())
                .build();
        }).toList();

        return ApiResponse.<List<TipoParcelaResponse>>builder()
            .success(true)
            .message("Lista de TipoParcela")
            .data(responseList)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteTipoParcela(Long id) {
        TipoParcela tipoParcela = tipoParcelaRepository.findById(id)
            .orElseThrow(() -> new BadRequest("TipoParcela no encontrada", ErrorCode.of("tp-001", "TipoParcela no encontrada en la BD")));

        tipoParcelaRepository.delete(tipoParcela);

        return ApiResponse.<Void>builder()
            .success(true)
            .message("TipoParcela eliminada correctamente")
            .build();
    }

    
}
