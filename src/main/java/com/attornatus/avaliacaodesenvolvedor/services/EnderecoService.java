package com.attornatus.avaliacaodesenvolvedor.services;

import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.repositories.EnderecoRepository;
import com.attornatus.avaliacaodesenvolvedor.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public String criaEndereco(Long id, Endereco endereco) {
        Pessoa pessoa = pessoaRepository.findById(id).get();
        pessoa.getEndereco().add(endereco);
        pessoaRepository.save(pessoa);

        return "Endereço criado com sucesso";
    }

    public List<Endereco> buscaEndereco() {
        return enderecoRepository.findAll();
    }

}
