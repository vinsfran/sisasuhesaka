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

/**
 *
 * @author vinsfran
 */
@Stateless
public class SisPersonaCrud {

    @PersistenceContext(unitName = "asusakaPU")
    private EntityManager em;

    public String crearPersona(SisPersona persona) {
        try {
            em.persist(persona);
            em.flush();
            return "OK";
        } catch (Exception ex) {
            return "Ocurrio una excepcion: (" + ex.getMessage() + ")";
        }
    }

    public String actualizarPersona(SisPersona persona) {
        try {
            em.merge(persona);
            em.flush();
            return "OK";
        } catch (Exception ex) {
            return "Ocurrio una excepcion: (" + ex.getMessage() + ")";
        }
    }

    public List<SisPersona> burcarCriterio(Integer codDependencia, String expresion) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM SisPersona e ");
        //jpql.append("WHERE e.fkCodTipoReclamo.fkCodDependencia.codDependencia = :paramCodDependencia ");
        jpql.append("AND (lower(e.direccionReclamo) like :paramExpresion ");
        jpql.append("OR lower(e.fkCodTipoReclamo.nombreTipoReclamo) like :paramExpresion ");
        jpql.append("OR lower(e.fkCodUsuario.loginUsuario) like :paramExpresion ");
        jpql.append("OR lower(e.fkCodUsuario.fkCodPersona.cedulaPersona) like :paramExpresion ");
        jpql.append("OR lower(e.fkCodUsuario.fkCodPersona.nombrePersona) like :paramExpresion ");
        jpql.append("OR lower(e.fkCodUsuario.fkCodPersona.apellidoPersona) like :paramExpresion) ");
        jpql.append("ORDER BY e.codReclamo");

        //jpql.append("WHERE e.persona.nombre LIKE '%:paramNombre%'");
        Query q = em.createQuery(jpql.toString());
        q.setParameter("paramCodDependencia", codDependencia);
        q.setParameter("paramExpresion", "%" + expresion.toLowerCase().trim() + "%");
//        q.setParameter("paramNombreTipoReclamo", "%" + expresion.trim() + "%");
//        q.setParameter("paramLoginUsuario", "%" + expresion.trim() + "%");
//        q.setParameter("paramCedulaPersona", "%" + expresion.trim() + "%");
//        q.setParameter("paramNombrePersona", "%" + expresion.trim() + "%");
//        q.setParameter("paramApellidoPersona", "%" + expresion.trim() + "%");
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public SisPersona consultarPersonaId(Integer id) {
        Query q = em.createNamedQuery("SisPersona.findById");
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (SisPersona) q.getResultList().get(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    public SisPersona consultarPersonaCi(String ci) {
        Query q = em.createNamedQuery("SisPersona.findByCi");
        q.setParameter("ci", ci);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (SisPersona) q.getResultList().get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SisPersona> listarPersonas() {
        Query q = em.createNamedQuery("SisPersona.findAll");
        return q.getResultList();
    }
    
    public List<SisPersona> listarPorDependencia(Integer id) {
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT e ");
        jpql.append("FROM SisPersona e ");
        jpql.append("WHERE e.sisDependenciaId.id = :paramId ");

        //jpql.append("WHERE e.persona.nombre LIKE '%:paramNombre%'");
        Query q = em.createQuery(jpql.toString());
        q.setParameter("paramId", id);
        return q.getResultList();
    }
}
