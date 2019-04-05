package ua.com.sliusar.presentation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.services.impl.OrderServiceImpl;

import java.util.List;

/**
 * Class OrderController
 *
 * @author create by ivanslusar
 * 3/29/19
 * @project MyLuxoftProject
 */
@RestController
public class OrderController {
    private static final Logger logger = Logger.getLogger(OrderController.class);
    @Autowired
    private OrderServiceImpl service;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> findAll() {
        logger.info("Find all Orders");
        return service.findAll();
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public Order findById(@PathVariable String id) {
        logger.info("Find order by id");
        return service.findById(id);
    }

    @RequestMapping(value = "/orders/{id}", params = "id", method = RequestMethod.DELETE)
    public HttpStatus deleteClient(@PathVariable String id) {
        logger.info("Delete Order by id");
        service.delete(id);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public HttpStatus createOrUpdateOrder(@RequestBody Order order) {
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
