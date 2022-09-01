package com.statestreet.students.entity.one_to_one_unidirect;

import javax.persistence.*;

@Entity
@Table(name = "web_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String orderTrackingNumber;

    @OneToOne
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private BillingAddressEntity billingAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public BillingAddressEntity getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddressEntity billingAddress) {
        this.billingAddress = billingAddress;
    }
}
