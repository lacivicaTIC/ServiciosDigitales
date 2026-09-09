package com.servicios.digitales.service;

import com.servicios.digitales.model.SdUsuario;
import com.servicios.digitales.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

public interface ISdLoginService extends Serializable {
    public ResponseEntity<?> login(HttpServletRequest request, LoginRequest loguinRequest, BindingResult bindingResult, HttpServletResponse response) throws Exception;

    public ResponseEntity<?> cerrarSession(HttpServletRequest request, HttpServletResponse response, SdUsuario idUsuario) throws Exception;

   // public ResponseEntity<?> registrar (HttpServletRequest request, RegisteRequest registeRequest, BindingResult bindingResult) throws Exception;


}
