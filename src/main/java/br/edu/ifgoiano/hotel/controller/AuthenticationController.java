package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.request.AuthenticationDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.LoginResponseDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.RefreshTokenDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserInputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.security.TokenService;
import br.edu.ifgoiano.hotel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @Operation(summary = "Realizar autenticação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao autenticar usuário.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO authenticationDTO){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(),authenticationDTO.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var loginResponse = tokenService.getAuthentication((User) auth.getPrincipal());
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("refresh-token")
    @Operation(summary = "Atualizar autenticação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticação atualizada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RefreshTokenDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar autenticação.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO){
        var loginResponse = tokenService.getRefreshToken(refreshTokenDTO);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("register")
    @Operation(summary = "Criar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailOutputDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao registrar usuário.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<UserDetailOutputDTO> create(@RequestBody @Valid UserInputDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }


}
