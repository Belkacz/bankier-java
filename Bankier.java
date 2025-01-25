/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package belkadev.pl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author belkacz
 */
public class Bankier {
    static Scanner scan = new Scanner(System.in);
    private List<Client> clients = new ArrayList<>();


    private static void addClient(){
        System.out.println("Podaj imię klienta:");
        String firstName = scan.nextLine();
        System.out.println("Podaj nazwisko klienta:");
        String lastName = scan.nextLine();
        System.out.println("Podaj Stan konta:");
        String accStart = scan.nextLine();
        System.out.println("Klient " + firstName);
    }

    public void addNewClient(String name, String surname, Double balance, Double interest) {
        Client client = new Client(name, surname, balance, interest);
        clients.add(client);
    }

    public List<Client> getClients() {
        return clients;
    }

    public boolean transferMoney(Client client1, Client client2, double amount){
        double c1Saldo = client1.getAccBalance();
        if(c1Saldo < amount) {
            return false;
        }
        double c2Saldo = client2.getAccBalance();
        c1Saldo -= amount;
        c2Saldo += amount;
        client1.setAccBalance(c1Saldo);
        client2.setAccBalance(c2Saldo);
        return true;
    }

    public static void main(String[] args) {
        addClient();
    }
}

