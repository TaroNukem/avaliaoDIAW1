package com.example.avaliacao.controller;

import com.example.avaliacao.Candidato;
import com.example.avaliacao.service.CandidatosTseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService service;

    public CandidatosTseController(CandidatosTseService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String partido,
            @RequestParam(required = false) String texto,
            Model model) {

        if (cargo == null) cargo = "";
        if (partido == null) partido = "";
        if (texto == null) texto = "";

        List<Candidato> candidatos = service.filtrar(cargo, partido, texto);

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("total", candidatos.size());
        model.addAttribute("cargos", service.listarCargos());
        model.addAttribute("partidos", service.listarPartidos());

        model.addAttribute("cargoSelecionado", cargo);
        model.addAttribute("partidoSelecionado", partido);
        model.addAttribute("textoSelecionado", texto);

        return "index";
    }
}