package com.postechhackaton.funcionarios.domain.gateways;

import com.postechhackaton.funcionarios.application.dto.CadastroFuncionarioDto;
import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;

public interface CredentialGateway {
    void cadastrar(FuncionarioEntity funcionario);
}
