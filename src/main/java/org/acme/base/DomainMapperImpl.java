package org.acme.base;

import java.util.List;

public class DomainMapperImpl implements DomainMapper {

  @Override
  public Domain toEntity(DomainDTO dto) {

    if (dto == null) {
      return null;
    }
    Domain domain = new Domain();
    domain.setName(dto.getName());
    domain.setTimeStamp();
    domain.setRole(dto.getRole());

    return domain;
  }

  @Override
  public DomainDTO toDto(Domain entity) {

    if (entity == null) {
      return null;
    }

    DomainDTO dto = new DomainDTO();
    dto.setName(entity.getName());
    dto.setTimeStamp(entity.getTimeStamp());
    dto.setRole(entity.getRole());

    return dto;
  }

  @Override
  public List<Domain> toEntity(List<DomainDTO> dtoList) {
    return List.of();
  }

  @Override
  public List<DomainDTO> toDto(List<Domain> entityList) {
    return List.of();
  }
}
