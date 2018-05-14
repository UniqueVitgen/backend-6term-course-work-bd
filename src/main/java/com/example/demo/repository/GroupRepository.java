package com.example.demo.repository;

import com.example.demo.entity.Group;
import com.example.demo.entity.Specialization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Integer> {
    @Query(value = "select * from  diplom_work.dbo.группа as sp where sp.id_specialization = :id", nativeQuery = true)
    List<Group> findAllBySpecializationId(@Param("id") Integer id);

    @Override
    List<Group> findAll();
}
