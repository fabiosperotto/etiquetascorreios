
package com.correios.DAO;

import com.correios.entities.Endereco;
import com.correios.utilities.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class EnderecoDAO {
    
    /**
     * Persiste o objeto no banco de dados
     * @param endereco objeto para persistir
     * @return boolean true se o insert foi realizado, false do contrario
     */
    public static boolean salvar(Endereco endereco) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            session.persist(endereco);
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
     * Realiza a persistencia em lote de varioa objetos de Endereco
     * @param enderecos List com objetos de Endereco
     * @return boolean true se os inserts foram realizados, false do contrario
     */
    public static boolean salvarVarios(List enderecos) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            enderecos.forEach(endereco -> session.persist(endereco));
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
     * @return Endereco objeto instanciado se encontrou dados ou null casso contrario
     */
    public static Endereco buscarPorId(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Endereco p = session.get(Endereco.class, id);
        session.close();
        return p;
    }
    
    /**
     * Retorna uma lista com todos os registros de enderecos no BD com as devidas 
     * instanciacoes em objetos
     * @return List list de Endereco
     */
    public static List<Endereco> listarTodos() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Endereco> list = session.createQuery("FROM Endereco").list();        
        session.close();
        return list; 
    }

    /**
     * 
     * @param endereco
     * @return boolean true se o update foi realizado, false do contrario
     */
    public static boolean atualizar(Endereco endereco) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            session.merge(endereco);
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
     * Exclui dados de uma endereco do BD
     * @param endereco
     * @return boolean true se o delete foi realizado, false do contrario
     */
    public static boolean excluir(Endereco endereco) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tr = session.beginTransaction();
            session.remove(endereco);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            System.out.println("Erro no delete: "+e.getMessage());
            //throw e;
            return false;
        }
    }
    
}
