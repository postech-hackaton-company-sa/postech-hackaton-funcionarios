package com.postechhackaton.funcionarios.domain.usecase;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;

public interface CadastrarUseCase {
    FuncionarioEntity execute(FuncionarioEntity funcionario);
}
