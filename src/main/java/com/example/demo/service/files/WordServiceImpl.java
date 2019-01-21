package com.example.demo.service.files;

import com.example.demo.entity.*;
import com.example.demo.service.UserService;
import com.example.demo.service.workers.StringWorkerService;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private UserService<Student, Integer> userService;
    @Autowired
    private StringWorkerService stringWorkerService;
    ObjectFactory factory;
    WordprocessingMLPackage wordPackage;
    final String[] columns = {"Название", "Комментарий", "Дата начала", "Дата окончания", "Процент"};
    static String path = "src/main/resources/";
    static SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");

    public WordServiceImpl() {
        factory = Context.getWmlObjectFactory();
        try {
            wordPackage = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
    @Override
    public File writeDiplomWork(DiplomWork diplomWork) {
        try {
            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
            int columnNumber = 5;
            int writableWidthTwips = wordPackage.getDocumentModel()
                    .getSections().get(0).getPageDimensions().getWritableWidthTwips();
            Tbl tbl = TblFactory.createTable(diplomWork.getPercentages().size() + 1, columnNumber, writableWidthTwips/columnNumber);
            List<Object> rows = tbl.getContent();
            ObjectFactory factory = Context.getWmlObjectFactory();
            addTitle(mainDocumentPart, diplomWork.getStudent());
            addEmptyLine(mainDocumentPart,2);
            addStatementTitle(mainDocumentPart);
            addEmptyLine(mainDocumentPart,1);
            addName(mainDocumentPart, diplomWork);
            addEmptyLine(mainDocumentPart,1);
            addLeader(mainDocumentPart, diplomWork.getLeader());
            if(diplomWork.getTeoConsultor() != null) {
                addEmptyLine(mainDocumentPart,1);
                addTeoConsultor(mainDocumentPart, diplomWork.getTeoConsultor());
            }
            addEmptyLine(mainDocumentPart,3);
            addDate(mainDocumentPart);
            addAggreeTeoConsultor(mainDocumentPart);
            addAggreeLeader(mainDocumentPart);
            String pathname = WordServiceImpl.path + "Диплом работа: " + diplomWork.getName() + ".docx";
            File exportFile = new File(pathname);

            wordPackage.save(exportFile);
            return exportFile;
        }
        catch(Docx4JException docExc) {

        }
        return null;
    }

    public P createCenteredParagraph(String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();

        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);


        RPr rpr = factory.createRPr();

        PPr paragraphProperties = factory.createPPr();
        Jc justification = factory.createJc();
        justification.setVal(JcEnumeration.CENTER);
        paragraphProperties.setJc(justification);

        p.setPPr(paragraphProperties);
        r.getContent().add(t);
        p.getContent().add(r);
    //        Color green = factory.createColor();
//        green.setVal("green");
//        rpr.setColor(green);
        return p;
    }

    private void addTitle(MainDocumentPart mainDocumentPart, Student student) {
        int columnNumber = 2;
        int rowNumber = 1;
        Br br = factory.createBr(); // this Br element is used break the current and go for next line
        RPr rPr = new RPr();
        rPr.setU(createUnderline());
        Text chunkNewLine = createText("\n");
        R rBr = factory.createR();
        rBr.getContent().add(br);
        PPr paragraphProperties = factory.createPPr();
        Jc justification = factory.createJc();
        justification.setVal(JcEnumeration.BOTH);
        paragraphProperties.setJc(justification);

//        PPrBase.Spacing sp = factory.createPPrBaseSpacing();
//        sp.setAfter(BigInteger.ZERO);
//        sp.setBefore(BigInteger.ZERO);
//        sp.setLine(BigInteger.valueOf(482));
//        sp.setLineRule(STLineSpacingRule.AUTO);
//        paragraphProperties.setSpacing(sp);

        R chunkHead =  createR("Заведующему кафедрой " + student.getGroup().getSpecialization().getDepartment()
                .getShortName(), null);
        R chunkSpace = createR("                          ", rPr);
        R chunkSpecializationLabel =  createR("Обучающегося по специальности", null);
        R chunkSpecialization = createR(student.getGroup().getSpecialization().getCode()
        + " " + "«" +  student.getGroup().getSpecialization().getName() + "»", rPr);
        R chunkFormEducatuon =  createR("   дневной   ",  rPr);
        R chunkFormEducatuonLabel =  createR("формы получения образования", null);
        R chunkGroupLabel = createR("группы", null);
        R chunkGroup = createR(student.getGroup().getNumber(), rPr);
        R chunkStudent = createR(userService.formatFullName(student), rPr);
//        R chunk
        R[] textUniversityRu = {chunkHead, chunkSpace, chunkSpecializationLabel, chunkSpecialization, chunkSpace,
        chunkFormEducatuon, chunkFormEducatuonLabel, chunkGroupLabel, chunkGroup, chunkSpace, chunkStudent};
//        Object[] textUniversityBy = {chunkTitleBy, br, br, chunkUniversityBy, br, chunkOrderBy,
//                br, br, chunkCityRu};
        Text textDescription =  createText("Об утвержении тем, руководителей и назначении консультантов"
                + " и нормоконтролера дипломных проектов студентам " + student.getGroup().getSpecialization().getDepartment().getFaculty().getShortName()
                + " дневной формы получения образования");
        P paragraphRu =  createParagraph(textUniversityRu);
        paragraphRu.setPPr(paragraphProperties);
//        P paragraphBy =  createParagraph(textUniversityBy, null);
        P paragraphDescription =  createParagraph(textDescription, null);
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        Tbl tbl = TblFactory.createTable(rowNumber, columnNumber, writableWidthTwips/columnNumber);
        List<Object> rows = tbl.getContent();
        Object row = rows.get(0);
        Tr tr = (Tr) row;
        List<Object> cells = tr.getContent();
        Tc tdUniversityRu = (Tc) cells.get(1);
        Tc tdUniversityBy = (Tc) cells.get(0);
        tdUniversityBy.getContent().add(createParagraph("", null));
        tdUniversityRu.getContent().add(paragraphRu);
        addBorders(tbl);
        mainDocumentPart.getContent().add(tbl);
//        tdDescription.getContent().add(paragraph)
    }

    private void addStatementTitle(MainDocumentPart mainDocumentPart) {;
        PPr paragraphProperties = factory.createPPr();
        Jc justification = factory.createJc();
        justification.setVal(JcEnumeration.CENTER);
        paragraphProperties.setJc(justification);
        P paragraph = createParagraph("Заявление", null);
        paragraph.setPPr(paragraphProperties);
        mainDocumentPart.getContent().add(paragraph);
    }

    private void addEmptyLine(MainDocumentPart mainDocumentPart, int count) {
        for (int z = 0; z < count; z++) {
            P paragraph = createParagraph("", null);
            mainDocumentPart.getContent().add(paragraph);
        }
    }

    private void addEmptyLineWithUnderline(MainDocumentPart mainDocumentPart, int count) {
        RPr rPr = new RPr();
        rPr.setU(createUnderline());
        String str = "";
        for(int z = 0; z < count; z++) {
            for (int i = 0; i < 128; i++) {
                str += " ";
            }
            P paragraph = createParagraph(str, rPr);
            mainDocumentPart.getContent().add(paragraph);
        }
    }

    private void addName(MainDocumentPart mainDocumentPart, DiplomWork diplomWork) {
        RPr rPr = new RPr();
        rPr.setU(createUnderline());
        Text chunkNewLine = createText("\n");
        R rBr = factory.createR();
        R whiteSpace = createR(" ", null);
        R chunkSpace = createR("                          ", rPr);
        R chunkNameLabel = createR("Прошу утвердить тему дипломного проекта:", null);
        R chunkName = createR(diplomWork.getName(), rPr);
        R[] texts = {chunkNameLabel, whiteSpace, chunkName,rBr, chunkSpace, rBr, chunkSpace, rBr, chunkSpace};
        P paragraph = createParagraph(texts);
        mainDocumentPart.getContent().add(paragraph);
        addEmptyLineWithUnderline(mainDocumentPart, 2);
    }

    private void addLeader(MainDocumentPart mainDocumentPart, Lector lector) {
        RPr rPr = new RPr();
        rPr.setU(createUnderline());
        R chunkLeaderLabel = createR("Руководитель дипломного проекта", null);
        R chunkSpace = createR("                          ", rPr);
        R whiteSpace = createR(" ", null);
        String leaderString = "";
        if(lector instanceof LectorUniversity) {
           LectorUniversity lectorUniversity = (LectorUniversity) lector;
           leaderString += lectorUniversity.getPost().getName() + "," + " " + lectorUniversity.getDegree().getShortName() + ",";
        }
        R chunkLeader = createR(leaderString + " " + userService.formatSurnameWithInitials(lector), rPr);
        R[] texts = {chunkLeaderLabel, whiteSpace, chunkLeader, chunkSpace};
        P paragraph = createParagraph(texts);
        mainDocumentPart.getContent().add(paragraph);
        addEmptyLineWithUnderline(mainDocumentPart, 1);
        addLeaderDescription(mainDocumentPart);
    }

    private void addDate(MainDocumentPart mainDocumentPart) {
        RPr rPr = new RPr();
        int dateRelation = 2, monthRelation = 6, yearRelation = 4, spaceBetweenRelation = 6,
                signatureRelation = 6, whiteSpaceRelation = 1;
        int step = 4;
        rPr.setU(createUnderline());
        R chunkDate = createR(stringWorkerService.createEmptyString(dateRelation * step), rPr);
        R chunkMonth = createR(stringWorkerService.createEmptyString(monthRelation * step), rPr);
        R chunkYear = createR(stringWorkerService.createEmptyString(yearRelation * step), rPr);
        R chunkSignature = createR(stringWorkerService.createEmptyString(signatureRelation * step), rPr);
        R spaceBetweenDateAndSignature = createR(stringWorkerService.createEmptyString(spaceBetweenRelation * step), null);
        R whiteSpace = createR(stringWorkerService.createEmptyString(whiteSpaceRelation * step), null);
        R[] texts = {whiteSpace, chunkDate, whiteSpace, chunkMonth, whiteSpace, chunkYear,
                spaceBetweenDateAndSignature, chunkSignature};
        P paragraph = createParagraph(texts);
        mainDocumentPart.addObject(paragraph);
        addDateDescription(mainDocumentPart);
    }

    private void addAggreeTeoConsultor(MainDocumentPart mainDocumentPart) {
        RPr rPr = new RPr();
        int dateRelation = 2, monthRelation = 6, yearRelation = 4, spaceBetweenRelation = 3,
                signatureRelation = 6, whiteSpaceRelation = 1;
        int step = 4;
        rPr.setU(createUnderline());
        R chunkAgreeLabel = createR("«Согласен»", null);
        R chunkDate = createR(stringWorkerService.createEmptyString(dateRelation * step), rPr);
        R chunkMonth = createR(stringWorkerService.createEmptyString(monthRelation * step), rPr);
        R chunkYear = createR(stringWorkerService.createEmptyString(yearRelation * step), rPr);
        R chunkSignature = createR(stringWorkerService.createEmptyString(signatureRelation * step), rPr);
        R spaceBetweenDateAndSignature = createR(stringWorkerService.createEmptyString(spaceBetweenRelation * step), rPr);
        R whiteSpace = createR(stringWorkerService.createEmptyString(whiteSpaceRelation * step), null);
        R leftArrow = createR("«", null);
        R rightArrow = createR("»", null);
        R[] texts = {chunkAgreeLabel, whiteSpace, leftArrow,whiteSpace, chunkDate,whiteSpace, rightArrow,
                whiteSpace, chunkMonth, chunkYear, spaceBetweenDateAndSignature,
                chunkSignature};
        P paragraph = createParagraph(texts);
        mainDocumentPart.addObject(paragraph);
        addTeoConsultorDescription(mainDocumentPart);
    }

    private void addAggreeLeader(MainDocumentPart mainDocumentPart) {
        RPr rPr = new RPr();
        int dateRelation = 2, monthRelation = 6, yearRelation = 4, spaceBetweenRelation = 3,
                signatureRelation = 6, whiteSpaceRelation = 1;
        int step = 4;
        rPr.setU(createUnderline());
        R chunkAgreeLabel = createR("«Согласен»", null);
        R chunkDate = createR(stringWorkerService.createEmptyString(dateRelation * step), rPr);
        R chunkMonth = createR(stringWorkerService.createEmptyString(monthRelation * step), rPr);
        R chunkYear = createR(stringWorkerService.createEmptyString(yearRelation * step), rPr);
        R chunkSignature = createR(stringWorkerService.createEmptyString(signatureRelation * step), rPr);
        R spaceBetweenDateAndSignature = createR(stringWorkerService.createEmptyString(spaceBetweenRelation * step), rPr);
        R whiteSpace = createR(stringWorkerService.createEmptyString(whiteSpaceRelation * step), null);
        R leftArrow = createR("«", null);
        R rightArrow = createR("»", null);
        R[] texts = {chunkAgreeLabel, whiteSpace, leftArrow,whiteSpace, chunkDate,whiteSpace, rightArrow,
                whiteSpace, chunkMonth, chunkYear, spaceBetweenDateAndSignature,
                chunkSignature};
        P paragraph = createParagraph(texts);
        mainDocumentPart.addObject(paragraph);
        addLeaderAgreeDescription(mainDocumentPart);
    }

    private void addDateDescription(MainDocumentPart mainDocumentPart) {
        RPr rpr = new RPr();
        HpsMeasure sz = new HpsMeasure();
        sz.setVal(new BigInteger("12"));
        rpr.setSz(sz);
        rpr.setSzCs(sz);
//        r.setRPr(rpr);

        int dateRelation = 2, monthRelation = 6, yearRelation = 4, spaceBetweenRelation = 6,
                signatureRelation = 6, whiteSpaceRelation = 1;
        int step = 4;
        R chunkDate = createR(stringWorkerService.createEmptyString(dateRelation * step), null);
        R chunkMonth = createR(stringWorkerService.createEmptyString(monthRelation * step), null);
        R chunkYear = createR(stringWorkerService.createEmptyString(yearRelation * step), null);
        R chunkSignature = createR(stringWorkerService.createEmptyString(signatureRelation * step - 10) + "(Подпись)     ", rpr);
        R spaceBetweenDateAndSignature = createR(stringWorkerService.createEmptyString(spaceBetweenRelation * step), null);
        R whiteSpace = createR(stringWorkerService.createEmptyString(whiteSpaceRelation * step), null);
        R[] texts = {whiteSpace, chunkDate, whiteSpace, chunkMonth, whiteSpace, chunkYear,
                spaceBetweenDateAndSignature, chunkSignature};
        P paragraph = createParagraph(texts);
        mainDocumentPart.getContent().add(paragraph);
    }

    private void addTeoConsultorDescription(MainDocumentPart mainDocumentPart) {
        RPr rpr = new RPr();
        HpsMeasure sz = new HpsMeasure();
        sz.setVal(new BigInteger("12"));
        rpr.setSz(sz);
        rpr.setSzCs(sz);
//        r.setRPr(rpr);

        int dateRelation = 2, monthRelation = 6, yearRelation = 4, spaceBetweenRelation = 4,
                signatureRelation = 6, whiteSpaceRelation = 1;
        int step = 4;
        R chunkDate = createR(stringWorkerService.createEmptyString(dateRelation * step), null);
        R chunkMonth = createR(stringWorkerService.createEmptyString(monthRelation * step), null);
        R chunkYear = createR(stringWorkerService.createEmptyString(yearRelation * step), null);
        R chunkSignature = createR(stringWorkerService.createEmptyString(signatureRelation * step - 10) + "(Подпись консультанта)     ", rpr);
        R spaceBetweenDateAndSignature = createR(stringWorkerService.createEmptyString(spaceBetweenRelation * step), null);
        R whiteSpace = createR(stringWorkerService.createEmptyString(whiteSpaceRelation * step), null);
        R[] texts = {whiteSpace, chunkDate, whiteSpace, chunkMonth, whiteSpace, chunkYear,
                spaceBetweenDateAndSignature, chunkSignature};
        P paragraph = createParagraph(texts);
        mainDocumentPart.getContent().add(paragraph);
    }

    private void addLeaderAgreeDescription(MainDocumentPart mainDocumentPart) {
        RPr rpr = new RPr();
        HpsMeasure sz = new HpsMeasure();
        sz.setVal(new BigInteger("12"));
        rpr.setSz(sz);
        rpr.setSzCs(sz);
//        r.setRPr(rpr);

        int dateRelation = 2, monthRelation = 6, yearRelation = 4, spaceBetweenRelation = 4,
                signatureRelation = 6, whiteSpaceRelation = 1;
        int step = 4;
        R chunkDate = createR(stringWorkerService.createEmptyString(dateRelation * step), null);
        R chunkMonth = createR(stringWorkerService.createEmptyString(monthRelation * step), null);
        R chunkYear = createR(stringWorkerService.createEmptyString(yearRelation * step), null);
        R chunkSignature = createR(stringWorkerService.createEmptyString(signatureRelation * step - 10) + "(Подпись руководителя)     ", rpr);
        R spaceBetweenDateAndSignature = createR(stringWorkerService.createEmptyString(spaceBetweenRelation * step), null);
        R whiteSpace = createR(stringWorkerService.createEmptyString(whiteSpaceRelation * step), null);
        R[] texts = {whiteSpace, chunkDate, whiteSpace, chunkMonth, whiteSpace, chunkYear,
                spaceBetweenDateAndSignature, chunkSignature};
        P paragraph = createParagraph(texts);
        mainDocumentPart.getContent().add(paragraph);
    }

    private void addTeoConsultor(MainDocumentPart mainDocumentPart, Lector lector) {
        RPr rPr = new RPr();
        rPr.setU(createUnderline());
        R chunkLeaderLabel = createR("Консультант по компьютерному проектированию", null);
        R chunkSpace = createR("                          ", rPr);
        R whiteSpace = createR(" ", null);
        String leaderString = "";
        if(lector instanceof LectorUniversity) {
            LectorUniversity lectorUniversity = (LectorUniversity) lector;
            leaderString += lectorUniversity.getPost().getName() + "," + " " + lectorUniversity.getDegree().getShortName() + ",";
        }
        R chunkLeader = createR(leaderString + " " + userService.formatSurnameWithInitials(lector), rPr);
        R[] texts = {chunkLeaderLabel, whiteSpace, chunkLeader, chunkSpace};
        P paragraph = createParagraph(texts);
        mainDocumentPart.getContent().add(paragraph);
        addEmptyLineWithUnderline(mainDocumentPart, 1);
        addLeaderDescription(mainDocumentPart);
    }

    private void addLeaderDescription(MainDocumentPart mainDocumentPart) {
        PPr paragraphProperties = factory.createPPr();
        Jc justification = factory.createJc();
        justification.setVal(JcEnumeration.CENTER);
        paragraphProperties.setJc(justification);

        RPr rpr = new RPr();
        HpsMeasure sz = new HpsMeasure();
        sz.setVal(new BigInteger("16"));
        rpr.setSz(sz);
        rpr.setSzCs(sz);
        R description = createR("(указать: должность, ученое звание, фамилия, инициалы)", rpr);
        R[] texts = {description};
        P paragraph = createParagraph(texts);
        paragraph.setPPr(paragraphProperties);
        mainDocumentPart.getContent().add(paragraph);
//        r.setRPr(rpr);
    }

    public RPr createTitleRpr() {
        ObjectFactory factory = Context.getWmlObjectFactory();

        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        rpr.setB(b);
        rpr.setI(b);
        rpr.setCaps(b);
//        Color green = factory.createColor();
//        green.setVal("green");
//        rpr.setColor(green);
        return rpr;
    }

    public Text createText(String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        Text t = factory.createText();
        t.setValue(text);
        return t;
    }

    public R createR(String text, RPr rPr) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        Text t = factory.createText();
        R r = factory.createR();
        if(rPr == null) {
            rPr = new RPr();
        }
        r.setRPr(rPr);
        t.setValue(text);
        r.getContent().add(t);
        return r;
    }

    public P createParagraph(Text text, RPr rPr) {
        ObjectFactory factory = Context.getWmlObjectFactory();

        P p = factory.createP();
        R r = factory.createR();
        r.getContent().add(text);
        p.getContent().add(r);
        return p;
    }

    public P createParagraph(Text[] texts, RPr rPr) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        if(rPr == null) {
            rPr = new RPr();
        }
        r.setRPr(rPr);
        for(Text text: texts) {
            r.getContent().add(text);
        }
        p.getContent().add(r);
        return p;
    }

    public P createParagraph(Object[] texts, RPr rPr) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();

        //centering the paragraph
        R r = factory.createR();
        if(rPr == null) {
            rPr = new RPr();
        }
        r.setRPr(rPr);
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

    public P createParagraph(R[] rs) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        for(R r: rs) {
            p.getContent().add(r);
        }
        return p;
    }

    public P createParagraph(String text, RPr rpr) {
        ObjectFactory factory = Context.getWmlObjectFactory();


        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        if(rpr == null) {
            rpr = new RPr();
        }
        r.setRPr(rpr);
//        HpsMeasure sz = new HpsMeasure();
////        sz.setVal(new BigInteger("12"));
//        rpr.setSz(sz);
//        rpr.setSzCs(sz);
//        r.setRPr(rpr);
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

    public U createUnderline() {
        U underline = new U();
        underline.setVal(UnderlineEnumeration.SINGLE);
        return underline;
//        rPr.setU(underline);
    }
}
