package com.example.demo.repository;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Lector;
import com.example.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiplomWorkRepository extends CrudRepository<DiplomWork, Integer> {

    @Override
    List<DiplomWork> findAll();

    List<DiplomWork> findByName(String name);

    List<DiplomWork> findAllByLeader(Lector leader);

    List<DiplomWork> findAllByLeaderIdPerson(Integer idPerson);

    List<DiplomWork> findAllByLeaderOrScienceConsultorOrRecensorOrTeoConsultorOrOtConsultor(Lector lector, Lector lector2, Lector lector3, Lector lector4, Lector lector5);

    List<DiplomWork> findAllByLeaderIdPersonOrScienceConsultorIdPersonOrRecensorIdPersonOrTeoConsultorIdPersonOrOtConsultorIdPerson(Integer lector, Integer lector2, Integer lector3, Integer lector4, Integer lector5);

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<DiplomWork> findById(Integer integer);

    Optional<DiplomWork> findByStudent(Student student);

    Optional<DiplomWork> findByStudentIdPerson(Integer id);
}
