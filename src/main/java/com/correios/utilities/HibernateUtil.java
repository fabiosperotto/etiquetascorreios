
package com.correios.utilities;

import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Classe utilitaria a fim de realizar a leitura do arquivo de configuracao para o hibernate interagir com banco de dados
 * 
 * @version 1.0
 * @author fabiosperotto, hibernate docs
 */
public class HibernateUtil {
//    private static final SessionFactory sessionFactory = buildSessionFactory();    
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            //criacao do registry com base no hibernate.cfg.xml
//            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                    .configure() // carrega hibernate.cfg.xml
//                    .build();
//            
//            //criacao do metadata com classes mapeadas
//            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
//
//            //construção do SessionFactory
//            return metadata.getSessionFactoryBuilder().build();
//
//        } catch (Exception e) {
//            //System.err.println("Erro ao construir SessionFactory: " + e);
//            //throw new ExceptionInInitializerError(e);
//            return null;
//            
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        if (sessionFactory != null) {
//            sessionFactory.close();
//        }
//    }
    
    private static SessionFactory sessionFactory;

    static {
        try {
            String nomeArquivo = "hibernate.cfg.xml";
            // Caminho absoluto para o arquivo hibernate.cfg.xml externo
            File configFile = new File(nomeArquivo);
            if (!configFile.exists()) {
                throw new RuntimeException("Arquivo "+nomeArquivo+" não encontrado em: " + configFile.getAbsolutePath());
            }
            
            //criacao do registry com base no hibernate.cfg.xml
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure(configFile) // carrega o XML fora do classpath
                    .build();

            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            //e.printStackTrace();
            //throw new RuntimeException("Falha ao construir o SessionFactory", e);
            sessionFactory = null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
