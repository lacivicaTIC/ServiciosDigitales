package com.servicios.digitales.request;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class LoginRequest {
    private String username;
    private String password;

    public  BindingResult valide(BindingResult bindingResult) {

        if (getUsername() == null || getUsername().trim().isEmpty()) {
            bindingResult.addError(new FieldError("Login", "field", "Usuario Vacio"));
        }

        if (getPassword() == null || getPassword().trim().isEmpty()) {
            bindingResult.addError(new FieldError("Login", "field", "Contraseña Vacia"));
        }

        return bindingResult;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
