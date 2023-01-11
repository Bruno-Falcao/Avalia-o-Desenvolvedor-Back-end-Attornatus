package com.attornatus.avaliacaodesenvolvedor.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEnderecoDto {

    private String nome;
    private String logradouro;

    private String cep;

    private Long numero;

    private String cidade;

    private Boolean enderecoPrincipal;
}
