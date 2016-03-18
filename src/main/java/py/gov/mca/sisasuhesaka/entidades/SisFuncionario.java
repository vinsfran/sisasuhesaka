/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.mca.sisasuhesaka.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vinsfran
 */
@Entity
@Table(name = "sis_funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisFuncionario.findAll", query = "SELECT s FROM SisFuncionario s"),
    @NamedQuery(name = "SisFuncionario.findById", query = "SELECT s FROM SisFuncionario s WHERE s.id = :id"),
    @NamedQuery(name = "SisFuncionario.findByCi", query = "SELECT s FROM SisFuncionario s WHERE s.ci = :ci"),
    @NamedQuery(name = "SisFuncionario.findByNombre", query = "SELECT s FROM SisFuncionario s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SisFuncionario.findByApellido", query = "SELECT s FROM SisFuncionario s WHERE s.apellido = :apellido"),
    @NamedQuery(name = "SisFuncionario.findByInstitucion", query = "SELECT s FROM SisFuncionario s WHERE s.institucion = :institucion"),
    @NamedQuery(name = "SisFuncionario.findByRelacionLaboral", query = "SELECT s FROM SisFuncionario s WHERE s.relacionLaboral = :relacionLaboral"),
    @NamedQuery(name = "SisFuncionario.findByObjetoGastoCodigo", query = "SELECT s FROM SisFuncionario s WHERE s.objetoGastoCodigo = :objetoGastoCodigo"),
    @NamedQuery(name = "SisFuncionario.findByMonto", query = "SELECT s FROM SisFuncionario s WHERE s.monto = :monto"),
    @NamedQuery(name = "SisFuncionario.findByConcepto", query = "SELECT s FROM SisFuncionario s WHERE s.concepto = :concepto"),
    @NamedQuery(name = "SisFuncionario.findByCargo", query = "SELECT s FROM SisFuncionario s WHERE s.cargo = :cargo"),
    @NamedQuery(name = "SisFuncionario.findByDependencia", query = "SELECT s FROM SisFuncionario s WHERE s.dependencia = :dependencia"),
    @NamedQuery(name = "SisFuncionario.findByAnioIngreso", query = "SELECT s FROM SisFuncionario s WHERE s.anioIngreso = :anioIngreso"),
    @NamedQuery(name = "SisFuncionario.findByAnio", query = "SELECT s FROM SisFuncionario s WHERE s.anio = :anio"),
    @NamedQuery(name = "SisFuncionario.findByMes", query = "SELECT s FROM SisFuncionario s WHERE s.mes = :mes")})
public class SisFuncionario implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "institucion")
    private String institucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "relacion_laboral")
    private String relacionLaboral;
    @Basic(optional = false)
    @NotNull
    @Column(name = "objeto_gasto_codigo")
    private int objetoGastoCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "dependencia")
    private String dependencia;
    @Column(name = "anio_ingreso")
    private Integer anioIngreso;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "mes")
    private Integer mes;
    @JoinColumn(name = "sis_usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisUsuario sisUsuarioId;

    public SisFuncionario() {
    }

    public SisFuncionario(Integer id) {
        this.id = id;
    }

    public SisFuncionario(Integer id, String ci, String nombre, String apellido, String institucion, String relacionLaboral, int objetoGastoCodigo, int monto, String concepto, String cargo, String dependencia) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.institucion = institucion;
        this.relacionLaboral = relacionLaboral;
        this.objetoGastoCodigo = objetoGastoCodigo;
        this.monto = monto;
        this.concepto = concepto;
        this.cargo = cargo;
        this.dependencia = dependencia;
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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getRelacionLaboral() {
        return relacionLaboral;
    }

    public void setRelacionLaboral(String relacionLaboral) {
        this.relacionLaboral = relacionLaboral;
    }

    public int getObjetoGastoCodigo() {
        return objetoGastoCodigo;
    }

    public void setObjetoGastoCodigo(int objetoGastoCodigo) {
        this.objetoGastoCodigo = objetoGastoCodigo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public Integer getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(Integer anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public SisUsuario getSisUsuarioId() {
        return sisUsuarioId;
    }

    public void setSisUsuarioId(SisUsuario sisUsuarioId) {
        this.sisUsuarioId = sisUsuarioId;
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
        if (!(object instanceof SisFuncionario)) {
            return false;
        }
        SisFuncionario other = (SisFuncionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisFuncionario[ id=" + id + " ]";
    }
    
}
