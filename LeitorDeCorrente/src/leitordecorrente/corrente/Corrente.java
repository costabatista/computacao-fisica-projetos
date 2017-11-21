/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitordecorrente.corrente;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author paulo
 */
@Entity(name = "Corrente")
@NamedQuery(name = "corrente.consultarPorNaoEnviadaAoWebservice", query = "SELECT c FROM Corrente c WHERE c.webservice = :enviadoparawebservice")
public class Corrente implements Serializable {

    public Corrente() {
        
    }
    
    public Corrente(double valor) {
        this.setWebservice(false);
        this.setValor(valor);
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false,precision = 3,updatable = false,name = "valor")
    private double valor;

    @Column(nullable= false, updatable = true, name = "webservice")
    private boolean webservice;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void setWebservice(boolean enviado) {
        this.webservice = enviado;
    }
    
    public boolean getWebservice() {
        return this.webservice;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corrente)) {
            return false;
        }
        Corrente other = (Corrente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "leitordecorrente.corrente.Corrente[ id=" + id + " ]";
    }
    
}
