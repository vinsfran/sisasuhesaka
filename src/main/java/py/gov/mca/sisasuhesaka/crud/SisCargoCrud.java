/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.mca.sisasuhesaka.crud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisasuhesaka.entidades.SisCargo;

/**
 *
 * @author vinsfran
 */
@Stateless
public class SisCargoCrud {
    
    @PersistenceContext(unitName = "asusakaPU")
    private EntityManager em;
    
    
    @SuppressWarnings("unchecked")
    public List<SisCargo> listarCargos() {
        Query q = em.createNamedQuery("SisCargo.findAll");
        return q.getResultList();
    }
    
}
