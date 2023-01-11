package com.attornatus.avaliacaodesenvolvedor.controllers;

import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("criar_endereco")
    public ResponseEntity<String> criaEndereco(@RequestParam(name = "id") Long id,
                                               @RequestBody Endereco endereco) {
        try {
            return ResponseEntity.ok().body(enderecoService.criaEndereco(id, endereco));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("busca_endereco")
    public ResponseEntity<Object> buscaEndereco() {
        try {
            return ResponseEntity.ok().body(enderecoService.buscaEndereco());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
