package belkadev.pl;

import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private static int counterId = 0;

    private String firstName;
    private String surName;
    private Double accBalance;
    private Double interest;

    public Client(String firstName, String surName, Double initialAccBalance, Double interest) {
        counterId++;
        this.id = counterId;
        this.firstName = firstName;
        this.surName = surName;
        this.accBalance = initialAccBalance;
        this.interest = interest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Client.counterId = counterId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(Double accBalance) {
        this.accBalance = accBalance;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public boolean getVipStatus() {
        return false;
    }

    public boolean setVipStatus() {
        return false;
    }


    public void calculateInterest() {
        this.accBalance += this.accBalance * (this.interest / 100);
    }

    @Override
    public String toString() {
        return "ID " + this.id + ", Imię: " + this.firstName + ", Nazwisko: " + this.surName + ", saldo konta: " + this.accBalance
                + ", oprocentowanie: " + this.interest;
    }

    public String toStringShort(){
        return "ID " + this.id + ", Imię: " + this.firstName + ", Nazwisko: " + this.surName + ", saldo konta: " + this.accBalance;
    }
}
