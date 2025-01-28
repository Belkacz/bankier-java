package belkadev.pl;

public class VipClient extends Client {
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
}
