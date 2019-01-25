package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.entity.form.UserUploadForm;
import com.example.demo.repository.LectorRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
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
    private ImageService imageService;



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
    public User save(User user) {
        User savedUser = this.findOne(user.getIdPerson());
        savedUser.setRoles(user.getRoles());
        return userRepository.save(savedUser);
    }

    @Override
    public User editOrganizerRole(User user, boolean isOrganizer) {
        User savedUser = this.findOne(user.getIdPerson());
        Role organizerRole = roleService.findByName("ORGANIZER");
        if (isOrganizer) {
            if(!savedUser.getRoles().contains(organizerRole)) {
                savedUser.getRoles().add(organizerRole);
            }
        } else {
            if(savedUser.getRoles().contains(organizerRole)) {
                savedUser.getRoles().remove(organizerRole);
            }
        }
        return userRepository.save(savedUser);
    }

    @Override
    public User uploadPhoto(Integer idPerson, String filename) {
        User user = userRepository.findById(idPerson).get();
        if(filename != null && !filename.equals("")) {
            ImageModel imageModel = imageService.findAllByFilename(filename).get(0);
            ImageModelHasUser imageModelHasNews = imageService.saveHasUser(imageModel);
            user.setImageModel(imageModelHasNews);
        }
//        User user = userRepository.findById(userUploadForm.getUser().getIdPerson()).get();
//        if(userUploadForm.getFilename() != null && !userUploadForm.getFilename() .equals("")) {
//            ImageModel imageModel = imageService.findAllByFilename(userUploadForm.getFilename()).get(0);
//            ImageModelHasUser imageModelHasNews = imageService.saveHasUser(imageModel);
//            user.setImageModel(imageModelHasNews);
//        }
        return userRepository.save(user);
    }

    @Override
    public Lector saveLectorUniversity(LectorUniversity lector) {
        User newUser = new User();
        Set<Role> roles = new HashSet<>();
//        Role role = new Role();
//        role.setName("LECTOR");
//        roles.add(role);
        roles.add(roleService.findByName("LECTOR"));
        newUser.setRoles(roles);
        newUser.setUsername(lector.getUsername());
        newUser.setFirstname(lector.getFirstname());
        newUser.setMiddlename(lector.getMiddlename());
        newUser.setLastname(lector.getLastname());
        newUser.setPassword(bcryptEncoder.encode(lector.getPassword()));
        //newUser = userRepository.save(newUser);


        LectorUniversity newLector = new LectorUniversity();
//        newLector.setFree(true);
        newLector.setMaxCountOfDiplom(7);
        newLector.setRoles(newUser.getRoles());
        newLector.setUsername(newUser.getUsername());
        newLector.setFirstname(newUser.getFirstname());
        newLector.setMiddlename(newUser.getMiddlename());
        newLector.setLastname(newUser.getLastname());
        newLector.setPassword(newUser.getPassword());
        newLector.setDepartment(lector.getDepartment());
        newLector.setTitle(lector.getTitle());
        newLector.setPost(lector.getPost());
        newLector.setDegree(lector.getDegree());

        //newLector.setTitle(lector.getTitle());
        return lectorRepository.save(newLector);
    }

    @Override
    public Lector saveLectorOrganizer(LectorOrganization lectorOrganization) {
        Set<Role> roles = new HashSet<>();
//        Role role = new Role();
//        role.setName("LECTOR");
//        roles.add(role);
        roles.add(roleService.findByName("LECTOR"));


        LectorOrganization newLector = new LectorOrganization();
//        newLector.setFree(true);
        newLector.setMaxCountOfDiplom(7);
        newLector.setRoles(roles);
        newLector.setUsername(lectorOrganization.getUsername());
        newLector.setFirstname(lectorOrganization.getFirstname());
        newLector.setMiddlename(lectorOrganization.getMiddlename());
        newLector.setLastname(lectorOrganization.getLastname());
        newLector.setPassword(bcryptEncoder.encode(lectorOrganization.getPassword()));
        newLector.setPostOrganization(lectorOrganization.getPostOrganization());
        newLector.setOrganization(lectorOrganization.getOrganization());
        //newLector.setTitle(lector.getTitle());
        return lectorRepository.save(newLector);
    }

    @Override
    public Student saveStudent(Student student) {
        Student newStudent = new Student();
        Set<Role> roles = new HashSet<>();
//        Role role = new Role();
//        role.setName("STUDENT");
//        roles.add(role);
        roles.add(roleService.findByName("STUDENT"));
        newStudent.setRoles(roles);
        newStudent.setUsername(student.getUsername());
        newStudent.setFirstname(student.getFirstname());
        newStudent.setMiddlename(student.getMiddlename());
        newStudent.setLastname(student.getLastname());
        newStudent.setPassword(bcryptEncoder.encode(student.getPassword()));
        Integer idPerson;
        newStudent.setGroup(student.getGroup());
        Student createdStudent =  studentRepository.save(newStudent);
        //User createdStudent = userRepository.findById(idPerson).get();
        //createdStudent.getRoles().add(roleService.findByName("STUDENT"));
        //createdStudent = userRepository.save(createdStudent);
        return (Student) createdStudent;
    }

    @Override
    public User saveOrganizer(User organizer) {
        Set<Role> roles = new HashSet<>();
//        Role role = new Role();
//        role.setName("ORGANIZER");
//        roles.add(role);
        roles.add(roleService.findByName("ORGANIZER"));
        organizer.setRoles(roles);
        organizer.setPassword(bcryptEncoder.encode(organizer.getPassword()));
        userRepository.save(organizer);
        return organizer;
    }

    @Override
    public User saveSecretary(User secretary) {
        Set<Role> roles = new HashSet<>();
//        Role role = new Role();
//        role.setName("SECRETARY_SEC");
//        roles.add(role);
        roles.add(roleService.findByName("SECRETARY_SEC"));
        secretary.setRoles(roles);
        secretary.setPassword(bcryptEncoder.encode(secretary.getPassword()));
        userRepository.save(secretary);
        return secretary;
    }

    @Override
    public String formatFullName(User user) {
        String fullname = "";
        if (!user.getLastname().equals("")) {
            fullname += user.getLastname();
        }
        if (!user.getFirstname().equals("")) {
            if (!fullname.equals("")) {
                fullname += " ";
            }
            fullname += user.getFirstname();
        }
        if (!user.getMiddlename().equals("")) {
            if (!fullname.equals("")) {
                fullname += " ";
            }
            fullname += user.getMiddlename();
        }
        return fullname;
    }

    @Override
    public String formatSurnameWithInitials(User user) {
        String fullname = "";
        if (!user.getLastname().equals("")) {
            fullname += user.getLastname();
        }
        if (!user.getFirstnameInitial().equals("")) {
            if (!fullname.equals("")) {
                fullname += " ";
            }
            fullname += user.getFirstnameInitial() + ".";
        }
        if (!user.getMiddlenameInitial().equals("")) {
            if (!fullname.equals("")) {
                fullname += " ";
            }
            fullname += user.getMiddlenameInitial() + ".";
        }
        return fullname;
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
