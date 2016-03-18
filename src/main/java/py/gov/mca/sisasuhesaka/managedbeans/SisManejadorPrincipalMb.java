/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.mca.sisasuhesaka.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import org.primefaces.model.map.DefaultMapModel;
import py.gov.mca.sisasuhesaka.crud.SisCargoCrud;
import py.gov.mca.sisasuhesaka.crud.SisConceptoCrud;
import py.gov.mca.sisasuhesaka.crud.SisDependenciaCrud;
import py.gov.mca.sisasuhesaka.crud.SisInstitucionCrud;
import py.gov.mca.sisasuhesaka.crud.SisPersonaCrud;
import py.gov.mca.sisasuhesaka.crud.SisPersonaDetalleCrud;
import py.gov.mca.sisasuhesaka.crud.SisRelacionLaboralCrud;
import py.gov.mca.sisasuhesaka.crud.SisUsuarioCrud;
import py.gov.mca.sisasuhesaka.entidades.SisCargo;
import py.gov.mca.sisasuhesaka.entidades.SisConcepto;
import py.gov.mca.sisasuhesaka.entidades.SisDependencia;
import py.gov.mca.sisasuhesaka.entidades.SisInstitucion;
import py.gov.mca.sisasuhesaka.entidades.SisPersona;
import py.gov.mca.sisasuhesaka.entidades.SisPersonaDetalle;
import py.gov.mca.sisasuhesaka.entidades.SisRelacionLaboral;
import py.gov.mca.sisasuhesaka.entidades.SisUsuario;

/**
 *
 * @author vinsfran
 */
@ManagedBean(name = "sisManejadorPrincipalMb")
@SessionScoped
public class SisManejadorPrincipalMb implements Serializable {

    @Inject
    private SisPersonaCrud sisPersonaCrud;
    @Inject
    private SisPersonaDetalleCrud sisPersonaDetalleCrud;
    @Inject
    private SisInstitucionCrud sisInstitucionCrud;
    @Inject
    private SisCargoCrud sisCargoCrud;
    @Inject
    private SisDependenciaCrud sisDependenciaCrud;
    @Inject
    private SisRelacionLaboralCrud sisRelacionLaboralCrud;
    @Inject
    private SisConceptoCrud sisConceptoCrud;
    @Inject
    private SisUsuarioCrud sisUsuarioCrud;

    private DataModel<SisPersonaDetalle> listaPersonaDetalles;
    private DataModel<SisPersona> listaPersonas;
    private List<SisPersona> filteredPersonas;

    private List<SisInstitucion> listaInstituciones;
    private List<SisCargo> listaCargos;
    private List<SisDependencia> listaDependencias;
    private List<SisRelacionLaboral> listaRelacionesLaborales;
    private List<SisConcepto> listaConceptos;

    private boolean bloquearCampoCedulaFormPersona;
    private boolean bloquearCamposFormPersona;
    private boolean bloquearCampoCedulaFormPersonaDetalle;
    private boolean bloquearCamposFormPersonaDetalle;
    private boolean mostrarDialogoPersonaDetalle;
    private boolean mostrarDialogoEliminarPersonaDetalle;

    private SisPersona persona;
    private SisPersonaDetalle personaDetalle;

    private SisUsuario usuario;
    private boolean sessionIniciada;

    @PostConstruct
    public void init() {
        this.sessionIniciada = false;
        this.usuario = new SisUsuario();
    }

    public SisManejadorPrincipalMb() {
        

    }

    //Seccion de Login
    public String btnIngresar() {
        String pagina;
        String mensaje1 = "Validación de ingreso:";
        FacesMessage message = new FacesMessage();
        FacesContext context = FacesContext.getCurrentInstance();
        if (getUsuario().getLogin().equals("") || getUsuario().getPassword().equals("")) {
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            message.setSummary(mensaje1);
            message.setDetail("Los campos no pueden estar vacios.");
            pagina = "index";
        } else {
            setUsuario(sisUsuarioCrud.consultarUsuarioLoginPassword(getUsuario().getLogin(), getUsuario().getPassword()));
            if (getUsuario() != null) {
                if (getUsuario().getActivo()) {
                    message.setSeverity(FacesMessage.SEVERITY_INFO);
                    message.setSummary("Bienvenido:");
                    message.setDetail(getUsuario().getSisPersonaId().getNombre());
                    setSessionIniciada(true);
                    pagina = "inicio";
                } else {
                    message.setSeverity(FacesMessage.SEVERITY_WARN);
                    message.setSummary(mensaje1);
                    message.setDetail("Usuario inactivo");
                    pagina = "index";
                }
            } else {
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                message.setSummary(mensaje1);
                message.setDetail("Usuario no valido.");
                pagina = "index";
            }
        }
        context.addMessage(null, message);
        return pagina;
    }

    public String btnCrearModificarPersona() {
        FacesMessage message = new FacesMessage();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        if (persona.getCi().equals("") || persona.getCi() == null
                || persona.getNombre().equals("")
                || persona.getCi().equals("")
                || persona.getAnioIngreso().equals(0)
                || persona.getSisCargoId().getId().equals(0)
                || persona.getSisDependenciaId().getId().equals(0)
                || persona.getSisInstitucionId().getId().equals(0)) {

            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Atención");
            message.setDetail("Los campos con (*) no pueden estar vacio.");
            context.addMessage(null, message);
            return "from_persona";
        } else if (sisPersonaCrud.actualizarPersona(persona).equals("OK")) {
            return "personas";
        } else {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Atención");
            message.setDetail("Los campos con (*) no pueden estar vacio.");
            context.addMessage(null, message);
            return "from_persona";
        }
    }

    public String btnCrearModificarPersonaDetalle() {
        System.out.println("ENTRO");
        FacesMessage message = new FacesMessage();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        if (personaDetalle.getObjetoGastoCodigo().equals(0) || personaDetalle.getObjetoGastoCodigo() == null
                || personaDetalle.getMonto().equals(0) || personaDetalle.getMonto() == null
                || personaDetalle.getSisRelacionLaboralId().getId().equals(0) || personaDetalle.getSisRelacionLaboralId().getId() == null
                || personaDetalle.getSisConceptoId().getId().equals(0) || personaDetalle.getSisConceptoId().getId() == null) {

            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Atención");
            message.setDetail("Los campos con (*) no pueden estar vacio.");
            context.addMessage(null, message);
            return "from_persona_detalle";
        } else if (sisPersonaDetalleCrud.actualizarPersonaDetalle(personaDetalle).equals("OK")) {
            return "personas_detalles";
        } else {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Atención");
            message.setDetail("Los campos con (*) no pueden estar vacio.");
            context.addMessage(null, message);
            return "from_persona_detalle";
        }
    }

    public String btnAgregarPersona() {
        setBloquearCampoCedulaFormPersona(false);
        setBloquearCamposFormPersona(false);
        setPersona(new SisPersona());
        getPersona().setSisCargoId(new SisCargo());
        getPersona().setSisDependenciaId(new SisDependencia());
        getPersona().setSisInstitucionId(new SisInstitucion());
        return "from_persona";
    }

    public String btnAgregarPersonaDetalle() {
        setBloquearCampoCedulaFormPersonaDetalle(false);
        setBloquearCamposFormPersonaDetalle(true);
        setMostrarDialogoPersonaDetalle(false);
        setPersonaDetalle(new SisPersonaDetalle());
        getPersonaDetalle().setSisPersonaId(new SisPersona());
        getPersonaDetalle().setSisRelacionLaboralId(new SisRelacionLaboral());
        getPersonaDetalle().setSisConceptoId(new SisConcepto());
        //Toma el usuario de session
        getPersonaDetalle().setSisUsuarioId(new SisUsuario(1));

        return "from_persona_detalle";
    }

    public String btnModificarPersona(Integer id) {
        setBloquearCampoCedulaFormPersona(true);
        setBloquearCamposFormPersona(false);
        setPersona(sisPersonaCrud.consultarPersonaId(id));
        System.err.println("Persona: " + getPersona().getNombre());
        return "from_persona";
    }

    public String btnModificarPersonaDetalle(Integer id) {
        setBloquearCampoCedulaFormPersonaDetalle(true);
        setBloquearCamposFormPersonaDetalle(false);
        setMostrarDialogoPersonaDetalle(false);
        setPersonaDetalle(sisPersonaDetalleCrud.consultarPersonaDetalleId(id));
        System.err.println("Persona: " + getPersonaDetalle().getSisPersonaId().getNombre());
        return "from_persona_detalle";
    }

    public void btnEliminarPersonaDetalle(Integer id) {
        setMostrarDialogoEliminarPersonaDetalle(true);
        setPersonaDetalle(sisPersonaDetalleCrud.consultarPersonaDetalleId(id));
        System.err.println("Persona eliminar: " + getPersonaDetalle().getSisPersonaId().getNombre());
    }

    public String btnCancelarEliminarPersonaDetalle() {
        setMostrarDialogoEliminarPersonaDetalle(false);
        return "personas_detalles";
    }

    public String btnAceptarEliminarPersonaDetalle() {
        setMostrarDialogoEliminarPersonaDetalle(false);
        System.err.println(sisPersonaDetalleCrud.eliminarPersonaDetalle(getPersonaDetalle()));
        //sisPersonaDetalleCrud.eliminarPersonaDetalle(getPersonaDetalle());        
        return "personas_detalles";
    }

    public void btnEliminarPersona(SisPersona persona) {
        setPersona(persona);
    }

    public void buscarPorCedula() {
        SisPersona personaBuscada = sisPersonaCrud.consultarPersonaCi(getPersona().getCi());
        if (personaBuscada != null) {
            setBloquearCamposFormPersona(true);
        } else {
            setBloquearCamposFormPersona(false);
        }
    }

    public void buscarPorCedulaPersonaDetalle() {
        SisPersona personaBuscada = sisPersonaCrud.consultarPersonaCi(getPersonaDetalle().getSisPersonaId().getCi());

        if (personaBuscada != null) {
            setBloquearCamposFormPersonaDetalle(false);
            setMostrarDialogoPersonaDetalle(false);
            getPersonaDetalle().setSisPersonaId(personaBuscada);
        } else {
            getPersonaDetalle().setSisPersonaId(new SisPersona());
            setBloquearCamposFormPersonaDetalle(true);
            setMostrarDialogoPersonaDetalle(true);
        }
    }

    /**
     * @return the listaPersonaDetalles
     */
    public DataModel<SisPersonaDetalle> getListaPersonaDetalles() {
        // listaPersonaDetalles = new ListDataModel<>(sisPersonaDetalleCrud.listarPersonaDetalles());
        listaPersonaDetalles = new ListDataModel<>(sisPersonaDetalleCrud.listarPorDependencia(1));
        return listaPersonaDetalles;
    }

    /**
     * @param listaPersonaDetalles the listaPersonaDetalles to set
     */
    public void setListaFuncionarioDetalle(DataModel<SisPersonaDetalle> listaPersonaDetalles) {
        this.listaPersonaDetalles = listaPersonaDetalles;
    }

    /**
     * @return the listaPersonas
     */
    public DataModel<SisPersona> getListaPersonas() {
        //listaPersonas = new ListDataModel<>(sisPersonaCrud.listarPersonas());
        listaPersonas = new ListDataModel<>(sisPersonaCrud.listarPorDependencia(1));
        return listaPersonas;
    }

    /**
     * @param listaPersonas the listaPersonas to set
     */
    public void setListaPersonas(DataModel<SisPersona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    /**
     * @return the persona
     */
    public SisPersona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(SisPersona persona) {
        this.persona = persona;
    }

    /**
     * @return the listaInstituciones
     */
    public List<SisInstitucion> getListaInstituciones() {
        listaInstituciones = sisInstitucionCrud.listarInstitucion();
        return listaInstituciones;
    }

    /**
     * @param listaInstituciones the listaInstituciones to set
     */
    public void setListaInstituciones(List<SisInstitucion> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    /**
     * @return the listaCargos
     */
    public List<SisCargo> getListaCargos() {
        listaCargos = sisCargoCrud.listarCargos();
        return listaCargos;
    }

    /**
     * @param listaCargos the listaCargos to set
     */
    public void setListaCargos(List<SisCargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    /**
     * @return the listaDependencias
     */
    public List<SisDependencia> getListaDependencias() {
        listaDependencias = sisDependenciaCrud.listarDependencia();
        return listaDependencias;
    }

    /**
     * @param listaDependencias the listaDependencias to set
     */
    public void setListaDependencias(List<SisDependencia> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    /**
     * @return the bloquearCamposFormPersona
     */
    public boolean isBloquearCamposFormPersona() {
        return bloquearCamposFormPersona;
    }

    /**
     * @param bloquearCamposFormPersona the bloquearCamposFormPersona to set
     */
    public void setBloquearCamposFormPersona(boolean bloquearCamposFormPersona) {
        this.bloquearCamposFormPersona = bloquearCamposFormPersona;
    }

    /**
     * @return the bloquearCampoCedulaFormPersona
     */
    public boolean isBloquearCampoCedulaFormPersona() {
        return bloquearCampoCedulaFormPersona;
    }

    /**
     * @param bloquearCampoCedulaFormPersona the bloquearCampoCedulaFormPersona
     * to set
     */
    public void setBloquearCampoCedulaFormPersona(boolean bloquearCampoCedulaFormPersona) {
        this.bloquearCampoCedulaFormPersona = bloquearCampoCedulaFormPersona;
    }

    /**
     * @return the filteredPersonas
     */
    public List<SisPersona> getFilteredPersonas() {
        return filteredPersonas;
    }

    /**
     * @param filteredPersonas the filteredPersonas to set
     */
    public void setFilteredPersonas(List<SisPersona> filteredPersonas) {
        this.filteredPersonas = filteredPersonas;
    }

    /**
     * @return the personaDetalle
     */
    public SisPersonaDetalle getPersonaDetalle() {
        return personaDetalle;
    }

    /**
     * @param personaDetalle the personaDetalle to set
     */
    public void setPersonaDetalle(SisPersonaDetalle personaDetalle) {
        this.personaDetalle = personaDetalle;
    }

    /**
     * @return the listaRelacionesLaborales
     */
    public List<SisRelacionLaboral> getListaRelacionesLaborales() {
        listaRelacionesLaborales = sisRelacionLaboralCrud.listarRelacionesLaborales();
        return listaRelacionesLaborales;
    }

    /**
     * @param listaRelacionesLaborales the listaRelacionesLaborales to set
     */
    public void setListaRelacionesLaborales(List<SisRelacionLaboral> listaRelacionesLaborales) {
        this.listaRelacionesLaborales = listaRelacionesLaborales;
    }

    /**
     * @return the listaConceptos
     */
    public List<SisConcepto> getListaConceptos() {
        listaConceptos = sisConceptoCrud.listarConceptos();
        return listaConceptos;
    }

    /**
     * @param listaConceptos the listaConceptos to set
     */
    public void setListaConceptos(List<SisConcepto> listaConceptos) {
        this.listaConceptos = listaConceptos;
    }

    /**
     * @return the bloquearCamposFormPersonaDetalle
     */
    public boolean isBloquearCamposFormPersonaDetalle() {
        return bloquearCamposFormPersonaDetalle;
    }

    /**
     * @param bloquearCamposFormPersonaDetalle the
     * bloquearCamposFormPersonaDetalle to set
     */
    public void setBloquearCamposFormPersonaDetalle(boolean bloquearCamposFormPersonaDetalle) {
        this.bloquearCamposFormPersonaDetalle = bloquearCamposFormPersonaDetalle;
    }

    /**
     * @return the mostrarDialogoPersonaDetalle
     */
    public boolean isMostrarDialogoPersonaDetalle() {
        return mostrarDialogoPersonaDetalle;
    }

    /**
     * @param mostrarDialogoPersonaDetalle the mostrarDialogoPersonaDetalle to
     * set
     */
    public void setMostrarDialogoPersonaDetalle(boolean mostrarDialogoPersonaDetalle) {
        this.mostrarDialogoPersonaDetalle = mostrarDialogoPersonaDetalle;
    }

    /**
     * @return the bloquearCampoCedulaFormPersonaDetalle
     */
    public boolean isBloquearCampoCedulaFormPersonaDetalle() {
        return bloquearCampoCedulaFormPersonaDetalle;
    }

    /**
     * @param bloquearCampoCedulaFormPersonaDetalle the
     * bloquearCampoCedulaFormPersonaDetalle to set
     */
    public void setBloquearCampoCedulaFormPersonaDetalle(boolean bloquearCampoCedulaFormPersonaDetalle) {
        this.bloquearCampoCedulaFormPersonaDetalle = bloquearCampoCedulaFormPersonaDetalle;
    }

    /**
     * @return the mostrarDialogoEliminarPersonaDetalle
     */
    public boolean isMostrarDialogoEliminarPersonaDetalle() {
        return mostrarDialogoEliminarPersonaDetalle;
    }

    /**
     * @param mostrarDialogoEliminarPersonaDetalle the
     * mostrarDialogoEliminarPersonaDetalle to set
     */
    public void setMostrarDialogoEliminarPersonaDetalle(boolean mostrarDialogoEliminarPersonaDetalle) {
        this.mostrarDialogoEliminarPersonaDetalle = mostrarDialogoEliminarPersonaDetalle;
    }

    /**
     * @return the usuario
     */
    public SisUsuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(SisUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the sessionIniciada
     */
    public boolean isSessionIniciada() {
        return sessionIniciada;
    }

    /**
     * @param sessionIniciada the sessionIniciada to set
     */
    public void setSessionIniciada(boolean sessionIniciada) {
        this.sessionIniciada = sessionIniciada;
    }

}
