package com.ruggerii.apiqrgenerator.generator;

import com.ruggerii.apiqrgenerator.config.exceptions.GeradorException;

public class Geradores {

    public static Gerador obterTipoGerador(GeneratorType generatorType) {
        Gerador gerador;
        switch (generatorType) {
            case QR_CODE: {
                gerador = new QrCodeGenerator();
                break;
            }
            default: {
                throw new GeradorException("Gerador não encontrado");
            }
        }
        return gerador;
    }

}
