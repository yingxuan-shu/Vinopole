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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModifierFiche extends JFrame {

    private JTextField textField;
    private JTextField textField_2;
    private List<Criteria> criterias = new ArrayList<Criteria>();

    /**
     * Create the frame.
     *
     * @param
     */
    public ModifierFiche(final Form form) {
        this(form, null);
    }

    /**
     * Create frame from detailFiche (which will be update when this frame will be closed)
     *
     * @param form
     * @param frame
     */
    public ModifierFiche(final Form form, final DetailFiche frame) {
        final ViewCriteriaModel vCM = new ViewCriteriaModel(new CriteriaDAO().getAll(), form);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 559, 430);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Nom");
        lblName.setBounds(117, 35, 65, 15);
        contentPane.add(lblName);

        textField = new JTextField();
        textField.setText(form.getName());
        textField.setBounds(203, 35, 175, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewQuestionnaire = new JLabel("Modifier fiche");
        lblNewQuestionnaire.setBounds(203, 10, 149, 15);
        contentPane.add(lblNewQuestionnaire);

        JLabel lblNewLabel = new JLabel("Date création");
        lblNewLabel.setBounds(117, 83, 76, 15);
        contentPane.add(lblNewLabel);

        textField_2 = new JTextField();
        Date date = form.getCreationDate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        textField_2.setText(formatter.format(date));
        textField_2.setColumns(10);
        textField_2.setBounds(203, 80, 175, 21);
        contentPane.add(textField_2);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String formName = textField.getText();
                String formDate = textField_2.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date;
                try {
                    date = formatter.parse(formDate);
                    criterias = vCM.getSelected();


                    FormDAO formDao = new FormDAO();
                    form.setName(formName);
                    form.setCreationDate(date);
                    form.setCriterias(criterias);

                    formDao.update(form);
                    dispose();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                if (frame != null) {
                    frame.updateTable();
                }

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
        table.setModel(vCM);

        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        scrollPane.setViewportView(table);
    }
}
