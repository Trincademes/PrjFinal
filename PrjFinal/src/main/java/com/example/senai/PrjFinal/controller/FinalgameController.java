package com.example.senai.PrjFinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.senai.PrjFinal.entities.Finalgame;
import com.example.senai.PrjFinal.service.FinalgameService;

@RestController
@RequestMapping("/final")
public class FinalgameController {

	
		@Autowired
		private final FinalgameService finalgameService;

		public FinalgameController(FinalgameService finalgameService) {
			this.finalgameService = finalgameService;
		}

		@PostMapping
		public Finalgame createfinalgame(@RequestBody Finalgame finalgame) {
			return finalgameService.saveFinalgame(finalgame);

		}

		@GetMapping("/{id}")
		public ResponseEntity<Finalgame> getFinalgame(@PathVariable Long id) {
			Finalgame finalgame = finalgameService.getFinalgameById(id);
			if (finalgame != null) {
				return ResponseEntity.ok(finalgame);
			} else {
				return ResponseEntity.notFound().build();
			}

		}

		@GetMapping("/home")
		public String paginainicial() {
			return "index";

		}
	@DeleteMapping("/{id}")
		public void deleteFinalgame(@PathVariable Long id) {
		finalgameService.deleteFinalgame(id);
		}
		


	@GetMapping
	public ResponseEntity<List<Finalgame>> getAllFinalgame(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Finalgame> finalgame = finalgameService.getAllFinalgame();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(finalgame);
	}

	@PutMapping("/{id}")
	public Finalgame updateFinalgame(@PathVariable Long id, @RequestBody Finalgame finalgame) {
	    return finalgameService.updateFinalgame(id, finalgame);
	}
	}

