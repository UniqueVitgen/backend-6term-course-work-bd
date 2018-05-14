package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService  {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> findAllBySpecialization(Integer id_Specialization) {
        return groupRepository.findAllBySpecializationId(id_Specialization);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
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
