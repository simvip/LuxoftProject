package ua.com.sliusar.presentation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.services.impl.ProductServiceImpl;

import java.util.List;

/**
 * Class ProductController
 *
 * @author create by ivanslusar
 * 3/29/19
 * @project MyLuxoftProject
 */
@RestController
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class);
    @Autowired
    private ProductServiceImpl service;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> findAll() {
        logger.info("Find all products");
        return service.findAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable String id) {
        logger.info("Find Product by id");
        return service.findById(id);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteProduct(@PathVariable String id) {
        logger.info("Delete Product by id");
        service.delete(id);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
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
