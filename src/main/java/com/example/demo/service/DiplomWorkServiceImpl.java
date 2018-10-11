package com.example.demo.service;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Lector;
import com.example.demo.entity.Status;
import com.example.demo.entity.Student;
import com.example.demo.repository.DiplomWorkRepository;
import com.example.demo.service.files.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiplomWorkServiceImpl implements DiplomWorkService{
    @Autowired
    private DiplomWorkRepository diplomWorkRepository;

    @Autowired
    private StatusService statusService;

    @Override
    public DiplomWork findById(Integer id) {
        return diplomWorkRepository.findById(id).get();
    }

    @Override
    public List<DiplomWork> getAll() {
        return diplomWorkRepository.findAll();
    }

    @Override
    public DiplomWork save(DiplomWork diplomWork) {
        Status status = statusService.findByName("Начало");
        diplomWork.setStatus(status);
        return diplomWorkRepository.save(diplomWork);
    }

    @Override
    public DiplomWork edit(DiplomWork diplomWork) {
//        diplomWorkRepository.flush()
        return diplomWorkRepository.save(diplomWork);
    }

    @Override
    public DiplomWork findByStudent(Student student) {
        return diplomWorkRepository.findByStudent(student).get();
    }

    @Override
    public DiplomWork findByStudent(Integer idPerson) {
        return diplomWorkRepository.findByStudentIdPerson(idPerson).get();
    }

    @Override
    public List<DiplomWork> findAllByLeader(Lector leader) {
        return diplomWorkRepository.findAllByLeader(leader);
    }

    @Override
    public List<DiplomWork> findAllByLeader(Integer idPerson) {
        return null;
    }

    @Override
    public List<DiplomWork> findAllByLector(Lector lector) {
        return diplomWorkRepository.findAllByLeaderOrScienceConsultorOrRecensorOrTeoConsultorOrOtConsultor(lector, lector, lector,lector,lector);
    }

    @Override
    public List<DiplomWork> findAllByLector(Integer idPerson) {
        return diplomWorkRepository.findAllByLeaderIdPersonOrScienceConsultorIdPersonOrRecensorIdPersonOrTeoConsultorIdPersonOrOtConsultorIdPerson(idPerson, idPerson, idPerson, idPerson, idPerson);
    }

    @Override
    public void delete(Integer id) {
        diplomWorkRepository.deleteById(id);
    }
}
