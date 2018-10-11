package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.entity.Specialization;
import com.example.demo.entity.sec.SEC;

import java.util.List;

public interface GroupService {
    List<Group> findAllBySpecialization(Integer id_Specialization);

    List<Group> findAllBySecList(List<SEC> secList);

    List<Group> findAllBySecIdList(List<Integer> secIdList);

    List<Group> findAllBySecListOrNull(List<SEC> secList);

    List<Group> findAllBySecIdListOrNull(List<Integer> secIdList);

    List<Group> findAll();

    Group save(Group group);

    Group Edit(Group group);

    Group get(Integer id);

    void delete(Integer id);
}
