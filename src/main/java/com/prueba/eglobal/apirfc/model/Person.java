package com.prueba.eglobal.apirfc.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private  String nombre;
    private String fechaNacimiento;
    private String rfc;

    public Person(String nombre,String fechaNacimiento,String rfc){
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
    }

}
