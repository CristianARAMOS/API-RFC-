package com.prueba.eglobal.apirfc.service.impl;

import com.prueba.eglobal.apirfc.dto.PersonDto;
import com.prueba.eglobal.apirfc.dto.ResponseRFC;
import com.prueba.eglobal.apirfc.exeption.DataNoExistException;
import com.prueba.eglobal.apirfc.exeption.InvalidDataRfcException;
import com.prueba.eglobal.apirfc.model.Person;
import com.prueba.eglobal.apirfc.repository.RfcRepository;
import com.prueba.eglobal.apirfc.service.RfcService;
import com.prueba.eglobal.apirfc.utils.RfcUtilis;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class RfcServiceImpl implements RfcService {
    private RfcRepository rfcRepository;
    public  RfcServiceImpl(RfcRepository rfcRepository){
        this.rfcRepository = rfcRepository;
    }
    @Override
    public ResponseRFC generarRfc(String nombre, String fechaNac) throws InvalidDataRfcException {
        log.info("RFC CONTROLLER V1>>>>>>>>INICIA GENERAR RFC");
        String rfc = RfcUtilis.generarRFC(nombre.split(" "), fechaNac);
        Person persona = new  Person(nombre,fechaNac,rfc);
        rfcRepository.save(persona);
        return new ResponseRFC("null",rfc);
    }

    @Override
    public PersonDto obtenerPerson( String data) throws DataNoExistException {
        log.info("OBTENIENDO REGISTRO>>>>>><");
        try {
            String type = RfcUtilis.ValidarTipo(data);
            Optional<Person> personaOptional = switch (type) {
                case "RFC" -> rfcRepository.findByRfc(data);
                case "NAME" -> rfcRepository.findByNombre(data);
                case "FECHA" -> rfcRepository.findByFechaNacimiento(data);
                default -> Optional.empty();
            };
            Person persona = personaOptional
                    .orElseThrow(() -> new DataNoExistException(
                            "No se encontró persona con " + type + ": " + data
                    ));


            return RfcUtilis.convertirAPersonDto(persona);
        } catch (Exception e){
            log.error("REGISTRO DUPLICADO EN DB");
            throw new DataNoExistException("Registros duplicados en la DB");
        }

    }

    @Override
    public List<PersonDto> obtenerTodosRegistros() {
        log.info("OBTENIENDO TODOS LOS REGISTROS>>>>>><");

        return rfcRepository.findAll().
                stream().map(RfcUtilis::convertirAPersonDto)
                .toList();
    }
}
