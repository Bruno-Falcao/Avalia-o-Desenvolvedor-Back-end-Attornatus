package com.attornatus.avaliacaodesenvolvedor.controllers;

import com.attornatus.avaliacaodesenvolvedor.models.form.PessoaForm;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.services.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/criar_pessoa")
    @Transactional
    public ResponseEntity<Object> criaPessoa(@RequestBody Pessoa novaPessoa) {
        try {
            return ResponseEntity.ok().body(pessoaService.criacaoPessoa(novaPessoa));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PatchMapping("/atualizar_pessoa")
    @Transactional
    public ResponseEntity<Object> atualizaPessoa(@RequestBody PessoaForm pessoaForm) {
        try {
            return ResponseEntity.ok().body(pessoaService.alteraPessoa(pessoaForm));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> buscaPessoaPorId(@RequestParam(required = false, value = "id") Long id) {
        try {
            if (id != null) {
                return ResponseEntity.ok().body(pessoaService.buscaPessoaById(id));
            }
            return ResponseEntity.ok().body(pessoaService.buscaTodasAsPessoas());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(String.format("usuario $s não encontrado", id));
        }
    }

    @GetMapping("/pessoa_endereco")
    public ResponseEntity buscaPessoaEndereco(@RequestParam(name = "id") Long id) {
        try {
            return ResponseEntity.ok().body(pessoaService.buscaPessoaEndereco(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/define_endereco_principal")
    public ResponseEntity defineEnderecoPrincipal(@RequestParam(name = "id") Long id,
                                                  @RequestParam(name = "id_endereco") Long idEndereco) {
        try {
            String mensagem = pessoaService.defineEnderecoPrincipal(id, idEndereco);
            return ResponseEntity.ok().body(mensagem);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
