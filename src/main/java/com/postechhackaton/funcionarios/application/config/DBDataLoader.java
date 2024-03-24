package com.postechhackaton.funcionarios.application.config;

import com.postechhackaton.funcionarios.application.controller.FuncionariosController;
import com.postechhackaton.funcionarios.application.dto.CadastroFuncionarioDto;
import com.postechhackaton.funcionarios.infra.repositories.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class DBDataLoader implements CommandLineRunner {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionariosController funcionariosController;

    @Override
    public void run(String... args) {
        if (funcionarioRepository.findAll().isEmpty()) {
            loadInitialData();
        }
    }

    private void loadInitialData() {
        var cadastroDto = new CadastroFuncionarioDto();
        cadastroDto.setCpf("123456789");
        cadastroDto.setRoles(List.of("admin"));
        cadastroDto.setUsername("usuario-admin");
        cadastroDto.setNome("Usuario");
        cadastroDto.setSobrenome("Admin");
        cadastroDto.setEmail("danielmariadasilva@gmail.com");
        funcionariosController.cadastrar("load", "admin", cadastroDto);
    }
}
