package ru.curriculum.service.etp;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ETP_DTO {
    private Integer id;
    private String name;
    private List<ModuleDTO> modules;

    public ETP_DTO() {
        this.modules = new ArrayList<>();
    }
}
