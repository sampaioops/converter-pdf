package com.sampaio.converterpdf.service;

import com.sampaio.converterpdf.pdf.PDF;
import com.sampaio.converterpdf.pdf.PDFCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ConverterPdfServiceImpl implements ConverterPdfService {

    private final PDFCreator pdfCreator;

    public ConverterPdfServiceImpl(@Qualifier("pdfFromImagesCreator") final PDFCreator pdfCreator) {
        this.pdfCreator = pdfCreator;
    }

    @Override
    public byte[] convertImagesToPdf(final List<byte[]> images) {
        final PDF pdf = pdfCreator.createPdfFromImages(images);

        return pdf.getArchive();
    }
}
