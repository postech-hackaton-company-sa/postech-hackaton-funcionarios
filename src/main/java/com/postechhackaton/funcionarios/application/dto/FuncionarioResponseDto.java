package com.postechhackaton.funcionarios.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuppressWarnings("unused")
public class FuncionarioResponseDto {
    private String nome;
    private String sobrenome;
    private String username;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
}
