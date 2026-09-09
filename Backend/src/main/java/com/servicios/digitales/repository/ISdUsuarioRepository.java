package com.servicios.digitales.repository;

import com.servicios.digitales.model.SdUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ISdUsuarioRepository extends JpaRepository<SdUsuario, Integer> {

    Optional<SdUsuario> findByUsuNombre(String usuNombre);

    @Query(value = "select sm.id_modulo ,sm.mod_nombre , " +
            "JSON_ARRAYAGG(" +
            "JSON_OBJECT(" +
            "'OPCION', ss.sub_nombre ," +
            "'SUBMODULO_ID', ss.id_submodulo ," +
            "'RUTA', ss.sub_ruta )) AS OPCIONES " +
            "from sd_usuarios su " +
            "inner join conf_perfil_usuarios cpu on cpu.usuario_id =su.id_usuario " +
            "inner join conf_perfil_modulo cpm on cpm.perfil_id =cpu.perfil_id " +
            "inner join sd_modulos sm on sm.id_modulo =cpm.modulo_id " +
            "inner join servicios_digitales.sd_submodulos ss on ss.modulo_id =sm.id_modulo and ss.estado_modulo_id =1 " +
            "where su.id_usuario = :userId " +
            "group by sm.id_modulo ,sm.mod_nombre ", nativeQuery = true)
    List<Map<String,Object>> obtenerMenu(@Param("userId") Integer userId) throws Exception;
}