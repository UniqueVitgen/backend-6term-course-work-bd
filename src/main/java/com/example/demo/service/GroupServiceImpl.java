package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.entity.sec.SEC;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.sec.SECRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService  {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SECRepository secRepository;

    @Override
    public List<Group> findAllBySpecialization(Integer id_Specialization) {
        return groupRepository.findAllBySpecializationIdSpecialization(id_Specialization);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

//    @Override
//    public List<Group> findAll(List<Group> groups) {
//        List<Integer> ids = groups.stream().map(student -> student.getIdPerson()).collect(Collectors.toList());
//        return new HashSet<>(studentRepository.findAllByIdPersonIn(ids));
//    }

    @Override
    public List<Group> findAllBySpecializationIds(List<Integer> specializationIds) {
        return groupRepository.findAllBySpecializationIdSpecializationIn(specializationIds);
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group Edit(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group get(Integer id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        Group group = groupRepository.findById(id).get();
        groupRepository.delete(group);
    }
}
