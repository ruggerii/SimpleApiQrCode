package com.ruggerii.apiqrgenerator.generator;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ruggerii.apiqrgenerator.config.exceptions.GeradorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@Slf4j
public class QrCodeGenerator implements Gerador {

    @Override
    public MultipartFile gerar(final String hash) {
        final var encoder = new QRCodeWriter();
        final Integer largura = 300;
        final Integer altura = 300;
        final BitMatrix qrCode;

        try {
            qrCode = encoder.encode(hash, BarcodeFormat.QR_CODE, largura, altura);
            final ByteArrayOutputStream imagem = new ByteArrayOutputStream();
            final BufferedImage bufferImage = gerarImagem(largura, altura, qrCode);

           ImageIO.write(bufferImage, "jpg", imagem);
            final MultipartFile file = gerarMultiPartFile(imagem);
            return file;
        } catch (WriterException | IOException ex) {
            log.error("Falha ao gerar QRCODE", ex);
            throw new GeradorException("Falha ao gerar QRCODE");
        }
    }

    private MultipartFile gerarMultiPartFile(final ByteArrayOutputStream imagem) throws IOException {
        final InputStream in = new ByteArrayInputStream(imagem.toByteArray());
        final File file = File.createTempFile("tmp", ".jpg");

        copyInputStreamToFile(in, file);
        final FileItem fileItem =
                new DiskFileItem(
                        "file",
                        Files.probeContentType(file.toPath()),
                        false,
                        file.getName(),
                        (int) file.length(),
                        file.getParentFile());

        final InputStream input = new FileInputStream(file);
        final OutputStream os = fileItem.getOutputStream();
        IOUtils.copy(input, os);
        final MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        return multipartFile;
    }

    private void copyInputStreamToFile(final InputStream inputStream, final File file)
            throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[inputStream.available()];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        }

    }

    private BufferedImage gerarImagem(final Integer largura, final Integer altura, final BitMatrix bitMatrix) {
        final BufferedImage bufferDaImagem = new BufferedImage(largura, largura,
                BufferedImage.TYPE_INT_RGB);

        bufferDaImagem.createGraphics();

        final Graphics2D grafico = (Graphics2D) bufferDaImagem.getGraphics();
        grafico.setColor(Color.WHITE);
        grafico.fillRect(0, 0, largura, altura);
        grafico.setColor(Color.BLACK);

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < largura; j++) {
                if (bitMatrix.get(i, j)) {
                    grafico.fillRect(i, j, 1, 1);
                }
            }
        }

        return bufferDaImagem;
    }


}
