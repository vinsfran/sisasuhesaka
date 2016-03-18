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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vinsfran
 */
@Entity
@Table(name = "sis_persona_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisPersonaDetalle.findAll", query = "SELECT s FROM SisPersonaDetalle s"),
    @NamedQuery(name = "SisPersonaDetalle.findById", query = "SELECT s FROM SisPersonaDetalle s WHERE s.id = :id"),
    @NamedQuery(name = "SisPersonaDetalle.findByObjetoGastoCodigo", query = "SELECT s FROM SisPersonaDetalle s WHERE s.objetoGastoCodigo = :objetoGastoCodigo"),
    @NamedQuery(name = "SisPersonaDetalle.findByMonto", query = "SELECT s FROM SisPersonaDetalle s WHERE s.monto = :monto")})
public class SisPersonaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "objeto_gasto_codigo")
    private Integer objetoGastoCodigo;
    @Column(name = "monto")
    private Integer monto;
    @JoinColumn(name = "sis_persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisPersona sisPersonaId;
    @JoinColumn(name = "sis_relacion_laboral_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisRelacionLaboral sisRelacionLaboralId;
    @JoinColumn(name = "sis_concepto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisConcepto sisConceptoId;
    @JoinColumn(name = "sis_usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SisUsuario sisUsuarioId;

    public SisPersonaDetalle() {
    }

    public SisPersonaDetalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjetoGastoCodigo() {
        return objetoGastoCodigo;
    }

    public void setObjetoGastoCodigo(Integer objetoGastoCodigo) {
        this.objetoGastoCodigo = objetoGastoCodigo;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public SisPersona getSisPersonaId() {
        return sisPersonaId;
    }

    public void setSisPersonaId(SisPersona sisPersonaId) {
        this.sisPersonaId = sisPersonaId;
    }

    public SisRelacionLaboral getSisRelacionLaboralId() {
        return sisRelacionLaboralId;
    }

    public void setSisRelacionLaboralId(SisRelacionLaboral sisRelacionLaboralId) {
        this.sisRelacionLaboralId = sisRelacionLaboralId;
    }

    public SisConcepto getSisConceptoId() {
        return sisConceptoId;
    }

    public void setSisConceptoId(SisConcepto sisConceptoId) {
        this.sisConceptoId = sisConceptoId;
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
        if (!(object instanceof SisPersonaDetalle)) {
            return false;
        }
        SisPersonaDetalle other = (SisPersonaDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gov.mca.sisasuhesaka.entidades.SisPersonaDetalle[ id=" + id + " ]";
    }
    
}
