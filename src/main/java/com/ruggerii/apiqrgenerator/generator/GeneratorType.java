package com.ruggerii.apiqrgenerator.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public enum GeneratorType {

    QR_CODE("QR_CODE");


    private String type;

}
