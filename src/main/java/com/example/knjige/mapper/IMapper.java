package com.example.knjige.mapper;

import java.util.List;

public interface IMapper<Model, DTO> {

    Model toModel(DTO dto);
    List<Model> toModel(List<DTO> dto);
    DTO toDTO(Model model);
    List<DTO> toDTO(List<Model> model);

}
