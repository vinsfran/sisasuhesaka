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
import py.gov.mca.sisasuhesaka.entidades.SisDependencia;

/**
 *
 * @author vinsfran
 */
@Stateless
public class SisDependenciaCrud {
    @PersistenceContext(unitName = "asusakaPU")
    private EntityManager em;
    
    
    @SuppressWarnings("unchecked")
    public List<SisDependencia> listarDependencia() {
        Query q = em.createNamedQuery("SisDependencia.findAll");
        return q.getResultList();
    }
    
}
