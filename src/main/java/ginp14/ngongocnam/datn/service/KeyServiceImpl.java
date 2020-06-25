package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.dao.KeyRepository;
import ginp14.ngongocnam.datn.model.GenerateKeys;
import ginp14.ngongocnam.datn.model.HashedOrder;
import ginp14.ngongocnam.datn.model.Order;
import ginp14.ngongocnam.datn.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    private KeyRepository keyRepository;

    @Override
    public void save(GenerateKeys keys) {
        keyRepository.save(keys);
    }

    @Override
    public GenerateKeys generateKeys() {
        GenerateKeys gk = new GenerateKeys();
        try {
            gk.createKeys();
            return gk;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public PrivateKey getPrivateKey(byte[] privateKey) {
//        byte[] keyBytes = keyRepository.findPrivateKeyByOrderId(id);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public PublicKey getPublicKey(int id) {
        byte[] keyBytes = keyRepository.findPublicKeyByOrderId(id);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public byte[] sign(byte[] hashedOrder, byte[] privateKey) {
        try {
            Signature rsa = Signature.getInstance("SHA1withRSA");
            rsa.initSign(getPrivateKey(privateKey));
            rsa.update(hashedOrder);
            return rsa.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean verifySignature(byte[] data, byte[] signature, HashedOrder hashedOrder) {
        try {
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initVerify(getPublicKey(hashedOrder.getId()));
            sig.update(data);
            return sig.verify(signature);
        }
        catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }


}
