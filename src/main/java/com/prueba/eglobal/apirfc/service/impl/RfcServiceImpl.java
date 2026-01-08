package com.prueba.eglobal.apirfc.service.impl;

import com.prueba.eglobal.apirfc.dto.ResponseRFC;
import com.prueba.eglobal.apirfc.exeption.InvalidDataRfcException;
import com.prueba.eglobal.apirfc.model.Person;
import com.prueba.eglobal.apirfc.repository.RfcRepository;
import com.prueba.eglobal.apirfc.service.RfcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RfcServiceImpl implements RfcService {
    private RfcRepository rfcRepository;
    public void RfcService(RfcRepository rfcRepository){
        this.rfcRepository = rfcRepository;
    }
    @Override
    public ResponseRFC generarRfc(String nombre, String fechaNac) throws InvalidDataRfcException {
        log.info("RFC CONTROLLER V1>>>>>>>>INICIA GENERAR RFC");
        rfcRepository.save(new Person(nombre,fechaNac,"rfccc"));
        return new ResponseRFC("null","rfc1");
    }
}
