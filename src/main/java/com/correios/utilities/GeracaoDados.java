package com.correios.utilities;

import com.correios.DAO.PessoaDAO;
import com.correios.entities.Endereco;
import com.correios.entities.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de geracao de dados para banco de dados e outros testes
 *
 * @version 1.0
 * @author fabiosperotto
 */
public class GeracaoDados {

    /**
     * Alimenta (seed) o banco de dados com pessoas e enderecos de testes
     */
    public static void seedPessoasEnderecos() {

        List<Pessoa> lista = new ArrayList<Pessoa>();
        Pessoa p1 = new Pessoa("Bill Gates");
        p1.addEndereco(new Endereco("rua da paz", 123));
        p1.addEndereco(new Endereco("rua do triangulo", 33));
        lista.add(p1);

        Pessoa p2 = new Pessoa("Linus Torvals");
        p2.addEndereco(new Endereco("rua do kernel limpo", 123));
        lista.add(p2);

        PessoaDAO.salvarVarios(lista);
    }

}
