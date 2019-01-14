package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Faculty;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyService facultyService;

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> findAllByFaculty(Faculty faculty) {
        return departmentRepository.findAllByFaculty(faculty);
    }

    @Override
    public List<Department> findAllByFacultyId(Integer idFaculty) {
        Faculty faculty = facultyService.get(idFaculty);
        return findAllByFaculty(faculty);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department Edit(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department get(Integer id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        Department department = departmentRepository.findById(id).get();
        departmentRepository.delete(department);
    }
}
