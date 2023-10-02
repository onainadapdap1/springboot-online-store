package com.spring.ecommerceyt.controller;

import com.spring.ecommerceyt.entity.Pengguna;
import com.spring.ecommerceyt.model.JwtResponse;
import com.spring.ecommerceyt.model.LoginRequest;
import com.spring.ecommerceyt.model.SignupRequest;
import com.spring.ecommerceyt.security.jwt.JwtUtils;
import com.spring.ecommerceyt.security.service.UserDetailsImpl;
import com.spring.ecommerceyt.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //is an interface provided by Spring Security for authentication purposes.
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PenggunaService penggunaService;

    //is typically used for encoding passwords before storing them in a database
    // and for comparing encoded passwords during authentication.
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
        /*is used to authenticate the user. It takes the provided UsernamePasswordAuthenticationToken,
        which includes the username and password from the LoginRequest*/
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        /*to set the authenticated Authentication object in the security context. This step is crucial
        for maintaining the user's authentication status for the duration of the request.*/
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(new JwtResponse(token, principal.getUsername(), principal.getEmail()));
    }

    @PostMapping("/signup")
    public Pengguna signup(@RequestBody SignupRequest request) {
        Pengguna pengguna = new Pengguna();
        pengguna.setId(request.getUsername());
        pengguna.setEmail(request.getEmail());
        pengguna.setPassword(passwordEncoder.encode(request.getPassword()));
        pengguna.setNama(request.getNama());
        pengguna.setRoles("user");
        Pengguna created = penggunaService.create(pengguna);
        return created;
    }
}
