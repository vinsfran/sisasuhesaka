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
@Table(name = "sis_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisUsuario.findAll", query = "SELECT s FROM SisUsuario s"),
    @NamedQuery(name = "SisUsuario.findById", query = "SELECT s FROM SisUsuario s WHERE s.id = :id"),
    @NamedQuery(name = "SisUsuario.findByLogin", query = "SELECT s FROM SisUsuario s WHERE s.login = :login"),
    @NamedQuery(name = "SisUsuario.findByPassword", query = "SELECT s FROM SisUsuario s WHERE s.password = :password"),
    @NamedQuery(name = "SisUsuario.findByActivo", query = "SELECT s FROM SisUsuario s WHERE s.activo = :activo")})
public class SisUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Column(name = "activo")
    private Boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisUsuarioId")
    private List<SisPersonaDetalle> sisPersonaDetalleList;
    @JoinColumn(name = "sis_rol_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisRol sisRolId;
    @JoinColumn(name = "sis_persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisPersona sisPersonaId;
    @JoinColumn(name = "sis_dependencia_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisDependencia sisDependenciaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sisUsuarioId")
    private List<SisFuncionario> sisFuncionarioList;

    public SisUsuario() {
    }

    public SisUsuario(Integer id) {
        this.id = id;
    }

    public SisUsuario(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<SisPersonaDetalle> getSisPersonaDetalleList() {
        return sisPersonaDetalleList;
    }

    public void setSisPersonaDetalleList(List<SisPersonaDetalle> sisPersonaDetalleList) {
        this.sisPersonaDetalleList = sisPersonaDetalleList;
    }

    public SisRol getSisRolId() {
        return sisRolId;
    }

    public void setSisRolId(SisRol sisRolId) {
        this.sisRolId = sisRolId;
    }

    public SisPersona getSisPersonaId() {
        return sisPersonaId;
    }

    public void setSisPersonaId(SisPersona sisPersonaId) {
        this.sisPersonaId = sisPersonaId;
    }

    public SisDependencia getSisDependenciaId() {
        return sisDependenciaId;
    }

    public void setSisDependenciaId(SisDependencia sisDependenciaId) {
        this.sisDependenciaId = sisDependenciaId;
    }

    @XmlTransient
    public List<SisFuncionario> getSisFuncionarioList() {
        return sisFuncionarioList;
    }

    public void setSisFuncionarioList(List<SisFuncionario> sisFuncionarioList) {
        this.sisFuncionarioList = sisFuncionarioList;
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
        if (!(object instanceof SisUsuario)) {
            return false;
        }
        SisUsuario other = (SisUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisUsuario[ id=" + id + " ]";
    }
    
}
