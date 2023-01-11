package com.attornatus.avaliacaodesenvolvedor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;

    private String cep;

    private Long numero;

    private String cidade;

    @JsonProperty("endereco_principal")
    private Boolean enderecoPrincipal;

}
