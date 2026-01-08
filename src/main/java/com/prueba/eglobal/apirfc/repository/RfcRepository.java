package com.prueba.eglobal.apirfc.repository;

import com.prueba.eglobal.apirfc.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RfcRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByNombre(String nombre);
    Optional<Person> findByRfc(String rfc);
}
