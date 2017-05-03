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

public class ModifyCriteria extends JFrame {
    private JTextField textField;

    /**
     * Create the frame.
     */
    public ModifyCriteria(final Criteria criteria) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 529, 374);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblModifycriteria = new JLabel("Modifier un critère");
        lblModifycriteria.setBounds(190, 10, 186, 22);
        contentPane.add(lblModifycriteria);

        JLabel lblName = new JLabel("Nom");
        lblName.setBounds(114, 50, 54, 15);
        contentPane.add(lblName);

        textField = new JTextField();
        textField.setText(criteria.getName());
        textField.setBounds(167, 47, 169, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblTheme = new JLabel("Thème");
        lblTheme.setBounds(114, 121, 54, 15);
        contentPane.add(lblTheme);

        CriteriaThemeDAO themeDao = new CriteriaThemeDAO();
        List<CriteriaTheme> themels = themeDao.getAll();
        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(themels.toArray()));
        comboBox.setBounds(167, 118, 169, 21);
        contentPane.add(comboBox);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(107, 185, 61, 16);
        contentPane.add(lblType);

        CriteriaTypeDAO typeDao = new CriteriaTypeDAO();
        List<CriteriaType> typels = typeDao.getAll();
        final JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(typels.toArray()));
        comboBox_1.setBounds(167, 181, 169, 21);
        contentPane.add(comboBox_1);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CriteriaTheme criteriaTheme = (CriteriaTheme) comboBox.getSelectedItem();
                CriteriaType criteriaType = (CriteriaType) comboBox_1.getSelectedItem();
                CriteriaDAO criteriaDao = new CriteriaDAO();
                criteria.setName(textField.getText());
                criteria.setTheme(criteriaTheme);
                criteria.setType(criteriaType);

                criteriaDao.update(criteria);
                dispose();

            }
        });
        btnNewButton.setBounds(111, 229, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnCancle = new JButton("Annuler");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancle.setBounds(283, 229, 93, 23);
        contentPane.add(btnCancle);
    }

}
