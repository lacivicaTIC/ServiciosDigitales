package com.servicios.digitales.controller;

import com.servicios.digitales.model.SdUsuario;
import com.servicios.digitales.request.LoginRequest;
import com.servicios.digitales.response.LoginResponse;
import com.servicios.digitales.service.ISdLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/rest/api/login")
@Getter
@Setter
@Tag(name = "Login", description = "Endpoints de autenticación")
public class LoginController {

    @Autowired
    private ISdLoginService sdLoginService;

    @Operation(
            summary = "Iniciar sesión",
            description = "Recibe usuario y contraseña y devuelve un JWT en una cookie segura, los permisos del usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login exitoso", content = @Content(mediaType = "application/json",schema = @Schema(implementation = LoginResponse.class))),
                     @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Credenciales incorrectas",content = @Content)
            }
    )
    @PostMapping("/ingresar")
    public ResponseEntity<?> ingresar(HttpServletRequest request,
                                      @RequestBody LoginRequest loginRequest,
                                      BindingResult bindingResult,
                                      HttpServletResponse response) throws Exception {
        return sdLoginService.login(request, loginRequest, bindingResult, response);
    }

    @Operation(
            summary = "Cerrar Session",
            description = "Recibe el id de Usuario y destruye la cookie del JWT y la session en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro exitoso", content = @Content(mediaType = "application/json")),
                     @ApiResponse(responseCode = "400", description = "Sesion Invalida", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Credenciales incorrectas",content = @Content)
            }
    )
    @PostMapping("/cerrarsession/{idUsuario}")
    public ResponseEntity<?> cerrarsession(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUsuario") SdUsuario idUsuario) throws Exception {
        return sdLoginService.cerrarSession(request,response,idUsuario);
    }
}
