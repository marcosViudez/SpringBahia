package com.example.demo.dao;

import com.example.demo.entity.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPersonaDAO extends CrudRepository<Persona, Long> {

}
