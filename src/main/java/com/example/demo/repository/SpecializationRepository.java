package com.example.demo.repository;

import com.example.demo.entity.Faculty;
import com.example.demo.entity.Specialization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecializationRepository extends CrudRepository<Specialization, Integer> {

    @Override
    List<Specialization> findAll();

//    @Query("select * from Specialization as sp where sp.id_faculty = :id")
//    List<Specialization> findSpecializationByFaculty()

    @Query(value = "select * from  diplom_work.dbo.специальность as sp where sp.id_faculty = :id", nativeQuery = true)
    List<Specialization> findSpecializationByFacultyId(@Param("id") Integer id);

    List<Specialization> findAllByFaculty(Faculty faculty);
//    @Query("select * from Specialization as sp where sp.name = :name")
//    Faculty findByName(@Param("name") String name);
}
