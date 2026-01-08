package com.prueba.eglobal.apirfc.service;

import com.prueba.eglobal.apirfc.dto.ResponseRFC;
import com.prueba.eglobal.apirfc.exeption.InvalidDataRfcException;


public interface RfcService {
    public ResponseRFC generarRfc(String nombre, String fechaNac) throws InvalidDataRfcException;
}
