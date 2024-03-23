package com.postechhackaton.funcionarios.application.usecase;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.domain.gateways.CredentialGateway;
import com.postechhackaton.funcionarios.domain.gateways.FuncionariosDatabaseGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarUseCaseImplTest {

    @Mock
    private FuncionariosDatabaseGateway funcionariosDatabaseGateway;
    @Mock
    private CredentialGateway credentialGateway;

    @InjectMocks
    private CadastrarUseCaseImpl cadastrarUseCase;

    @Test
    void testExecute_deveChamarCredentialEFuncionarioGateway_quandoChamado() {
        cadastrarUseCase.execute(new FuncionarioEntity());
        verify(credentialGateway, times(1)).cadastrar(any());
        verify(funcionariosDatabaseGateway, times(1)).salvar(any());
    }

}