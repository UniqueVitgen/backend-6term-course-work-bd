package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import org.docx4j.wml.*;

import java.io.File;
import java.util.List;

public interface WordService {
    File writeDiplomWork(DiplomWork diplomWork);
    P createCenteredParagraph(String text);
    P createParagraph(String text, RPr rpr);
    R createR(String text, RPr rPr);
    RPr createTitleRpr();
    U createUnderline();
    BooleanDefaultTrue createBold();
    Text createText(String text);
    P createParagraph(Text text, RPr rPr);
    P createParagraph(Text[] texts, RPr rPr);
    P createParagraph(R[] rs);
    P createParagraph(List<R> rs);
    P createParagraph(Object[] texts, RPr rPr);
    void addBorders(Tbl table);
}
