package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.model.GenerateKeys;
import ginp14.ngongocnam.datn.model.HashedOrder;
import ginp14.ngongocnam.datn.model.Order;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

public interface KeyService {
    void save(GenerateKeys keys);
    GenerateKeys generateKeys();
    PrivateKey getPrivateKey(byte[] privateKey);
    PublicKey getPublicKey(int id);
    byte[] sign(byte[] hashed_order, byte[] private_key);
    boolean verifySignature(byte[] data, byte[] signature, HashedOrder order);
}
