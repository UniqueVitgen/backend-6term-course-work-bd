package com.example.demo.service;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Lector;
import com.example.demo.entity.Student;

import java.util.List;

public interface DiplomWorkService {

    DiplomWork findById(Integer id);

    List<DiplomWork> getAll();

    DiplomWork save(DiplomWork diplomWork);

    DiplomWork edit(DiplomWork diplomWork);

    DiplomWork findByStudent(Student student);

    DiplomWork findByStudent(Integer idPerson);

    List<DiplomWork> findAllByLeader(Lector leader);

    List<DiplomWork> findAllByLeader(Integer idPerson);

    List<DiplomWork> findAllByLector(Lector lector);

    List<DiplomWork> findAllByLector(Integer idPerson);

    void delete(Integer id);
}
