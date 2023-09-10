package com.example.demo.service;

import com.example.demo.dao.IPersonaDAO;
import com.example.demo.entity.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService{

    @Autowired
    private IPersonaDAO personaDAO;
    @Autowired
    private EntityManager em;

    @Override
    public List<Persona> findAll() {

        // con el DAO crudrepository - return (List<Persona>) personaDAO.findAll();
        return em.createQuery("from Persona").getResultList();
    }

    @Transactional
    @Override
    public void save(Persona p) {
        if(p.getIdpersona() != null && p.getIdpersona() > 0)
        {
            // actualiza registro
            em.merge(p);
        }else{
            // crea uno nuevo
            em.persist(p);
        }
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        em.remove(findOne(id));
    }

    @Override
    public Persona findOne(Long id) {
        return em.find(Persona.class,id);
    }
}
