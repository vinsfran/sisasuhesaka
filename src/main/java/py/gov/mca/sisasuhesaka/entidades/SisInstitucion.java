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
@Table(name = "sis_institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisInstitucion.findAll", query = "SELECT s FROM SisInstitucion s"),
    @NamedQuery(name = "SisInstitucion.findById", query = "SELECT s FROM SisInstitucion s WHERE s.id = :id"),
    @NamedQuery(name = "SisInstitucion.findByInstitucion", query = "SELECT s FROM SisInstitucion s WHERE s.institucion = :institucion")})
public class SisInstitucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "institucion")
    private String institucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisInstitucionId")
    private List<SisPersona> sisPersonaList;

    public SisInstitucion() {
    }

    public SisInstitucion(Integer id) {
        this.id = id;
    }

    public SisInstitucion(Integer id, String institucion) {
        this.id = id;
        this.institucion = institucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
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
        if (!(object instanceof SisInstitucion)) {
            return false;
        }
        SisInstitucion other = (SisInstitucion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisInstitucion[ id=" + id + " ]";
    }
    
}
