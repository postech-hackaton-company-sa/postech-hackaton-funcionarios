package com.postechhackaton.funcionarios.domain.usecase;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;

import java.time.LocalDate;
import java.util.List;

public interface BuscarUseCase {
    List<FuncionarioEntity> execute(String queryUsername);
}
