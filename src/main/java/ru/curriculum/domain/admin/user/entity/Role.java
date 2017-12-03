package ru.curriculum.domain.admin.user.entity;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@EqualsAndHashCode
public class Role {
    @Id
    private String code;
    private String name;

    public Role() {}

    public Role(@NonNull String code, @NonNull String name) {
        this.code = code;
        this.name = name;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }
}
