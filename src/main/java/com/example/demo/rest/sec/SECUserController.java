package com.example.demo.rest.sec;

import com.example.demo.entity.sec.SECUser;
import com.example.demo.service.sec.SECUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/sec-user")
public class SECUserController {
    @Autowired
    private SECUserService secUserService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<SECUser> findAll() {
        return secUserService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    SECUser save(@RequestBody SECUser secUser) {
        return secUserService.save(secUser);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    SECUser edit(@RequestBody SECUser secUser) {
        return secUserService.edit(secUser);
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            secUserService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
