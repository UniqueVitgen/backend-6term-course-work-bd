package com.example.demo.rest;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Status;
import com.example.demo.service.DiplomWorkService;
import com.example.demo.service.files.PDFService;
import com.example.demo.service.files.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/diplom-work")
public class DiplomWorkController {
    @Autowired
    private DiplomWorkService diplomWorkService;

    @Autowired
    private WordService wordService;

    @Autowired
    private PDFService pdfService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<DiplomWork> diplomWorkList() {
        return diplomWorkService.getAll();
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/get-all-by-leader-{idPerson}", method = RequestMethod.GET)
    public @ResponseBody
    List<DiplomWork> diplomWorkListByLeader(@PathVariable("idPerson") Integer idPerson) {
        return diplomWorkService.findAllByLeader(idPerson);
    }

    @RequestMapping(value="/edit-status-{idDiplom}", method = RequestMethod.PUT)
    DiplomWork editByStatus(@PathVariable("idDiplom") Integer idDiplom, @RequestBody Status status) {
        DiplomWork diplomWork = diplomWorkService.findById(idDiplom);
        diplomWork.setStatus(status);
        diplomWork = diplomWorkService.edit(diplomWork);
        return diplomWork;
    }

    @RequestMapping(value = "/edit-name-{idDiplom}", method = RequestMethod.GET)
    DiplomWork editByStatus(@PathVariable("idDiplom") Integer idDiplom, @RequestParam("name") String name) {
        DiplomWork diplomWork = diplomWorkService.findById(idDiplom);
        diplomWork.setName(name);
        diplomWork = diplomWorkService.edit(diplomWork);
        return diplomWork;
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/get-all-by-lector-{idPerson}", method = RequestMethod.GET)
    public @ResponseBody
    List<DiplomWork> diplomWorkListByLector(@PathVariable("idPerson") Integer idPerson) {
        return diplomWorkService.findAllByLector(idPerson);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/get-by-student-{idPerson}", method = RequestMethod.GET)
    public @ResponseBody
    DiplomWork diplomWorkByStudent(@PathVariable("idPerson") Integer idPerson) {
        return diplomWorkService.findByStudent(idPerson);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/diplom-work-{id}", method = RequestMethod.GET)
    public @ResponseBody
    DiplomWork diplomWork(@PathVariable("id") Integer id) {
        return diplomWorkService.findById(id);
    }


    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    DiplomWork save(@RequestBody DiplomWork diplomWork) {
        return diplomWorkService.save(diplomWork);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody
    DiplomWork edit(@RequestBody DiplomWork diplomWork) {
        return diplomWorkService.edit(diplomWork);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        try {
            diplomWorkService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/pdf-{idDiplom}", method = RequestMethod.GET)
    public
    ResponseEntity<Resource>  savePDF(@PathVariable("idDiplom" ) Integer idDipom) {
        DiplomWork diplomWork = diplomWorkService.findById(idDipom);
        File file = pdfService.writeDiplomWork(diplomWork);

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
        DiplomWork diplomWork = diplomWorkService.findById(id);
        File file = wordService.writeDiplomWork(diplomWork);

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
}
