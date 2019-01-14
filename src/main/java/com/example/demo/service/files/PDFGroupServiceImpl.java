package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PDFGroupServiceImpl implements PDFGroupService {
    Font fontRu;
    Font fontRuTitle;
    public PDFGroupServiceImpl() {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("./arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        this.fontRuTitle = new Font(bf, 16);
        this.fontRuTitle.setStyle("bold");
        this.fontRu = new Font(bf, 16);

    }

    @Override
    public File writePdf(Group group) {
        Document document = new Document();
        String pathname = PDFServiceImpl.path + "Группа: " + group.getNumber() + ".pdf";
        try {
            PdfWriter.getInstance(document,  new FileOutputStream(pathname));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        addTitle(document, group);
        addGroupTitle(document, group);
        addStudents(document, group);
        document.close();
        File file = new File(pathname);
        return file;
    }


    private void addGroupTitle(Document document, Group group) {
        Chunk chunkNameTitle = new Chunk("Приказываю: ", fontRuTitle);
        Chunk chunkGroup = new Chunk("\t\tУчебная группа " + group.getNumber() ,fontRu );

        Chunk chunkNameContent = new Chunk("\t1. Утвердить ниже перечисленными студентами 4-го курса, обучающимся"
                + "по специальности " + group.getSpecialization().getCode()
                + " \"" + group.getSpecialization().getName() + "\""
                + " " + group.getSpecialization().getDepartment().getFaculty().getName()
                + " в дневной форме получения образования, следующие темы дипломных проектов"
                + ", руководителей и  назначить консультантов дипломных проектов:", fontRu);

//            document.add(chunk);

//            document.add(chunk);
        try {
            Paragraph paragraphSpace = new Paragraph();
        Paragraph paragraphName = new Paragraph();
        Paragraph paragraphContent = new Paragraph();
        Paragraph paragraphGroup = new Paragraph();

        paragraphName.add(chunkNameTitle);
        addEmptyLine(paragraphSpace,1);
        paragraphContent.add(chunkNameContent);
        paragraphGroup.add(chunkGroup);

            document.add(paragraphSpace);
            document.add(paragraphName);
            document.add(paragraphSpace);
        document.add(paragraphContent);
            document.add(paragraphSpace);
        document.add(paragraphGroup);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private void addStudents(Document document, Group group) {
        Paragraph paragraphStudents = new Paragraph();
        for(int i = 0; i < group.getStudents().size(); i++) {
            Student student = group.getStudents().get(i);
            Chunk chunkStudent = new Chunk("\t1." + (i + 1)
                    + " " + student.getLastname() + " " + student.getFirstname() + " " + student.getMiddlename() +
                    " - \"" + student.getDiplomWork().getName() + "\"."
                    + " Руководитель и консультант по компьютерному проектированию - "
                    //+ student.getDiplomWork().getLeader().getPost().getName() + " кафедры"
                    + " " + student.getDiplomWork().getLeader().getLastname()
                    + " " + student.getDiplomWork().getLeader().getFirstname()
                    + " " + student.getDiplomWork().getLeader().getMiddlename() + ".", fontRu);
            paragraphStudents.add(chunkStudent);
        }
        try {
            document.add(paragraphStudents);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }


    }

    private void addTitle(Document document, Group group) {
        float [] pointColumnWidths = {150F, 150F, 150F};
        int numColumns = 2;
        PdfPTable table = new PdfPTable(2);
        PdfPCell cellRu = new PdfPCell();
        PdfPCell cellBy = new PdfPCell();
        PdfPCell cellDescription = new PdfPCell();
        Paragraph paragraphRu = new Paragraph();
        Paragraph paragraphBy = new Paragraph();
        Paragraph paragraphDescription = new Paragraph();
        Chunk chunkNewLine = new Chunk("\n");
        Chunk chunkTitleRu = new Chunk("МИНИСТЕРСТВО ОБРАЗОВАНИЯ РЕСПУБЛИКИ БЕЛАРУСЬ", fontRu);
        Chunk chunkUniversityRu = new Chunk("БЕЛОРУССКИЙ НАЦИОНАЛЬНЫЙ ТЕХНИЧЕСКИЙ УНИВЕРСИТЕТ", fontRu);
        Chunk chunkCityRu = new Chunk("г. Минск", fontRu);
        Chunk chunkOrderRu = new Chunk("ПРИКАЗ", fontRu);
        Chunk chunkTitleBy = new Chunk("МIНIСТЭРСТВА АДУКАЦЫI РЭСПУБЛIКI БЕЛАРУСЬ", fontRu);
        Chunk chunkUniversityBy = new Chunk("БЕЛАРУСКI НАЦЫЯНАЛЬНЫ ТЭХНIЧНЫ УНИВЕРСIТЭТ", fontRu);
        Chunk chunkOrderBy = new Chunk("ЗАГАД\n______№___", fontRu);
        Chunk chunkCityBy = new Chunk("г.Мiнск", fontRu);
        Chunk chunkDescription = new Chunk("Об утвержении тем, руководителей и назначении консультантов"
        + " и нормоконтролера дипломных проектов студентам " + group.getSpecialization().getDepartment().getFaculty().getShortName()
        + " дневной формы получения образования", fontRu);

        paragraphRu.add(chunkTitleRu);
        addEmptyLine(paragraphRu, 1);
        paragraphRu.add(chunkUniversityRu);
        paragraphRu.add(chunkNewLine);
        paragraphRu.add(chunkOrderRu);
        addEmptyLine(paragraphRu, 2);
        paragraphRu.add(chunkCityRu);
        paragraphRu.setAlignment(Paragraph.ALIGN_CENTER);
        cellRu.addElement(paragraphRu);
        cellRu.setBorder(Rectangle.NO_BORDER);

        paragraphBy.add(chunkTitleBy);
        addEmptyLine(paragraphBy,1);
        paragraphBy.add(chunkUniversityBy);
        paragraphBy.add(chunkNewLine);
        paragraphBy.add(chunkOrderBy);
        paragraphBy.add(chunkNewLine);
        //addEmptyLine(paragraphBy,2);
        paragraphBy.add(chunkCityBy);
        paragraphBy.setAlignment(Paragraph.ALIGN_CENTER);
        cellBy.addElement(paragraphBy);
        cellBy.setBorder(Rectangle.NO_BORDER);

        paragraphDescription.add(chunkDescription);
        cellDescription.addElement(paragraphDescription);
        cellDescription.setBorder(Rectangle.NO_BORDER);


//        table.addCell("Key");
//        table.addCell("Value");
//        table.setHeaderRows(1);
//        table.setSkipFirstHeader(true);
        table.addCell(cellBy);
        table.addCell(cellRu);
        table.addCell(cellDescription);
        table.addCell(getEmptyCell());
        try {
            document.add(table);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private PdfPCell getEmptyCell() {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
}
