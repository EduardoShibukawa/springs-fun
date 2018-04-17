package com.algaworks.vinhos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.vinhos.model.TipoVinho;
import com.algaworks.vinhos.model.Vinho;
import com.algaworks.vinhos.repository.Vinhos;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	@Autowired
	private Vinhos vinhos;
	
	public ModelAndView abrirTela(Vinho vinho) {
		ModelAndView modelAndView = new ModelAndView("vinhos/cadastro-vinho");
		modelAndView.addObject(vinho);
		modelAndView.addObject("tipos", TipoVinho.values());
		return modelAndView;	
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Optional<Vinho> vinho = vinhos.findById(id);		
		return this.abrirTela(vinho.get());
	}

	@GetMapping("/novo")
	public ModelAndView novo() {		
		return this.abrirTela(new Vinho());		
	} 
	
	@PostMapping("/novo")
	public String salvar(Vinho vinho) {
		vinhos.save(vinho);
		return "redirect:/vinhos/novo";
	}
}