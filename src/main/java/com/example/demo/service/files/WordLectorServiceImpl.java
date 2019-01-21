package com.example.demo.service.files;

import com.example.demo.entity.Group;
import com.example.demo.entity.Lector;
import com.example.demo.repository.LectorRepository;
import com.example.demo.service.UserService;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

@Service
public class WordLectorServiceImpl implements WordLectorService {
    ObjectFactory factory;
    WordprocessingMLPackage wordPackage;
    final String[] columns = {"п\\п", "ФИО руководителя", "п\\п", "ФИО студента", "группа"};
    @Autowired
    private WordService wordService;
    @Autowired
    UserService<Lector, LectorRepository> userService;
    String tblXML = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" " +
            "xmlns:v=\"urn:schemas-microsoft-com:vml\" " +
            "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" " +
            "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">"
            +"<w:tblPr>"
            +"<w:tblStyle w:val=\"TableGrid\" />"
            +"<w:tblW w:w=\"0\" w:type=\"auto\" />"
            +"<w:tblLook w:val=\"04A0\" />"
            +"</w:tblPr>"
            +"<w:tblGrid>"
            +"<w:gridCol w:w=\"392\" />"
            +"<w:gridCol w:w=\"328\" />"
            +"</w:tblGrid>"
            +"<w:tr w:rsidR=\"005C211D\" w:rsidTr=\"005C211D\">"
            +"<w:tc>"
            +"<w:tcPr>"
            +"<w:tcW w:w=\"392\" w:type=\"dxa\" />"
            +"</w:tcPr>"
            +"<w:p w:rsidR=\"005C211D\" w:rsidRDefault=\"005C211D\" w:rsidP=\"005C211D\">"
            +"<w:r>"
            +"<w:t>1</w:t>"
            +"</w:r>"
            +"</w:p>"
            +"</w:tc>"
            +"<w:tc>"
            +"<w:tcPr>"
            +"<w:tcW w:w=\"328\" w:type=\"dxa\" />"
            +"</w:tcPr>"
            +"<w:p w:rsidR=\"005C211D\" w:rsidRDefault=\"005C211D\" w:rsidP=\"005C211D\">"
            +"<w:r>"
            +"<w:t>2</w:t>"
            +"</w:r>"
            +"</w:p>"
            +"</w:tc>"
            +"</w:tr>"
            +"<w:tr w:rsidR=\"005C211D\" w:rsidTr=\"005C211D\">"
            +"<w:trPr>"
            +"<w:trHeight w:val=\"70\" />"
            +"</w:trPr>"
            +"<w:tc>"
            +"<w:tcPr>"
            +"<w:tcW w:w=\"392\" w:type=\"dxa\" />"
            +"</w:tcPr>"
            +"<w:p w:rsidR=\"005C211D\" w:rsidRDefault=\"005C211D\" w:rsidP=\"005C211D\">"
            +"<w:r>"
            +"<w:t>3</w:t>"
            +"</w:r>"
            +"</w:p>"
            +"</w:tc>"
            +"<w:tc>"
            +"<w:tcPr>"
            +"<w:tcW w:w=\"328\" w:type=\"dxa\" />"
            +"</w:tcPr>"
            +"<w:p w:rsidR=\"005C211D\" w:rsidRDefault=\"005C211D\" w:rsidP=\"005C211D\">"
            +"<w:r>"
            +"<w:t>4</w:t>"
            +"</w:r>"
            +"</w:p>"
            +"</w:tc>"
            +"</w:tr>"
            +"</w:tbl>";

    public WordLectorServiceImpl() {
        factory = Context.getWmlObjectFactory();
        try {
            wordPackage = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File writeWord(List<Lector> lectors) {

        try {
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
            addTitle(mainDocumentPart);
            addLectorsTable(mainDocumentPart, lectors);
            String pathname = WordServiceImpl.path + "Преподователи.docx";
            File exportFile = new File(pathname);
            wordPackage.save(exportFile);
//            return main();
            return exportFile;
        }
        catch(Docx4JException docExc) {

        }
        catch (Exception exception) {

        }
        return null;
    }

    private void addTitle(MainDocumentPart mainDocumentPart) {
        P p0 = factory.createP();
        R r0 = factory.createR();
        Text t0 = factory.createText();
        t0.setValue("CАМОЗАПИСЬ НА ДИПЛОМНОЕ ПРОЕКТИРОВАНИЕ 2018-2019 уч. год:");
        r0.getContent().add(t0);
        p0.getContent().add(r0);

        mainDocumentPart.getContent().add(p0);
    }
    private void addLectorsTable(MainDocumentPart mainDocumentPart, List<Lector> lectors) {
        int columnNumber = 5;
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        int countRows = 0;
        for(Lector lector : lectors) {
            countRows += lector.getMaxCountOfDiplom();
        }
        Tbl tbl = TblFactory.createTable(countRows + 1, columnNumber, writableWidthTwips/columnNumber);
        List<Object> rows = tbl.getContent();
        P p = factory.createP();
        P paragraph = new P();
            for (int i = 0, currentRow=0; currentRow < rows.size(); i++) {
                if(i == 0) {
                    Object row = rows.get(currentRow);
                    Tr tr = (Tr) row;
                    List<Object> cells = tr.getContent();
                    int j = 0;
                    for(Object cell : cells) {
                        Tc td = (Tc) cell;
                        RPr titleRpr = wordService.createTitleRpr();
                        p = wordService.createParagraph(columns[j], titleRpr);
                        td.getContent().add(p);
                        j++;
                    }
                    currentRow++;
                }
                else {
                    for(int z = 0; z < lectors.get(i - 1).getMaxCountOfDiplom(); z++, currentRow++) {
                        Object row = rows.get(currentRow);
                        Tr tr = (Tr) row;
                        List<Object> cells = tr.getContent();
                        int j = 0;
                        for(Object cell : cells) {
                            Tc td = (Tc) cell;
                            if(j == 0) {
//                            td.getContent().add(percentages.get(i - 1).getName());
                                if(z == 0) {
                                    p = wordService.createParagraph(i + "", null);
                                    td.getContent().add(p);
                                } else {
                                    p = wordService.createParagraph( "", null);
                                    td.getContent().add(p);
                                }
                            }
                            if(j == 1) {
//                            td.getContent().add(percentages.get(i - 1).getComment());
                                if(z == 0) {
                                    p = wordService.createParagraph(userService.formatFullName(lectors.get(i - 1)), null);
                                    td.getContent().add(p);
                                } else {
                                    p = wordService.createParagraph( "", null);
                                    td.getContent().add(p);
                                }
                            }
                            if(j == 2) {
//                            td.getContent().add(percentages.get(i - 1).getComment());
                                p = wordService.createParagraph(z + 1 + "", null);
                                td.getContent().add(p);
                            }
                            if(j == 3) {
//                            td.getContent().add(percentages.get(i - 1).getComment());
                                p = wordService.createParagraph( "", null);
                                td.getContent().add(p);
                            }
                            if(j == 4) {
//                            td.getContent().add(percentages.get(i - 1).getPercent());
                                p = wordService.createParagraph( "", null);
                                td.getContent().add(p);
                            }
                            j++;
                        }
                    }
                }
            }
        System.out.println(tbl);
//            mainDocumentPart.addObject(tbl);
            mainDocumentPart.getContent().add(tbl);
//        }
    }
    private Tbl getEmptyTableWithEmptyRows(int countRows) {
        int columnNumber = 1;
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        Tbl tbl = TblFactory.createTable(columnNumber, columnNumber, writableWidthTwips/columnNumber/5);
        List<Object> rows = tbl.getContent();
        P p = factory.createP();
        for (int i = 0; i < rows.size(); i++) {
            Object row = rows.get(i);
            if(i == 0) {
                Tr tr = (Tr) row;
                List<Object> cells = tr.getContent();
                int j = 0;
                for(Object cell : cells) {
                    Tc td = (Tc) cell;
                    RPr titleRpr = wordService.createTitleRpr();
                    p = wordService.createParagraph("", titleRpr);
                    td.getContent().add(p);
                    j++;
                }
            }
        }
        return tbl;
    }

    private void addTc(Tr tr,String label, String text, String width) {
        Tc tc = factory.createTc();
        TcPr tcPr = new TcPr();
        TblWidth tblwidth = new TblWidth();
        tblwidth.setType("dxa");
        tblwidth.setW(new BigInteger(width));
        tcPr.setTcW(tblwidth);
        tc.setTcPr(tcPr);



        tc.getEGBlockLevelElts().add( wordPackage.getMainDocumentPart().createParagraphOfText(text) );
        tr.getEGContentCellContent().add( tc );
    }
    private void addTcVMerge(Tr tr,String label, String text, String vMergeVal,String width) {
        Tc tc = factory.createTc();
        TcPr tcPr = new TcPr();
        TblWidth tblwidth = new TblWidth();
        tblwidth.setType("dxa");
        tblwidth.setW(new BigInteger(width));
        tcPr.setTcW(tblwidth);

        TcPrInner.VMerge merge = new TcPrInner.VMerge();
        if(vMergeVal !=null){
            merge.setVal(vMergeVal);
        }
        tcPr.setVMerge(merge);

        tc.setTcPr(tcPr);
        if(text != null) {
            tc.getEGBlockLevelElts().add( wordPackage.getMainDocumentPart().createParagraphOfText(text) );
        }

        tr.getEGContentCellContent().add( tc );
    }

    public File main() throws Exception {

        System.out.println( "Creating package..");
//        wordMLPackage = WordprocessingMLPackage.createPackage();

        Tbl mainTable = factory.createTbl();
        TblPr tblPr = new TblPr();

        CTTblPrBase.TblStyle tblStyle = new CTTblPrBase.TblStyle();
        tblStyle.setVal("TableGrid");

        tblPr.setTblStyle(tblStyle);
        mainTable.setTblPr(tblPr);


        TblWidth width = new TblWidth();
        width.setType("auto");
        width.setW(new BigInteger("0"));
        tblPr.setTblW(width);

        // create row 1
        Tr tr = factory.createTr();

        // col 1 of row 1
        addTc(tr,"Ab","Ab","3192");
        //col 2 of row 1
        addTc(tr,"Ac","Ac","3192");

        // a vertically merged col
        addTcVMerge(tr ,"test", "Vertical Merge","restart","9000");

        mainTable.getEGContentRowContent().add(tr);

        // create row 2
        Tr tr2 = factory.createTr();

        TrPr pr = new TrPr();
        tr2.setTrPr(pr);

        // col 1 of row 2

        Tc tc1 = factory.createTc();
        TcPr tcPr = new TcPr();
        TblWidth widtha = new TblWidth();
        widtha.setType("dxa");
        widtha.setW(new BigInteger("0"));
        tcPr.setTcW(widtha);

        tc1.setTcPr(tcPr);
        tc1.getEGBlockLevelElts().add(XmlUtils.unmarshalString(tblXML));
        //The following is important or you may get a corrupted docx file
        tc1.getEGBlockLevelElts().add( wordPackage.getMainDocumentPart().createParagraphOfText(""));
        tr2.getEGContentCellContent().add( tc1 );

        mainTable.getEGContentRowContent().add(tr2);

        //col2 of row 2
        addTc(tr2,"bA","bA","3192");

        // end  vertically merged col
        addTcVMerge(tr2 ,"", "",null,"9000");


        // create row 3
        Tr tr3 = factory.createTr();

        TrPr pr3 = new TrPr();
        tr3.setTrPr(pr3);

        Tc tc31 = factory.createTc();
        TcPr tcPr3 = new TcPr();
        TblWidth width31 = new TblWidth();
        width31.setType("dxa");
        width31.setW(new BigInteger("0"));
        tcPr3.setTcW(width31);

        // for setting colspan of 3
        TcPrInner.GridSpan gridSpan = new TcPrInner.GridSpan();
        gridSpan.setVal(new BigInteger("3"));

        tcPr3.setGridSpan(gridSpan);

        tc31.setTcPr(tcPr3);
        tc31.getEGBlockLevelElts().add( wordPackage.getMainDocumentPart().createParagraphOfText("Horizontal merge 3 col"));

        tr3.getEGContentCellContent().add( tc31 );
        mainTable.getEGContentRowContent().add(tr3);


        // create row 4 -6384
        Tr tr4 = factory.createTr();

        TrPr pr4 = new TrPr();
        tr4.setTrPr(pr4);

        Tc tc41 = factory.createTc();
        TcPr tcPr4 = new TcPr();
        TblWidth width41 = new TblWidth();
        width41.setType("dxa");
        width41.setW(new BigInteger("0"));
        tcPr4.setTcW(width31);

        // for setting colspan of 3
        TcPrInner.GridSpan gridSpan41 = new TcPrInner.GridSpan();
        gridSpan41.setVal(new BigInteger("2"));

        tcPr4.setGridSpan(gridSpan41);

        tc41.setTcPr(tcPr4);
        tc41.getEGBlockLevelElts().add( wordPackage.getMainDocumentPart().createParagraphOfText("Horizontal merge 2 col"));

        tr4.getEGContentCellContent().add( tc41 );

        // Add an image to the last col

//        File file = new File("C:\\test\\1_burndown.png" );

//        java.io.InputStream is = new java.io.FileInputStream(file );

//        long length = file.length();
        // You cannot create an array using a long type.
        // It needs to be an int type.
//        if (length > Integer.MAX_VALUE) {
//            System.out.println("File too large!!");
//        }
//        byte[] bytes = new byte[(int)length];
//        int offset = 0;
//        int numRead = 0;
//        while (offset < bytes.length
//                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
//            offset += numRead;
//        }
//        // Ensure all the bytes have been read in
//        if (offset < bytes.length) {
//            System.out.println("Could not completely read file "+file.getName());
//        }
//        is.close();

        String filenameHint = null;
        String altText = null;
        int id1 = 0;
        int id2 = 1;

//        org.docx4j.wml.P p2 = newImage( wordPackage, bytes, filenameHint, altText, id1, id2, 3000 );


//        Tc tc42 = factory.createTc();
//        tc42.getEGBlockLevelElts().add(p2);
//        tr4.getEGContentCellContent().add( tc42 );



//        mainTable.getEGContentRowContent().add(tr4);

        wordPackage.getMainDocumentPart().addObject(mainTable);
        // Now save it
        String pathname = WordServiceImpl.path + "Преподователи1.docx";
        File exportFile = new File(pathname);
        wordPackage.save(exportFile);

        System.out.println("Done.");
        return exportFile;

    }
    public static org.docx4j.wml.P newImage( WordprocessingMLPackage wordMLPackage,
                                             byte[] bytes,
                                             String filenameHint, String altText,
                                             int id1, int id2, long cx) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        Inline inline = imagePart.createImageInline( filenameHint, altText,
                id1, id2, cx);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory = new org.docx4j.wml.ObjectFactory();
        org.docx4j.wml.P  p = factory.createP();
        org.docx4j.wml.R  run = factory.createR();
        p.getParagraphContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getRunContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }

}
