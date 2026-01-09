package com.prueba.eglobal.apirfc.service;

import com.prueba.eglobal.apirfc.dto.PersonDto;
import com.prueba.eglobal.apirfc.dto.ResponseRFC;
import com.prueba.eglobal.apirfc.exeption.DataNoExistException;
import com.prueba.eglobal.apirfc.exeption.InvalidDataRfcException;
import com.prueba.eglobal.apirfc.exeption.InvalidFormatExeption;


import java.util.List;


public interface RfcService {
    public ResponseRFC generarRfc(String nombre, String fechaNac) throws InvalidDataRfcException;
    public PersonDto obtenerPerson(String data) throws DataNoExistException, InvalidFormatExeption;
    public List<PersonDto> obtenerTodosRegistros();

}
