package com.ruggerii.apiqrgenerator.services.interfaces;

import com.ruggerii.apiqrgenerator.models.dto.MensagemDTO;
import org.springframework.core.io.Resource;

public interface IQrCodeGeneratorService {

    Resource gerarQrCode(final MensagemDTO hash);
}
