package com.servicios.digitales.repository;

import com.servicios.digitales.model.SdSucursal;
import com.servicios.digitales.model.SdUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ISdSucursalRepository extends JpaRepository<SdSucursal,Integer> {

@Query(value ="select * from sd_sucursal ss \n" +
        "inner join conf_perfil_usuarios cpu on ss.id_sucursal =cpu.sucursal_id \n" +
        "and cpu.usuario_id = :userId " +
        "group by ss.id_sucursal ", nativeQuery = true)
    List<SdSucursal> listSucursalByUsuario(@Param("userId") Integer userId) throws Exception;
}
