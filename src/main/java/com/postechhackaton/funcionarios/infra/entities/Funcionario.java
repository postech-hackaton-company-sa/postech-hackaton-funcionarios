package com.postechhackaton.funcionarios.infra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@SuppressWarnings("unused")
@Table(name = "Funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String cpf;
    private String telefone;
    @Column(unique = true)
    private String email;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
    private String cadastradoPor;
}
