package vinopole.view.form;

import vinopole.dao.FormDAO;
import vinopole.dao.criteria.CriteriaDAO;
import vinopole.model.criteria.Criteria;
import vinopole.model.form.Form;
import vinopole.view.model.ViewCriteriaModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewForm extends JFrame {

    private JTextField textField;
    private List<Criteria> criterias = new ArrayList<Criteria>();

    /**
     * Create the frame.
     */
    public NewForm() {
        final ViewCriteriaModel vCM = new ViewCriteriaModel(new CriteriaDAO().getAll());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 559, 430);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Nom");
        lblName.setBounds(117, 62, 65, 15);
        contentPane.add(lblName);

        textField = new JTextField();
        textField.setBounds(177, 58, 175, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewQuestionnaire = new JLabel("Nouvelle  Fiche");
        lblNewQuestionnaire.setBounds(203, 10, 149, 15);
        contentPane.add(lblNewQuestionnaire);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String formName = textField.getText();
                Calendar c = Calendar.getInstance();
                Date dataCurrentTime = new Date(c.getTimeInMillis());
                criterias = vCM.getSelected();
                Form form = new Form(formName, dataCurrentTime, criterias);
                FormDAO formDao = new FormDAO();
                formDao.save(form);

                dispose();

            }
        });
        btnNewButton.setBounds(155, 359, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Annuler");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(300, 359, 93, 23);
        contentPane.add(btnNewButton_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 115, 507, 241);
        contentPane.add(scrollPane);

        JTable table = new JTable();

        table.setModel(new ViewCriteriaModel(new CriteriaDAO().getAll()));
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        scrollPane.setViewportView(table);
    }
}
