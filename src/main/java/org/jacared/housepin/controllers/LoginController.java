package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.services.anunciante.AnuncianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    private AnuncianteService anuncianteService;

    @RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        modelAndView.addObject("username", name);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro(){
        ModelAndView modelAndView = new ModelAndView();
        Anunciante anunciante = new Anunciante();
        modelAndView.addObject("anunciante", anunciante);
        modelAndView.setViewName("cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroDeNovoAnunciante(@Valid Anunciante anunciante, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Anunciante anuncianteExists = anuncianteService.buscarAnunciantePorEmail(anunciante.getEmail());
        if (anuncianteExists != null) {
            bindingResult
                    .rejectValue("email", "error.anunciante",
                            "Já existe um usuário registrado com o email fornecido.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("cadastro");
        } else {
            anuncianteService.adicionar(anunciante);
            modelAndView.addObject("successMessage", "Anunciante foi registrado com sucesso");
            modelAndView.addObject("anunciante", new Anunciante());
            modelAndView.setViewName("cadastro");

        }
        return modelAndView;
    }

//    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//    public ModelAndView home1(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Anunciante anunciante = anuncianteService.buscarAnunciantePorEmail(auth.getName());
//        modelAndView.addObject("anuncianteName", "Welcome " + anunciante.getNome() + " (" + anunciante.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuário com papel de anunciante");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }
}
