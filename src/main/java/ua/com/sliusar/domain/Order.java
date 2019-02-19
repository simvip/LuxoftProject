package ua.com.sliusar.domain;

import java.util.List;

/**
 * Class Order
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class Order {
    private Double id;
    private Double clientID;
    private List<Product> product;

    public Double getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientID=" + clientID +
                ", product=" + product +
                '}';
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getClientID() {
        return clientID;
    }

    public void setClientID(Double clientID) {
        this.clientID = clientID;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
