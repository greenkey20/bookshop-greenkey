package com.greenkey20.order.entity;

import com.greenkey20.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;

// 2022.12.20(화) 17h55 구현
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ORDER_BOOK")
public class OrderBook extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderBookId;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public void setOrder(Order order) {
        this.order = order;
        if (!this.order.getOrderBooks().contains(this)) {
            this.order.getOrderBooks().add(this);
        }
    }

    public void setBookt(Book book) {
        this.book = book;
        if (!this.book.getOrderBooks().contains(this)) {
            this.book.getOrderBooks().add(this);
        }
    }
}
