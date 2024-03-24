package com.postechhackaton.funcionarios.infra.gateways;

import com.postechhackaton.funcionarios.application.mapper.FuncionarioMapper;
import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.infra.entities.Funcionario;
import com.postechhackaton.funcionarios.infra.repositories.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FuncionariosDatabaseGatewayImplTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioMapper funcionarioMapper;

    @InjectMocks
    private FuncionariosDatabaseGatewayImpl funcionariosDatabaseGateway;

    @Test
    void salvar_ShouldSaveFuncionarioEntity_WhenCalled() {
        FuncionarioEntity entity = new FuncionarioEntity();
        Funcionario funcionario = new Funcionario();
        when(funcionarioMapper.toDbEntity(entity)).thenReturn(funcionario);

        funcionariosDatabaseGateway.salvar(entity);

        verify(funcionarioMapper, times(1)).toDbEntity(entity);
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void buscarPorExemplo_ShouldReturnListOfFuncionarioEntity_WhenCalled() {
        Example<Funcionario> example = Example.of(new Funcionario());
        List<Funcionario> funcionarios = Collections.singletonList(new Funcionario());
        when(funcionarioRepository.findAll(example)).thenReturn(funcionarios);

        List<FuncionarioEntity> result = funcionariosDatabaseGateway.buscarPorExemplo(example);

        assertNotNull(result);
        verify(funcionarioRepository, times(1)).findAll(example);
        verify(funcionarioMapper, times(1)).toEntityList(funcionarios);
    }
}
