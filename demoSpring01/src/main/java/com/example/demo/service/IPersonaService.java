package com.example.demo.service;

import com.example.demo.entity.Persona;

import java.util.List;

public interface IPersonaService {

    public List<Persona> findAll();

    public void save(Persona p);

    public void eliminar(Long id);

    public Persona findOne(Long id);
}
