package ua.com.sliusar.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import ua.com.sliusar.services.OrderService;
import ua.com.sliusar.util.ParserHttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Class OrderServlet
 *
 * @author create by ivanslusar
 * 3/20/19
 * @project MyLuxoftProject
 */
public class OrderServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(OrderService.class);
    private OrderService service;

    public OrderServlet(OrderService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String result;
        String idOrder = req.getParameter("id");
        if (idOrder == null) {
            logger.info("doGet  obtaining a list of all orders");
            result = new Gson().toJson(service.findAll());
        } else {
            logger.info("doGet  obtaining order by id:" + idOrder);
            result = new Gson().toJson(service.findById(idOrder));
        }
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doPost Order");
        String parameter = ParserHttpRequest.toJson(req);
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> mapParameters = new Gson().fromJson(parameter, type);
        if (mapParameters.containsKey("clientId")
                && mapParameters.containsKey("price")
                && mapParameters.containsKey("productId")
        ) {
            service.create(
                    mapParameters.get("clientId"),
                    mapParameters.get("price"),
                    mapParameters.get("productId")
            );
        } else {
            logger.info("Wrong id");
        }
        this.doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doPut Order");
        String parameter = ParserHttpRequest.toJson(req);
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> mapParameters = new Gson().fromJson(parameter, type);
        if (mapParameters.containsKey("id")) {
            service.update(mapParameters.get("id"), mapParameters);
        } else {
            logger.info("Wrong id");
        }
        this.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("doDelete Order");
        String idOrder = req.getParameter("id");
        service.delete(idOrder);
    }
}
