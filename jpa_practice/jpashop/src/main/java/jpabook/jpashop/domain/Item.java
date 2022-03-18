package jpabook.jpashop.domain;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private List<Category> categories;

    //--비즈니스 로직==// : 도메인 설계에서 엔티티자체에서 해결할 수 있는 것들은 엔티티 안에 로직을 넣는것이 좋음(객체지향적)
//    -> setter를 가지고 어떻게 하는게 아니라 엔티티 안에서 지지고 볶기

    /**
     * 재고 수량 증가
     *
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * 재고 수량 감소
     *
     * @param quantity
     */
    public void removeStock(int quantity) {
        int resStock = this.stockQuantity - quantity;
        if (resStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resStock;
    }
}
