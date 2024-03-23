package com.postechhackaton.funcionarios.infra.gateways;

import com.postechhackaton.funcionarios.application.mapper.FuncionarioMapper;
import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.domain.gateways.FuncionariosDatabaseGateway;
import com.postechhackaton.funcionarios.infra.entities.Funcionario;
import com.postechhackaton.funcionarios.infra.repositories.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FuncionariosDatabaseGatewayImpl implements FuncionariosDatabaseGateway {

    private final FuncionarioRepository funcionarioRepository;

    private final FuncionarioMapper funcionarioMapper;

    @Override
    public void salvar(FuncionarioEntity entity) {
        Funcionario funcionario = funcionarioMapper.toDbEntity(entity);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public List<FuncionarioEntity> buscarPorExemplo(Example<Funcionario> example) {
        List<Funcionario> funcionarios = funcionarioRepository.findAll(example);
        return funcionarioMapper.toEntityList(funcionarios);
    }
}
