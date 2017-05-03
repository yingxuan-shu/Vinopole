package vinopole.view.theme;

import vinopole.dao.criteria.CriteriaThemeDAO;
import vinopole.model.criteria.CriteriaTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyTheme extends JFrame {
    private JTextField textField;

    /**
     * Create the frame.
     */
    public ModifyTheme(final CriteriaTheme theme) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Modifier le thème");
        lblNewLabel.setBounds(151, 28, 142, 16);
        contentPane.add(lblNewLabel);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(78, 63, 61, 16);
        contentPane.add(lblNom);

        textField = new JTextField();
        textField.setText(theme.toString());
        textField.setBounds(151, 58, 169, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String themeName = textField.getText();
                CriteriaThemeDAO themeDao = new CriteriaThemeDAO();
                theme.setName(themeName);
                themeDao.update(theme);
                dispose();
            }
        });
        btnNewButton.setBounds(78, 159, 117, 29);
        contentPane.add(btnNewButton);

        JButton btnCancle = new JButton("Annuler");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancle.setBounds(244, 159, 117, 29);
        contentPane.add(btnCancle);
    }
}
