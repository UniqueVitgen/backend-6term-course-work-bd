package com.example.demo.rest;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
import com.example.demo.entity.sec.SEC;
import com.example.demo.repository.sec.SECRepository;
import com.example.demo.service.GroupService;
import com.example.demo.service.StudentService;
import com.example.demo.service.files.PDFGroupService;
import com.example.demo.service.files.WordGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/group")
@CrossOrigin(origins = {"http://localhost:4200"})
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PDFGroupService pdfGroupService;

    @Autowired
    private WordGroupService wordGroupService;

    @RequestMapping(value = "/get-by-specialization/{id-specialization}", method = RequestMethod.GET, consumes = "application/json; charset=UTF-8")
    public @ResponseBody
    List<Group> getGroupsBySpecialization(@PathVariable("id-specialization") Integer id) {
        List<Group> groups = groupService.findAllBySpecialization(id);
        return groups;
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Group> groupsList() {
        return groupService.findAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/group-{id}", method = RequestMethod.GET)
    public @ResponseBody
    Group group(@PathVariable("id") Integer id) {
        return groupService.get(id);
    }


    @RequestMapping(value = "/get-by-specializations", method = GET, consumes = "application/json; charset=UTF-8")
    public @ResponseBody
    List<Group> getSpecializationByFaculty(@RequestParam("ids") List<Integer> ids) {
        List<Group> groups = groupService.findAllBySpecializationIds(ids);
        return groups;
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Group save(@RequestBody Group group) {
//        group.setAmount_student(0);
        return groupService.save(group);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    Group edit(@RequestBody Group group) {
        return groupService.Edit(group);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            groupService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/pdf-{idDiplom}", method = RequestMethod.GET)
    public
    ResponseEntity<Resource>  savePDF(@PathVariable("idDiplom" ) Integer idDipom) {
        Group diplomWork = groupService.get(idDipom);
        File file = pdfGroupService.writePdf(diplomWork);

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Resource resource = null;
//        try {
//            resource = new UrlResource(file.getName());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);

    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/word-{idDiplom}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Resource>  getWord(@PathVariable("idDiplom") Integer id) {
        Group group = groupService.get(id);
        File file = wordGroupService.writeWord(group);

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @RequestMapping(value = "/find-all-student")
    public @ResponseBody
    List<Student>
    findStudentListByGroup(@RequestParam("idGroup") Integer id) {
        return studentService.findAllByGroupId(id);
    }

//    @RequestMapping(value="/find-all-by-sec", method = RequestMethod.GET)
//    public @ResponseBody
//    List<Group> findBySecs(@RequestParam("secIds") List<Integer> secIds) {
//        return groupService.findAllBySecIdList(secIds);
//    }
//
//    @RequestMapping(value="/find-all-by-sec-or-null", method = RequestMethod.GET)
//    public @ResponseBody
//    List<Group> findBySecsOrNull(@RequestParam("secIds") List<Integer> secIds) {
//        return groupService.findAllBySecIdListOrNull(secIds);
//    }
//
//    @RequestMapping(value="/find-all-by-where-sec-is-null", method = RequestMethod.GET)
//    public @ResponseBody
//    List<Group> findBySecIsNull() {
//        return groupService.findAllBySecIsNull();
//    }
}
