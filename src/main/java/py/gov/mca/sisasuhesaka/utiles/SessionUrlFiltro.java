package py.gov.mca.sisasuhesaka.utiles;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import py.gov.mca.sisasuhesaka.managedbeans.SisManejadorPrincipalMb;
//import py.gov.mca.reclamosmca.beansession.MbSUsuarios;

/**
 *
 * @author vinsfran
 */
@WebFilter("*.xhtml")
public class SessionUrlFiltro implements Filter {

    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String requestUrl = req.getRequestURL().toString();
        // Obtengo el bean que representa el usuario desde el scope sesión
        SisManejadorPrincipalMb sisManejadorPrincipalMb = (SisManejadorPrincipalMb) ((HttpServletRequest) request).getSession().getAttribute("sisManejadorPrincipalMb");
        

        HttpSession session = req.getSession(true);
        //MbSUsuarios mbSUsuarios = (MbSUsuarios) session.getAttribute("mbSUsuarios");
        
        if (sisManejadorPrincipalMb == null) {            
//            System.err.println("ENTRO EN FILTRO1 " + sisManejadorPrincipalMb.isSessionIniciada());
            chain.doFilter(request, response);
        } else {
            if(!sisManejadorPrincipalMb.isSessionIniciada()){
                res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            }            
            System.err.println("ENTRO EN FILTRO " + sisManejadorPrincipalMb.isSessionIniciada());
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
