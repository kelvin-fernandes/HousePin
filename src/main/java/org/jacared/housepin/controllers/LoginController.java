package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
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

    @RequestMapping(value="/usuario/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("anunciante", new Anunciante());
        modelAndView.setViewName("cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroDeNovoAnunciante(@Valid Anunciante anunciante, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Anunciante anuncianteExists = (Anunciante) usuarioService.buscarUsuarioPorEmail(anunciante.getEmail());
        if (anuncianteExists != null) {
            bindingResult
                    .rejectValue("email", "error.usuario",
                            "Já existe um usuário registrado com o email fornecido.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("cadastro");
        } else {
            usuarioService.adicionar(anunciante);
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
//        Anunciante usuario = usuarioService.buscarAnunciantePorEmail(auth.getName());
//        modelAndView.addObject("anuncianteName", "Welcome " + usuario.getNome() + " (" + usuario.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuário com papel de usuario");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }
}
