package com.attornatus.avaliacaodesenvolvedor.services;

import com.attornatus.avaliacaodesenvolvedor.exceptions.NotFoundException;
import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.models.dto.PessoaEnderecoDto;
import com.attornatus.avaliacaodesenvolvedor.models.form.PessoaForm;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * Caso o ID da Pessoa for informado, o método deve fazer a alteração da pessoa indicada.
     *
     * @param pessoa
     * @return
     */
    public String criacaoPessoa(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            Pessoa novaPessoa = pessoaRepository.save(pessoa);
            return String.format("Nova Pessoa criada com o id: %s", novaPessoa.getId());
        }

        return "Não foi possível savar a pessoa";
    }

    /**
     * Deve receber um objeto com um ID válido e fazer a sua alteração
     *
     * @param pessoaForm
     * @return
     */
    public String alteraPessoa(PessoaForm pessoaForm) {
        Optional<Pessoa> optional = pessoaRepository.findById(pessoaForm.getId());

        if (optional.isPresent()) {
            Pessoa pessoa = pessoaForm.atualizar(pessoaForm.getId(), pessoaRepository);
            return String.format("%s alterado(a) com sucesso", pessoa.getNome());
        }

        return String.format("%s não encontrado(a)", pessoaForm.getNome());
    }


    /**
     * Esse método retorna a Pessoa conforme o ID informado.
     *
     * @param id
     * @return
     */
    public Pessoa buscaPessoaById(Long id) {
        Optional<Pessoa> pessoaExistente = Optional.ofNullable(pessoaRepository.
                findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada")));

        return pessoaExistente.get();
    }

    public List<Pessoa> buscaTodasAsPessoas() {
        return pessoaRepository.findAll();
    }

    /**
     * O método retorna os endereços relacionados à pesosa
     * @param id
     * @return
     */
    public List<Endereco> buscaPessoaEndereco(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(Pessoa::getEndereco).orElse(null);
    }

    /**
     * O método define o endereço principal da pessoa
     * @param id
     * @param idEndereco
     * @return
     */
    public String defineEnderecoPrincipal(Long id, Long idEndereco) {
        Pessoa pessoa = buscaPessoaById(id);

        pessoa.getEndereco().forEach(c -> c.setEnderecoPrincipal(false));
        pessoa.getEndereco().stream().filter(endereco -> endereco.getId()
                .equals(idEndereco)).findFirst().ifPresent(endereco -> endereco.setEnderecoPrincipal(true));

        pessoaRepository.save(pessoa);
        return "Endereço definido com sucesso";
    }
}
