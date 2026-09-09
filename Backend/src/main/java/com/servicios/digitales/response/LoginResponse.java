package com.servicios.digitales.response;

import com.servicios.digitales.model.SdUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LoginResponse {

    @Schema(description = "Codigo de estado de la peticion ", example = "ok , error , warning ...")
    private String codigo;

    @Schema(description = "Mensaje informativo del login", example = "Login exitoso, Acceso denegado , Credenciales Incorrectos ")
    private String mensaje;

    @Schema(description = "Nombre Completo del Usuario", example = "Juan Perez")
    private String nombre;


    @Schema(description = "id del Usuario", example = "1 , 1231")
    private Integer userId;

    @Schema(description = "Listado de modulos y permisos asignados al usuario")
    private List<Map<String,Object>> permisos;

    @Schema(description = "Object Usuario")
    private SdUsuario usuario;


}
