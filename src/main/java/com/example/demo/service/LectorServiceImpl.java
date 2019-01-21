package com.example.demo.service;

import com.example.demo.entity.Lector;
import com.example.demo.entity.sec.SECUser;
import com.example.demo.repository.LectorRepository;
import com.example.demo.repository.sec.SECUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorServiceImpl implements LectorService {

  @Autowired
  private LectorRepository lectorRepository;
  @Autowired
  private SECUserRepository secUserRepository;
  @Override
  public List<Lector> findAll() {
    return lectorRepository.findAll();
  }

  @Override
  public List<Lector> findAllBySecUserIdIsNotInOrSecUserNull(List<Integer> ids) {
    List<SECUser> secUsers = secUserRepository.findAllById(ids);
    return lectorRepository.findAllBySecUsersIsNotContaining(secUsers);
  }

  @Override
  public Lector save(Lector lector) {
    return lectorRepository.save(lector);
  }

    @Override
    public Lector edit(Lector lector) {
      Lector editedLector = lectorRepository.findById(lector.getIdPerson()).get();
      editedLector.setMaxCountOfDiplom(lector.getMaxCountOfDiplom());
      editedLector.setFirstname(lector.getFirstname());
      editedLector.setMiddlename(lector.getMiddlename());
      editedLector.setLastname(lector.getLastname());
      return lectorRepository.save(editedLector);
    }

  @Override
  public Lector findByUsername(String username) {
    return lectorRepository.findByUsername(username).get();
  }


}
