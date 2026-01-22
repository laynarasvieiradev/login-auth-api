package com.example.loginauthapi.infra.security;

@Service
public class TokenService {
 public String generateToken(User user) {
   try {
    Algotithm algorithm = Algorithm.HMAC256("secrect");
   } catch (JWTCreationException exception) {
    throw new RuntimeException("Erro ao tentar autenticar");
   }
 }
}