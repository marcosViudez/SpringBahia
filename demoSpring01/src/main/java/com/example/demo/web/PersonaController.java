package com.example.demo.web;

import com.example.demo.dao.IPersonaDAO;
import com.example.demo.entity.Persona;
import com.example.demo.service.IPersonaService;
import com.example.demo.validation.PersonaValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersonaController {

    @Autowired
    private PersonaValidation validador;

    private IPersonaService personaService;

    @Autowired
    public PersonaController(IPersonaService personaService){
        this.personaService = personaService;
    }

    @GetMapping("/index")
    public String index(Model model)
    {
        model.addAttribute("titulo","listado de personas");
        model.addAttribute("personas", personaService.findAll());
        return "index";
    }

    @GetMapping("/formulario")
    public String formulario(Model model)
    {
        Persona persona = new Persona();
        model.addAttribute("titulo","formulario personas");
        model.addAttribute("persona", persona);
        return "formulario";
    }

    @ModelAttribute("paises")
    public List<String> paises()
    {
        return Arrays.asList("España", "Francia", "EEUU");
    }

    @PostMapping("/formulario")
    public String procesarForm(@Valid Persona persona, BindingResult result, Model model)
    {
        validador.validate(persona,result);
        model.addAttribute("titulo","resultado persona");

        if(result.hasErrors())
        {
            return "formulario";
        }

        personaService.save(persona);
        model.addAttribute("persona", persona);
        return "redirect:index";
    }

    @GetMapping("/formulario/{id}")
    public String editar(@PathVariable Long id, Model model)
    {
        Persona persona = null;
        if(id > 0)
        {
            persona = personaService.findOne(id);
        }else{
            return "redirect:/index";
        }

        model.addAttribute("persona", persona);
        model.addAttribute("titulo","editar persona");
        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id)
    {
        if(id > 0)
        {
            personaService.eliminar(id);
        }
        return "redirect:/index";
    }

}
