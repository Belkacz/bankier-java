package belkadev.pl;

public class VipClient extends Client {
    Double additionalInterests;

    public VipClient(String firstName, String surName, Double initialAccBalance, Double interest,
            Double additionalInterests) {
        super(firstName, surName, initialAccBalance, interest);
        this.additionalInterests = additionalInterests;
    }
}
