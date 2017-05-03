package vinopole.view.theme;

import vinopole.dao.criteria.CriteriaThemeDAO;
import vinopole.model.criteria.CriteriaTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTheme extends JFrame {

    private JTextField textField;


    /**
     * Create the frame.
     */
    public NewTheme() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 396);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Nom");
        lblName.setBounds(93, 123, 54, 15);
        contentPane.add(lblName);

        JLabel lblInsertNewTheme = new JLabel("Ajouter un thème");
        lblInsertNewTheme.setBounds(142, 48, 126, 15);
        contentPane.add(lblInsertNewTheme);

        textField = new JTextField();
        textField.setBounds(173, 119, 165, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String themeName = textField.getText();
                CriteriaTheme theme = new CriteriaTheme(themeName, 0);
                CriteriaThemeDAO themeDao = new CriteriaThemeDAO();
                themeDao.save(theme);

                dispose();
            }
        });
        btnNewButton.setBounds(107, 239, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnCancle = new JButton("Annuler");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancle.setBounds(245, 239, 93, 23);
        contentPane.add(btnCancle);
    }

}
