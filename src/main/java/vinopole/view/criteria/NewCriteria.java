package vinopole.view.criteria;

import vinopole.dao.criteria.CriteriaDAO;
import vinopole.dao.criteria.CriteriaThemeDAO;
import vinopole.dao.criteria.CriteriaTypeDAO;
import vinopole.model.criteria.Criteria;
import vinopole.model.criteria.CriteriaTheme;
import vinopole.model.criteria.CriteriaType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewCriteria extends JFrame {

    private JTextField textField;

    /**
     * Create the frame.
     */
    public NewCriteria() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 501, 416);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Nom");
        lblName.setBounds(104, 84, 54, 15);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Thème");
        lblNewLabel.setBounds(104, 144, 54, 15);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(160, 81, 162, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nouveau critère");
        lblNewLabel_1.setBounds(196, 50, 138, 21);
        contentPane.add(lblNewLabel_1);

        CriteriaThemeDAO themeDao = new CriteriaThemeDAO();
        List<CriteriaTheme> themels = themeDao.getAll();
        if (themels.size() < 1) {
            JOptionPane.showMessageDialog(null, (Object) "Pas de thème, merci d'en ajouter un !");
        }
        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(themels.toArray()));
        comboBox.setBounds(160, 141, 162, 18);
        contentPane.add(comboBox);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(104, 193, 61, 16);
        contentPane.add(lblType);
        CriteriaTypeDAO typeDao = new CriteriaTypeDAO();
        List<CriteriaType> typels = typeDao.getAll();

        final JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(typels.toArray()));
        comboBox_1.setBounds(160, 189, 162, 27);
        contentPane.add(comboBox_1);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String criteriaName = textField.getText();
                CriteriaTheme cTheme = (CriteriaTheme) comboBox.getSelectedItem();
                CriteriaType cType = (CriteriaType) comboBox_1.getSelectedItem();
                Criteria criteria = new Criteria(criteriaName, cTheme, cType);
                CriteriaDAO criteriaDao = new CriteriaDAO();
                criteriaDao.save(criteria);

                dispose();
            }
        });
        btnNewButton.setBounds(106, 246, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnCancle = new JButton("Annuler");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancle.setBounds(253, 246, 93, 23);
        contentPane.add(btnCancle);
    }
}
