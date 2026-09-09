package com.servicios.digitales.repository;

import com.servicios.digitales.model.EstadosSession;
import com.servicios.digitales.model.SdSession;
import com.servicios.digitales.model.SdUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISdSessionRepository extends JpaRepository<SdSession, Long> {

    Optional<SdSession> findByUsuarioAndEstadoSession(SdUsuario sdUsuario, EstadosSession estadosSession);
    Boolean existsByTokenAndEstadoSession(String token, EstadosSession estadoSession);
}
