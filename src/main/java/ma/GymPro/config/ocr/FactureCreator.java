package ma.GymPro.config.ocr;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ma.GymPro.beans.Achat;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class FactureCreator {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public  byte[] createInvoice(Achat achat) throws IOException, DocumentException, com.itextpdf.text.DocumentException {
           /* String path="./Invoice.pdf";
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(path,
                    new WriterProperties().addUAXmpMetadata().setPdfVersion(PdfVersion.PDF_1_7)));
            Document document = new Document(pdfDoc,/ PageSize.A4);
            */
        com.itextpdf.text.Font fontH1 = new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN,42, Font.BOLD);
        fontH1.setColor(BaseColor.WHITE);

        Document document=new Document(PageSize.A4);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, stream);
        document.open();
        float col=400;
        float columnWidth[]={col,col};
        String imageFile = "imgs/logo.png";

        ImageData data = ImageDataFactory.create(imageFile);
        Image img = new Image(data);
        PdfPTable table=new PdfPTable(columnWidth);



        PdfPCell cell1 =new PdfPCell(new Phrase("FACTURE",fontH1));
        cell1.setFixedHeight(100);
        PdfPCell cell2=new PdfPCell(new Phrase("Informations du client : \n \n \n "));
        PdfPCell cell3=new PdfPCell(com.itextpdf.text.Image.getInstance("imgs/logo.png"),true);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell3.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        cell3.setBackgroundColor(BaseColor.BLACK);
        PdfPCell cell4=new PdfPCell();
        PdfPCell cell5=new PdfPCell();
        PdfPCell cell6=new PdfPCell();
        PdfPCell cell7=new PdfPCell();
        PdfPCell cell8=new PdfPCell();
        PdfPCell cell9=new PdfPCell();
        PdfPCell cell10=new PdfPCell();
        PdfPCell cell11=new PdfPCell();
        PdfPCell cell12=new PdfPCell();
        PdfPCell cell13=new PdfPCell();
        PdfPCell cell14=new PdfPCell();
        PdfPCell cell15=new PdfPCell();
        PdfPCell cell16=new PdfPCell();
        PdfPCell cell17=new PdfPCell();

        cell1.setBackgroundColor(BaseColor.BLACK);

        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1 .setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        cell2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setColspan(4);
        table.addCell(cell1);
        table.addCell(cell3);

        float columnDetailsWidth[]={80,220,80,100};
        PdfPTable tableClientDetails=new PdfPTable(columnDetailsWidth);
        tableClientDetails.addCell(new PdfPCell(cell2));
        tableClientDetails.addCell(new PdfPCell(new Phrase("Nom"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableClientDetails.addCell(new PdfPCell(new Phrase(":    "+achat.getClient().getProfil().getNom()+ " "+achat.getClient().getProfil().getPrenom()))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableClientDetails.addCell(new PdfPCell(new Phrase("N°Facture"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableClientDetails.addCell(new PdfPCell(new Phrase(":    FCT"+achat.getFacture().getId()))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableClientDetails.addCell(new PdfPCell(new Phrase("CIN"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableClientDetails.addCell(new PdfPCell(new Phrase(":    "+achat.getClient().getProfil().getCin()))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableClientDetails.addCell(new PdfPCell(new Phrase("Date"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableClientDetails.addCell(new PdfPCell(new Phrase(":    "+ achat.getFacture().getDate().toString()+" \n \n \n \n \n \n \n "))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        float colDetailsAchat[]={30, 70,250,100,30};
        PdfPTable detailsAchatPdfPTable=new PdfPTable(colDetailsAchat);
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);;
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase("Réference")));
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase("Description")));
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase("Prix")));
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);

        //foreach
        for (ma.GymPro.beans.Service service:achat.getServices()) {
            detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
            detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase("SRV"+service.getId()))).setHorizontalAlignment(Element.ALIGN_CENTER);
            detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(service.getDescription()))).setHorizontalAlignment(Element.ALIGN_CENTER);
            detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(service.getPrix()+"DH "))).setHorizontalAlignment(Element.ALIGN_CENTER);
            detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        }


        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase("\n \n \n \n \n "))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        detailsAchatPdfPTable.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        float AchatTotal[]={300,100,100};
        PdfPTable tableTotal =new PdfPTable(AchatTotal);
        tableTotal.addCell(new PdfPCell(new Phrase(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableTotal.addCell(new PdfPCell(new Phrase("Total :"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        tableTotal.addCell(new PdfPCell(new Phrase(achat.getFacture().getMontant()+" DH"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);

        // other lines with the content of qrcode
        BarcodeQRCode qrCode = new BarcodeQRCode(bCryptPasswordEncoder.encode(achat.getClient().getEmail()+achat.getId()+achat.getFacture().getId())+" GYMPRO.MA");


        //Image barcodeImage = new Image(barcodeObject).setWidth(100f).setHeight(100f);
        //QRCode qrCode1=new QRCode();
        com.itextpdf.text.Image QrImage=com.itextpdf.text.Image.getInstance(qrCode.createAwtImage(Color.BLACK,Color.WHITE),Color.BLACK);
        document.add( table);
        document.add(tableClientDetails);
        document.add( detailsAchatPdfPTable);
        document.add( tableTotal);

        document.add(QrImage);

        document.close();

        return stream.toByteArray();
    }
}
