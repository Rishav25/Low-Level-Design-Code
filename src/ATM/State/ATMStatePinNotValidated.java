package State;

import exceptions.InvalidOperationException;
import models.ATMCard;

public class ATMStatePinNotValidated implements ATMState {
    @Override
    public boolean validatePin(ATMCard atmCard, String pin) throws InvalidOperationException {
        if (pin.equals(atmCard.getPin()))
            return true;
        else
            throw new InvalidOperationException("Invalid PIN");
    }

    @Override
    public float displayBalance(ATMCard atmCard) throws InvalidOperationException {
        throw new InvalidOperationException("PIN not validated");
    }

    @Override
    public void withdrawBalance(ATMCard atmCard, int amount) throws InvalidOperationException {
        throw new InvalidOperationException("PIN not validated");
    }

    @Override
    public void depositBalance(ATMCard atmCard, int amount) throws InvalidOperationException {
        throw new InvalidOperationException("PIN not validated");
    }
}
