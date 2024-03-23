package com.postechhackaton.funcionarios.application.usecase;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.domain.gateways.FuncionariosDatabaseGateway;
import com.postechhackaton.funcionarios.domain.usecase.BuscarUseCase;
import com.postechhackaton.funcionarios.infra.entities.Funcionario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuscarUseCaseImpl implements BuscarUseCase {

    private final FuncionariosDatabaseGateway funcionariosDatabaseGateway;
    @Override
    public List<FuncionarioEntity> execute(String queryUsername) {
        Funcionario funcionario = new Funcionario();
        funcionario.setUsername(queryUsername);

        return funcionariosDatabaseGateway.buscarPorExemplo(Example.of(funcionario));
    }
}
