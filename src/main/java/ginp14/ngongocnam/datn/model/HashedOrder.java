package ginp14.ngongocnam.datn.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hashed_order")
public class HashedOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "hashed_order_info", columnDefinition = "blob")
    private byte[] hashedOrderInfo;

    @Column(name = "signed_order_info", columnDefinition = "blob")
    private byte[] signedOrderInfo;

    public HashedOrder() {
    }

    public HashedOrder(User user, byte[] hashedOrderInfo, byte[] signedOrderInfo) {
        this.user = user;
        this.hashedOrderInfo = hashedOrderInfo;
        this.signedOrderInfo = signedOrderInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getHashedOrderInfo() {
        return hashedOrderInfo;
    }

    public void setHashedOrderInfo(byte[] hashedOrderInfo) {
        this.hashedOrderInfo = hashedOrderInfo;
    }

    public byte[] getSignedOrderInfo() {
        return signedOrderInfo;
    }

    public void setSignedOrderInfo(byte[] signedOrderInfo) {
        this.signedOrderInfo = signedOrderInfo;
    }
}
