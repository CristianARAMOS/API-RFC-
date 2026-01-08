package com.prueba.eglobal.apirfc.controller;

import com.prueba.eglobal.apirfc.dto.RequestRFC;
import com.prueba.eglobal.apirfc.dto.ResponseRFC;
import com.prueba.eglobal.apirfc.exeption.InvalidDataRfcException;
import com.prueba.eglobal.apirfc.service.impl.RfcServiceImpl;
import com.prueba.eglobal.apirfc.utils.RfcUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/rfc")
public class RfcController {
    @Autowired
    private RfcServiceImpl rfcService;
    @PostMapping("/generate")
    public ResponseEntity<?> generarRfc(@RequestBody RequestRFC request){

        try {
            if(!RfcUtilis.validarDatos(request)){
                throw new InvalidDataRfcException("DATOS NO VALIDOS");
            }
            return ResponseEntity.ok(rfcService.generarRfc(request.nombre(),request.fechaNacimiento()));
        } catch (InvalidDataRfcException e) {
            return ResponseEntity.badRequest().body(new ResponseRFC("001","null"));
        }

    }
}
