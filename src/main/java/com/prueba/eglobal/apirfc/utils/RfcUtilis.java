package com.prueba.eglobal.apirfc.utils;

import com.prueba.eglobal.apirfc.dto.PersonDto;
import com.prueba.eglobal.apirfc.dto.RequestRFC;
import com.prueba.eglobal.apirfc.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;


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

    public static PersonDto convertirAPersonDto(Person person){
        PersonDto personDto = new PersonDto(person.getNombre(), person.getFechaNacimiento(), person.getRfc());
        return personDto;
    }

    public static String ValidarTipo(String data){
        log.info("VALIDANDO TIPO DE CONSULTA>>>>>>>>>>");
        if(data.length() == 8 && data.charAt(4) == '1'){
            log.info("ES FECHA NACI");
            return  "FECHA";
        }
        if (data.length() == 10 && Character.isDigit(data.charAt(9))) {
            log.info("ES RFC");
            return "RFC";
        }
        return "NAME";
    }


    private static String[] extraerNombres(String[] partes) {
        log.info("SEPARANDO NOMBRE Y APELLIDOS>>>>>");
            int totalPartes = partes.length;


            String apellidoPaterno = partes[totalPartes - 2];
            String apellidoMaterno = partes[totalPartes - 1];


            StringBuilder nombres = new StringBuilder();
            for (int i = 0; i < totalPartes - 2; i++) {
                if (i > 0) nombres.append(" ");
                nombres.append(partes[i]);
            }

            return new String[]{
                    nombres.toString(),
                    apellidoPaterno,
                    apellidoMaterno
            };
    }


    private static String extraerPrimeraLetra(String apellido) {
        log.info("OBTENIENDO PRIMERA LETRA>>>>>");

        String primeraLetra = apellido.substring(0, 1);


        if (!Character.isLetter(primeraLetra.charAt(0))) {
            return "X";
        }

        return primeraLetra;
    }

    private static String extraerVocalInterna(String apellido) {
        log.info("OBTENIENDO VOCAL>>");
        if (apellido == null || apellido.length() < 2) {
            return "X";
        }


        for (int i = 1; i < apellido.length(); i++) {
            char letra = Character.toLowerCase(apellido.charAt(i));

            if (esVocal(letra)) {
                return String.valueOf(apellido.charAt(i));
            }
        }


        return "X";
    }

    private static boolean esVocal(char letra) {
        letra = Character.toLowerCase(letra);
        return letra == 'a' || letra == 'e' || letra == 'i' ||
                letra == 'o' || letra == 'u';
    }
    private static String extraerFechaRFC(String fechaNacimiento) {

        String año = fechaNacimiento.substring(2, 4);


        String mes = fechaNacimiento.substring(4, 6);


        String dia = fechaNacimiento.substring(6, 8);

        return año + mes + dia;
    }

    private static String extraerPrimeraLetraNombre(String nombreCompleto) {
        log.info("OBTENIENDO PRIMERA LETRA NOMBRE>>>>>");
        if (nombreCompleto == null || nombreCompleto.isEmpty()) {
            return "X";
        }


        String[] partes = nombreCompleto.split(" ");


        String primerNombre = partes[0];


        if (primerNombre.length() > 0) {
            char primeraLetra = primerNombre.charAt(0);

            if (Character.isLetter(primeraLetra)) {
                return String.valueOf(primeraLetra);
            }
        }

        return "X";
    }
    public static String generarRFC(String[] nombres, String fechaNacimiento) {
        String nombre = extraerNombres(nombres)[0];
        String apellidoPaterno = extraerNombres(nombres)[1];
        String apellidoMaterno = extraerNombres(nombres)[2];
        log.info("EMPIEZA GENERACION DE RFC A 10 DIGITOS>>>>>>>>>");

        String primeraLetraApellidoPaterno = extraerPrimeraLetra(apellidoPaterno);
        String vocalInternaApellidoPaterno = extraerVocalInterna(apellidoPaterno);
        String primeraLetraApellidoMaterno = extraerPrimeraLetra(apellidoMaterno);
        String primeraLetraNombre = extraerPrimeraLetraNombre(nombre);

        String fechaRFC = extraerFechaRFC(fechaNacimiento);
        StringBuilder rfc = new StringBuilder();
        rfc.append(primeraLetraApellidoPaterno);
        rfc.append(vocalInternaApellidoPaterno);
        rfc.append(primeraLetraApellidoMaterno);
        rfc.append(primeraLetraNombre);
        rfc.append(fechaRFC);

        return rfc.toString().toUpperCase();
    }


}