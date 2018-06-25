package org.sber.www.docophila.ru.OCR;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OCR {
    public static BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toOCR(List<BufferedImage> inputFile, String language) {
        StringBuilder result = new StringBuilder();
        inputFile.forEach((img)->result.append(toOCR(img,language)));
        return result.toString();
    }

    public static String toOCR(BufferedImage inputFile, String language) {
        String result = new String();
        ITesseract instance = new Tesseract();
        instance.setLanguage(language);
        instance.setDatapath(System.getProperty("user.dir")+File.separator + "tessdata");
        try {
            result = instance.doOCR(inputFile);
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static List<BufferedImage> PdfToPngList(byte[] inputPdfFile) throws IOException {
        PDDocument document = PDDocument.load(inputPdfFile);
        PDPageTree pdf = document.getPages();
        PDFRenderer renderer = new PDFRenderer(document) ;
        int countList = pdf.getCount();
        List<BufferedImage> arraayImage = new ArrayList<>();
        for(int i = 0; i < countList; i++){
            arraayImage.add(renderer.renderImage(i));
        }
        return arraayImage;
    }
}
