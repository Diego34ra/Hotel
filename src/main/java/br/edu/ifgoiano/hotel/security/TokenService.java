package br.edu.ifgoiano.hotel.security;

import br.edu.ifgoiano.hotel.controller.dto.request.LoginResponseDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.RefreshTokenDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceBadRequestException;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration}")
    private Integer hourExpirationToken;

    @Value("${api.security.refresh-token.expiration}")
    private Integer hourExpirationRefreshToken;

    @Autowired
    private UserService userService;

    public LoginResponseDTO getAuthentication(User user){
        var token  = generateToken(user,hourExpirationToken);
        var refreshToken  = generateToken(user,hourExpirationRefreshToken);
        return new LoginResponseDTO(token,refreshToken);
    }

    public LoginResponseDTO getRefreshToken(RefreshTokenDTO refreshTokenDTO){
        var login = validateToken(refreshTokenDTO.refreshToken());
        UserDetails user = userService.findByEmail(login);

        if(user == null)
            throw new ResourceBadRequestException("Refresh token inválido.");

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return getAuthentication((User) authentication.getPrincipal());
    }

    public String generateToken(User user, Integer hourExpirationToken){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withClaim("id",user.getId())
                    .withClaim("role",user.getRole().toString())
                    .withExpiresAt(genExpirationDate(hourExpirationToken))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro enquanto gerava o token ", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(Integer hour){
        return LocalDateTime.now().plusHours(hour).toInstant(ZoneOffset.of("-03:00"));
    }
}
