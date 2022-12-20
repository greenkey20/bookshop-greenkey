package com.greenkey20.order.entity;

import com.greenkey20.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 2022.12.20(화) 16h20 구현
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "orders")
public class Order extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderBook> orderBooks = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void setOrderBook(OrderBook orderBook) {
        orderBooks.add(orderBook);
        if (orderBook.getOrder() != this) {
            orderBook.setOrder(this);
        }
    }

    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확인"),
        ORDER_COMPLETE(3, "주문 처리 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int statusNumber;

        @Getter
        private String statusDescription;

        OrderStatus(int statusNumber, String statusDescription) {
            this.statusNumber = statusNumber;
            this.statusDescription = statusDescription;
        }
    }
}
