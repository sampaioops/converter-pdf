package com.sampaio.converterpdf.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

import java.io.IOException;
import java.util.List;

public class PdfFromImages extends AbstractPdf implements PDF {

    private final List<byte[]> images;

    public PdfFromImages(final List<byte[]> images) {
        this.images = images;
    }

    @Override
    public byte[] getArchive() {
        return getPdf();
    }

    @Override
    protected void process() {
        images.forEach(bytes -> {
            try {
                final Image image = Image.getInstance(bytes);

                final float scalePercentage = getScalePercentage(image.getWidth());
                image.scalePercent(scalePercentage);

                addElement(image);

            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
