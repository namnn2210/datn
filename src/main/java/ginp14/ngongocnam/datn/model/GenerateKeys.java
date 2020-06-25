package ginp14.ngongocnam.datn.model;

import javax.persistence.*;
import java.io.IOException;
import java.security.*;

@Entity
@Table(name = "digital_sign")
public class GenerateKeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    private int id;


    @OneToOne
    @JoinColumn(name = "hashed_order_id")
    private HashedOrder hashedOrder;

    @Column(name = "private_key", columnDefinition = "blob")
    private byte[] privateKey;
    @Column(name = "public_key", columnDefinition = "blob")
    private byte[] publicKey;

    public GenerateKeys() {
    }

    public void createKeys() throws NoSuchAlgorithmException, NoSuchProviderException{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate().getEncoded();
        this.publicKey = pair.getPublic().getEncoded();
    }

    public byte[] getPrivateKey() {
        return this.privateKey;
    }

    public byte[] getPublicKey() {
        return this.publicKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashedOrder getHashedOrder() {
        return hashedOrder;
    }

    public void setHashedOrder(HashedOrder hashedOrder) {
        this.hashedOrder = hashedOrder;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public static void main(String[] args) {
        GenerateKeys gk = new GenerateKeys();
        try {
            gk.createKeys();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        }
    }
}
