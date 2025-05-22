package com.correios;

import com.correios.gui.TelaPrincipal;
import com.correios.utilities.GeracaoDados;
import com.correios.utilities.Internacionalizacao;
import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.hibernate.cfg.Configuration;

/**
 * Classe principal inicializadora (bootstrap) da aplicacao. Verifica por
 * conectividade com banco de dados e internacionalizacao.
 *
 * @version 1.0
 * @author fabiosperotto
 */
public class Main {

    public static void main(String[] args) {

        //verifica se a configuracao de criacao do banco de dados esta em modo de criacao
        //caso esta em modo de criacao, ira alimentar o banco de dados com dados teste
        File configFile = new File("hibernate.cfg.xml");
        Configuration cfg = new Configuration().configure(configFile);
        if (cfg.getProperty("hibernate.hbm2ddl.auto").equals("create")) {
            GeracaoDados.seedPessoasEnderecos();
        }
        
        //processando arquivos de traducoes para enviar para as telas
        ResourceBundle traducoes = Internacionalizacao.processarTraducoes();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            TelaPrincipal tela = new TelaPrincipal(traducoes);
            //tela.setExtendedState(JFrame.MAXIMIZED_BOTH); //abrir jframe maximizado
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
