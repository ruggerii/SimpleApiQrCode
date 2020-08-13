package com.ruggerii.apiqrgenerator.generator;

import org.springframework.web.multipart.MultipartFile;

public interface Gerador {

    MultipartFile gerar(final String hash);

}
