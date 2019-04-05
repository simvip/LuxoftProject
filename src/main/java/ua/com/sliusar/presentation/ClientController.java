package ua.com.sliusar.presentation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.services.impl.ClientServiceImpl;

import java.util.List;

/**
 * Class ClientController
 *
 * @author create by ivanslusar
 * 3/22/19
 * @project MyLuxoftProject
 */
@RestController
public class ClientController {
    private static final Logger logger = Logger.getLogger(ClientController.class);
    @Autowired
    private ClientServiceImpl service;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<Client> findAll() {
        logger.info("Find all clients");
        return service.findAll();
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public Client findById(@PathVariable String id) {
        logger.info("Find Client by id");
        return service.findById(id);
    }

    @RequestMapping(value = "/clients/{id}", params = "id", method = RequestMethod.DELETE)
    public HttpStatus deleteClient(@PathVariable String id) {
        logger.info("Delete client by id");
        service.delete(id);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public HttpStatus createOrUpdateClient(@RequestBody Client client) {
        if (client.getId() == null) {
            logger.info("Create new client");
            service.create(client);
        } else {
            logger.info("Update client");
            service.update(client);
        }
        return HttpStatus.OK;
    }
}
