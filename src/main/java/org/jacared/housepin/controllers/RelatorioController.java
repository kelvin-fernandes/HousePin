package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.services.anunciante.AnuncianteService;
import org.jacared.housepin.services.anuncio.AnuncioService;
import org.jacared.housepin.utils.EnumFinalidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RelatorioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private AnuncianteService anuncianteService;

    @GetMapping(value = {"/relatorio"})
    public ModelAndView relatorio(@RequestParam("finalidade") String finalidade) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        EnumFinalidade enumFinalidade = EnumFinalidade.valueOf(finalidade.toUpperCase());

        List<Anuncio> anuncios = anuncioService.buscarAnunciosDoAnunciantePorFinalidade(currentPrincipalName, enumFinalidade);

        double valorTotal = 0;
        for(Anuncio anuncio : anuncios){
            valorTotal += anuncio.getValor();
        }

        Usuario usuario = anuncianteService.buscarAnunciantePorEmail(currentPrincipalName);

        //cabeçalho
        modelAndView.addObject("finalidade", enumFinalidade.toString());
        modelAndView.addObject("nomeUsuario", usuario.getNome());

        //corpo
        modelAndView.addObject("anuncios", anuncios);
        modelAndView.addObject("valorTotal", valorTotal);

        modelAndView.setViewName("/relatorios/relatorio");

        return modelAndView;
    }
}
