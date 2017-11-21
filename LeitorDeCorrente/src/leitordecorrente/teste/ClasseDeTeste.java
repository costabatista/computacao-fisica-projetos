/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitordecorrente.teste;

import java.util.List;
import leitordecorrente.corrente.Corrente;
import leitordecorrente.corrente.dao.CorrenteDAO;

/**
 *
 * @author paulo
 */
public class ClasseDeTeste {
    public static void main(String[] args) {
        CorrenteDAO correnteDAO = new CorrenteDAO();
        Corrente corrente = new Corrente(0.3);
        //corrente.setWebservice(true);
        correnteDAO.persist(new Corrente(0.1987));
        correnteDAO.persist(corrente);
        
        List<Corrente> correntes = correnteDAO.getCorrenteNaoEnviadaParaWebservice();
        
        for(Corrente c : correntes) {
            System.out.println("valor de corrente: " + c.getValor());
            System.out.println("valor do id: " + c.getId());
            System.out.println("valor de envio para webservice: " +c.getWebservice());
        }
        
        corrente.setWebservice(true);
        System.out.println(corrente.getWebservice());
        correnteDAO.persist(corrente);
        correntes = correnteDAO.getCorrenteNaoEnviadaParaWebservice();
          for(Corrente c : correntes) {
            System.out.println("valor de corrente: " + c.getValor());
            System.out.println("valor do id: " + c.getId());
            System.out.println("valor de envio para webservice: " +c.getWebservice());
        }
    }
}
