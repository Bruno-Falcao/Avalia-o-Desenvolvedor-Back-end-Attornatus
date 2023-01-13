package com.attornatus.avaliacaodesenvolvedor.utils;

import com.attornatus.avaliacaodesenvolvedor.exceptions.NotFoundException;
import com.attornatus.avaliacaodesenvolvedor.models.Pessoa;
import com.attornatus.avaliacaodesenvolvedor.repositories.PessoaRepository;

import java.util.Optional;

public class PessoaUtils {

    /**
     * Esse método retorna a Pessoa conforme o ID informado.
     * @param id
     * @param pessoaRepository
     * @return
     */
    public static Optional<Pessoa> buscaPessoaById(Long id, PessoaRepository pessoaRepository) {
        return Optional.ofNullable(pessoaRepository.
                findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada")));
    }
}
