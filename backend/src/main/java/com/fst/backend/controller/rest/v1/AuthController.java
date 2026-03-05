package com.fst.backend.controller.rest.v1;

import com.fst.backend.dto.request.UserRequest;
import com.fst.backend.security.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserRequest userRequest) {
        // ToDo: validate user credentials (this is just a placeholder)
        String token = jwtService.generateJwtToken(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        return ResponseEntity.ok(Map.of("token", token));
    }
}
