package com.example.demo.service.files;

import com.example.demo.entity.Department;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class WordDepartmentServiceImpl implements WordDepartmentService {
    ObjectFactory factory;
    WordprocessingMLPackage wordPackage;
    @Autowired
    private WordService wordService;

    public WordDepartmentServiceImpl() {
        factory = Context.getWmlObjectFactory();
        try {
            wordPackage = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File writeWord(Department department) {
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        return null;
    }
}
