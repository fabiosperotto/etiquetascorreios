package com.correios.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para tratar aspectos de verificao de idiomas do usuario e carregar
 * arquivos de traducoes
 *
 * @author fabiosperotto
 */
public class Internacionalizacao {

    /**
     * Verifica por arquivos de idiomas disponiveis, se nao existir o idioma do
     * usuario, carrega o pt-BR como padrao
     *
     * @return ResourceBundle objeto recurso com arquivo de traducao carregado
     */
    public static ResourceBundle processarTraducoes() {

        String idioma = System.getProperty("user.language"); //idioma na config do sistema operacional
        String pais = System.getProperty("user.country"); //pais na config do sistema operacional
        //avaliando se no diretorio de idiomas da raiz do executavel possui o idioma
        String busca = idioma + "_" + pais;
        boolean temTraducao = false;
        File diretorio = new File("idiomas");
        File[] listagemDiretorio = diretorio.listFiles();
        if (listagemDiretorio != null) { //lista de arquivos encontrados
            for (File arquivo : listagemDiretorio) {
                if (arquivo.getName().contains(busca)) {
                    temTraducao = true;
                    break;
                }
            }
        }
        if (!temTraducao) { //se nao encontrou entao usa uma traducao padrao
            idioma = "pt";
            pais = "BR";
        }

        Locale localCorrente;
        ResourceBundle traducoes = null;

        InputStream newInputStream;
        String nomeArquivo = "./idiomas/MessagesBundle_" + idioma + "_" + pais + ".properties";
        try {
            newInputStream = Files.newInputStream(Paths.get(nomeArquivo));
            traducoes = new PropertyResourceBundle(newInputStream);
        } catch (IOException ex) {
            Logger.getLogger(Internacionalizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Locale localCorrente = new Locale.Builder().setLanguage(idioma).setRegion(pais).build();
        return traducoes; //teremos MessagesBundle_idioma_pais.properties

    }

}
