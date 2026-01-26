package com.example.loginauthapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.dto.LoginRequestDTO;
import com.example.loginauthapi.dto.ResponseDTO;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
 private final UserRepository repository;
 private final PasswordEncoder passwordEncoder;
 private final TokenService tokenService;

 @PostMapping("/login")
 public ResponseEntity login(@RequestBody LoginRequestDTO body) {
  User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
  if(passwordEncoder.matches(user.getPassword(), body.password())){
   String token = this.tokenService.generateToken(user);
   return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
  }
  return ResponseEntity.badRequest().build();
 }
 
 @PostMapping("/register")
 public ResponseEntity register(@RequestBody LoginRequestDTO body) {
  User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
  if(passwordEncoder.matches(user.getPassword(), body.password())){
   String token = this.tokenService.generateToken(user);
   return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
  }
  return ResponseEntity.badRequest().build();
 }
}
