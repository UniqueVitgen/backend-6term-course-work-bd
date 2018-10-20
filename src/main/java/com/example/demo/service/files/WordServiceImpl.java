package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Percentage;
import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    final String[] columns = {"Название", "Комментарий", "Дата начала", "Дата окончания", "Процент"};
    static String path = "src/main/resources";
    static SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");
    @Override
    public File writeDiplomWork(DiplomWork diplomWork) {
        try {
            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
            mainDocumentPart.addStyledParagraphOfText("Title", diplomWork.getName());
            int columnNumber = 5;
            int writableWidthTwips = wordPackage.getDocumentModel()
                    .getSections().get(0).getPageDimensions().getWritableWidthTwips();
            Tbl tbl = TblFactory.createTable(diplomWork.getPercentages().size() + 1, columnNumber, writableWidthTwips/columnNumber);
            List<Object> rows = tbl.getContent();
            List<Percentage> percentages = new ArrayList<Percentage>(diplomWork.getPercentages());
            ObjectFactory factory = Context.getWmlObjectFactory();
            P p = factory.createP();
            R r = factory.createR();
            if(diplomWork.getComment() != null) {
                mainDocumentPart.addParagraphOfText(diplomWork.getComment());
            }
            P paragraph = new P();
            if(diplomWork.getPercentages().size() > 0) {
                paragraph = createCenteredParagraph("Табл. Процентовки");
                mainDocumentPart.addObject(paragraph);
                for (int i = 0; i < rows.size(); i++) {
                    Object row = rows.get(i);
                    if(i == 0) {
                        Tr tr = (Tr) row;
                        List<Object> cells = tr.getContent();
                        int j = 0;
                        for(Object cell : cells) {
                            Tc td = (Tc) cell;
                            RPr titleRpr = createTitleRpr();
                            p = createParagraph(columns[j], titleRpr);
                            td.getContent().add(p);
                            j++;
                        }
                    }
                    else {
                        Tr tr = (Tr) row;
                        List<Object> cells = tr.getContent();
                        int j = 0;
                        for(Object cell : cells) {
                            Tc td = (Tc) cell;
                            if(j == 0) {
//                            td.getContent().add(percentages.get(i - 1).getName());
                                p = createParagraph(percentages.get(i - 1).getName(), null);
                                td.getContent().add(p);
                            }
                            if(j == 1) {
//                            td.getContent().add(percentages.get(i - 1).getComment());
                                p = createParagraph(percentages.get(i - 1).getComment(), null);
                                td.getContent().add(p);
                            }
                            if(j == 2) {
//                            td.getContent().add(percentages.get(i - 1).getComment());
                                p = createParagraph(dt1.format(percentages.get(i - 1).getStartDate()), null);
                                td.getContent().add(p);
                            }
                            if(j == 3) {
//                            td.getContent().add(percentages.get(i - 1).getComment());
                                p = createParagraph(dt1.format(percentages.get(i - 1).getEndDate()), null);
                                td.getContent().add(p);
                            }
                            if(j == 4) {
//                            td.getContent().add(percentages.get(i - 1).getPercent());
                                p = createParagraph(percentages.get(i - 1).getPercent() + "", null);
                                td.getContent().add(p);
                            }
                            j++;
                        }
                    }
                }
//            mainDocumentPart.addObject(tbl);
                mainDocumentPart.getContent().add(tbl);
            }
            String pathname = WordServiceImpl.path + "Диплом работа: " + diplomWork.getName() + ".docx";
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

    private P createCenteredParagraph(String text) {
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

    private RPr createTitleRpr() {
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

    private P createParagraph(String text, RPr rpr) {
        ObjectFactory factory = Context.getWmlObjectFactory();

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
}
