package cuny.edu.com.resumebuilder.pdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cuny.edu.com.resumebuilder.ResumeInformation;

/**
 * Created by 86984L on 10/3/2015.
 */
public class ResumeGenerator {

    private static String LOG_TAG = "RGEN";

    public class Watermark extends PdfPageEventHelper {

        protected Phrase watermark = new Phrase("CAPSTONE-RESUME-BUILDER", new Font(Font.FontFamily.HELVETICA, 60,
                Font.NORMAL, BaseColor.LIGHT_GRAY));

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte canvas = writer.getDirectContentUnder();
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 298, 421, 45);
        }
    }

    public void generateResume(ResumeInformation resumeInformation, Context context) {
        try {
            createPdf(resumeInformation, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPdf(ResumeInformation information, Context context) throws FileNotFoundException, DocumentException {
        String fileName = "resume.pdf";
        context.deleteFile(fileName);
        FileOutputStream output = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        Rectangle layout = new Rectangle(PageSize.A4);
        layout.setBackgroundColor(BaseColor.WHITE);

        Document document = new Document();
        PdfWriter writer  = PdfWriter.getInstance(document, output);
        document.open();
        document.add(Chunk.NEWLINE);
        writer.setPageEvent(new Watermark());

        try {
            createHeader(information,         document);
            createSummary(information,        document);
            createSkills(information,         document);
            createEducation(information,      document);
            createWorkExperience(information, document);
            createInterest(information,       document);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            try {
                output.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createHeader(ResumeInformation information, Document document) throws DocumentException {

        document.add(new Chunk(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2)));
        Paragraph paragraph = createParagraph(20, 20, 20, 20);
        paragraph.setLeading(5.0f, 1.0f);

        Chunk nameChunk = new Chunk(information.getName());
        paragraph.add(nameChunk);
        paragraph.add(Chunk.NEWLINE);
        document.add(paragraph);

        Chunk addressChunk = new Chunk(information.getAddress() + "  ");
        paragraph.add(addressChunk);

        Chunk cityStateAndZip = new Chunk(information.getCity() + ", " + information.getZipCode());
        paragraph.add(cityStateAndZip);
        paragraph.add(Chunk.NEWLINE);
        document.add(paragraph);

        paragraph = createParagraph(10, 10, 20, 20);
        Chunk email = new Chunk(information.getEmail());
        paragraph.add(email);
        paragraph.add(Chunk.NEWLINE);

        document.add(paragraph);
        document.add(new Chunk(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2)));
    }

    private void createSkills(ResumeInformation information, Document document) throws DocumentException {

        Paragraph paragraph = createParagraph(10, 10, 0, 20);
        Font fontbold = FontFactory.getFont("Times-Roman", 30, Font.BOLD);
        paragraph.setFont(fontbold);
        paragraph.setSpacingAfter(25);
        paragraph.setSpacingBefore(25);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setLeading(5.0f, 1.0f);

        Chunk chunk = new Chunk("Skills:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
        paragraph.add(chunk);
        paragraph.add(Chunk.NEWLINE);
        document.add(paragraph);

        System.out.println("Skills " + information.getSkills());
        document.add(addInfo(information.getSkills(), 20));
        document.add(new Chunk(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2)));
    }

    private void createWorkExperience(ResumeInformation information, Document document) throws DocumentException {

        Paragraph paragraph = createParagraph(10, 10, 0, 20);
        Font fontbold = FontFactory.getFont("Times-Roman", 30, Font.BOLD);
        paragraph.setFont(fontbold);
        paragraph.setSpacingAfter(25);
        paragraph.setSpacingBefore(25);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        Chunk chunk = new Chunk("Employment:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
        paragraph.setLeading(5.0f, 1.0f);

        paragraph.add(chunk);
        paragraph.add(Chunk.NEWLINE);
        document.add(paragraph);

        System.out.println("getting employment " + information.getEmploymentLine1());
        document.add(addInfo(information.getEmploymentLine1(), 20));
        document.add(addInfo(information.getEmploymentLine2(), 40));
        document.add(addInfo(information.getEmploymentLine3(), 60));
        document.add(addInfo(information.getEmploymentLine4(), 80));
        document.add(addInfo(information.getEmploymentLine5(), 100));
        document.add(addInfo(information.getEmploymentLine6(), 120));
        document.add(new Chunk(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2)));

    }


    private void createEducation(ResumeInformation information, Document document) throws DocumentException{

        Paragraph paragraph = createParagraph(10, 10, 0, 20);
        Font fontbold = FontFactory.getFont("Times-Roman", 30, Font.BOLD);
        paragraph.setFont(fontbold);
        paragraph.setSpacingAfter(25);
        paragraph.setSpacingBefore(25);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setLeading(5.0f, 1.0f);

        Chunk chunk = new Chunk("Education:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));

        paragraph.add(chunk);
        document.add(paragraph);

        document.add(addInfo(information.getEducationLine1(), 20));
        document.add(addInfo(information.getEducationLine2(), 40));
        document.add(addInfo(information.getEducationLine3(), 60));
        document.add(addInfo(information.getEducationLine4(), 80));
        document.add(addInfo(information.getEducationLine5(), 100));
        document.add(addInfo(information.getEducationLine6(), 120));
        document.add(new Chunk(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2)));

    }

    private Paragraph addInfo(String info, int ident) {

        Paragraph paragraph = createParagraph(10, 10, 20, 20);
        Font fontbold       = FontFactory.getFont("Times-Roman", 20, Font.BOLD);
        paragraph.setFont(fontbold);
        paragraph.setSpacingAfter(25);
        paragraph.setSpacingBefore(25);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setIndentationRight(ident);

        if (isNotEmpty(info)) {
            System.out.println("INFO " + info);
            Chunk chunk = new Chunk(info);
            chunk.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 30));
            paragraph.add(chunk);
        }
        return paragraph;
    }

    private Paragraph createParagraph() {

         return  createParagraph(50,50,50,50);
    }

    private Paragraph createParagraph(int before, int after, int left, int right) {
        Paragraph paragraph = new Paragraph();
        Font fontbold = new Font(Font.FontFamily.HELVETICA  , 25, Font.BOLD);
        paragraph.setFont(fontbold);
        paragraph.setSpacingAfter(after);
        paragraph.setSpacingBefore(before);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setIndentationLeft(left);
        paragraph.setIndentationRight(right);

        return paragraph;
    }

    private boolean isNotEmpty(String str) {
        return ((str != null) && (!str.isEmpty()));
    }

    private void createSummary(ResumeInformation information, Document document) throws DocumentException {
        Paragraph paragraph = createParagraph(20, 20, 20, 20);
        Font fontbold = FontFactory.getFont("Times-Roman", 30, Font.BOLD);
        paragraph.setFont(fontbold);
        paragraph.setSpacingAfter(25);
        paragraph.setSpacingBefore(25);
        paragraph.setAlignment(Element.ALIGN_LEFT);

        Chunk chunk1 = new Chunk("Objectives: ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 30));
        paragraph.setLeading(5.0f, 1.0f);
        paragraph.add(chunk1);
        paragraph.add(Chunk.NEWLINE);
        Phrase phrase = new Phrase();

        for (String career : information.getCareerObjectives()) {
            if (isNotEmpty(career)) {
                Chunk chunk = new Chunk(career,
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 30, Font.BOLD, new BaseColor(255, 0,
                                0)));
                phrase.add(chunk);

            }
        }
        paragraph.add(phrase);

        document.add(paragraph);
        document.add(new Chunk(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2)));
    }

    private void createInterest(ResumeInformation information, Document document) throws DocumentException {
        Paragraph paragraph = createParagraph(10, 10, 0, 20);
        Font fontbold = FontFactory.getFont("Times-Roman", 30, Font.BOLD);
        paragraph.setFont(fontbold);
        paragraph.setSpacingAfter(25);
        paragraph.setSpacingBefore(25);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setLeading(5.0f, 1.0f);

        Chunk chunk = new Chunk("Interests:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 30));
        paragraph.add(chunk);
        paragraph.add(Chunk.NEWLINE);

        String interest = information.getHobbies();
        if (isNotEmpty(interest)) {
            String[] interests = interest.split(",");
            List list = new List(true, 20);
            for (String str : interests) {
                list.add(new ListItem(str, FontFactory.getFont(FontFactory.TIMES_ROMAN, 30)));
            }
            paragraph.add(list);
        }
        document.add(paragraph);
        document.add(new Chunk(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2)));
    }

}
