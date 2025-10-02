package com.mllq.back.core.domain.core.repo.role_user;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mllq.back.core.domain.core.entities.RoleUser;
import com.mllq.back.core.domain.core.entities.User;


public interface RoleUserRepository extends JpaRepository<RoleUser, Long>{
    List<RoleUser> findAllByUserAndStatus(User user, Integer status);

    @Query(value = "SELECT r.nombre FROM rol_usuario ru " +
                   "JOIN rol r ON ru.id_rol = r.id_rol " +
                   "WHERE ru.id_usuario = :userId AND ru.status = 1 AND r.status = 1",
           nativeQuery = true)
    List<String> findRoleNamesByUserId(@Param("userId") Long userId);
}
