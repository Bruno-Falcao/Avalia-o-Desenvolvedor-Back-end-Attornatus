package com.attornatus.avaliacaodesenvolvedor.objetosCriados;

import com.attornatus.avaliacaodesenvolvedor.models.Endereco;

public class EnderecoCriado {

    public Endereco criaEnderecoPrimario() {

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Rua");
        endereco.setCep("65085360");
        endereco.setNumero(487L);
        endereco.setCidade("Raiding");
        endereco.setEnderecoPrincipal(true);

        return endereco;
    }

    public Endereco criaEnderecoSecundario() {
        Endereco endereco = new Endereco();
        endereco.setId(2L);
        endereco.setLogradouro("Aldeia");
        endereco.setCep("96‑500");
        endereco.setNumero(12L);
        endereco.setCidade("Żelazowa Wola");
        endereco.setEnderecoPrincipal(false);

        return endereco;
    }

    public Endereco criaEnderecoTerciario() {
        Endereco endereco = new Endereco();
        endereco.setId(3L);
        endereco.setLogradouro("Avenida");
        endereco.setCep("1010");
        endereco.setNumero(18L);
        endereco.setCidade("Vienna");
        endereco.setEnderecoPrincipal(false);

        return endereco;
    }
}
