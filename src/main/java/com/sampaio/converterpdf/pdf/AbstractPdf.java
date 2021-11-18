package com.sampaio.converterpdf.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public abstract class AbstractPdf {

    private final Document document;
    private final File tempFile;

    public AbstractPdf() {
        this.document = new Document(PageSize.A4);
        this.tempFile = createFile();

        document.open();
    }

    protected abstract void process();

    protected byte[] getPdf() {
        process();

        document.close();

        try {
            final byte[] bytesFile = Files.readAllBytes(tempFile.toPath());

            tempFile.deleteOnExit();

            return bytesFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private File createFile() {
        try {
            final File tempFile = File.createTempFile("temp", "pdf");

            PdfWriter.getInstance(this.document, new FileOutputStream(tempFile));

            return tempFile;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void addElement(final Element element) {
        try {
            if (this.document.isOpen()) {
                this.document.add(element);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    protected float getScalePercentage(final float widthImage) {
        final int indentation = 0;

        final float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin() - indentation) / widthImage) * 100;

        return scaler;
    }

}
