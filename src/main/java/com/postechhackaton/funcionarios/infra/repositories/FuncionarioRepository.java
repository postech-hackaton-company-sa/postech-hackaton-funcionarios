package com.postechhackaton.funcionarios.infra.repositories;

import com.postechhackaton.funcionarios.infra.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
