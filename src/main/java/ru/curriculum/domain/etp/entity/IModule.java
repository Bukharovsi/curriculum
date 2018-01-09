package ru.curriculum.domain.etp.entity;

import java.util.Set;

public interface IModule {
    String id();
    String name();
    Set<ISection> sections();
}
