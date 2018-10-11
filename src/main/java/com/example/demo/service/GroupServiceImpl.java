package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.entity.sec.SEC;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.sec.SECRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService  {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SECRepository secRepository;

    @Override
    public List<Group> findAllBySpecialization(Integer id_Specialization) {
        return groupRepository.findAllBySpecializationId(id_Specialization);
    }

    @Override
    public List<Group> findAllBySecList(List<SEC> secList) {
        return groupRepository.findAllBySecIn(secList);
    }

    @Override
    public List<Group> findAllBySecIdList(List<Integer> secIdList) {
        List<SEC> secList = new ArrayList<>();
        for(Integer secId: secIdList) {
            secList.add(secRepository.findById(secId).get());
        }
        return groupRepository.findAllBySecIn(secList);
    }

    @Override
    public List<Group> findAllBySecListOrNull(List<SEC> secList) {
        List<Integer> ids = new ArrayList<>();
        for(SEC sec: secList) {
            ids.add(sec.getId());
        }
        return groupRepository.findAllBySecInOrNull(ids);
    }

    @Override
    public List<Group> findAllBySecIdListOrNull(List<Integer> secIdList) {
        return groupRepository.findAllBySecInOrNull(secIdList);
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
