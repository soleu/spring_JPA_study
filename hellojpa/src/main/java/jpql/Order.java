package jpql;

import net.bytebuddy.dynamic.TypeResolutionStrategy;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }
}
