package com.postechhackaton.funcionarios.application.usecase;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.domain.gateways.CredentialGateway;
import com.postechhackaton.funcionarios.domain.gateways.FuncionariosDatabaseGateway;
import com.postechhackaton.funcionarios.domain.usecase.CadastrarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CadastrarUseCaseImpl implements CadastrarUseCase {

    private final FuncionariosDatabaseGateway funcionariosDatabaseGateway;
    private final CredentialGateway credentialGateway;

    @Override
    @Transactional
    public FuncionarioEntity execute(FuncionarioEntity funcionario) {
        funcionario.setDataCadastro(LocalDate.now());
        funcionariosDatabaseGateway.salvar(funcionario);
        credentialGateway.cadastrar(funcionario);

        return funcionario;
    }
}
