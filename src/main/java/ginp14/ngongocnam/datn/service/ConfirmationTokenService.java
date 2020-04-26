package ginp14.ngongocnam.datn.service;

import ginp14.project3.model.ConfirmationToken;

public interface ConfirmationTokenService {
    void save(ConfirmationToken confirmationToken);
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
