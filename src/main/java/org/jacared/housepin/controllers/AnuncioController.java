package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.services.anuncio.AnuncioService;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = {"/anuncio/cadastro"}, method = RequestMethod.GET)
    public ModelAndView cadastro(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("anuncio/cadastro");
        modelAndView.addObject("visualizando", false);
        if(id == 0){
            modelAndView.addObject("anuncio", new Anuncio());
            return modelAndView;
        }

        Anuncio anuncio = anuncioService.buscarAnuncioPorId(id).get();
        modelAndView.addObject("anuncio", anuncio);
        return modelAndView;
    }

    @RequestMapping(value = {"/anuncio"}, method = RequestMethod.GET)
    public ModelAndView visualizar(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Anuncio anuncio = anuncioService.buscarAnuncioPorId(id).get();
        modelAndView.addObject("anuncio", anuncio);
        modelAndView.addObject("visualizando", true);
        modelAndView.setViewName("anuncio/detalhes");
        return modelAndView;
    }

//    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Usuario usuario = usuarioService.buscarUsuarioPorEmail(auth.getName());
//        modelAndView.addObject("userName", "Bem vindo" + usuario.getNome() +  " (" + usuario.getEmail() + ")");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }

    @RequestMapping(value = {"/anuncio/cadastro"}, method = RequestMethod.POST)
    public String cadastro(@ModelAttribute Anuncio anuncio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Usuario anunciante = usuarioService.buscarUsuarioPorEmail(authentication.getName());
            anuncio.setAnunciante((Anunciante) anunciante);
            anuncioService.adicionar(anuncio);
            return "redirect:/";
        }

        return "/anuncio/cadastro";
    }

//    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//    public ModelAndView home1(){
//        ModelAndView modelAndView = new ModelAndView();
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        Anunciante usuario = (Anunciante) usuarioService.buscarUsuarioPorEmail(auth.getName());
////        modelAndView.addObject("anuncianteName", "Welcome " + usuario.getNome() + " (" + usuario.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuário com papel de usuario");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }
}
