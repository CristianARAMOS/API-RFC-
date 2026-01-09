package com.prueba.eglobal.apirfc.controller;

import com.prueba.eglobal.apirfc.dto.PersonDto;
import com.prueba.eglobal.apirfc.dto.RequestRFC;
import com.prueba.eglobal.apirfc.dto.ResponseRFC;
import com.prueba.eglobal.apirfc.exeption.DataNoExistException;
import com.prueba.eglobal.apirfc.exeption.InvalidDataRfcException;
import com.prueba.eglobal.apirfc.service.impl.RfcServiceImpl;
import com.prueba.eglobal.apirfc.utils.RfcUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.badRequest().body(new ResponseRFC("001","NOMBRE O FECHA NACIMIENTO INVALIDOS","null"));
        }

    }

    @GetMapping("/obtain")
    public ResponseEntity<?> obtenerDatosPersona(@RequestParam(required = false) String data){

        try {
            if (data == null || data.isEmpty() || data.equalsIgnoreCase("null")){
                List<PersonDto> personas = rfcService.obtenerTodosRegistros();
                return ResponseEntity.ok(personas);
            }
            PersonDto resultado = rfcService.obtenerPerson(data);

            return ResponseEntity.ok(resultado);
        } catch (DataNoExistException e) {
        return ResponseEntity.badRequest().body(new ResponseRFC("002","NO EXISTE INFORMACION O MULTIPLES REGISTROS CON EL CRITERIO DE BUSQUEDA","null"));

        }
    }




}
