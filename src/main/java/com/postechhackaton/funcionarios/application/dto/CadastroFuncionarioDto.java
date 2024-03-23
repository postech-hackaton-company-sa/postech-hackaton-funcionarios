package com.postechhackaton.funcionarios.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuppressWarnings("unused")
public class CadastroFuncionarioDto {
    @NotNull
    private String nome;
    private String sobrenome;
    private String username;
    private String cpf;
    private List<String> roles;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
}

