package belkadev.pl;

public class Client {
    private int id;
    private static int counterId = 0;

    private String firstName;
    private String surName;
    public double accBalance;
    private double interest;

    public Client(String firstName, String surName, Double initialAccBalance, Double interest) {
        counterId++;
        this.id = counterId;
        this.firstName = firstName;
        this.surName = surName;
        this.accBalance = initialAccBalance;
        this.interest = interest;
    }

    public int getId() {
        return this.id;
    }

    public String getFname() {
        return this.firstName;
    }

    public String getSname() {
        return this.surName;
    }

    public double getSaldo() {
        return this.accBalance;
    }

    public void setSaldo(double saldo) {
        this.accBalance = saldo;
    }

    public double getOprocentowanie() {
        return this.interest;
    }

    public void naliczOprocentowanie() {
        this.accBalance += this.accBalance * (this.interest / 100);
    }

    @Override
    public String toString() {
        return "Imię: " + this.firstName + ", Nazwisko: " + this.surName + ", saldo konta: " + this.accBalance
                + ", oprocentowanie: " + this.interest;
    }
}
