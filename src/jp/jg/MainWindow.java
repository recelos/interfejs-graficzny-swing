package jp.jg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


/*
    Autor: Jakub Grelowski gr SR/NP 9:15
    Zadanie: Panel logowania w Swingu
*/

public class MainWindow extends JFrame {


    public static void main(String[] args) {
        new MainWindow()
                .setVisible(true);
    }

    public MainWindow() {
        super("Panel logowania");
        buildFrame();
    }

    private void buildFrame() {

        setSize(new Dimension(350, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new GridBagLayout());

        JLabel loginLabel = new JLabel("Login: ");
        JTextField loginTextField = new JTextField(300);
        Box loginBox = Box.createHorizontalBox();


        loginBox.add(loginLabel);
        loginBox.add(loginTextField);
        Box passwordBox = Box.createHorizontalBox();

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(300);

        passwordBox.add(passwordLabel);
        passwordBox.add(passwordField);

        Box buttonBox = Box.createHorizontalBox();

        JButton continueButton = new JButton("Continue");
        JButton cancelButton = new JButton("Cancel");

        buttonBox.add(continueButton);
        buttonBox.add(cancelButton);


        final String HINT = "login admin, hasło admin lub login user haslo password";
        JLabel hintLabel = new JLabel(HINT);

        Box hintBox = Box.createHorizontalBox();
        hintBox.add(hintLabel);

        hintBox.setVisible(false);



        continueButton.addActionListener(l -> {
            continueButtonClick(loginTextField, passwordField, hintBox);
        });


        cancelButton.addActionListener(l -> {
            cancelButtonClick(loginTextField, passwordField);
        });



        Box root = Box.createVerticalBox();

        setupRoot(loginBox, passwordBox, buttonBox, hintBox ,root);

        panel.add(root);


    }

    /**
     * Implementacja logiki przycisku Cancel.
     *
     * Po wciśnięciu usuwa treść z pól tekstowych.
     *
     * @param loginTextField pole wpisywania loginu
     * @param passwordField pole wpisywania hasła
     */
    private void cancelButtonClick(JTextField loginTextField, JPasswordField passwordField) {
        loginTextField.setText("");
        passwordField.setText("");
    }

    private void setupRoot(Box loginBox, Box passwordBox, Box buttonBox, Box hintBox, Box root) {
        root.add(loginBox);
        root.add(passwordBox);
        root.add(buttonBox);
        root.add(hintBox);
    }

    /**
     * Implementacja logiki przycisku Continue.
     *
     * W przypadku wprowadzenia poprawnych danych wyświetla okno dialogowe i
     * zmienia kolor na zielony.
     *
     * W przypadku wprowadzenia niepoprawnych danych zmienia kolor tła na czerwony
     * i wyświetla podpowiedź.
     *
     * @param loginTextField  pole wpisywania loginu
     * @param passwordField  pole wpisywania hasła
     * @param hintBox  komponent zawierający podpowiedź dotyczącą hasła
     */
    private void continueButtonClick(JTextField loginTextField, JPasswordField passwordField, Box hintBox) {
        Database data = new Database();

        if (loginTextField.getText() != null && passwordField.getPassword() != null) {
            if (data.authorize(loginTextField.getText(), passwordField.getPassword())) {
                hintBox.setVisible(false);
                this.getContentPane().setBackground(Color.green);
                JOptionPane.showMessageDialog(this, "Zalogowałeś się!");
            } else {
                hintBox.setVisible(true);
                this.getContentPane().setBackground(Color.red);
            }

        }
    }
}


