package belkadev.pl;

public class VipClient extends Client {
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

    @Override
    public String toString() {
        return "Imię: " + getFirstName() + ", Nazwisko: " + getSurName() + ", saldo konta: " + getAccBalance()
                + ", oprocentowanie: " + getInterest() + ", dodatkowe oprocentowanie" + this.additionalInterests;
    }
}
