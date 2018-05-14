package com.example.demo.service;

import com.example.demo.entity.Lector;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import com.example.demo.repository.LectorRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl<T> implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectorRepository lectorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;



    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public  User findOne(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
        return user;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public Lector saveLector(Lector lector) {
        User newUser = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findOne(2));
        newUser.setRoles(roles);
        newUser.setUsername(lector.getUsername());
        newUser.setFirstname(lector.getFirstname());
        newUser.setMiddlename(lector.getMiddlename());
        newUser.setLastname(lector.getLastname());
        newUser.setPassword(bcryptEncoder.encode(lector.getPassword()));
        //newUser = userRepository.save(newUser);


        Lector newLector = new Lector();
        //Set<Role> roles = new HashSet<>();
        //roles.add(roleService.findOne(2));
        newLector.setRoles(newUser.getRoles());
        //newLector.setId_Person(newUser.getId_Person());
        newLector.setUsername(newUser.getUsername());
        newLector.setFirstname(newUser.getFirstname());
        newLector.setMiddlename(newUser.getMiddlename());
        newLector.setLastname(newUser.getLastname());
        newLector.setPassword(newUser.getPassword());

        newLector.setTitle(lector.getTitle());
        //Integer idPerson = lectorRepository.createLector(newLector.getLastname(), newLector.getFirstname(),newLector.getMiddlename(),newLector.getUsername(),newLector.getPassword(), newLector.getTitle(), newLector.getDegree(), newLector.getPost(), newLector.getDescription());
        //User createdLector = userRepository.findById(idPerson).get();
//        createdLector.getRoles().add(roleService.findOne(2));
//        createdLector = userRepository.save(createdLector);
//        return (Lector) createdLector;
         return lectorRepository.save(newLector);
    }

    @Override
    public Student saveStudent(Student student) {
        Student newStudent = new Student();
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findOne(3));
        newStudent.setRoles(roles);
        newStudent.setUsername(student.getUsername());
        newStudent.setFirstname(student.getFirstname());
        newStudent.setMiddlename(student.getMiddlename());
        newStudent.setLastname(student.getLastname());
        newStudent.setPassword(bcryptEncoder.encode(student.getPassword()));
        Integer idPerson;
        newStudent.setGroup(student.getGroup());
        idPerson = studentRepository.createStudent(student.getLastname(), student.getFirstname(), student.getMiddlename(),
                student.getUsername(), bcryptEncoder.encode(student.getPassword()), student.getGroup().getNumber());
        User createdStudent = userRepository.findById(idPerson).get();
        createdStudent.getRoles().add(roleService.findOne(3));
        createdStudent = userRepository.save(createdStudent);
        return (Student) createdStudent;
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userId);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<GrantedAuthority> getAuthority(User user) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
//        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }
}
