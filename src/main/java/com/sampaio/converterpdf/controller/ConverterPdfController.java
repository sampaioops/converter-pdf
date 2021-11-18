package com.sampaio.converterpdf.controller;

import com.sampaio.converterpdf.service.ConverterPdfService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pdf")
public class ConverterPdfController {

    private final ConverterPdfService converterPdfService;

    public ConverterPdfController(final ConverterPdfService converterPdfService) {
        this.converterPdfService = converterPdfService;
    }

    @PostMapping(value = "/v1.0",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> convertPdfFromImages(@RequestParam("files") final MultipartFile[] files) {
        final List<byte[]> images = Arrays.stream(files).map(file -> {

            try {
                return file.getBytes();
            } catch (IOException e) {
                return null;
            }
        }).collect(Collectors.toList());

        final var imagesConvertedToPdf = converterPdfService.convertImagesToPdf(images);

        final var inputStreamResource = new InputStreamResource(new ByteArrayInputStream(imagesConvertedToPdf));

        return ResponseEntity.ok(inputStreamResource);
    }

    @GetMapping
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Steal life is good");
    }
}
