package com.formation.projet.repository;


import java.util.Optional;

import com.formation.projet.entities.Role;
import com.formation.projet.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByRoleName(Roles role);
}
