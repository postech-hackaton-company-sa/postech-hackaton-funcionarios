package com.postechhackaton.funcionarios.application.mapper;

import com.postechhackaton.funcionarios.application.dto.CadastroFuncionarioDto;
import com.postechhackaton.funcionarios.application.dto.FuncionarioResponseDto;
import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.infra.entities.Funcionario;
import org.hibernate.annotations.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", imports = { LocalDate.class })
public interface FuncionarioMapper {
    List<FuncionarioResponseDto> toDtoList(List<FuncionarioEntity> entities);

    FuncionarioResponseDto toDto(FuncionarioEntity entity);

    @Mapping(target = "cadastradoPor", source = "usuario")
    FuncionarioEntity toEntity(CadastroFuncionarioDto dto, String usuario);

    Funcionario toDbEntity(FuncionarioEntity entity);

    List<FuncionarioEntity> toEntityList(List<Funcionario> funcionarios);
}
