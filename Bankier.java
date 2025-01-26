/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package belkadev.pl;

import java.io.*;
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

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public boolean transferMoney(Client client1, Client client2, double amount){
        double c1Saldo = client1.getAccBalance();
        if(c1Saldo < amount || amount <= 0) {
            return false;
        }
        double c2Saldo = client2.getAccBalance();
        c1Saldo -= amount;
        c2Saldo += amount;
        client1.setAccBalance(c1Saldo);
        client2.setAccBalance(c2Saldo);
        return true;
    }

    public boolean depositMoney(Client client, double amount){
        double clientSaldo = client.getAccBalance();
        if(amount <= 0) {
            return false;
        }
        clientSaldo += amount;
        client.setAccBalance(clientSaldo);
        return true;
    }

    public boolean withdrawMoney(Client client, double amount){
        double clientSaldo = client.getAccBalance();
        if(clientSaldo < amount || amount <= 0) {
            return false;
        }
        clientSaldo -= amount;
        client.setAccBalance(clientSaldo);
        return true;
    }

    public void changeToVip(Client client, double additionalInterestRate) {
        if (client instanceof VipClient) {
            System.out.println("Klient już jest VIP-em!");
            return;
        }

        VipClient vipClient = new VipClient(
                client.getFirstName(),
                client.getSurName(),
                client.getAccBalance(),
                client.getInterest(),
                additionalInterestRate
        );

        int index = clients.indexOf(client);
        if (index != -1) {
            clients.set(index, vipClient);
            System.out.println("Klient został promowany na VIP!");
        } else {
            System.out.println("Nie znaleziono klienta w liście.");
        }
    }

    public void changeToNonVip(Client vipClient) {
        if (vipClient instanceof Client) {
            System.out.println("Klient już nie jest chłopem!");
            return;
        }

        Client pesantClient = new Client(
                vipClient.getFirstName(),
                vipClient.getSurName(),
                vipClient.getAccBalance(),
                vipClient.getInterest()
        );

        int index = clients.indexOf(vipClient);
        if (index != -1) {
            clients.set(index, pesantClient);
            System.out.println("Klient został zdegradowany na chłopa!");
        } else {
            System.out.println("Nie znaleziono klienta w liście.");
        }
    }

    public void calculateClientsInterest(List<Client> clients) {
        for (Client client : clients) {
            if (client instanceof VipClient vipClient) {
                vipClient.calculateInterest();
            } else {
                client.calculateInterest();
            }
        }
    }

    public void saveClientsToFile(List<Client> clients, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(clients);
            System.out.println("Klienci zostali zapisani do pliku.");
        } catch (IOException e) {
            System.out.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }

    public List<Client> loadClientsFromFile(String fileName) {
        List<Client> clients = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            clients = (List<Client>) ois.readObject();  // Odczytanie listy klientów
            System.out.println("Klienci zostali wczytani z pliku.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd wczytywania z pliku: " + e.getMessage());
        }
        return clients;
    }

    public static void main(String[] args) {
        addClient();
    }
}

