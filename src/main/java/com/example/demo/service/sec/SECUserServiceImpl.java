package com.example.demo.service.sec;

import com.example.demo.entity.Lector;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.sec.SECUser;
import com.example.demo.repository.sec.SECUserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SECUserServiceImpl implements SECUserService {
    @Autowired
    private SECUserRepository secUserRepository;

    @Autowired
    private UserService<User, Integer> userService;

    @Autowired
    private RoleService roleService;

    @Override
    public List<SECUser> findAll() {
        return secUserRepository.findAll();
    }

    @Override
    public SECUser findById(Integer id) {
        return secUserRepository.findById(id).get();
    }

    @Override
    public SECUser save(SECUser secUser) {
        if (secUser.getRoles().get(0).getName().equals("SECRETARY")) {
            if(secUser.getUser() != null) {
                User user = userService.findOne(secUser.getUser().getIdPerson());
                Role role = roleService.findByName("SECRETARY_SEC");
                user.getRoles().add(role);
                secUser.setUser(userService.save(user));
            }
        }
        return secUserRepository.save(secUser);
    }

    @Override
    public SECUser edit(SECUser secUser) {
        return this.save(secUser);
    }

    @Override
    public String formatFullName(SECUser user) {
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
    public void delete(SECUser secUser) {
        if(secUser.getRoles().get(0).getName().equals("SECRETARY")) {
            Role role = roleService.findByName("SECRETARY_SEC");
            if (secUser.getUser().getRoles().contains(role)) {
                secUser.getUser().getRoles().remove(role);
                userService.save(secUser.getUser());
            }
        }
        secUserRepository.delete(secUser);
    }

    @Override
    public void delete(Integer id) {
        SECUser secUser  = this.findById(id);
        this.delete(secUser);
    }
}
