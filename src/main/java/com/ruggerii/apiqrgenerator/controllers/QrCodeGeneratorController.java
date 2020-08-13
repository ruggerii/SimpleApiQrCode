package com.ruggerii.apiqrgenerator.controllers;

import com.ruggerii.apiqrgenerator.models.dto.MensagemDTO;
import com.ruggerii.apiqrgenerator.services.interfaces.IQrCodeGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "qr-code")
@RequiredArgsConstructor
@Api(tags = "Gerador QR Code")
public class QrCodeGeneratorController {

    final IQrCodeGeneratorService service;

    @ApiOperation("Gerar um QR Code por uma String")
    @PostMapping(path = "/gerar", consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource gerarQrCodePorString(@RequestBody final MensagemDTO mensagem) {
        return service.gerarQrCode(mensagem);
    }

}
