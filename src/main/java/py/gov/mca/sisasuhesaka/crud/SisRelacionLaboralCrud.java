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
import py.gov.mca.sisasuhesaka.entidades.SisRelacionLaboral;

/**
 *
 * @author vinsfran
 */
@Stateless
public class SisRelacionLaboralCrud {
    
    @PersistenceContext(unitName = "asusakaPU")
    private EntityManager em;
    
    
    @SuppressWarnings("unchecked")
    public List<SisRelacionLaboral> listarRelacionesLaborales() {
        Query q = em.createNamedQuery("SisRelacionLaboral.findAll");
        return q.getResultList();
    }
    
}
