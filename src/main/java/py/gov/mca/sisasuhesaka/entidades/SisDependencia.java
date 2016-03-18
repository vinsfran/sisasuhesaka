/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.mca.sisasuhesaka.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vinsfran
 */
@Entity
@Table(name = "sis_dependencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisDependencia.findAll", query = "SELECT s FROM SisDependencia s"),
    @NamedQuery(name = "SisDependencia.findById", query = "SELECT s FROM SisDependencia s WHERE s.id = :id"),
    @NamedQuery(name = "SisDependencia.findByDependencia", query = "SELECT s FROM SisDependencia s WHERE s.dependencia = :dependencia")})
public class SisDependencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "dependencia")
    private String dependencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisDependenciaId")
    private List<SisUsuario> sisUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisDependenciaId")
    private List<SisPersona> sisPersonaList;

    public SisDependencia() {
    }

    public SisDependencia(Integer id) {
        this.id = id;
    }

    public SisDependencia(Integer id, String dependencia) {
        this.id = id;
        this.dependencia = dependencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    @XmlTransient
    public List<SisUsuario> getSisUsuarioList() {
        return sisUsuarioList;
    }

    public void setSisUsuarioList(List<SisUsuario> sisUsuarioList) {
        this.sisUsuarioList = sisUsuarioList;
    }

    @XmlTransient
    public List<SisPersona> getSisPersonaList() {
        return sisPersonaList;
    }

    public void setSisPersonaList(List<SisPersona> sisPersonaList) {
        this.sisPersonaList = sisPersonaList;
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
        if (!(object instanceof SisDependencia)) {
            return false;
        }
        SisDependencia other = (SisDependencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisDependencia[ id=" + id + " ]";
    }
    
}
