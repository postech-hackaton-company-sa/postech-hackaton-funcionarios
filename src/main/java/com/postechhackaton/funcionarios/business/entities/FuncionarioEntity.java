package com.postechhackaton.funcionarios.business.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuppressWarnings("unused")
public class FuncionarioEntity {
    private String nome;
    private String sobrenome;
    private String username;
    private String cpf;
    private String telefone;
    private String email;
    private List<String> roles;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
    private String cadastradoPor;
}