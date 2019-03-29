package ua.com.sliusar.presentation;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.services.ProductService;

/**
 * Class ProductController
 *
 * @author create by ivanslusar
 * 3/29/19
 * @project MyLuxoftProject
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class);
    @Autowired
    private ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getAll() {
        logger.info("Find all clients");
        return new Gson().toJson(service.findAll());
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    @ResponseBody
    public String findById(@RequestParam("id") String id) {
        logger.info("Find Client by id");
        return new Gson().toJson(service.findById(id));
    }

    @RequestMapping(params = "id", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpStatus deleteProduct(@RequestParam("id") String id) {
        logger.info("Delete Client by id");
        service.delete(id);
        return HttpStatus.OK;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public HttpStatus createOrUpdateProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            logger.info("Create new Product");
            service.create(product);
        } else {
            logger.info("Update Product");
            service.update(product);
        }
        return HttpStatus.OK;
    }
}
