/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitordecorrente.corrente.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import leitordecorrente.corrente.Corrente;

/**
 *
 * @author paulo
 */
public class CorrenteDAO {

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LeitorDeCorrentePU");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public void persist(Corrente corrente) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            if(corrente.getId() == null) {
               em.persist(corrente); 
            }
            else {
                if(!em.contains(corrente)) {
                    if(em.find(corrente.getClass(), corrente.getId()) == null) {
                        throw new Exception("Erro ao atualizar");
                    }
                }
                System.out.println("caiu aqui");
                em.merge(corrente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Corrente> getCorrenteNaoEnviadaParaWebservice() {
        EntityManager em = getEntityManager();
        List<Corrente> listaDeCorrente = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("corrente.consultarPorNaoEnviadaAoWebservice");
            query.setParameter("enviadoparawebservice", false);
            listaDeCorrente = query.getResultList();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            listaDeCorrente = new ArrayList<>();
        } finally {
            em.close();
        }
        
        return listaDeCorrente;
    }

}
