package ua.com.sliusar.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Class Order
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
@Entity
@Table(name = "Main_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Total_price")
    private BigDecimal totalPrice;

    @ManyToOne()
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private Client client;

    @JoinTable(
            name = "ORDER_PRODUCTS",
            joinColumns = @JoinColumn(name = "ID_ORDER", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "id"))
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> productList;

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, client, productList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(client, order.client) &&
                Objects.equals(productList, order.productList);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public Order(Long id, BigDecimal totalPrice, Client client, List<Product> productList) {
        this(totalPrice, client, productList);
        this.id = id;
    }

    public Order(BigDecimal totalPrice, Client client, List<Product> productList) {
        this(client, productList);
        this.totalPrice = totalPrice;
    }

    public Order(Client client, List<Product> product) {
        this.client = client;
        this.productList = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", client=" + client +
                ", productList=" + productList +
                '}';
    }

    public Order(Long id, BigDecimal totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public Order() {
        // this form used by Hibernate
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
