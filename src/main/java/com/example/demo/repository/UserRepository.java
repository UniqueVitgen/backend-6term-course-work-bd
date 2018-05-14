package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends UserBaseRepository<User>  {
    User findByUsername (String username);

    @Override
    List<User> findAll();
}
