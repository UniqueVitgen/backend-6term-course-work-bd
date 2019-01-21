package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import org.docx4j.wml.*;

import java.io.File;

public interface WordService {
    File writeDiplomWork(DiplomWork diplomWork);
    P createCenteredParagraph(String text);
    P createParagraph(String text, RPr rpr);
    RPr createTitleRpr();
    Text createText(String text);
    P createParagraph(Text text, RPr rPr);
    P createParagraph(Text[] texts, RPr rPr);
    P createParagraph(R[] rs);
    P createParagraph(Object[] texts, RPr rPr);
    void addBorders(Tbl table);
}
