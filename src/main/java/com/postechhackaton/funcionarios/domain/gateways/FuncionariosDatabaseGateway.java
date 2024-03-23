package com.postechhackaton.funcionarios.domain.gateways;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.infra.entities.Funcionario;
import org.springframework.data.domain.Example;

import java.util.List;

public interface FuncionariosDatabaseGateway {
    void salvar(FuncionarioEntity entity);

    List<FuncionarioEntity> buscarPorExemplo(Example<Funcionario> of);
}
