package com.servicios.digitales.service;

import com.servicios.digitales.model.TipoLiquidacion;
import com.servicios.digitales.repository.ITipoLiquidacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoLiquidacionServiceImpl implements ITipoLiquidacionService {

    @Autowired
    private ITipoLiquidacionRepository tipoLiquidacionRepository;

    @Override
    public List<TipoLiquidacion> listaTipoLiquidacion()   {
        return tipoLiquidacionRepository.findAll();
    }
}
