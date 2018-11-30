package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Group;

import java.io.File;

public interface PDFGroupService {
    File writePdf(Group group);
}
