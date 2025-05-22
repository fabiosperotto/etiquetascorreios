
package com.correios.DAO;

import com.correios.entities.Pessoa;
import com.correios.utilities.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PessoaDAO {
    
    private PessoaDAO(){}
    
    /**
     * Persiste o objeto no banco de dados
     * @see Pessoa
     * @param pessoa objeto para persistir
     * @return boolean true se o insert foi realizado, false do contrario
     */
    public static boolean salvar(Pessoa pessoa) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            session.persist(pessoa);
            tr.commit();
            return true;
        } catch (Exception e) {            
            if (tr != null) tr.rollback();
            System.out.println("Erro no insert: "+e.getMessage());
            //throw e;            
            return false;
        }
    }
    
    /**
     * Realiza a persistencia em lote de varioa objetos de Pessoa
     * @param pessoas List com objetos de Pessoa
     * @return boolean true se os inserts foram realizados, false do contrario
     */
    public static boolean salvarVarios(List pessoas) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            pessoas.forEach(pessoa -> session.persist(pessoa));
            tr.commit();
            return true;
        } catch (Exception e) {            
            if (tr != null) tr.rollback();
            System.out.println("Erro no insert de lista: "+e.getMessage());
            //throw e;
            return false;
        }
    }

    /**
     * Busca um registro pelo seu id
     * @param id int 
     * @return Pessoa objeto instanciado se encontrou dados ou null casso contrario
     */
    public static Pessoa buscarPorId(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Pessoa p = session.get(Pessoa.class, id);
        session.getTransaction().commit();
        return p;
    }
    
    /**
     * Retorna uma lista com todos os registros de pessoas no BD com as devidas 
     * instanciacoes em objetos
     * @return List list de Pessoa
     */
    public static List<Pessoa> listarTodos() {  
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Pessoa> list = session.createQuery("FROM Pessoa",Pessoa.class).list();        
        session.getTransaction().commit();
        return list;   
        
    }
    
    /**
     * Retorna a lista de pessoas cadastradas com um limitador de quantidade de registros
     * @param limite limite maximo de registros para o SQL
     * @return List contendo objetos de Pessoa
     */
    public static List<Pessoa> listar(int limite){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Pessoa> list = session.createQuery("FROM Pessoa",Pessoa.class)
                .setMaxResults(limite)
                .list();          
        session.getTransaction().commit();
        return list;
    }

    /**
     * 
     * @param pessoa
     * @return boolean true se o update foi realizado, false do contrario
     */
    public static boolean atualizar(Pessoa pessoa) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            session.merge(pessoa);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            System.out.println("Erro no update: "+e.getMessage());
            //throw e;
            return false;
        }
    }

    /**
     * Exclui dados de uma pessoa do BD
     * @param pessoa
     * @return boolean true se o delete foi realizado, false do contrario
     */
    public static boolean excluir(Pessoa pessoa) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            session.remove(pessoa);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            System.out.println("Erro no delete: "+e.getMessage());
            //throw e;
            return false;
        }
    }
    
    /**
     * Realiza o carregamento de enderecos para uma pessoa se a mesma possuir as 
     * relacoes no BD. Considera que a entidade possui configuracao LAZY na relacao
     * @param id identificador para a Pessoa
     * @return Pessoa objeto com a lista de enderecos carregada
     */
    public static Pessoa buscarPessoaComEnderecos(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Pessoa pessoa = session.get(Pessoa.class, id);
        if (pessoa != null) {
            Hibernate.initialize(pessoa.getEnderecos()); //fara o carregamento de enderecos
        }
        session.getTransaction().commit();
        return pessoa;
    }
    
    /**
     * Realiza a execucaso de uma consulta nomeada e parametrizada para buscar um registro de Pessoa pelo seu nome.
     * @param nome String
     * @return Pessoa objeto se registro encontrado ou null do contario
     */
    public static Pessoa buscarNome(String nome){        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Pessoa p = session.createNamedQuery("get_pessoa_pelo_nome", Pessoa.class)
                .setParameter("nome", nome).getSingleResult();
        session.getTransaction().commit();
        return p;
    }
    
}
