package com.servicios.digitales.repository;

import com.servicios.digitales.model.SdPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISdPersonaRepository  extends JpaRepository<SdPersona, Long> {
}
