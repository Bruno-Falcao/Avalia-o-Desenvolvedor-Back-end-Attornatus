package com.attornatus.avaliacaodesenvolvedor.models.form;

import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.repositories.PessoaRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PessoaForm {

    private Long id;

    private String nome;

    @JsonProperty("data_de_nascimento")
    private String dataDeNascimento;

    public Pessoa atualizar(Long id, PessoaRepository pessoaRepository) {
        Pessoa pessoa = pessoaRepository.getReferenceById(id);

        pessoa.setNome(this.nome);
        pessoa.setDataDeNascimento(this.dataDeNascimento);

        return pessoa;
    }
}
