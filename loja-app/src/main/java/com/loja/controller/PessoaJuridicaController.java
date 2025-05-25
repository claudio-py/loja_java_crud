package com.loja.controller;

import com.loja.model.entity.PessoaJuridica;
import com.loja.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/juridica")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService service;

	@GetMapping
	public List<PessoaJuridica> getAll() {
		return service.getAll();
	}

	@PostMapping
	public ResponseEntity<PessoaJuridica> createPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica) {
		PessoaJuridica savedPessoaJuridica = service.save(pessoaJuridica);
		return ResponseEntity.ok(savedPessoaJuridica);
	}
}
