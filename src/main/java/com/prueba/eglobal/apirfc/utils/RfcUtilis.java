package com.prueba.eglobal.apirfc.utils;

import com.prueba.eglobal.apirfc.dto.RequestRFC;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RfcUtilis {
    public static Boolean validarDatos(RequestRFC requestRFC){
        log.info("Validando informacion>>>>>");
        if (requestRFC.nombre() == null || requestRFC.nombre().trim().isEmpty()
                || requestRFC.fechaNacimiento() == null || requestRFC.fechaNacimiento().trim().isEmpty()){
            log.info("nombre o fecha nacimiento vacios o nulos");
            return false;
        }
        return true;
    }


}