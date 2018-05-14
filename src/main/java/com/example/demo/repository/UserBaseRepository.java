package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserBaseRepository<T extends User>
        extends CrudRepository<T, Integer> {

    @Override
    Optional<T> findById(Integer integer);

    @Override
    <S extends T> S save(S s);
}
