package com.postechhackaton.funcionarios.application.config;

import com.postechhackaton.funcionarios.application.controller.FuncionariosController;
import com.postechhackaton.funcionarios.infra.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DBDataLoaderTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionariosController funcionariosController;

    @InjectMocks
    private DBDataLoader dbDataLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldLoadInitialData_WhenFuncionarioRepositoryIsEmpty() {
        when(funcionarioRepository.findAll()).thenReturn(Collections.emptyList());

        dbDataLoader.run();

        verify(funcionarioRepository, times(1)).findAll();
        verify(funcionariosController, times(1)).cadastrar(anyString(), anyString(), any());
    }
}