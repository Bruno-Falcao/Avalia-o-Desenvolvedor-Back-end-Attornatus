package com.attornatus.avaliacaodesenvolvedor.objetosCriados;

import com.attornatus.avaliacaodesenvolvedor.models.Endereco;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.models.form.PessoaForm;

import java.util.List;

public class PessoaCriada {

    public static Pessoa criaObjetoPessoaPrimaria() {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome("Franz Liszt");
        novaPessoa.setDataDeNascimento("22-10-1811");

        List<Endereco> enderecos = List.of(new EnderecoCriado().criaEnderecoPrimario(),
                new EnderecoCriado().criaEnderecoPrimario());

        novaPessoa.setEndereco(enderecos);

        return novaPessoa;
    }

    public static Pessoa criaObjetoPessoaSencundaria() {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome("Frédéric Chopin");
        novaPessoa.setDataDeNascimento("01-03-1810");

        List<Endereco> enderecos = List.of(new EnderecoCriado().criaEnderecoPrimario(),
                new EnderecoCriado().criaEnderecoPrimario());

        enderecos.get(0).setEnderecoPrincipal(false);
        enderecos.get(1).setEnderecoPrincipal(true);
        novaPessoa.setEndereco(enderecos);

        return novaPessoa;
    }

    public static PessoaForm criaPessoaForm() {
        PessoaForm pessoaForm = new PessoaForm();
        pessoaForm.setNome("Martha Argerich");
        pessoaForm.setDataDeNascimento("05/06/1941");

        return pessoaForm;
    }
}
