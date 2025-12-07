package models;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    Map<Person, Double> balanceSheet;

    public BalanceSheet() {
        this.balanceSheet = new HashMap<>();
    }

    public void adjustAmount(Person p, double amount) {
        balanceSheet.put(p, balanceSheet.getOrDefault(p, 0.0) + amount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Person, Double> en : balanceSheet.entrySet()) {
            Person p = en.getKey();
            double value = en.getValue();
            String st = p.getName() + " : " + value + "\n";
            sb.append(st);
        }
        return sb.toString();
    }
}
