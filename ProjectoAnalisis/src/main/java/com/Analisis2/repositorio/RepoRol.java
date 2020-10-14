package com.Analisis2.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.Analisis2.entity.Role;

@Repository
public interface RepoRol extends CrudRepository<Role,Long>  {

}
