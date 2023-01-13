package com.attornatus.avaliacaodesenvolvedor.controllers;

import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.services.EnderecoService;
import com.attornatus.avaliacaodesenvolvedor.utils.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("criar_endereco")
    public ResponseEntity<ResponseAPI> criaEndereco(@RequestParam(name = "id") Long id,
                                                    @RequestBody Endereco endereco) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseAPI.getInstance(Collections
                            .singletonList(enderecoService.criaEndereco(id,endereco))));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseAPI.getInstance(String.format("Erro ao criar um endereço", ex.getMessage()),
                            Arrays.stream(ex.getSuppressed()).map(Throwable::getMessage)
                                    .toArray(String[]::new)));
        }
    }

}
