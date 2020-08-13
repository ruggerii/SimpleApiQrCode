package com.ruggerii.apiqrgenerator.services;

import com.ruggerii.apiqrgenerator.generator.GeneratorType;
import com.ruggerii.apiqrgenerator.generator.Geradores;
import com.ruggerii.apiqrgenerator.models.dto.MensagemDTO;
import com.ruggerii.apiqrgenerator.services.interfaces.IQrCodeGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QrCodeGeneratorService implements IQrCodeGeneratorService {


    @Override
    public Resource gerarQrCode(final MensagemDTO hash) {
        final var qrGenerator = Geradores.obterTipoGerador(GeneratorType.QR_CODE);
        return qrGenerator.gerar(hash.getMensagem()).getResource();
    }

}
