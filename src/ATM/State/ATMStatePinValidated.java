package State;

import exceptions.InvalidOperationException;
import models.ATMCard;

public class ATMStatePinValidated implements ATMState {
    @Override
    public boolean validatePin(ATMCard atmCard, String pin) throws InvalidOperationException {
        throw new InvalidOperationException("PIN already validated");
    }

    @Override
    public float displayBalance(ATMCard atmCard) throws InvalidOperationException {
        return atmCard.getBankAccount().getBalance();
    }

    @Override
    public void withdrawBalance(ATMCard atmCard, int amount) throws InvalidOperationException {
        if (atmCard.getBankAccount().getBalance() < amount)
            throw new InvalidOperationException("Insufficient Balance");
        else {
            atmCard.getBankAccount().withdrawBalance(amount);
        }
    }

    @Override
    public void depositBalance(ATMCard atmCard, int amount) throws InvalidOperationException {
        atmCard.getBankAccount().addBalance(amount);
    }
}
