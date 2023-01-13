package com.attornatus.avaliacaodesenvolvedor.services;

import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.repositories.EnderecoRepository;
import com.attornatus.avaliacaodesenvolvedor.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.attornatus.avaliacaodesenvolvedor.utils.PessoaUtils.buscaPessoaById;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * O metodo busca se a pessou passada por parâmetro existe depois adiciona o Endereco recebido na lista
     * de Endereco da Pessoa
     * @param id
     * @param endereco
     * @return
     */
    public String criaEndereco(Long id, Endereco endereco) {
        Pessoa pessoa = buscaPessoaById(id, pessoaRepository).get();
        pessoa.getEndereco().add(endereco);
        pessoaRepository.save(pessoa);

        return "Endereço criado com sucesso";
    }

    public List<Endereco> buscaEndereco() {
        return enderecoRepository.findAll();
    }

}
