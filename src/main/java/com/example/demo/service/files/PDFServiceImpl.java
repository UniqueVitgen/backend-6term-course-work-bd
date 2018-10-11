package com.example.demo.service.files;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Percentage;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class PDFServiceImpl implements PDFService {
    @Override
    public File writeDiplomWork(DiplomWork diplomWork) {
        try {
            Document document = new Document();
            String pathname = "Диплом работа: " + diplomWork.getName() + ".pdf";
            PdfWriter.getInstance(document,  new FileOutputStream(pathname));

            document.open();
            final BaseFont bf = BaseFont.createFont("./arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font fontRuTitle = new Font(bf, 16);
            fontRuTitle.setStyle("bold");
            Font fontRu = new Font(bf, 16);
            Chunk chunkNameTitle = new Chunk("Название: ", fontRuTitle);


//            document.add(chunk);
            Chunk chunkName = new Chunk(diplomWork.getName(), fontRu);

//            document.add(chunk);
            Paragraph paragraphName = new Paragraph();
            paragraphName.add(chunkNameTitle);
            paragraphName.add(chunkName);

            addEmptyLine(paragraphName,2);

            document.add(paragraphName);

            if(diplomWork.getPercentages().size() > 0) {

                Paragraph paragraph = new Paragraph();
                Chunk forTable1 = new Chunk("Таблица процентовок", fontRu);
                paragraph.add(forTable1);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                addEmptyLine(paragraph,1);

                document.add(paragraph);

                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(80);
                table.setWidths(new int[]{3, 3, 3});


                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

                PdfPCell hcell;
                hcell = new PdfPCell(new Phrase("Название", fontRuTitle));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Комментарий", fontRuTitle));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Процент", fontRuTitle));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                for(Percentage percentage: diplomWork.getPercentages()) {


                    PdfPCell cell;

                    cell = new PdfPCell(new Phrase(percentage.getName().toString(), fontRu));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(percentage.getComment(), fontRu));
                    cell.setPaddingLeft(5);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(String.valueOf(percentage.getPercent()), fontRu));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingRight(5);
                    table.addCell(cell);
                }
                document.add(table);

            }
            document.close();
            File file = new File(pathname);
            return file;
        }
        catch(FileNotFoundException fnfe) {

        }
        catch (DocumentException de) {

        }
        catch (IOException ioexc) {

        }
        return null;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
