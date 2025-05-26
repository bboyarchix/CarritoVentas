package com.example.microservice_auth.controller;

import com.example.microservice_auth.dto.LoginRequest;
import com.example.microservice_auth.dto.RegisterRequest;
import com.example.microservice_auth.dto.TokenDto;
import com.example.microservice_auth.entity.AuthUser;
import com.example.microservice_auth.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Validated @RequestBody LoginRequest loginRequest) {
        try {
            TokenDto tokenDto = authUserService.login(loginRequest);

            if (tokenDto == null || tokenDto.getToken() == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new TokenDto(null));
            }

            return ResponseEntity.ok(tokenDto);

        } catch (IllegalArgumentException e) {
            // Error de validación de datos
            return ResponseEntity.badRequest()
                    .body(new TokenDto(null));
        } catch (Exception e) {
            // Error de autenticación u otros errores
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenDto(null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest registerRequest) {
        try {
            AuthUser authUser = authUserService.registerUser(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(authUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validateToken(@RequestHeader("Authorization") String token) {
        try {
            // Remover el prefijo "Bearer " si existe
            token = token.startsWith("Bearer ") ? token.substring(7) : token;
            TokenDto tokenDto = authUserService.validateToken(token);
            return ResponseEntity.ok(tokenDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenDto(null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        try {
            // Remover el prefijo "Bearer " si existe
            token = token.startsWith("Bearer ") ? token.substring(7) : token;
            authUserService.logout(token);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // Endpoint de prueba para verificar roles
    @GetMapping("/admin/test")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("¡Acceso de administrador exitoso!");
    }

    @GetMapping("/vendedor/test")
    public ResponseEntity<String> vendedorTest() {
        return ResponseEntity.ok("¡Acceso de vendedor exitoso!");
    }

    @GetMapping("/cliente/test")
    public ResponseEntity<String> clienteTest() {
        return ResponseEntity.ok("¡Acceso de cliente exitoso!");
    }

    // Manejador global de excepciones para este controlador
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }
}
