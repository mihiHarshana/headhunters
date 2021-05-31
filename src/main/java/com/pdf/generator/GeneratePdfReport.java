package com.pdf.generator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.CV;
import com.model.Qualification;
import com.model.QualificationType;
@Component
public class GeneratePdfReport {

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream citiesReport(List<QualificationType> qualificationTypes, CV cv, List<Qualification> qualifications) {

    	Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
        	 PdfPTable table = new PdfPTable(2);
             table.setWidthPercentage(100);
             table.setWidths(new int[]{12, 12});

             Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
             Font cellFont = FontFactory.getFont(FontFactory.TIMES, 15);

             PdfPCell cell1 = new PdfPCell(new Phrase("Full Name", cellFont));
             cell1.setPaddingLeft(5);
             cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell1.setBorder(0);
             cell1.setFixedHeight(45f);
             table.addCell(cell1);
             PdfPCell cell2 = new PdfPCell(new Phrase(":" + cv.getF_name() + ' ' + cv.getL_name() , cellFont));
             cell2.setPaddingLeft(5);
             cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell2.setBorder(0);
             cell2.setFixedHeight(50f);
             table.addCell(cell2);
   
             PdfPCell cell3 = new PdfPCell(new Phrase("Email Address", cellFont));
             cell3.setPaddingLeft(5);
             cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell3.setBorder(0);
             cell3.setFixedHeight(45f);
             table.addCell(cell3);
             PdfPCell cell4 = new PdfPCell(new Phrase(":" + cv.getEmailaddress(), cellFont));
             cell4.setPaddingLeft(5);
             cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell4.setBorder(0);
             cell4.setFixedHeight(45f);
             table.addCell(cell4);
             
             PdfPCell cell5 = new PdfPCell(new Phrase("Telephone Number", cellFont));
             cell5.setPaddingLeft(5);
             cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell5.setBorder(0);
             cell5.setFixedHeight(45f);
             table.addCell(cell5);
             PdfPCell cell6 = new PdfPCell(new Phrase(":" + cv.getTel_no(), cellFont));
             cell6.setPaddingLeft(5);
             cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell6.setBorder(0);
             cell6.setFixedHeight(45f);
             table.addCell(cell6);
             
             PdfPCell cell7 = new PdfPCell(new Phrase("Home Address", cellFont));
             cell7.setPaddingLeft(5);
             cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell7.setBorder(0);
             cell7.setFixedHeight(45f);
             table.addCell(cell7);
             PdfPCell cell8 = new PdfPCell(new Phrase(":" + cv.getAddress(), cellFont));
             cell8.setPaddingLeft(5);
             cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell8.setBorder(0);
             cell7.setFixedHeight(45f);
             table.addCell(cell8);
             
             for (QualificationType obj : qualificationTypes) {

                 PdfPCell cell;

                 cell = new PdfPCell(new Phrase(obj.getQualificationType(), cellFont));
                 cell.setPaddingLeft(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setBorder(0);
               //  cell.setFixedHeight(45f);
                 table.addCell(cell);
                 com.itextpdf.text.List list = new com.itextpdf.text.List();
                 for(Qualification qualification: qualifications) {
                	 if (obj.getId() == qualification.qualificationType) {
                		 list.add(new ListItem(qualification.getValue(), cellFont));
                	 }
                 }
                 cell = new PdfPCell();
                 cell.setPaddingLeft(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.addElement(list);
                 cell.setBorder(0);
               //  cell.setFixedHeight(45f);
                 table.addCell(cell);
             }
             
             PdfWriter writer = PdfWriter.getInstance(document, out);
             document.open();
             PdfContentByte cb = writer.getDirectContent();
             Phrase header = new Phrase(cv.getF_name().toUpperCase() + " " + cv.getL_name().toUpperCase(), headFont);
            
             ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                     header,
                     (document.right() - document.left()) / 2 + document.leftMargin(),
                     document.top() + -10, 0);
             String pattern = "MM-dd-yyyy";
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
             String date = simpleDateFormat.format(new Date());
             Phrase footer = new Phrase("Date :" + date, cellFont);
             ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                     footer,
                     (document.right() - document.left()) / 2 + document.leftMargin(),
                     document.bottom() - 10, 0);
             Rectangle rect= new Rectangle(577,825,18,15); // you can resize rectangle 
             rect.enableBorderSide(1);
             rect.enableBorderSide(2);
             rect.enableBorderSide(4);
             rect.enableBorderSide(8);
             rect.setBorderColor(BaseColor.BLACK);
             rect.setBorderWidth(1);
             document.add(rect);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            document.add(table);
            document.close();
        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}