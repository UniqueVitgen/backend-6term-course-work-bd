package com.example.demo.repository;

import com.example.demo.entity.Lector;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student> {
    @Query(nativeQuery = true, value = "USE [diplom_work]" +
            "DECLARE @RC int \n" +
            "DECLARE @firstname varchar(255) = :firstname\n" +
            "DECLARE @middlename varchar(255) = :middlename\n" +
            "DECLARE @lastname varchar(255) = :lastname\n" +
            "DECLARE @username varchar(255) = :username\n" +
            "DECLARE @password varchar(255) = :password\n" +
            "DECLARE @number varchar(255) = :number\n" +
            "EXECUTE @RC =  [dbo].[create_student]  @firstname, @middlename, @lastname, @username, @password, @number" +
            " ")
    Integer createStudent(@Param("lastname") String lastname,
                          @Param("firstname") String firstname,
                          @Param("middlename") String middlename,
                          @Param("username") String username,
                          @Param("password") String password,
                          @Param("number") String number);

    @Override
    List<Student> findAll();

    @Override
    Optional<Student> findById(Integer integer);
}
