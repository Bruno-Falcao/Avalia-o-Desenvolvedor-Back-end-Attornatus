package com.attornatus.avaliacaodesenvolvedor;

import com.attornatus.avaliacaodesenvolvedor.exceptions.NotFoundException;
import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.objetosCriados.EnderecoCriado;
import com.attornatus.avaliacaodesenvolvedor.objetosCriados.PessoaCriada;
import com.attornatus.avaliacaodesenvolvedor.repositories.EnderecoRepository;
import com.attornatus.avaliacaodesenvolvedor.repositories.PessoaRepository;
import com.attornatus.avaliacaodesenvolvedor.services.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    public void testaSeMetodoCriaEnderecoRetornaExceptionSeIdDaPessoaNaoExistir() {
        Pessoa novaPessoa = PessoaCriada.criaObjetoPessoaPrimaria();
        novaPessoa.setId(1L);
        Endereco novoEndereco = new EnderecoCriado().criaEnderecoTerciario();

        when(pessoaRepository.findById(novaPessoa.getId())).thenReturn(Optional.of(novaPessoa));
        when(pessoaRepository.save(novaPessoa)).thenReturn(novaPessoa);


        Assertions.assertThrows(NotFoundException.class,
                () -> enderecoService.criaEndereco(5L, novoEndereco));
    }
}
