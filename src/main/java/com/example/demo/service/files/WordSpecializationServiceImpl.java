package com.example.demo.service.files;

import com.example.demo.entity.Group;
import com.example.demo.entity.LectorUniversity;
import com.example.demo.entity.Specialization;
import com.example.demo.entity.Student;
import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class WordSpecializationServiceImpl implements WordSpecializationService{
    ObjectFactory factory;
    WordprocessingMLPackage wordPackage;
    @Autowired
    private WordService wordService;

    public WordSpecializationServiceImpl() {
        factory = Context.getWmlObjectFactory();
        try {
            wordPackage = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File writeWord(Specialization specialization) {

        try {
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
            addTitle(mainDocumentPart, specialization);
            addSpecializationTitle(mainDocumentPart, specialization);
            for(Group group: specialization.getGroups()) {
                addGroupTitle(mainDocumentPart, group);
                addStudents(mainDocumentPart, group);
            }
            String pathname = WordServiceImpl.path + "специализация: " + specialization.getName() + ".docx";
            File exportFile = new File(pathname);

            wordPackage.save(exportFile);
            return exportFile;
        }
        catch(Docx4JException docExc) {

        }
        return null;
    }

    private void addTitle(MainDocumentPart mainDocumentPart, Specialization specialization) {
        int columnNumber = 2;
        int rowNumber = 2;
        Br br = factory.createBr(); // this Br element is used break the current and go for next line
        Text chunkNewLine = wordService.createText("\n");
        Text chunkTitleRu =  wordService.createText("МИНИСТЕРСТВО ОБРАЗОВАНИЯ РЕСПУБЛИКИ БЕЛАРУСЬ");
        Text chunkUniversityRu =  wordService.createText("БЕЛОРУССКИЙ НАЦИОНАЛЬНЫЙ ТЕХНИЧЕСКИЙ УНИВЕРСИТЕТ");
        Text chunkCityRu =  wordService.createText("г. Минск");
        Text chunkOrderRu =  wordService.createText("ПРИКАЗ");
        Text chunkTitleBy =  wordService.createText("МIНIСТЭРСТВА АДУКАЦЫI РЭСПУБЛIКI БЕЛАРУСЬ");
        Text chunkUniversityBy =  wordService.createText("БЕЛАРУСКI НАЦЫЯНАЛЬНЫ ТЭХНIЧНЫ УНИВЕРСIТЭТ");
        Text chunkOrderBy =  wordService.createText("ЗАГАД\n______№___");
        Text chunkCityBy =  wordService.createText("г.Мiнск");
        Text chunkDescription =  wordService.createText("Об утверждении тем, руководителей и назначении консультантов"
                + " и нормоконтролера дипломных проектов студентам " + specialization.getDepartment().getFaculty().getShortName()
                + " дневной формы получения образования");
        Object[] textUniversityRu = {chunkTitleRu, br, br, chunkUniversityRu, br, chunkOrderRu,
                br, br, chunkCityRu};
        Object[] textUniversityBy = {chunkTitleBy, br, br, chunkUniversityBy, br, chunkOrderBy,
                br, br, chunkCityRu};
        Text textDescription =  wordService.createText("Об утвержении тем, руководителей и назначении консультантов"
                + " и нормоконтролера дипломных проектов студентам " + specialization.getDepartment().getFaculty().getShortName()
                + " дневной формы получения образования");

        P paragraphRu =  wordService.createParagraph(textUniversityRu, null);
        P paragraphBy =  wordService.createParagraph(textUniversityBy, null);
        P paragraphDescription =  wordService.createParagraph(textDescription, null);
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
        wordService.addBorders(tbl);
        mainDocumentPart.getContent().add(tbl);
//        tdDescription.getContent().add(paragraph)
    }

    private void addSpecializationTitle(MainDocumentPart mainDocumentPart, Specialization specialization) {

        P p0 = factory.createP();
        R r0 = factory.createR();
        Text t0 = factory.createText();
//        P p1 = factory.createP();
//        R r1 = factory.createR();
//        Text t1 = factory.createText();
        P p2 = factory.createP();
        R r2 = factory.createR();
        Text t2 = factory.createText();

        t0.setValue("ПРИКАЗЫВАЮ:");
        r0.getContent().add(t0);
        p0.getContent().add(r0);

//        t1.setValue("\t\tУчебная группа " + group.getNumber() );
//        r1.getContent().add(t1);
//        p1.getContent().add(r1);

        t2.setValue("\t1. Утвердить ниже перечисленными студентами 4-го курса, обучающимся"
                + " " + "по специальности " + specialization.getCode()
                + " " + "«" + specialization.getName() + "»"
                + " " + specialization.getDepartment().getFaculty().getShortName()
                + " " + "в дневной форме получения образования, следующие темы дипломных проектов"
                + ", руководителей и  назначить консультантов дипломных проектов:");
        r2.getContent().add(t2);
        p2.getContent().add(r2);

        mainDocumentPart.getContent().add(p0);
        mainDocumentPart.getContent().add(p2);
//        mainDocumentPart.getContent().add(p1);
    }

    private void addGroupTitle(MainDocumentPart mainDocumentPart, Group group) {

//        P p0 = factory.createP();
//        R r0 = factory.createR();
//        Text t0 = factory.createText();
        P p1 = factory.createP();
        R r1 = factory.createR();
        Text t1 = factory.createText();
//        P p2 = factory.createP();
//        R r2 = factory.createR();
//        Text t2 = factory.createText();

//        t0.setValue("ПРИКАЗЫВАЮ:");
//        r0.getContent().add(t0);
//        p0.getContent().add(r0);

        t1.setValue("\t\tУчебная группа " + group.getNumber() );
        r1.getContent().add(t1);
        p1.getContent().add(r1);

//        t2.setValue("\t1. Утвердить ниже перечисленными студентами 4-го курса, обучающимся"
//                + "по специальности " + group.getSpecialization().getCode()
//                + " \"" + group.getSpecialization().getName() + "\""
//                + " " + group.getSpecialization().getDepartment().getFaculty().getName()
//                + " в дневной форме получения образования, следующие темы дипломных проектов"
//                + ", руководителей и  назначить консультантов дипломных проектов:");
//        r2.getContent().add(t2);
//        p2.getContent().add(r2);

//        mainDocumentPart.getContent().add(p0);
//        mainDocumentPart.getContent().add(p2);
        mainDocumentPart.getContent().add(p1);
    }

    private void addStudents(MainDocumentPart mainDocumentPart, Group group) {

        for(int i = 0; i < group.getStudents().size(); i++) {

            Student student = group.getStudents().get(i);
            if(student.getDiplomWork() != null) {
                Text text = factory.createText();
                Text textLeader = factory.createText();
                Text textStudent = factory.createText();
                P p = factory.createP();
                R r = factory.createR();
                textStudent.setValue("\t1." + (i + 1)
                        + " " + student.getLastname() + " " + student.getFirstname() + " " + student.getMiddlename() +
                        " - «" + student.getDiplomWork().getName() + "»."
                        + " Руководитель и консультант по компьютерному проектированию - ");
//                        + student.getDiplomWork().getLeader().get().getName() + " кафедры"
                if (student.getDiplomWork().getLeader() instanceof LectorUniversity) {
                    LectorUniversity lectorUniversity = (LectorUniversity) student.getDiplomWork().getLeader();
                    textLeader.setValue(
                            lectorUniversity.getPost().getName()
                                    + " " + "кафедры"
                                    + " " + "«" +lectorUniversity.getDepartment().getShortName() + "»"
                                    + " " + lectorUniversity.getDepartment().getFaculty().getShortName()
                                    + " " + lectorUniversity.getDepartment().getFaculty().getUniversity().getShortName()
                                    + " " + lectorUniversity.getDegree().getShortName() + ","
                                    + " " + lectorUniversity.getTitle().getName()
                                    + " " + lectorUniversity.getLastname()
                                    + " " + lectorUniversity.getFirstnameInitial() + "."
                                    + " " + lectorUniversity.getMiddlenameInitial() + ".");
                } else {
                    textLeader.setValue("");
                }
//                paragraphStudents.add(chunkStudent);
//                paragraphStudents.add(chunkLeader);

//                text.setValue("\t1." + (i + 1)
//                        + " " + student.getLastname() + " " + student.getFirstname() + " " + student.getMiddlename() +
//                        " - «" + student.getDiplomWork().getName() + "»."
//                        + " Руководитель и консультант по компьютерному проектированию - "
//                        //+ student.getDiplomWork().getLeader().getPost().getName() + " кафедры"
//                        + " " + student.getDiplomWork().getLeader().getLastname()
//                        + " " + student.getDiplomWork().getLeader().getFirstname()
//                        + " " + student.getDiplomWork().getLeader().getMiddlename() + ".");
//                r.getContent().add(text);
                r.getContent().add(textStudent);
                r.getContent().add(textLeader);
                p.getContent().add(r);
                mainDocumentPart.getContent().add(p);
            }
            //paragraphStudents.add(chunkStudent);
        }
    }
}
