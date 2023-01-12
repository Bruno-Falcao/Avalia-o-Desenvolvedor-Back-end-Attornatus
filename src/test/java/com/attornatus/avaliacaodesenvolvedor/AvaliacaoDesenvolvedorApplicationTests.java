package com.attornatus.avaliacaodesenvolvedor;

import com.attornatus.avaliacaodesenvolvedor.controllers.EnderecoController;
import com.attornatus.avaliacaodesenvolvedor.controllers.PessoaController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AvaliacaoDesenvolvedorApplicationTests {

    @Autowired
    private PessoaController pessoaController;
    @Autowired
    private EnderecoController enderecoController;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(pessoaController);
        Assertions.assertNotNull(enderecoController);
    }


}
