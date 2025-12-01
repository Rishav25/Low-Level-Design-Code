package State;

import exceptions.InvalidOperationException;
import models.ATMCard;

public interface ATMState {
    public boolean validatePin(ATMCard atmCard, String pin) throws InvalidOperationException;

    public float displayBalance(ATMCard atmCard) throws InvalidOperationException;

    public void withdrawBalance(ATMCard atmCard, int amount) throws InvalidOperationException;

    public void depositBalance(ATMCard atmCard, int amount) throws InvalidOperationException;

}
