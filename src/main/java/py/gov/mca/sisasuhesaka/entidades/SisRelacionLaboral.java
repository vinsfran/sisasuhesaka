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
@Table(name = "sis_relacion_laboral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisRelacionLaboral.findAll", query = "SELECT s FROM SisRelacionLaboral s"),
    @NamedQuery(name = "SisRelacionLaboral.findById", query = "SELECT s FROM SisRelacionLaboral s WHERE s.id = :id"),
    @NamedQuery(name = "SisRelacionLaboral.findByRelacionLaboral", query = "SELECT s FROM SisRelacionLaboral s WHERE s.relacionLaboral = :relacionLaboral")})
public class SisRelacionLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "relacion_laboral")
    private String relacionLaboral;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisRelacionLaboralId")
    private List<SisPersonaDetalle> sisPersonaDetalleList;

    public SisRelacionLaboral() {
    }

    public SisRelacionLaboral(Integer id) {
        this.id = id;
    }

    public SisRelacionLaboral(Integer id, String relacionLaboral) {
        this.id = id;
        this.relacionLaboral = relacionLaboral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelacionLaboral() {
        return relacionLaboral;
    }

    public void setRelacionLaboral(String relacionLaboral) {
        this.relacionLaboral = relacionLaboral;
    }

    @XmlTransient
    public List<SisPersonaDetalle> getSisPersonaDetalleList() {
        return sisPersonaDetalleList;
    }

    public void setSisPersonaDetalleList(List<SisPersonaDetalle> sisPersonaDetalleList) {
        this.sisPersonaDetalleList = sisPersonaDetalleList;
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
        if (!(object instanceof SisRelacionLaboral)) {
            return false;
        }
        SisRelacionLaboral other = (SisRelacionLaboral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisRelacionLaboral[ id=" + id + " ]";
    }
    
}
