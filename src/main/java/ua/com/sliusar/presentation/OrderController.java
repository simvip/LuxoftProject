package ua.com.sliusar.presentation;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.services.OrderService;

/**
 * Class OrderController
 *
 * @author create by ivanslusar
 * 3/29/19
 * @project MyLuxoftProject
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private static final Logger logger = Logger.getLogger(OrderController.class);
    @Autowired
    private OrderService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getAll() {
        logger.info("Find all Orders");
        return new Gson().toJson(service.findAll());
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    @ResponseBody
    public String findById(@RequestParam("id") String id) {
        logger.info("Find order by id");
        return new Gson().toJson(service.findById(id));
    }

    @RequestMapping(params = "id", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpStatus deleteClient(@RequestParam("id") String id) {
        logger.info("Delete Order by id");
        service.delete(id);
        return HttpStatus.OK;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public HttpStatus createOrUpdateClient(@RequestBody Order order) {
        if (order.getId() == null) {
            logger.info("Create new order");
            service.create(order);
        } else {
            logger.info("Update Order");
            service.update(order);
        }
        return HttpStatus.OK;
    }
}
