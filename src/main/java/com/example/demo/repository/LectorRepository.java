package com.example.demo.repository;

import com.example.demo.entity.Lector;
import com.example.demo.entity.sec.SEC;
import com.example.demo.entity.sec.SECUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface LectorRepository extends UserBaseRepository<Lector> {
  @Query(nativeQuery = true, value = "USE [diplom_work]\n" +
          "DECLARE @RC int\n" +
          "DECLARE @firstname varchar(255) = :firstname\n" +
          "DECLARE @middlename varchar(255) = :middlename\n" +
          "DECLARE @lastname varchar(255) = :lastname\n" +
          "DECLARE @username varchar(255) = :username\n" +
          "DECLARE @title varchar(255) = :title\n" +
          "DECLARE @degree varchar(255) = :degree\n" +
          "DECLARE @post varchar(255) = :post\n" +
          "DECLARE @password varchar(255) = :password\n" +
          "DECLARE @description varchar(255) = :description\n" +
          "EXECUTE @RC =  [dbo].[create_lector]  @firstname\n" +
          "  ,@middlename\n" +
          "  ,@lastname\n" +
          "  ,@username\n" +
          "  ,@title\n" +
          "  ,@degree\n" +
          "  ,@post\n" +
          "  ,@password\n" +
          "  ,@description")
  Integer createLector(@Param("lastname") String lastname,
                        @Param("firstname") String firstname,
                        @Param("middlename") String middlename,
                        @Param("username") String username,
                        @Param("password") String password,
                        @Param("title") String title,
                       @Param("degree") String degree,
                       @Param("post") String post,
                       @Param("description") String descritpion);

  @Override
  List<Lector> findAll();

//  List<Lector> findAllBySecUserIsNullOrSecUserIdIsNotIn(List<Integer> ids);
//
//  List<Lector> findAllBySecUserIsNull();
//
//  List<Lector> findAllBySecUserNull();
//
//  List<Lector> findAllBySecUserIdIsNull();
//
//  List<Lector> findAllBySecUserIdIsNullOrSecUserIdIsNotIn(List<Integer> ids);

  List<Lector> findAllBySecUsersIsNotContaining(List<SECUser> secUsers);

  List<Lector> findAllBySecUsersNotContaining(List<SECUser> secUsers);

  List<Lector> findAllBySecUsersIsNotIn(List<SECUser> secUsers);

  List<Lector> findAllBySecUsersNotIn(List<SECUser> secUsers);
  List<Lector> findAllBySecUsersIdNotIn(List<Integer> ids);

  List<Lector> findAllBySecUsersNotContains(List<SECUser> secUsers);

  @Query(value = "select * from lector where id_person not in " +
          "(SELECT distinct lector.id_Person FROM diplom_work.lector left join sec_user \n" +
          "on lector.id_person = sec_user.id_Person \n" +
          "where sec_user.id_sec_user in (:secUsersIds)) ;", nativeQuery = true)
  List<Integer> findIdsBySecUsersIdsNotIn(@Param("secUsersIds")List<Integer> ids);

  List<Lector> findAllByIdPersonIn(List<Integer> ids);

  Optional<Lector> findByUsername(String username);
}
