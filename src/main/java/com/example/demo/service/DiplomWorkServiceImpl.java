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
import java.util.NoSuchElementException;

@Service
public class DiplomWorkServiceImpl implements DiplomWorkService{
    @Autowired
    private DiplomWorkRepository diplomWorkRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private LectorService lectorService;

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
        Status status;
        try {
            status = statusService.findById(1);
            diplomWork.setStatus(status);
        }
        catch (NoSuchElementException e) {

        }
        return diplomWorkRepository.save(diplomWork);
    }

    @Override
    public DiplomWork edit(DiplomWork diplomWork) {
        DiplomWork editedDiplomWork = diplomWorkRepository.findById(diplomWork.getId()).get();
//        diplomWorkRepository.flush()
        editedDiplomWork.setName(diplomWork.getName());
        editedDiplomWork.setStatus(diplomWork.getStatus());
        editedDiplomWork.setMark(diplomWork.getMark());
        editedDiplomWork.setLeader(lectorService.findByUsername(diplomWork.getLeader().getUsername()));
        editedDiplomWork.setComment(diplomWork.getComment());
        if(diplomWork.getTeoConsultor() != null) {
            editedDiplomWork.setTeoConsultor(lectorService.findByUsername(diplomWork.getTeoConsultor().getUsername()));
        }
        if (diplomWork.getScienceConsultor() != null) {
            editedDiplomWork.setScienceConsultor(lectorService.findByUsername(diplomWork.getScienceConsultor().getUsername()));
        }
        if (diplomWork.getOtConsultor() != null) {
            editedDiplomWork.setOtConsultor(lectorService.findByUsername(diplomWork.getOtConsultor().getUsername()));
        }
        if (diplomWork.getRecensor() != null) {
            editedDiplomWork.setRecensor(lectorService.findByUsername(diplomWork.getRecensor().getUsername()));
        }
//        editedDiplomWork.set
        return diplomWorkRepository.save(editedDiplomWork);
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
