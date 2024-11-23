import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PetAdoptionUI {

    private static final String URL = "jdbc:mysql://localhost:3306/pet_adoption_db"; // Database URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "1234"; // Your MySQL password
    
    private static Connection connection;
    
    // Swing components
    private JFrame frame;
    private JPanel panel;
    private JTextField petNameField, petBreedField, petAgeField, petTypeField, adopterNameField;
    private JButton addPetButton, adoptPetButton;
    private JTable petTable;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                PetAdoptionUI window = new PetAdoptionUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
