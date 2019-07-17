/*

MIT License

Copyright (c) 2019 Fellipe Azevedo Porfirio Maia

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/
package br.com.FellipeMaia.filter;

import br.com.FellipeMaia.model.Login;
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

/**
 *
 * @author FMaia
 */
@WebFilter(filterName = "autenticacao", urlPatterns = {"*.html"})
public class FilterAutenticacao implements Filter{
    
    private FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hSRequest = ((HttpServletRequest)request);
        String path = hSRequest.getServletPath().replace("/", "").replace(".html", "");
        String id = hSRequest.getParameter("id");
        if(!path.contains("login")){
            String idSession = (String)hSRequest.getSession().getAttribute("idSession");
            Login loginServer = (Login)hSRequest.getSession().getAttribute("login");
            if( null == idSession || null == id || null == loginServer ){
                hSRequest.getSession().invalidate();
                ((HttpServletResponse)response).sendRedirect("login.html");
            }else{
                if(idSession.equals(id)){
                    if(loginServer.getListPermissao().contains(path)){
                        chain.doFilter(request, response);
                    }else{
                        hSRequest.getSession().invalidate();
                        ((HttpServletResponse)response).sendRedirect("login.html");
                    }
                }else{
                    hSRequest.getSession().invalidate();
                    ((HttpServletResponse)response).sendRedirect("login.html");
                }
            }
        }else{
            chain.doFilter(request, response);
        }
        
    }

    @Override
    public void destroy() {
        System.out.println("Fim do filtro");
    }

    
}
