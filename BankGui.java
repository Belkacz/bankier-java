package belkadev.pl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BankGui {
    Bankier bankier = new Bankier();

    private DefaultTableModel tableModel;

    public BankGui() {
        bankier = new Bankier();
        createGui();
    }

    private void showClientForm(JButton addClient) {
        addClient.setEnabled(false);
        JDialog clientFormDialog = new JDialog();
        clientFormDialog.setTitle("Nowy Klient");
        clientFormDialog.setSize(400, 300);  // Ustawiamy rozmiar okna
        clientFormDialog.setLocationRelativeTo(null);  // Ustawiamy okno na środku ekranu

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        formPanel.add(new JLabel("Imię:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Nazwisko:"));
        JTextField surnameField = new JTextField();
        formPanel.add(surnameField);

        formPanel.add(new JLabel("Saldo:"));
        JTextField balanceField = new JTextField();
        formPanel.add(balanceField);

        formPanel.add(new JLabel("Oprocentowanie:"));
        JTextField interestField = new JTextField();
        formPanel.add(interestField);


        JButton saveClient = new JButton("Zapisz Klienta");
        formPanel.add(saveClient);
        JButton cancelClient = new JButton("Anuluj");
        formPanel.add(cancelClient);
        clientFormDialog.add(formPanel);
        clientFormDialog.setVisible(true);
        saveClient.addActionListener(e -> {
            String name = nameField.getText();
            String surname = surnameField.getText();
            Double balance = Double.valueOf(balanceField.getText());
            Double interest = Double.valueOf(interestField.getText());
            this.bankier.addNewClient(name, surname, balance, interest);
            refreshTable();
        });

        cancelClient.addActionListener(e -> {
            clientFormDialog.dispose();
            addClient.setEnabled(true);
        });
    }

    private void showTransferForm(JButton moneyTransferBtn) {
        moneyTransferBtn.setEnabled(false);
        List<Client> clients = this.bankier.getClients();
        JDialog transferDialog = new JDialog();
        transferDialog.setTitle("Przelej Pieniądze");
        transferDialog.setSize(800, 400);
        transferDialog.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        formPanel.add(new JLabel("Nadawca:"));
        JComboBox<Client> senderComboBox = new JComboBox<>(clients.toArray(new Client[0]));
        formPanel.add(senderComboBox);

        formPanel.add(new JLabel("Odbiorca:"));
        JComboBox<Client> receiverComboBox = new JComboBox<>(clients.toArray(new Client[0]));
        formPanel.add(receiverComboBox);

        formPanel.add(new JLabel("Kwota:"));
        JTextField amountField = new JTextField();
        formPanel.add(amountField);

        JButton transferBtn = new JButton("Przelej");
        formPanel.add(transferBtn);
        JButton cancelButton = new JButton("Anuluj");
        formPanel.add(cancelButton);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        formPanel.add(errorLabel);

        transferDialog.add(formPanel);
        transferDialog.setVisible(true);
        transferBtn.addActionListener(e -> {
            Client c1 = (Client) senderComboBox.getSelectedItem();
            Client c2 = (Client) receiverComboBox.getSelectedItem();
            double amount = 0;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                errorLabel.setText("Coś jest nieteges z tą liczbą");
                return;
            }
            if(c1 != null && c2 != null && amount > 0) {
                if(this.bankier.transferMoney(c1, c2, amount)) {
                    refreshTable();
                    transferDialog.setVisible(false);
                    moneyTransferBtn.setEnabled(true);
                } else {
                    errorLabel.setText("Coś poszło bardzo nie ten teges");
                }
            }
        });
        cancelButton.addActionListener(e -> {
            transferDialog.dispose();
            moneyTransferBtn.setEnabled(true);
        });
    }

    private void createTable(JPanel bottomPanel) {

    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Client> clientsLocal = this.bankier.getClients();
        for (Client client : clientsLocal) {
            Object[] row = {
                    client.getId(),
                    client.getFirstName(),
                    client.getSurName(),
                    client.getAccBalance(),
                    client.getInterest()
            };
            tableModel.addRow(row);
        }
    }

    private void createGui() {
        JFrame frame = new JFrame("Bankier - aplikacja bankowa");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        String[] tableColumns = {"ID", "Imię", "Nazwisko", "Saldo", "Oprocentowanie", "VIP status"};
        tableModel = new DefaultTableModel(tableColumns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane centerPene = new JScrollPane(table);

        JPanel bottomPanel = new JPanel();

        JButton addClient = new JButton("Dodaj Klienta");
        bottomPanel.add(addClient);
        addClient.addActionListener(e -> showClientForm(addClient));

        JButton moneyTransferBtn = new JButton("Przelej Środki");
        bottomPanel.add(moneyTransferBtn);
        moneyTransferBtn.addActionListener(e -> showTransferForm(moneyTransferBtn));

        frame.add(centerPene, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        refreshTable();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankGui());
    }
}
