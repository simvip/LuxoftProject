package ua.com.sliusar.presentation;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.services.ClientService;

/**
 * Class ClientController
 *
 * @author create by ivanslusar
 * 3/22/19
 * @project MyLuxoftProject
 */
@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    private static final Logger logger = Logger.getLogger(ClientController.class);
    @Autowired
    private ClientService service;

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
    public HttpStatus deleteClient(@RequestParam("id") String id) {
        logger.info("Delete client by id");
        service.delete(id);
        return HttpStatus.OK;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
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
