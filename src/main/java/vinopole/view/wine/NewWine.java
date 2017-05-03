package vinopole.view.wine;

import vinopole.dao.wine.WineDAO;
import vinopole.dao.wine.WineTypeDAO;
import vinopole.model.wine.Wine;
import vinopole.model.wine.WineType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewWine extends JFrame {

    private JTextField textField;

    /**
     * Create the frame.
     */
    public NewWine() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 544, 407);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nom");
        lblNewLabel.setBounds(135, 92, 54, 15);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(218, 89, 153, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(135, 157, 54, 15);
        contentPane.add(lblType);

        WineTypeDAO wineTypeDao = new WineTypeDAO();
        List<WineType> ls = wineTypeDao.getAll();
        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(ls.toArray()));
        comboBox.setBounds(218, 154, 153, 21);
        contentPane.add(comboBox);

        JLabel lblInsertNewWine = new JLabel("Ajouter un vin");
        lblInsertNewWine.setBounds(201, 57, 93, 15);
        contentPane.add(lblInsertNewWine);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String wineName = textField.getText();
                WineType wineType = (WineType) comboBox.getSelectedItem();
                Wine wine = new Wine(wineName, wineType);
                WineDAO wineDao = new WineDAO();
                wineDao.save(wine);
                dispose();

            }
        });
        btnNewButton.setBounds(150, 230, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Annuler");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        btnNewButton_1.setBounds(295, 230, 93, 23);
        contentPane.add(btnNewButton_1);
    }
}
