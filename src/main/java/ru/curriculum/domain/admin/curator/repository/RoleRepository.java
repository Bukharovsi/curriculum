package ru.curriculum.domain.admin.curator.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.curriculum.domain.admin.curator.entity.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
}
