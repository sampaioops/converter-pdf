package com.sampaio.converterpdf.service;

import java.util.List;

public interface ConverterPdfService {

    byte[] convertImagesToPdf(List<byte[]> images);
}
