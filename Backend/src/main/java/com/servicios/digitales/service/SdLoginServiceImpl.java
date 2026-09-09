package com.servicios.digitales.service;


import com.servicios.digitales.model.EstadosSession;
import com.servicios.digitales.model.SdSession;
import com.servicios.digitales.model.SdUsuario;
import com.servicios.digitales.repository.ISdSessionRepository;
import com.servicios.digitales.repository.ISdUsuarioRepository;
import com.servicios.digitales.request.LoginRequest;
import com.servicios.digitales.response.LoginResponse;
import com.servicios.digitales.security.JwtUtils;
import com.servicios.digitales.util.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service

public class SdLoginServiceImpl implements ISdLoginService{

    @Autowired
    private ISdUsuarioRepository usuariosRepository;
    @Autowired
    private ISdSessionRepository sessionRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> login(HttpServletRequest request, LoginRequest loginRequest, BindingResult bindingResult, HttpServletResponse response) throws Exception {
        LoginResponse _response = new LoginResponse();
        Map<String, Object> _resp = new HashMap<>();
        LoginRequest loginR = loginRequest;
        BindingResult resultt = loginR.valide(bindingResult);
        if (resultt.hasErrors()) {
            _response.setCodigo("Error");
            _response.setMensaje("Error en los datos ingresados " + resultt.getFieldError().getDefaultMessage());
            return ResponseEntity.status(400).body(_response);
        }

        Optional<SdUsuario> user =
                usuariosRepository.findByUsuNombre(loginR.getUsername());
        Optional<SdSession> session = sessionRepository.findByUsuarioAndEstadoSession(user.get() ,  new EstadosSession(1));
        if (session.isPresent()) {
            _resp.put("codigo", "Error");
            _resp.put("mensaje", "Se detecto una session activa registrada, por seguridad se cerro la session anterior\n Por favor intente ingresar de nuevo");
            cerrarSession(request, response, user.get());
            return ResponseEntity.status(400).body(_resp);
        }


        if (user.get().getEstadoUsuario().getId() != 1) {
            _response.setCodigo("Acceso Denegado");
            _response.setMensaje("Usuario inactivo, por favor contacte al administrador");
            return ResponseEntity.status(401).body(_response);
        }
        if (user.isPresent()) {

            String hashDeContrasenaIngresada = HashUtil.getStringMessageDigest(loginR.getPassword(), "MD5");
            if (user.get().getUsuPassword().equals(hashDeContrasenaIngresada)) {
                String token = jwtUtils.generateToken(loginR.getUsername());
                ResponseCookie cookie = ResponseCookie.from("token", token)
                        .httpOnly(true)
                        .secure(false) // ⚠️ en localhost pon false, en producción debe ser true con HTTPS
                        .sameSite("Lax") // 🔑 necesario para cross-site
                        .path("/")
                        .maxAge(60 * 60) // 1 hora
                        .build();

                //  response.setHeader("Set-Cookie", cookie.toString());
//                Cookie cookie = new Cookie("token", token);
//                cookie.setHttpOnly(true);       // 🔒 el token no es accesible por JS
//                cookie.setSecure(false);         // 🔒 obligatorio con SameSite=None (usa HTTPS en prod)
//                cookie.setPath("/");
//                cookie.setMaxAge(60 * 60);      // 1 hora
//
//// Esto agrega la cookie al response
   //             response.addCookie(cookie);

// ⚠️ Pero debemos forzar SameSite=None:
                response.addHeader("Set-Cookie", cookie.toString());


                _response.setCodigo("ok");
                _response.setMensaje("Login exitoso");
                _response.setNombre(user.get().getUsuNombre() );


                // llenar el menu segun los permisos de los usuarios
                _response.setPermisos(usuariosRepository.obtenerMenu(user.get().getId()));
               // Servidor servidor = servidorRepository.findById(Long.parseLong("1")).get();
              //  _response.setHoraInicio(servidor);
                _response.setUsuario(user.get());

                SdSession _session = new SdSession();
                _session.setUsuario(user.get());
                _session.setEstadoSession(new EstadosSession(1));
                _session.setSdFechaIngreso(LocalDate.now());
                _session.setToken(token);
                sessionRepository.save(_session);

                user.get().setUsuPassword("!@#$$%&/()=?¡");

                return ResponseEntity.status(200).body(_response);
            } else {
                _response.setCodigo("Error");
                _response.setMensaje("Usuario o contraseña incorrecta");
                return ResponseEntity.status(401).body(_response);
            }

        }
        return null;
    }

    @Override
    public ResponseEntity<?> cerrarSession(HttpServletRequest request, HttpServletResponse response, SdUsuario idUsuario) throws Exception {
        Optional<SdSession> session = sessionRepository.findByUsuarioAndEstadoSession(idUsuario , new EstadosSession(1));
        if (session.isPresent()) {
            session.get().setEstadoSession(new EstadosSession(2));
            sessionRepository.save(session.get());
            ResponseCookie cookie = ResponseCookie.from("token", "")
                    .httpOnly(true)
                    .secure(false) // ⚠️ en localhost pon false, en producción debe ser true con HTTPS
                    .sameSite("Lax") // 🔑 necesario para cross-site
                    .path("/")
                    .maxAge(0) // Eliminar la cookie
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());

            return ResponseEntity.status(200).body("Sesión cerrada exitosamente");
        } else {
            return ResponseEntity.status(400).body("No se encontró una sesión activa para este usuario");
        }


    }

}
