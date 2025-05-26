package com.example.microservice_auth.security;

import com.example.microservice_auth.entity.AuthUser;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {
    private String secret = "mi_clave_secreta_muy_segura_y_larga_para_garantizar_la_seguridad";
    private Long expirationTime = 3600000L; // 1 hora

    //@Value("${jwt.secret-key}")
    //private String secret;

   // @Value("${jwt.expiration-time}")
    //private Long expirationTime;

    @PostConstruct
    protected void init() {
        log.info("Inicializando JwtTokenProvider...");
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        log.info("Secret codificado correctamente");
    }

    public String createToken(AuthUser authUser) {
        try {
            log.info("Iniciando creación de token para usuario: {}", authUser.getEmail());

            // Validaciones
            if (authUser == null) {
                log.error("AuthUser es null");
                throw new IllegalArgumentException("AuthUser no puede ser null");
            }

            if (authUser.getEmail() == null) {
                log.error("Email del usuario es null");
                throw new IllegalArgumentException("Email no puede ser null");
            }

            if (authUser.getRole() == null || authUser.getRole().getName() == null) {
                log.error("Role del usuario es null");
                throw new IllegalArgumentException("Role no puede ser null");
            }

            Map<String, Object> claims = new HashMap<>();
            claims.put("sub", authUser.getEmail());
            claims.put("id", authUser.getId());
            claims.put("role", authUser.getRole().getName().toString());

            Date now = new Date();
            Date validity = new Date(now.getTime() + expirationTime);

            log.info("Generando token con claims: {}", claims);

            String token = Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

            log.info("Token generado exitosamente");
            return token;

        } catch (Exception e) {
            log.error("Error al crear el token: ", e);
            throw new RuntimeException("Error al crear el token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            log.info("Validando token...");
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            log.info("Token válido");
            return true;
        } catch (Exception e) {
            log.error("Error validando token: ", e);
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        try {
            log.info("Extrayendo email del token");
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            log.info("Email extraído: {}", email);
            return email;
        } catch (Exception e) {
            log.error("Error al obtener email del token: ", e);
            return null;
        }
    }

    public String getUserRoleFromToken(String token) {
        try {
            log.info("Extrayendo rol del token");
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            String role = claims.get("role", String.class);
            log.info("Rol extraído: {}", role);
            return role;
        } catch (Exception e) {
            log.error("Error al obtener rol del token: ", e);
            return null;
        }
    }
}