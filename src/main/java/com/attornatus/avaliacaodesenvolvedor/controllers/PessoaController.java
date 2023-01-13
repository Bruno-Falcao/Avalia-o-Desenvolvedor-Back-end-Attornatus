package com.attornatus.avaliacaodesenvolvedor.controllers;

import com.attornatus.avaliacaodesenvolvedor.controllers.form.PessoaForm;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.services.PessoaService;
import com.attornatus.avaliacaodesenvolvedor.utils.ResponseAPI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/criar_pessoa")
    public ResponseEntity<Object> criaPessoa(@RequestBody Pessoa novaPessoa) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseAPI.getInstance(Collections
                            .singletonList(pessoaService.criacaoPessoa(novaPessoa))));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseAPI.getInstance(String.format("Erro ao criar uma pessoa", ex.getMessage()),
                            Arrays.stream(ex.getSuppressed()).map(Throwable::getMessage)
                                    .toArray(String[]::new)));
        }
    }


    @PatchMapping("/atualizar_pessoa")
    @Transactional
    public ResponseEntity<Object> atualizaPessoa(@RequestBody PessoaForm pessoaForm) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseAPI.getInstance(Collections
                            .singletonList(pessoaService.alteraPessoa(pessoaForm))));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseAPI.getInstance(String.format("Erro ao alterar uma pessoa", ex.getMessage()),
                            Arrays.stream(ex.getSuppressed()).map(Throwable::getMessage)
                                    .toArray(String[]::new)));
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> buscaPessoaPorId(@RequestParam(required = false, value = "id") Long id) {
        try {
            if (id != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(ResponseAPI.getInstance(Collections
                                .singletonList(pessoaService.buscaPorId(id))));
            }
            return ResponseEntity.ok().body(pessoaService.buscaTodasAsPessoas());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseAPI.getInstance(String.format("Erro encontrar pessoa", ex.getMessage()),
                            Arrays.stream(ex.getSuppressed()).map(Throwable::getMessage)
                                    .toArray(String[]::new)));
        }
    }

    @GetMapping("/pessoa_endereco")
    public ResponseEntity buscaPessoaEndereco(@RequestParam(name = "id") Long id) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseAPI.getInstance(Collections
                            .singletonList(pessoaService.buscaPessoaEndereco(id))));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseAPI.getInstance(String.format("Erro buscar endereços da pessoa", ex.getMessage()),
                            Arrays.stream(ex.getSuppressed()).map(Throwable::getMessage)
                                    .toArray(String[]::new)));
        }
    }

    @PatchMapping("/define_endereco_principal")
    public ResponseEntity defineEnderecoPrincipal(@RequestParam(name = "id") Long id,
                                                  @RequestParam(name = "id_endereco") Long idEndereco) {
        try {
            String mensagem = pessoaService.defineEnderecoPrincipal(id, idEndereco);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseAPI.getInstance(Collections
                            .singletonList(mensagem)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseAPI.getInstance(String.format("Erro ao definir endereço principal", ex.getMessage()),
                            Arrays.stream(ex.getSuppressed()).map(Throwable::getMessage)
                                    .toArray(String[]::new)));
        }
    }
}
