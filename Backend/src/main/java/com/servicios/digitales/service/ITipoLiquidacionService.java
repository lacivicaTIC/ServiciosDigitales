package com.servicios.digitales.service;

import com.servicios.digitales.model.TipoLiquidacion;

import java.io.Serializable;
import java.util.List;

public interface ITipoLiquidacionService extends Serializable {

    List<TipoLiquidacion> listaTipoLiquidacion();
}
