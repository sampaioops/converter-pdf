package com.sampaio.converterpdf.pdf;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdfFromImagesCreator implements PDFCreator {

    @Override
    public PDF createPdfFromImages(final List<byte[]> images) {
        return new PdfFromImages(images);
    }
}
