package ua.com.sliusar.servlets.filters;


import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class ClientFilter
 *
 * @author create by ivanslusar
 * 3/15/19
 * @project MyLuxoftProject
 */
//@WebFilter(urlPatterns = "/clients")
public class ClientFilter implements Filter {
    private ValidationService validationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.validationService = new ValidationServiceImp();
    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String age = request.getParameter("age");
        try {
            validationService.validateAge(Integer.parseInt(age));
        } catch (NumberFormatException | BusinessException e) {
            PrintWriter writer = response.getWriter();
            response.setContentType("html");
            writer.println("<h2> WRONG AGE!!!</h2>");
            return;
        }
        filterChain.doFilter(request,response);
    }
}
