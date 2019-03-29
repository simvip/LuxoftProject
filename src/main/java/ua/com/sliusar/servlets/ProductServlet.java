package ua.com.sliusar.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.util.ParserHttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Class ProductServlet
 *
 * @author create by ivanslusar
 * 3/20/19
 * @project MyLuxoftProject
 */
public class ProductServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ProductService.class);
    private ProductService service;

    public ProductServlet(ProductService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String result;
        String idProduct = req.getParameter("id");
        if (idProduct == null) {
            logger.info("doGet  Obtaining a list of all products");
            result = new Gson().toJson(service.findAll());
        } else {
            logger.info("doGet  obtaining product by id:" + idProduct);
            result = new Gson().toJson(service.findById(idProduct));
        }
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doPost Product");
        String parameter = ParserHttpRequest.toJson(req);
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> mapParameters = new Gson().fromJson(parameter, type);
        if (mapParameters.containsKey("name")
                && mapParameters.containsKey("price")
        ) {
//            service.create(
//                    mapParameters.get("name"),
//                    mapParameters.get("price")
//            );
        } else {
            logger.info("Wrong id");
        }
        this.doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doPut Product");
        String parameter = ParserHttpRequest.toJson(req);
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> mapParameters = new Gson().fromJson(parameter, type);
        if (mapParameters.containsKey("id")) {
//            service.update(mapParameters.get("id"), mapParameters);
        } else {
            logger.info("Wrong id");
        }
        this.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doDelete Product");
        String idProduct = req.getParameter("id");
        service.delete(idProduct);
        this.doGet(req, resp);
    }

}
