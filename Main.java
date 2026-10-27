import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    // Main program objects
    BloodInventory inventory = new BloodInventory();
    DonorList donorList = new DonorList();
    BloodRequestList requestList = new BloodRequestList();

    // Donor input fields
    JTextField donorNameField = new JTextField(10);
    JTextField donorIdField = new JTextField(10);
    JTextField donorAgeField = new JTextField(5);
    JTextField donorContactField = new JTextField(10);

    JComboBox<String> donorGroupBox = new JComboBox<>(new String[]{
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    });

    // Blood input fields
    JComboBox<String> bloodGroupBox = new JComboBox<>(new String[]{
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    });
    JTextField bloodUnitField = new JTextField(5);

    // Request input fields
    JTextField patientNameField = new JTextField(10);
    JComboBox<String> requestGroupBox = new JComboBox<>(new String[]{
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    });
    JTextField requestUnitField = new JTextField(5);

    // Area for showing output
    JTextArea outputArea = new JTextArea(14, 50);

    public Main() {
        setTitle("Blood Bank Management System");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 5, 5));

        // ---------------- DONOR PANEL ----------------
        JPanel donorPanel = new JPanel();
        donorPanel.setBorder(BorderFactory.createTitledBorder("Add Donor"));

        donorPanel.add(new JLabel("Name:"));
        donorPanel.add(donorNameField);
        donorPanel.add(new JLabel("ID:"));
        donorPanel.add(donorIdField);
        donorPanel.add(new JLabel("Age:"));
        donorPanel.add(donorAgeField);
        donorPanel.add(new JLabel("Blood:"));
        donorPanel.add(donorGroupBox);
        donorPanel.add(new JLabel("Contact:"));
        donorPanel.add(donorContactField);

        JButton addDonorButton = new JButton("Add Donor");
        donorPanel.add(addDonorButton);

        // ---------------- ADD BLOOD PANEL ----------------
        JPanel bloodPanel = new JPanel();
        bloodPanel.setBorder(BorderFactory.createTitledBorder("Add Blood"));

        bloodPanel.add(new JLabel("Blood Group:"));
        bloodPanel.add(bloodGroupBox);
        bloodPanel.add(new JLabel("Unit:"));
        bloodPanel.add(bloodUnitField);

        JButton addBloodButton = new JButton("Add Blood");
        bloodPanel.add(addBloodButton);

        // ---------------- REQUEST PANEL ----------------
        JPanel requestPanel = new JPanel();
        requestPanel.setBorder(BorderFactory.createTitledBorder("Blood Request"));

        requestPanel.add(new JLabel("Patient:"));
        requestPanel.add(patientNameField);
        requestPanel.add(new JLabel("Blood Group:"));
        requestPanel.add(requestGroupBox);
        requestPanel.add(new JLabel("Unit:"));
        requestPanel.add(requestUnitField);

        JButton requestButton = new JButton("Request Blood");
        requestPanel.add(requestButton);

        // ---------------- VIEW BUTTONS ----------------
        JPanel buttonPanel = new JPanel();

        JButton stockButton = new JButton("View Stock");
        JButton donorButton = new JButton("View Donors");
        JButton requestViewButton = new JButton("View Requests");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(stockButton);
        buttonPanel.add(donorButton);
        buttonPanel.add(requestViewButton);
        buttonPanel.add(clearButton);

        mainPanel.add(donorPanel);
        mainPanel.add(bloodPanel);
        mainPanel.add(requestPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // ---------------- ADD DONOR BUTTON ----------------
        addDonorButton.addActionListener(e -> {
            try {
                String name = donorNameField.getText();
                String id = donorIdField.getText();
                int age = Integer.parseInt(donorAgeField.getText());
                String group = donorGroupBox.getSelectedItem().toString();
                String contact = donorContactField.getText();

                if (name.isEmpty() || id.isEmpty() || contact.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Please fill all donor fields.");
                    return;
                }

                if (age <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter a valid age.");
                    return;
                }

                Donor donor = new Donor(name, id, age, group, contact);
                donorList.addDonor(donor);

                outputArea.append("Donor added: " + donor + "\n");

                donorNameField.setText("");
                donorIdField.setText("");
                donorAgeField.setText("");
                donorContactField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid age.");
            }
        });

        // ---------------- ADD BLOOD BUTTON ----------------
        addBloodButton.addActionListener(e -> {
            try {
                String group = bloodGroupBox.getSelectedItem().toString();
                int unit = Integer.parseInt(bloodUnitField.getText());

                if (unit <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Unit must be greater than 0.");
                    return;
                }

                inventory.addBlood(group, unit);
                outputArea.append(unit + " unit(s) of " + group +
                        " blood added.\n");
                bloodUnitField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid unit number.");
            }
        });

        // ---------------- REQUEST BLOOD BUTTON ----------------
        requestButton.addActionListener(e -> {
            try {
                String patientName = patientNameField.getText();
                String group = requestGroupBox.getSelectedItem().toString();
                int unit = Integer.parseInt(requestUnitField.getText());

                if (patientName.isEmpty() || unit <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Enter patient name and valid units.");
                    return;
                }

                if (inventory.removeBlood(group, unit)) {
                    BloodRequest request = new BloodRequest(
                            patientName, group, unit);

                    requestList.addRequest(request);

                    outputArea.append(patientName + " requested " + unit +
                            " unit(s) of " + group +
                            ". Request successful.\n");

                    patientNameField.setText("");
                    requestUnitField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Not enough blood available.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter valid request information.");
            }
        });

        // ---------------- VIEW STOCK BUTTON ----------------
        stockButton.addActionListener(e -> {
            outputArea.setText("");

            String[] groups = {
                    "A+", "A-", "B+", "B-",
                    "AB+", "AB-", "O+", "O-"
            };

            outputArea.append("----- BLOOD STOCK -----\n");

            for (String group : groups) {
                outputArea.append(group + " : " +
                        inventory.getStock(group) + " unit(s)\n");
            }
        });

        // ---------------- VIEW DONORS BUTTON ----------------
        donorButton.addActionListener(e -> {
            outputArea.setText(donorList.getAllDonors());
        });

        // ---------------- VIEW REQUESTS BUTTON ----------------
        requestViewButton.addActionListener(e -> {
            outputArea.setText(requestList.getAllRequests());
        });

        // ---------------- CLEAR BUTTON ----------------
        clearButton.addActionListener(e -> {
            outputArea.setText("");
        });
    }

    public static void main(String[] args) {
        if (AdminLogin.showLogin()) {
            BloodBankGUI.start();
        }
    }
}

// ---------------- ADMIN LOGIN PANEL ----------------
class AdminLogin {

    public static boolean showLogin() {
        JFrame loginFrame = new JFrame("Admin Login");
        loginFrame.setSize(350, 220);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton);

        final boolean[] loginSuccess = {false};

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("admin1234")) {
                loginSuccess[0] = true;
                loginFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(loginFrame,
                        "Invalid username or password.");
                passwordField.setText("");
            }
        });

        exitButton.addActionListener(e -> {
            loginFrame.dispose();
            System.exit(0);
        });

        loginFrame.add(panel);
        loginFrame.setVisible(true);

        while (loginFrame.isDisplayable()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        return loginSuccess[0];
    }
}
