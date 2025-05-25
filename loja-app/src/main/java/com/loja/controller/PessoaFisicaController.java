package com.loja.controller;
import com.loja.model.entity.PessoaFisica;
import com.loja.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/fisica")
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaService service;

	@GetMapping
	public List<PessoaFisica> getAll() {
		return service.getAll();
	}

	@PostMapping
	public ResponseEntity<PessoaFisica> createPessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
		PessoaFisica savedPessoaFisica = service.save(pessoaFisica);
		return ResponseEntity.ok(savedPessoaFisica);
	}
}
