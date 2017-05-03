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

public class ModifyWine extends JFrame {
    public static JTextField textField;


    /**
     * Create the frame.
     */
    public ModifyWine(final Wine wine) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblModifierVin = new JLabel("Modifier le vin");
        lblModifierVin.setBounds(155, 5, 100, 16);
        contentPane.add(lblModifierVin);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(140, 38, 30, 16);
        contentPane.add(lblNom);

        textField = new JTextField();
        textField.setText(wine.getName());
        textField.setBounds(185, 33, 189, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(140, 92, 30, 16);
        contentPane.add(lblType);

        final JComboBox comboBox = new JComboBox();
        WineTypeDAO wineTypeDao = new WineTypeDAO();
        List<WineType> ls = wineTypeDao.getAll();
        comboBox.setModel(new DefaultComboBoxModel(ls.toArray()));
        comboBox.setBounds(185, 88, 189, 27);
        contentPane.add(comboBox);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WineType wineType = (WineType) comboBox.getSelectedItem();
                WineDAO wineDao = new WineDAO();
                wine.setName(textField.getText());
                wine.setType(wineType);

                wineDao.update(wine);
                dispose();

            }
        });
        btnNewButton.setBounds(140, 179, 75, 29);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Annuler");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(262, 179, 86, 29);
        contentPane.add(btnNewButton_1);
    }

}
