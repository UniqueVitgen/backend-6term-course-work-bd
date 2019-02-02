package com.example.demo.service.files;

import com.example.demo.entity.*;
import com.example.demo.entity.sec.SEC;
import com.example.demo.entity.sec.SECRole;
import com.example.demo.entity.sec.SECUser;
import com.example.demo.service.UserService;
import com.example.demo.service.sec.SECRoleService;
import com.example.demo.service.sec.SECUserService;
import com.itextpdf.text.Paragraph;
import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordDepartmentServiceImpl implements WordDepartmentService {
    ObjectFactory factory;
    WordprocessingMLPackage wordPackage;
    @Autowired
    private WordService wordService;
    @Autowired
    private UserService<User, Integer> userService;
    @Autowired
    private SECUserService secUserService;
    @Autowired
    private SECRoleService secRoleService;

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
        try {
            String pathname = WordServiceImpl.path + "Кафедра: " + department.getName() + ".docx";
            File exportFile = new File(pathname);
            addTitle(mainDocumentPart, department);
            addTitle2(mainDocumentPart);
            Integer i = 0;
            for(SEC sec: department.getSecs()) {
                addSECInfo(mainDocumentPart, sec);
                addSECTitle(mainDocumentPart, i + 1);
                addSECUsers(mainDocumentPart, sec);
                i++;
            }
            wordPackage.save(exportFile);
            return exportFile;
        }
        catch(Docx4JException docExc) {

        }
        return null;
    }

    private void addTitle(MainDocumentPart mainDocumentPart, Department department) {
        int columnNumber = 2;
        int rowNumber = 5;
        Br br = factory.createBr(); // this Br element is used break the current and go for next line
        R university = wordService.createR(department.getFaculty().getUniversity().getName(), null);
        R decanLabel = wordService.createR("Декану ФИТР Трофименко Е.Е.", null);
        R university2 = wordService.createR(department.getFaculty().getName(), null);
        R departmentChunk = wordService.createR("Кафедра \"" + department.getName() + "\"", null);
//        R decanLabel2 = wordService.createR("Трофименко Е.Е.", null);
        R[] universityCol = {university};
        R[] decanLabelCol = {decanLabel};
        R[] university2Col = {university2};
        R[] departmentCol = {departmentChunk};
//        R[] decanLabel2Col = {decanLabel2};
        P paragraphUniversity =  wordService.createParagraph(universityCol);
        P paragraphDecanLabel =  wordService.createParagraph(decanLabelCol);
        P paragraphUniversity2 =  wordService.createParagraph(university2Col);
        P paragraphDepartment = wordService.createParagraph(departmentCol);
//        P paragraphDecanLabel2 =  wordService.createParagraph(decanLabel2Col);
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        Tbl tbl = TblFactory.createTable(rowNumber, columnNumber, writableWidthTwips/columnNumber);
        List<Object> rows = tbl.getContent();
        Object row = rows.get(0);
        Object row2 = rows.get(2);
        Object row3 = rows.get(4);
//        Object row4 = rows.get(3);
//        Object row5 = rows.get(4);
//        Object row6 = rows.get(5);
//        Object row7 = rows.get(6);
        Tr tr = (Tr) row;
        Tr tr1 = (Tr) row2;
        Tr tr2 = (Tr) row3;
        List<Object> cells = tr.getContent();
        List<Object> cells2 = tr1.getContent();
        List<Object> cells3 = tr2.getContent();
        Tc tdUniversity = (Tc) cells.get(0);
        Tc tdDecanLabel = (Tc) cells.get(1);
        Tc tdUniversity2 = (Tc) cells2.get(0);
        Tc tdDecanLabel2 = (Tc) cells2.get(1);
        Tc tdFaculty = (Tc) cells3.get(0);
        tdUniversity.getContent().add(paragraphUniversity);
        tdDecanLabel.getContent().add(paragraphDecanLabel);
        tdUniversity2.getContent().add(paragraphUniversity2);
        tdFaculty.getContent().add(paragraphDepartment);
//        tdDecanLabel2.getContent().add(paragraphDecanLabel2);
        wordService.addBorders(tbl);
        mainDocumentPart.getContent().add(tbl);
//        tdDescription.getContent().add(paragraph)
    }

    private void addTitle2(MainDocumentPart mainDocumentPart) {
        RPr rPr = new RPr();
        rPr.setU(wordService.createUnderline());

        R r = wordService.createR("ДОКЛАДНАЯ ЗАПИСКА", null);
        R NChunk = wordService.createR("___________ № _______.", null);
        R[] rs = {r};
        R[] rs1 = {NChunk};
        P paragraphTitle = wordService.createParagraph(rs);
        P paragraphN = wordService.createParagraph(rs1);
        mainDocumentPart.getContent().add(paragraphTitle);
        mainDocumentPart.getContent().add(paragraphN);
    }

    private void addSECInfo(MainDocumentPart mainDocumentPart, SEC sec) {
        R rTitle = wordService.createR("Для проведения в 2018-2019 гг. " +
                "защиты дипломных проектов по специальностям ", null);
//        R[] rs = {rTitle};
        List<R> rList = new ArrayList<R>();
        rList.add(rTitle);
        List<Specialization> specializations = new ArrayList<>(sec.getSpecializations());
        for(Integer i = 0; i < specializations.size(); i++) {
            Specialization specialization = specializations.get(i);
            String str = "";
            if (i == 0) {
                str += "и ";
            }
            str +=specialization.getCode() +
                    " " + "«" + specialization.getName() + "»";
            R rSpecialization = wordService.createR(str, null);
            rList.add(rSpecialization);
        }
        P paragraphSpecializations = wordService.createParagraph(rList);
        mainDocumentPart.getContent().add(paragraphSpecializations);
    }

    private void addSECTitle(MainDocumentPart mainDocumentPart, Integer count) {
        PPr paragraphProperties = factory.createPPr();
        Jc justification = factory.createJc();
        justification.setVal(JcEnumeration.CENTER);
        paragraphProperties.setJc(justification);
        RPr rPr = new RPr();
        rPr.setB(wordService.createBold());
        rPr.setU(wordService.createUnderline());
        P centeredParagraph = wordService.createParagraph("ГЭК-" + count, rPr);
        centeredParagraph.setPPr(paragraphProperties);
        mainDocumentPart.getContent().add(centeredParagraph);
    }

    private void addSECUsers(MainDocumentPart mainDocumentPart, SEC sec) {
        int columnNumber = 2;
        int rowNumber = sec.getUsers().size();
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        Tbl tbl = TblFactory.createTable(rowNumber, columnNumber, writableWidthTwips/columnNumber);
        List<Object> rows = tbl.getContent();
        List<SECUser> secUsers = new ArrayList<>(sec.getUsers());
//        SECRole secretaryRole = secRoleService.findByName("SECRETARY");
//        SECRole chairmanRole
        for(Integer i = 0; i < secUsers.size(); i++) {
            SECUser secUser = secUsers.get(i);
            User user = secUser.getUser();
            String userValue = "";
            if (user instanceof LectorUniversity) {
                LectorUniversity lectorUniversity = (LectorUniversity) user;
                userValue += lectorUniversity.getPost().getName() +
                        " " + "Кафедры" + " " + "«" + lectorUniversity.getDepartment().getName() + "»";
            }
//            if (secUser.getRoles().contains())
            R lectorLabel = wordService.createR(secUserService.formatFullName(secUser), null);
            R lectorValue = wordService.createR(userValue, null);
            R[] leactorLabelCol = {lectorLabel};
            R[] lectorValueCol = {lectorValue};
            P paragraphLabel = wordService.createParagraph(leactorLabelCol);
            P paragraphValue = wordService.createParagraph(lectorValueCol);
            Tr userRow = (Tr) rows.get(i);
            List<Object> cells = userRow.getContent();
            Tc labelCol = (Tc) cells.get(0);
            Tc valueCol = (Tc) cells.get(1);
            labelCol.getContent().add(paragraphLabel);
            valueCol.getContent().add(paragraphValue);
        }
        mainDocumentPart.getContent().add(tbl);
//        R
    }
}
