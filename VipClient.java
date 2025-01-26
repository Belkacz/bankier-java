package belkadev.pl;

import java.io.Serializable;

public class VipClient extends Client implements Serializable {
    private boolean vipStatus = true;
    private double additionalInterests;

    public VipClient(String firstName, String surName, Double initialAccBalance, Double interest,
            Double additionalInterests) {
        super(firstName, surName, initialAccBalance, interest);
        this.additionalInterests = additionalInterests;
    }

    public double getAdditionalInterests() {
        return additionalInterests;
    }

    public void setAdditionalInterests(double additionalInterests) {
        this.additionalInterests = additionalInterests;
    }

    public boolean getVipStatus() {
        return this.vipStatus;
    }

    public void setVipStatus(boolean status) {
        this.vipStatus = status;
    }

    public void calculateInterest() {
        double currentBalance = this.getAccBalance();
        double newBalance = currentBalance * ((this.getInterest() + this.additionalInterests) / 100);
        this.setAccBalance(newBalance);
    }

    @Override
    public String toString() {
        return "Imię: " + getFirstName() + ", Nazwisko: " + getSurName() + ", saldo konta: " + getAccBalance()
                + ", oprocentowanie: " + getInterest() + ", dodatkowe oprocentowanie" + this.additionalInterests;
    }
}
