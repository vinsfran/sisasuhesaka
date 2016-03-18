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
import py.gov.mca.sisasuhesaka.entidades.SisPersona;
import py.gov.mca.sisasuhesaka.entidades.SisPersonaDetalle;

/**
 *
 * @author vinsfran
 */
@Stateless
public class SisPersonaDetalleCrud {
    
    @PersistenceContext(unitName = "asusakaPU")
    private EntityManager em;
    
    public String crearPersonaDetalle(SisPersonaDetalle personaDetalle) {
        try {
            em.persist(personaDetalle);
            em.flush();
            return "OK";
        } catch (Exception ex) {
            return "Ocurrio una excepcion: (" + ex.getMessage() + ")";
        }
    }

    public String actualizarPersonaDetalle(SisPersonaDetalle personaDetalle) {
        try {
            em.merge(personaDetalle);
            em.flush();
            return "OK";
        } catch (Exception ex) {
            return "Ocurrio una excepcion: (" + ex.getMessage() + ")";
        }
    }
    
    public String eliminarPersonaDetalle(SisPersonaDetalle personaDetalle) {
        try {
            SisPersonaDetalle ac = em.find(SisPersonaDetalle.class, personaDetalle.getId());
            em.remove(ac);
            em.flush();
            return "OK";
        } catch (Exception ex) {
            return "Ocurrio una excepcion: (" + ex.getMessage() + ")";
        }
    }
    
    @SuppressWarnings("unchecked")
    public SisPersonaDetalle consultarPersonaDetalleId(Integer id) {
        Query q = em.createNamedQuery("SisPersonaDetalle.findById");
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (SisPersonaDetalle) q.getResultList().get(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<SisPersonaDetalle> listarPersonaDetalles() {
        Query q = em.createNamedQuery("SisPersonaDetalle.findAll");
        return q.getResultList();
    }
        
    public List<SisPersonaDetalle> listarPorDependencia(Integer id) {
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT e ");
        jpql.append("FROM SisPersonaDetalle e ");
        jpql.append("WHERE e.sisPersonaId.sisDependenciaId.id = :paramId ");

        //jpql.append("WHERE e.persona.nombre LIKE '%:paramNombre%'");
        Query q = em.createQuery(jpql.toString());
        q.setParameter("paramId", id);
        return q.getResultList();
    }
    
}
