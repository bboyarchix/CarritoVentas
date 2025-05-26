package com.example.microservice_auth.service.impl;

import com.example.microservice_auth.dto.*;
import com.example.microservice_auth.entity.AuthUser;
import com.example.microservice_auth.entity.Role;
import com.example.microservice_auth.entity.ERole;
import com.example.microservice_auth.feign.CustomerClient;
import com.example.microservice_auth.feign.VendorClient;
import com.example.microservice_auth.repository.AuthUserRepository;
import com.example.microservice_auth.repository.RoleRepository;
import com.example.microservice_auth.security.JwtTokenProvider;
import com.example.microservice_auth.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Autowired
   private JwtTokenProvider jwtTokenProvider;

   @Autowired
   private CustomerClient customerClient;

   @Autowired
   private VendorClient vendorClient;

    @Override
    @Transactional(readOnly = true)
    public TokenDto login(LoginRequest loginRequest) throws Exception {
        log.info("Iniciando proceso de login para email: {}", loginRequest.getEmail());

        try {
            // Validar entrada
            if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
                log.error("Credenciales incompletas");
                throw new IllegalArgumentException("Credenciales incompletas");
            }

            // Buscar usuario
            AuthUser user = authUserRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> {
                        log.error("Usuario no encontrado para email: {}", loginRequest.getEmail());
                        return new Exception("Email no encontrado");
                    });

            log.info("Usuario encontrado: {}", user.getEmail());

            // Validar contraseña
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                log.error("Contraseña incorrecta para usuario: {}", user.getEmail());
                throw new Exception("Contraseña incorrecta");
            }

            log.info("Contraseña validada correctamente");

            // Validar rol
            if (user.getRole() == null) {
                log.error("Usuario sin rol asignado: {}", user.getEmail());
                throw new IllegalStateException("Usuario sin rol asignado");
            }

            log.info("Rol validado: {}", user.getRole().getName());

            // Crear token
            String token = jwtTokenProvider.createToken(user);

            if (token == null) {
                log.error("Token generado es null");
                throw new IllegalStateException("Error al generar el token");
            }

            log.info("Token generado exitosamente");
            return new TokenDto(token);

        } catch (Exception e) {
            log.error("Error en el proceso de login: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public AuthUser registerUser(RegisterRequest registerRequest) {
        // Validar si existe el email
        if (authUserRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // Validar formato de email
        if (!isValidEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Formato de email inválido");
        }

        // Validar requisitos de contraseña
        if (!isValidPassword(registerRequest.getPassword())) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        /* Obtener rol CLIENTE por defecto
        Role defaultRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new IllegalStateException("Rol CLIENTE no encontrado")); */
        // Convertir el rol de String a ERole
        ERole roleType;
        try {
            roleType = ERole.valueOf(registerRequest.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rol inválido: " + registerRequest.getRole());
        }

        // Obtener el rol desde la base de datos
        Role role = roleRepository.findByName(roleType)
                .orElseThrow(() -> new IllegalStateException("Rol no encontrado"));
        // Usar el builder pattern correctamente
        AuthUser authUser = AuthUser.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();

        //return authUserRepository.save(authUser);

        //AuthUser savedUser = authUserRepository.save(authUser);

        // Llamada al microservicio correspondiente usando Feign
       /* try {
            if
            (registerRequest.getRole().equals(ERole.ROLE_CLIENT)) {
                CustomerProfileDto customerProfileDto = new CustomerProfileDto(savedUser.getId(), savedUser.getUsername());
                log.info("Enviando solicitud a customer-service para crear un perfil de cliente.");
                customerClient.createCustomerProfile(customerProfileDto);
                log.info("Solicitud enviada y procesada.");
            /*} else if (registerRequest.getRole().equals(ERole.ROLE_VENDOR)) {
                VendorProfileDto vendorProfileDto = new VendorProfileDto(savedUser.getId(), savedUser.getUsername());
                vendorClient.createVendorProfile(vendorProfileDto);
            }
        } catch (Exception e) {
            log.error("Error al crear el perfil en el microservicio correspondiente: ", e);
        }*/
        // Crear el perfil de cliente en el microservicio de customer

        AuthUser savedAuthUser = authUserRepository.save(authUser);

        // Crear el perfil en el microservicio correspondiente
        if (roleType == ERole.ROLE_CLIENT) {
            // Si el rol es CLIENTE, crear el perfil en el microservicio de customer
            CustomerProfileDto customerProfileDto = CustomerProfileDto.builder()
                    .authId(savedAuthUser.getId())
                    .username(savedAuthUser.getUsername())
                    .build();
            customerClient.createCustomerProfile(customerProfileDto);
        } else if (roleType == ERole.ROLE_VENDOR) {
            // Si el rol es VENDEDOR, crear el perfil en el microservicio de vendor
            VendorProfileDto vendorProfileDto = VendorProfileDto.builder()
                    .authId(savedAuthUser.getId())
                    .username(savedAuthUser.getUsername())
                    .build();
            vendorClient.createVendorProfile(vendorProfileDto);
        }

        return savedAuthUser;


    }

    @Override
    @Transactional(readOnly = true)
    public TokenDto validateToken(String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("Token inválido o expirado");
        }

        String email = jwtTokenProvider.getEmailFromToken(token);
        authUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return new TokenDto(token);
    }

    @Override
    @Transactional
    public void logout(String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("Token inválido");
        }

        // Aquí puedes implementar la lógica adicional de logout
        // Por ejemplo, agregar el token a una lista negra
        // blacklistService.addToBlacklist(token);
    }

    private boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidPassword(String password) {
        return password != null &&
                password.length() >= 8;
    }
}