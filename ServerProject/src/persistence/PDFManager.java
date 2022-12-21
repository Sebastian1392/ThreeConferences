package persistence;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import models.Conference;
import models.IOConference;
import servers.NodeTree;

public class PDFManager {

    private static final float[] SIZE_COLS = new float[] {6.0f, 6.0f};
    private static final String TITLE_CONFERENCE = "Lecturers And Assantist";
    private static final String POINT_CHARACTER = "\u2022 ";
    public static final String EXTENTION = "_report.pdf";
    public static final String PDF_PATH = "reports/";
    public static final String REPORT = " Report";
    public static final String SPACE = " ";
    public static final String[] HEAD_TABLE = {"Name","Appointment"};
    public static final Font TITLE_FONT = new Font(Font.HELVETICA,20,Font.BOLDITALIC,Color.BLUE); 
    public static final Font SUBTITLE_FONT = new Font(Font.HELVETICA,14,Font.BOLDITALIC,Color.BLACK); 
    public static final Font TEXT_FONT = new Font(Font.HELVETICA,12,Font.NORMAL,Color.BLACK); 

    public static String generatePdf(NodeTree<IOConference> nodeReport) {
        Document document = new Document();
        String filePath = PDF_PATH + nodeReport.getData().getName() + EXTENTION;
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Paragraph paragraph = new Paragraph(nodeReport.getData().getName() + SPACE +
            nodeReport.getData().typeConference() + REPORT,TITLE_FONT);
            document.add(paragraph);
            if(nodeReport.getChildren().size() > 0){
                for (NodeTree<IOConference> child : nodeReport.getChildren()) {
                    addSubtitle(child,document);
                }
            }else{
                addInformation(nodeReport,document);
            }
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private static void addInformation(NodeTree<IOConference> nodeReport, Document document){
        List list = new List();
        list.setListSymbol(SPACE);
        list.setIndentationLeft(20);
        
        ListItem subtitle = new ListItem(nodeReport.getData().typeConference(),SUBTITLE_FONT);
        list.add(subtitle);
        
        List childList = new List();
        childList.setListSymbol(POINT_CHARACTER);
        childList.setIndentationLeft(20);
        
        ListItem text = new ListItem(nodeReport.getData().getName(),TEXT_FONT);
        childList.add(text);
        
        list.add(childList);
        document.add(list);
    }

    private static void addSubtitle(NodeTree<IOConference> nodeReport, Document document){
        List list = new List();
        list.setListSymbol(SPACE);
        list.setIndentationLeft(20);
        if(nodeReport.getData().typeConference().equals(Conference.TYPE_CONFERENCE) && 
        nodeReport.getChildren().size() > 0){
            addTable(list,nodeReport, document);
        }else{
            ListItem subtitle = new ListItem(nodeReport.getData().typeConference(),SUBTITLE_FONT);
            list.add(subtitle);
            
            List childList = new List();
            childList.setListSymbol(POINT_CHARACTER);
            childList.setIndentationLeft(20);
            
            ListItem text = new ListItem(nodeReport.getData().getName(),TEXT_FONT);
            childList.add(text);
            
            list.add(childList);
            document.add(list);
            
            for (NodeTree<IOConference> child : nodeReport.getChildren()) {
                addSubtitle(child,document);
            }
        }
    }

    private static void addTable(List list,NodeTree<IOConference> nodeReport,Document document){
        addTitleConference(list, nodeReport, document);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80);
        table.setWidths(SIZE_COLS);
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(HEAD_TABLE[0], SUBTITLE_FONT));
        table.addCell(cell);
        cell.setPhrase(new Phrase(HEAD_TABLE[1], SUBTITLE_FONT));
        table.addCell(cell);
        for (NodeTree<IOConference> child : nodeReport.getChildren()) {
            table.addCell(child.getData().getName());
            table.addCell(child.getData().typeConference());
        }
        document.add(table);
    }

    private static void addTitleConference(List list, NodeTree<IOConference> nodeReport, Document document) {
        ListItem subtitle = new ListItem(nodeReport.getData().typeConference(),SUBTITLE_FONT);
        list.add(subtitle);
        List childList = new List();
        childList.setListSymbol(POINT_CHARACTER);
        childList.setIndentationLeft(20);
        ListItem text = new ListItem(nodeReport.getData().getName(),TEXT_FONT);
        childList.add(text);
        list.add(childList);
        ListItem conference = new ListItem(TITLE_CONFERENCE,SUBTITLE_FONT);
        list.add(conference);
        list.add(new ListItem(SPACE));
        document.add(list);
    }
}
