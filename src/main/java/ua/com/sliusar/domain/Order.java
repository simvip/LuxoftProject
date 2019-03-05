package ua.com.sliusar.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class Order
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class Order {
    private Long id;
    private BigDecimal totalPrice;
    private Client client;
    private List<Product> productList;


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public Order(Client client, List<Product> product) {
        this.client = client;
        this.productList = product;
    }

    public Order(Long id, BigDecimal totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientID=" + client.getName() +
                ", productList=" + productList +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
