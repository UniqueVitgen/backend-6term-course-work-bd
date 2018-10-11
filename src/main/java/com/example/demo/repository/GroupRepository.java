package com.example.demo.repository;

import com.example.demo.entity.Group;
import com.example.demo.entity.Specialization;
import com.example.demo.entity.sec.SEC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GroupRepository extends CrudRepository<Group, Integer> {
    @Query(value = "select * from  diplom_work.group as sp where sp.id_specialization = :id", nativeQuery = true)
    List<Group> findAllBySpecializationId(@Param("id") Integer id);

    List<Group> findAllBySecIn(List<SEC> secList);

    List<Group> findAllBySec(SEC sec);

    @Override
    List<Group> findAll();

    @Query("SELECT l1 FROM Group l1 WHERE l1.sec.id IN :ids or l1.sec.id is null")
    List<Group> findAllBySecInOrNull(@Param("ids") List<Integer> ids);

}
