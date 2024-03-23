package com.postechhackaton.funcionarios.application.usecase;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.domain.gateways.FuncionariosDatabaseGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUseCaseImplTest {
    @Mock
    private FuncionariosDatabaseGateway funcionariosDatabaseGateway;

    @InjectMocks
    private BuscarUseCaseImpl buscarUseCase;

    @Test
    void testExecute_deveCriarExampleEChamarGateway_quandoChamado() {
        when(funcionariosDatabaseGateway.buscarPorExemplo(any())).thenReturn(List.of(new FuncionarioEntity()));
        List<FuncionarioEntity> result = buscarUseCase.execute("query");
        assertNotNull(result);
        verify(funcionariosDatabaseGateway, times(1)).buscarPorExemplo(any(Example.class));
    }
}