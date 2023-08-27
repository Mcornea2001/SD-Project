package com.example.Assignment3.Service;

import com.example.Assignment3.model.Contract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFService {

    public byte[] generatePdfWithObjectDetails(Contract contract) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Add text to the PDF page
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 700); // Set the starting position
            contentStream.showText("Customer name: " + contract.getCustomerName());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Employee name: " + contract.getEmployeeName());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Car brand: " + contract.getCar().getBrand());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Car model: " + contract.getCar().getModel());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Location: " + contract.getLocation());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Period: " + contract.getPeriod());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Price: " + contract.getPrice());
            contentStream.endText();

            contentStream.close();

            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }
}
