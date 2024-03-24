package com.postechhackaton.funcionarios.application.controller;

import com.postechhackaton.funcionarios.application.dto.CadastroFuncionarioDto;
import com.postechhackaton.funcionarios.application.dto.ExceptionResponse;
import com.postechhackaton.funcionarios.application.dto.FuncionarioResponseDto;
import com.postechhackaton.funcionarios.application.mapper.FuncionarioMapper;
import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.domain.usecase.BuscarUseCase;
import com.postechhackaton.funcionarios.domain.usecase.CadastrarUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/funcionarios")
@RequiredArgsConstructor
public class FuncionariosController {

    private final BuscarUseCase buscarUseCase;
    private final CadastrarUseCase cadastrarUseCase;
    private final FuncionarioMapper funcionarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Altera o status de um pagamento",
            description = "Realiza uma alteracao do status do pagamento utilizando o id do mesmo."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Funcionario cadastrado com sucesso",
                    content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = FuncionarioResponseDto.class)
                    )
            }),
            @ApiResponse(responseCode = "400",
                    description = "Parametros invalidos para cadastrar um funcionario",
                    content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionResponse.class)
                    )
            }),
    })
    public FuncionarioResponseDto cadastrar(@RequestHeader("username") String usuario,
                                            @RequestHeader("roles") @Pattern(regexp = ".*admin.*", message = "Apenas administradores podem cadastrar funcionarios") String roles,
                                            @Valid @RequestBody @Parameter(description = "Objeto de criacao de funcionarios", required = true) CadastroFuncionarioDto cadastroFuncionarioDto) {
        FuncionarioEntity funcionario = funcionarioMapper.toEntity(cadastroFuncionarioDto, usuario);
        FuncionarioEntity funcionarioResponse = cadastrarUseCase.execute(funcionario);
        return funcionarioMapper.toDto(funcionarioResponse);
    }

    @GetMapping
    @Operation(
            summary = "Buscar funcionarios"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404",
                    description = "Parametros invalidos para calcular o ponto",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    }),
    })
    public List<FuncionarioResponseDto> buscarFuncionarios(@RequestHeader("username") String usuario,
                                @RequestParam(value = "username", required = false) String queryUsername) {
        log.info("Usuario {} requisitou uma busca de funcionarios", usuario);
        List<FuncionarioEntity> resposta =  buscarUseCase.execute(queryUsername);
        return funcionarioMapper.toDtoList(resposta);
    }

}
