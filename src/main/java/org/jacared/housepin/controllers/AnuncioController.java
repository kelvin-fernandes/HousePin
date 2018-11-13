package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.models.relatorio.EnumExportacao;
import org.jacared.housepin.models.relatorio.Relatorio;
import org.jacared.housepin.services.anunciante.AnuncianteService;
import org.jacared.housepin.services.anuncio.AnuncioService;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.jacared.housepin.utils.EnumFinalidade;
import org.jacared.housepin.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.jacared.housepin.utils.EnumFinalidade.*;

@Controller
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private AnuncianteService anuncianteService;

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

    @RequestMapping(value = {"/anuncio/favoritos"}, method = RequestMethod.GET)
    public ModelAndView favoritos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/anuncio/favoritos");

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            Usuario anunciante = usuarioService.buscarUsuarioPorEmail(authentication.getName());
//            anuncio.setAnunciante((Anunciante) anunciante);
//            anuncioService.adicionar(anuncio);
//            return "redirect:/";
//        }
//
//        modelAndView.addObject("visualizando", false);
//        if(id == 0){
//            modelAndView.addObject("anuncio", new Anuncio());
//            return modelAndView;
//        }
//
//        Anuncio anuncio = anuncioService.buscarAnuncioPorId(id).get();
//        modelAndView.addObject("anuncio", anuncio);
        return modelAndView;
    }

    @Transactional
    @GetMapping(value = {"/relatorio"})
    public ModelAndView relatorio(@RequestParam("finalidade") String finalidade) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        EnumFinalidade enumFinalidade = valueOf(finalidade.toUpperCase());

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

    @Transactional
    @ResponseBody
    @GetMapping(value = "/relatorio/exportar")
    public String exportar(HttpServletResponse response,
                           @RequestParam(value = "formato") EnumExportacao.Formato formato,
                           @RequestParam(value = "tipo") String tipo,
                           @RequestParam(value = "template") List<EnumExportacao.Componente> template) throws IOException {

        Anunciante anunciante = anuncianteService.buscarAnunciantePorEmail(SpringUtil.getCurrentUserName());

        Relatorio relatorio;

        EnumFinalidade finalidade = EnumFinalidade.valueOf(tipo);

        switch (finalidade) {
            case ALUGUEL:
                relatorio = anunciante.getRelatorioDeAnunciosDeAluguel();
                break;
            case VENDA:
                relatorio = anunciante.getRelatorioDeAnunciosDeVendas();
                break;
            default:
                return "Error: informe o 'tipo' de anuncios";
        }

//        byte[] bytes = (byte[]) relatorio.exportar().para(formato, template);
//
//        if (formato.isForDownload) {
//            response.setContentType("application/force-download;charset=UTF-8");
//            response.setHeader("Content-Transfer-Encoding", "binary");
//            response.setHeader("Content-Length", String.valueOf(bytes.length));
//            response.setHeader("Content-Disposition", "attachment; filename="+ tipo + new Date().getTime() + "." + formato.toString().toLowerCase());
//
//            response.getOutputStream().write(bytes);
//            return "redirect:/relatorio";
//        } else {
//            response.setContentType("text/plain;charset=UTF-8");
//            response.getOutputStream().write(bytes);
//        }
//
        return null;
    }
}
