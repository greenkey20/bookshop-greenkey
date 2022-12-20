package com.greenkey20.book.entity;

import com.greenkey20.order.entity.OrderBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 2022.12.20(화) 17h10 구현
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer quantity; // 재고 수량 관리용 속성

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("BOOK_FOR_SALE")
    @Column(length = 20, nullable = false)
    private BookStatus bookStatus;

    @ManyToOne
    @JoinColumn(name = "CLASSIFICATION_ID")
    private Classification classification;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<OrderBook> orderBooks = new ArrayList<>();

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public void setOrderBook(OrderBook orderBook) {
        orderBooks.add(orderBook);
        if (orderBook.getBook() != this) {
            orderBook.setBook(this);
        }
    }

    public enum BookStatus {
        BOOK_FOR_SALE("판매 중"),
        BOOK_SOLD_OUT("재고 없음"),
        BOOK_OUT_OF_PRINT("절판");

        @Getter
        private String status;

        BookStatus(String status) {
            this.status = status;
        }
    }
}
