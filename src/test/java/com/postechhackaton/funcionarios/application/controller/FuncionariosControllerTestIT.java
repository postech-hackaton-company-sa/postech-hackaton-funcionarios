package com.postechhackaton.funcionarios.application.controller;

import com.postechhackaton.funcionarios.infra.entities.Funcionario;
import com.postechhackaton.funcionarios.infra.repositories.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FuncionariosControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    void testBuscarFuncionarios() throws Exception {
        var funcionario = new Funcionario();
        funcionario.setUsername("user-1");
        funcionario.setCpf("cpf");
        funcionario.setEmail("teste@test.com");
        funcionarioRepository.save(funcionario);

        mockMvc.perform(get("/v1/funcionarios?username=user-1")
                        .header("username", "user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("user-1"));
    }

    @Test
    void testBuscarFuncionarios_deveRetornar200_quandoNaoExistirUsuario() throws Exception {
        mockMvc.perform(get("/v1/funcionarios?username=user-2")
                        .header("username", "user"))
                .andExpect(status().isOk());
    }
}
