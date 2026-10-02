package com.servicios.digitales.repository;

import com.servicios.digitales.model.TipoLiquidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoLiquidacionRepository extends JpaRepository<TipoLiquidacion, Integer> {


}
