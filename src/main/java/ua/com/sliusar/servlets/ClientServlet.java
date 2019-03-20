package ua.com.sliusar.servlets;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ua.com.sliusar.services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class ClientServlet
 *
 * @author create by ivanslusar
 * 3/15/19
 * @project MyLuxoftProject
 */
public class ClientServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ClientService.class);
    private ClientService service;

    public ClientServlet(ClientService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("DO GET");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String result = "";
        String idClients = req.getParameter("id");
        if (idClients == null) {
            result = new Gson().toJson(service.findAll());
        } else {
            result = new Gson().toJson(service.findById(idClients));
        }
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idClients = req.getParameter("id");
        if (idClients != null) {

        } else {
            logger.info("Wrong id");
        }
        // update
        // postman
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idClients = req.getParameter("id");
        service.delete(idClients);
        this.doGet(req, resp);
    }

    private String parseRequestToJson(HttpServletRequest req) {
        StringBuilder jb = new StringBuilder();
        String line = "";
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception ex) {
            logger.error("We have a problem with parse request to JSON", ex);
        }
        return jb.toString();
    }
}
