/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package belkadev.pl;

import java.util.Scanner;

/**
 *
 * @author belkacz
 */
public class Bankier {

    Client[] clients = {};
    public static Client addClient(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj imie klienta");
        String fName = scan.nextLine();
        System.out.println("Podaj nazwisko klienta");
        String surName = scan.nextLine();
        System.out.println("Podaj saldo klienta");
        double accSaldo = scan.nextDouble();
        System.out.println("Podaj oprocentowanie klienta");
        double accIntrest = scan.nextDouble();
        Client client = new Client(fName, surName, accSaldo, accIntrest);
        return client;
    }
    public static boolean transferMoney(Client client1, Client client2, double ammount) {
        double saldo1 = client1.getSaldo();
        double saldo2 = client2.getSaldo();
        if(saldo1 < ammount) {
            return false;
        }
        saldo1 = saldo1 - ammount;
        saldo2 = saldo2 + ammount;
        client1.setSaldo(saldo1);
        client2.setSaldo(saldo2);
        return true;
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scan = new Scanner(System.in);

        Client c1 = addClient();
        Client c2 = addClient();
        System.out.println("Podaj kwote:");
        double ammount = scan.nextDouble();
        transferMoney(c1, c2, ammount);
    }

}
