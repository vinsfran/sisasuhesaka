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
@Table(name = "sis_concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisConcepto.findAll", query = "SELECT s FROM SisConcepto s"),
    @NamedQuery(name = "SisConcepto.findById", query = "SELECT s FROM SisConcepto s WHERE s.id = :id"),
    @NamedQuery(name = "SisConcepto.findByConcepto", query = "SELECT s FROM SisConcepto s WHERE s.concepto = :concepto")})
public class SisConcepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "concepto")
    private String concepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisConceptoId")
    private List<SisPersonaDetalle> sisPersonaDetalleList;

    public SisConcepto() {
    }

    public SisConcepto(Integer id) {
        this.id = id;
    }

    public SisConcepto(Integer id, String concepto) {
        this.id = id;
        this.concepto = concepto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
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
        if (!(object instanceof SisConcepto)) {
            return false;
        }
        SisConcepto other = (SisConcepto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisConcepto[ id=" + id + " ]";
    }
    
}
