package com.postechhackaton.funcionarios.application.controller;

import com.postechhackaton.funcionarios.application.dto.FuncionarioResponseDto;
import com.postechhackaton.funcionarios.application.mapper.FuncionarioMapper;
import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.domain.usecase.BuscarUseCase;
import com.postechhackaton.funcionarios.domain.usecase.CadastrarUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuncionarioControllerTest {
    @Mock
    private BuscarUseCase buscarUseCase;
    @Mock
    private CadastrarUseCase cadastrarUseCase;
    @Spy
    private FuncionarioMapper funcionarioMapper = Mappers.getMapper(FuncionarioMapper.class);
    @InjectMocks
    private FuncionariosController funcionariosController;

    @Test
    void buscarUsuarios_deveRetornarUsuarios() {
        when(buscarUseCase.execute(anyString())).thenReturn(List.of(new FuncionarioEntity()));
        List<FuncionarioResponseDto> funcionarioResponseDtos = funcionariosController.buscarFuncionarios("user", "query");
        assertNotNull(funcionarioResponseDtos);
        assertEquals(1, funcionarioResponseDtos.size());
    }

    @Test
    void cadastrarUsuario_deveCadastrarUsuarioERetonar() {

    }
}
