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
@Table(name = "sis_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisCargo.findAll", query = "SELECT s FROM SisCargo s"),
    @NamedQuery(name = "SisCargo.findById", query = "SELECT s FROM SisCargo s WHERE s.id = :id"),
    @NamedQuery(name = "SisCargo.findByCargo", query = "SELECT s FROM SisCargo s WHERE s.cargo = :cargo")})
public class SisCargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cargo")
    private String cargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisCargoId")
    private List<SisPersona> sisPersonaList;

    public SisCargo() {
    }

    public SisCargo(Integer id) {
        this.id = id;
    }

    public SisCargo(Integer id, String cargo) {
        this.id = id;
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
        if (!(object instanceof SisCargo)) {
            return false;
        }
        SisCargo other = (SisCargo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisCargo[ id=" + id + " ]";
    }
    
}
