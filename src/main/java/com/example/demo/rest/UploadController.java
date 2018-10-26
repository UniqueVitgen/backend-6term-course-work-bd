package com.example.demo.rest;


import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.ImageModel;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import com.example.demo.service.StorageService;
import com.google.gson.Gson;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.hibernate.Hibernate;

@CrossOrigin(origins = {"http://localhost:4200"})
@Controller
public class UploadController {

    @Autowired
    StorageService storageService;

    private static final Gson gson = new Gson();

    @Autowired
    ImageService imageService;

    List<String> files = new ArrayList<String>();
    //private static final Gson gson = new Gson();

    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        ImageModel imageModel = new ImageModel();
        imageModel.setFilename(file.getOriginalFilename());
//        imageModel.setContent(file.get);
        byte[] bytes = file.getBytes();
        imageModel.setContent(bytes);
        imageModel.setContentType(file.getContentType());
//        Date date = new Date()
        imageModel.setCreated(new Date());
        imageService.save(imageModel);
//        files = new ArrayList<String>();
//        List<ImageModel> images = imageService.getAll();
//        for (ImageModel image: images
//             ) {
//            files.add(image.getFilename());
//        }
        files = imageService.findDistrinctFilenames();
        //String message = "";
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(message));
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(gson.toJson(message));
        }
    }

    @GetMapping("/getallfiles")
    @ResponseBody
    public HttpEntity<List<String>> getListFiles(Model model) throws SQLException {
        files = imageService.findDistrinctFilenames();

        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
