package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.request.AuthenticationDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.LoginResponseDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.RefreshTokenDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserInputDTO;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.security.TokenService;
import br.edu.ifgoiano.hotel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hotel/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(),authenticationDTO.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var loginResponse = tokenService.getAuthentication((User) auth.getPrincipal());
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody @Valid RefreshTokenDTO refreshTokenDTO){
        var loginResponse = tokenService.getRefreshToken(refreshTokenDTO);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("register")
    @Operation(summary = "Criar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailOutputDTO.class))})
    })
    public ResponseEntity<UserDetailOutputDTO> create(@RequestBody UserInputDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }


}
