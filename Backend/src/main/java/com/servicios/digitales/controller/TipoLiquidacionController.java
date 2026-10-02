package com.servicios.digitales.controller;

import com.servicios.digitales.model.TipoLiquidacion;
import com.servicios.digitales.service.ITipoLiquidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/rest/api/tipoliquidacion")
public class TipoLiquidacionController {

    @Autowired
    private ITipoLiquidacionService tipoLiquidacionService;

    @GetMapping("/listar")
    public List<TipoLiquidacion> listaTipoLiquidacion() throws Exception {
        return tipoLiquidacionService.listaTipoLiquidacion();
    }

}
