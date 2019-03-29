package ua.com.sliusar.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.util.ParserHttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

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
    private final String CLIENT_ID = "id";
    private final String NAME = "name";
    private final String SURNAME = "surname";
    private final String PHONE = "phone";


    public ClientServlet(ClientService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String result;
        String idClient = req.getParameter("id");
        if (idClient == null) {
            logger.info("doGet  Obtaining a list of all clients");
            result = new Gson().toJson(service.findAll());
        } else {
            logger.info("doGet  obtaining client by id:" + idClient);
            result = new Gson().toJson(service.findById(idClient));
        }
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doPost Clients");
        String parameter = ParserHttpRequest.toJson(req);
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> mapParameters = new Gson().fromJson(parameter, type);
        if (mapParameters.containsKey(NAME)
                && mapParameters.containsKey(SURNAME)
                && mapParameters.containsKey(PHONE)
        ) {
//            service.createClient(
//                    mapParameters.get(NAME),
//                    mapParameters.get(SURNAME),
//                    mapParameters.get(PHONE)
//            );
        } else {
            logger.info("Wrong id");
        }
        this.doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doPut Clients");
        String parameter = ParserHttpRequest.toJson(req);
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> mapParameters = new Gson().fromJson(parameter, type);
        if (mapParameters.containsKey(CLIENT_ID)) {
//            service.update(mapParameters.get(CLIENT_ID), mapParameters);
        } else {
            logger.info("Wrong id");
        }
        this.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doDelete Clients");
        String idClients = req.getParameter(CLIENT_ID);
        service.delete(idClients);
        this.doGet(req, resp);
    }


}
