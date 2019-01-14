package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordGroupServiceImpl implements WordGroupService {
    ObjectFactory factory;
    WordprocessingMLPackage wordPackage;

    public WordGroupServiceImpl() {
        factory = Context.getWmlObjectFactory();
        try {
            wordPackage = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File writeWord(Group group) {

        try {
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
            addTitle(mainDocumentPart, group);
            addGroupTitle(mainDocumentPart, group);
            addStudents(mainDocumentPart, group);
            String pathname = WordServiceImpl.path + "Диплом работа: " + group.getNumber() + ".docx";
            File exportFile = new File(pathname);

            wordPackage.save(exportFile);
            return exportFile;
//
//            RPr rpr = factory.createRPr();
//            BooleanDefaultTrue b = new BooleanDefaultTrue();
//            rpr.setB(b);
//            rpr.setI(b);
//            rpr.setCaps(b);
//            Color green = factory.createColor();
//            green.setVal("green");
//            rpr.setColor(green);
//            r.setRPr(rpr);
//
//            r.getContent().add(t);
//            p.getContent().add(r);
//            int writableWidthTwips = wordPackage.getDocumentModel()
//                    .getSections().get(0).getPageDimensions().getWritableWidthTwips();
//            int columnNumber = 3;
//            Tbl tbl = TblFactory.createTable(3, 3, writableWidthTwips/columnNumber);
//            List<Object> rows = tbl.getContent();
//            for (Object row : rows) {
//                Tr tr = (Tr) row;
//                List<Object> cells = tr.getContent();
//                for(Object cell : cells) {
//                    Tc td = (Tc) cell;
//                    td.getContent().add(p);
//                }
//            }
//            mainDocumentPart.getContent().add(tbl);
////            Text text = factory.createText();
////            text.setValue();
////            mainDocumentPart.getContent().add(text);
//
//            mainDocumentPart.getContent().add(p);
//            File exportFile = new File("welcome.docx");
//            wordPackage.save(exportFile);
        }
        catch(Docx4JException docExc) {

        }
        return null;
    }

    private void addTitle(MainDocumentPart mainDocumentPart, Group group) {
        int columnNumber = 2;
        int rowNumber = 2;
        Br br = factory.createBr(); // this Br element is used break the current and go for next line
        Text chunkNewLine = createText("\n");
        Text chunkTitleRu = createText("МИНИСТЕРСТВО ОБРАЗОВАНИЯ РЕСПУБЛИКИ БЕЛАРУСЬ");
        Text chunkUniversityRu = createText("БЕЛОРУССКИЙ НАЦИОНАЛЬНЫЙ ТЕХНИЧЕСКИЙ УНИВЕРСИТЕТ");
        Text chunkCityRu = createText("г. Минск");
        Text chunkOrderRu = createText("ПРИКАЗ");
        Text chunkTitleBy = createText("МIНIСТЭРСТВА АДУКАЦЫI РЭСПУБЛIКI БЕЛАРУСЬ");
        Text chunkUniversityBy = createText("БЕЛАРУСКI НАЦЫЯНАЛЬНЫ ТЭХНIЧНЫ УНИВЕРСIТЭТ");
        Text chunkOrderBy = createText("ЗАГАД\n______№___");
        Text chunkCityBy = createText("г.Мiнск");
        Text chunkDescription = createText("Об утвержении тем, руководителей и назначении консультантов"
                + " и нормоконтролера дипломных проектов студентам " + group.getSpecialization().getDepartment().getFaculty().getShortName()
                + " дневной формы получения образования");
        Object[] textUniversityRu = {chunkTitleRu, br, br, chunkUniversityRu, br, chunkOrderRu,
                br, br, chunkCityRu};
        Object[] textUniversityBy = {chunkTitleBy, br, br, chunkUniversityBy, br, chunkOrderBy,
                br, br, chunkCityRu};
        Text textDescription = createText("Об утвержении тем, руководителей и назначении консультантов"
                + " и нормоконтролера дипломных проектов студентам " + group.getSpecialization().getDepartment().getFaculty().getShortName()
                + " дневной формы получения образования");

        P paragraphRu = createParagraph(textUniversityRu);
        P paragraphBy = createParagraph(textUniversityBy);
        P paragraphDescription = createParagraph(textDescription);
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        Tbl tbl = TblFactory.createTable(rowNumber, columnNumber, writableWidthTwips/columnNumber);
        List<Object> rows = tbl.getContent();
        Object row = rows.get(0);
        Object row2 = rows.get(1);
        Tr tr = (Tr) row;
        Tr tr1 = (Tr) row2;
        List<Object> cells = tr.getContent();
        List<Object> cells2 = tr1.getContent();
        Tc tdUniversityRu = (Tc) cells.get(1);
        Tc tdUniversityBy = (Tc) cells.get(0);
        Tc tdDescription = (Tc) cells2.get(0);
        tdUniversityBy.getContent().add(paragraphBy);
        tdUniversityRu.getContent().add(paragraphRu);
        tdDescription.getContent().add(paragraphDescription);
        addBorders(tbl);
        mainDocumentPart.getContent().add(tbl);
//        tdDescription.getContent().add(paragraph)
    }

    private void addGroupTitle(MainDocumentPart mainDocumentPart, Group group) {

        P p0 = factory.createP();
        R r0 = factory.createR();
        Text t0 = factory.createText();
        P p1 = factory.createP();
        R r1 = factory.createR();
        Text t1 = factory.createText();
        P p2 = factory.createP();
        R r2 = factory.createR();
        Text t2 = factory.createText();

        t0.setValue("ПРИКАЗЫВАЮ:");
        r0.getContent().add(t0);
        p0.getContent().add(r0);

        t1.setValue("\t\tУчебная группа " + group.getNumber() );
        r1.getContent().add(t1);
        p1.getContent().add(r1);

        t2.setValue("\t1. Утвердить ниже перечисленными студентами 4-го курса, обучающимся"
                + "по специальности " + group.getSpecialization().getCode()
                + " \"" + group.getSpecialization().getName() + "\""
                + " " + group.getSpecialization().getDepartment().getFaculty().getName()
                + " в дневной форме получения образования, следующие темы дипломных проектов"
                + ", руководителей и  назначить консультантов дипломных проектов:");
        r2.getContent().add(t2);
        p2.getContent().add(r2);

        mainDocumentPart.getContent().add(p0);
        mainDocumentPart.getContent().add(p2);
        mainDocumentPart.getContent().add(p1);
    }

    private void addStudents(MainDocumentPart mainDocumentPart, Group group) {

        for(int i = 0; i < group.getStudents().size(); i++) {
            Student student = group.getStudents().get(i);
            Text text = factory.createText();
            P p = factory.createP();
            R r = factory.createR();

            text.setValue("\t1." + (i + 1)
                    + " " + student.getLastname() + " " + student.getFirstname() + " " + student.getMiddlename() +
                    " - \"" + student.getDiplomWork().getName() + "\"."
                    + " Руководитель и консультант по компьютерному проектированию - "
                    //+ student.getDiplomWork().getLeader().getPost().getName() + " кафедры"
                    + " " + student.getDiplomWork().getLeader().getLastname()
                    + " " + student.getDiplomWork().getLeader().getFirstname()
                    + " " + student.getDiplomWork().getLeader().getMiddlename() + ".");
            r.getContent().add(text);
            p.getContent().add(r);
            mainDocumentPart.getContent().add(p);
            //paragraphStudents.add(chunkStudent);
        }
    }

    private Text createText(String text) {
        Text t = factory.createText();
        t.setValue(text);
        return t;
    }

    private P createParagraph(Text text) {

        P p = factory.createP();
        R r = factory.createR();
        r.getContent().add(text);
        p.getContent().add(r);
        return p;
    }

    private P createParagraph(Text[] texts) {
        P p = factory.createP();
        R r = factory.createR();
        for(Text text: texts) {
            r.getContent().add(text);
        }
        p.getContent().add(r);
        return p;
    }

    private P createParagraph(Object[] texts) {
        P p = factory.createP();

        //centering the paragraph
        R r = factory.createR();
        PPr paragraphProperties = factory.createPPr();
        Jc justification = factory.createJc();
        justification.setVal(JcEnumeration.CENTER);
        paragraphProperties.setJc(justification);
        p.setPPr(paragraphProperties);
        for(Object text: texts) {
            r.getContent().add(text);
        }
        p.getContent().add(r);
        return p;
    }

    private P createParagraph(String text, RPr rpr) {

        
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        if(rpr == null) {
            rpr = new RPr();
        }
        HpsMeasure sz = new HpsMeasure();
        sz.setVal(new BigInteger("12"));
        rpr.setSz(sz);
        rpr.setSzCs(sz);
        r.setRPr(rpr);
        r.getContent().add(t);
        p.getContent().add(r);
        return p;
    }
    public  void addBorders(Tbl table)
    {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("0"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.NIL);

        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }
}
