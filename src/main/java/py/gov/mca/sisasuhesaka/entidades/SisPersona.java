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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sis_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisPersona.findAll", query = "SELECT s FROM SisPersona s"),
    @NamedQuery(name = "SisPersona.findById", query = "SELECT s FROM SisPersona s WHERE s.id = :id"),
    @NamedQuery(name = "SisPersona.findByCi", query = "SELECT s FROM SisPersona s WHERE s.ci = :ci"),
    @NamedQuery(name = "SisPersona.findByNombre", query = "SELECT s FROM SisPersona s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SisPersona.findByApellido", query = "SELECT s FROM SisPersona s WHERE s.apellido = :apellido"),
    @NamedQuery(name = "SisPersona.findByAnioIngreso", query = "SELECT s FROM SisPersona s WHERE s.anioIngreso = :anioIngreso")})
public class SisPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ci")
    private String ci;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "anio_ingreso")
    private Integer anioIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisPersonaId")
    private List<SisPersonaDetalle> sisPersonaDetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisPersonaId")
    private List<SisUsuario> sisUsuarioList;
    @JoinColumn(name = "sis_institucion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisInstitucion sisInstitucionId;
    @JoinColumn(name = "sis_cargo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisCargo sisCargoId;
    @JoinColumn(name = "sis_dependencia_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisDependencia sisDependenciaId;

    public SisPersona() {
    }

    public SisPersona(Integer id) {
        this.id = id;
    }

    public SisPersona(Integer id, String ci, String nombre, String apellido) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(Integer anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    @XmlTransient
    public List<SisPersonaDetalle> getSisPersonaDetalleList() {
        return sisPersonaDetalleList;
    }

    public void setSisPersonaDetalleList(List<SisPersonaDetalle> sisPersonaDetalleList) {
        this.sisPersonaDetalleList = sisPersonaDetalleList;
    }

    @XmlTransient
    public List<SisUsuario> getSisUsuarioList() {
        return sisUsuarioList;
    }

    public void setSisUsuarioList(List<SisUsuario> sisUsuarioList) {
        this.sisUsuarioList = sisUsuarioList;
    }

    public SisInstitucion getSisInstitucionId() {
        return sisInstitucionId;
    }

    public void setSisInstitucionId(SisInstitucion sisInstitucionId) {
        this.sisInstitucionId = sisInstitucionId;
    }

    public SisCargo getSisCargoId() {
        return sisCargoId;
    }

    public void setSisCargoId(SisCargo sisCargoId) {
        this.sisCargoId = sisCargoId;
    }

    public SisDependencia getSisDependenciaId() {
        return sisDependenciaId;
    }

    public void setSisDependenciaId(SisDependencia sisDependenciaId) {
        this.sisDependenciaId = sisDependenciaId;
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
        if (!(object instanceof SisPersona)) {
            return false;
        }
        SisPersona other = (SisPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisPersona[ id=" + id + " ]";
    }
    
}
