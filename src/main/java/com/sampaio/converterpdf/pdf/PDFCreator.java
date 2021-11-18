package com.sampaio.converterpdf.pdf;

import java.util.List;

public interface PDFCreator {

    PDF createPdfFromImages(List<byte[]> images);
}
