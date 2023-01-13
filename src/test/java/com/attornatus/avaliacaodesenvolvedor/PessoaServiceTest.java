package com.attornatus.avaliacaodesenvolvedor;

import com.attornatus.avaliacaodesenvolvedor.exceptions.NotFoundException;
import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.controllers.form.PessoaForm;
import com.attornatus.avaliacaodesenvolvedor.objetosCriados.EnderecoCriado;
import com.attornatus.avaliacaodesenvolvedor.objetosCriados.PessoaCriada;
import com.attornatus.avaliacaodesenvolvedor.repositories.PessoaRepository;
import com.attornatus.avaliacaodesenvolvedor.services.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;
    @InjectMocks
    private PessoaService pessoaService;

    @Test
    public void testaSePessoaEstaSendoCriadaSucesso() {
        Pessoa novaPessoa = PessoaCriada.criaObjetoPessoaPrimaria();

        when(pessoaRepository.save(novaPessoa)).thenReturn(novaPessoa);

        Assertions.assertEquals(pessoaService.criacaoPessoa(novaPessoa),
                "Nova pessoa criada com sucesso");
    }

    @Test
    public void testaSePessoaEstaSendoCriadaErro() {
        Pessoa novaPessoa = PessoaCriada.criaObjetoPessoaPrimaria();

        when(pessoaRepository.save(novaPessoa)).thenReturn(novaPessoa);
        novaPessoa.setId(1L);

        Assertions.assertEquals(pessoaService.criacaoPessoa(novaPessoa),
                "Não foi possível salvar a pessoa");
    }

    @Test
    public void testaSeAtributosDaPessoaEstaoAlterandoSucesso() {
        Pessoa novaPessoa = PessoaCriada.criaObjetoPessoaPrimaria();
        novaPessoa.setId(1L);
        PessoaForm pessoaForm = PessoaCriada.criaPessoaForm();
        pessoaForm.setId(1L);

        when(pessoaRepository.findById(pessoaForm.getId())).thenReturn(Optional.of(novaPessoa));
        when(pessoaRepository.getReferenceById(novaPessoa.getId())).thenReturn(novaPessoa);

        Assertions.assertEquals(pessoaService.alteraPessoa(pessoaForm), "Pessoa alterada com sucesso");
    }

    @Test
    public void testaSeAtributosDaPessoaEstaoAlterandoErro() {
        Pessoa novaPessoa = PessoaCriada.criaObjetoPessoaPrimaria();
        novaPessoa.setId(1L);
        PessoaForm pessoaForm = PessoaCriada.criaPessoaForm();
        pessoaForm.setId(1L);

        when(pessoaRepository.findById(null)).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> pessoaService.alteraPessoa(pessoaForm));
    }

    @Test
    public void testaSeFindByIdRetornPessoaErro() {
        Pessoa pessoaEncontrada = PessoaCriada.criaObjetoPessoaPrimaria();

        when(pessoaRepository.findById(pessoaEncontrada.getId())).thenReturn(Optional.of(pessoaEncontrada));

        Assertions.assertThrows(NotFoundException.class
                , () -> pessoaService.buscaPorId(1L));
    }

    @Test
    public void testaSeBucaTodasAsPessoasSucesso() {
        List<Pessoa> pessoas = List.of(PessoaCriada.criaObjetoPessoaPrimaria(),
                PessoaCriada.criaObjetoPessoaSencundaria());

        when(pessoaRepository.findAll()).thenReturn(pessoas);
        List<Pessoa> pessoasEncontradas = pessoaService.buscaTodasAsPessoas();

        Assertions.assertEquals(pessoasEncontradas, pessoas);
    }

    @Test
    public void testaSeBucaTodasAsPessoasErro() {

        when(pessoaRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundException.class
                , () -> pessoaService.buscaTodasAsPessoas());
    }

    @Test
    public void testeSeBuscaPessoaEnderecoRetornaEmailDaPessoaSolicitadaSucesso() {
        Pessoa pessoaEncontrada = PessoaCriada.criaObjetoPessoaPrimaria();
        pessoaEncontrada.setId(1L);

        when(pessoaRepository.findById(pessoaEncontrada.getId())).thenReturn(Optional.of(pessoaEncontrada));

        List<Endereco> enderecos = pessoaService.buscaPessoaEndereco(1L);

        Assertions.assertFalse(enderecos.isEmpty());
    }

    @Test
    public void testeSeBuscaPessoaEnderecoRetornaEmailDaPessoaSolicitadaErro() {
        Pessoa pessoaEncontrada = PessoaCriada.criaObjetoPessoaPrimaria();
        pessoaEncontrada.setId(1L);

        when(pessoaRepository.findById(pessoaEncontrada.getId())).thenReturn(Optional.of(pessoaEncontrada));

        Assertions.assertThrows(NotFoundException.class
                , () -> pessoaService.buscaPessoaEndereco(2L));
    }

    @Test
    public void testaSeDefineEnderecoPricipalAlteraEnderecosCorretamenteSucesso() {
        Pessoa pessoaEncontrada = PessoaCriada.criaObjetoPessoaPrimaria();
        List<Endereco> enderecos = Arrays.asList(new EnderecoCriado().criaEnderecoPrimario(),
                new EnderecoCriado().criaEnderecoSecundario(), new EnderecoCriado().criaEnderecoTerciario());

        pessoaEncontrada.setId(1L);
        pessoaEncontrada.setEndereco(enderecos);

        when(pessoaRepository.findById(pessoaEncontrada.getId())).thenReturn(Optional.of(pessoaEncontrada));

        Assertions.assertEquals(pessoaService.defineEnderecoPrincipal(1L, 3L)
                , "Endereço principal alterado");
    }

    @Test
    public void testaSeDefineEnderecoPricipalAlteraEnderecosCorretamenteErro() {
        Pessoa pessoaEncontrada = PessoaCriada.criaObjetoPessoaPrimaria();
        List<Endereco> enderecos = Arrays.asList(new EnderecoCriado().criaEnderecoPrimario(),
                new EnderecoCriado().criaEnderecoSecundario(), new EnderecoCriado().criaEnderecoTerciario());

        pessoaEncontrada.setId(1L);
        pessoaEncontrada.setEndereco(enderecos);

        when(pessoaRepository.findById(pessoaEncontrada.getId())).thenReturn(Optional.of(pessoaEncontrada));


        Assertions.assertEquals(pessoaService.defineEnderecoPrincipal(1L, 4L),
                "Endereço informado não existe ou não pertence a essa Pessoa");
    }
}
