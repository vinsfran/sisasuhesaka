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
import py.gov.mca.sisasuhesaka.entidades.SisUsuario;

/**
 *
 * @author vinsfran
 */
@Stateless
public class SisUsuarioCrud {
    
    @PersistenceContext(unitName = "asusakaPU")
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    public SisUsuario consultarUsuarioLoginPassword(String login, String password) {
        
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT e ");
        jpql.append("FROM SisUsuario e ");
        jpql.append("WHERE e.login = :paramLogin ");
        jpql.append("AND e.password = :paramPassword ");

        //jpql.append("WHERE e.persona.nombre LIKE '%:paramNombre%'");
        Query q = em.createQuery(jpql.toString());
        q.setParameter("paramLogin", login);
        //q.setParameter("paramPassword", Encriptador.getStringEncriptado(password, "MD5"));
        q.setParameter("paramPassword", password);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (SisUsuario) q.getResultList().get(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<SisUsuario> listarUsuarios() {
        Query q = em.createNamedQuery("SisUsuario.findAll");
        return q.getResultList();
    }
    
}
