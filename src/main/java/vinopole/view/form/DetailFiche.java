package vinopole.view.form;

import vinopole.model.form.Form;
import vinopole.view.model.ViewCriteriaModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailFiche extends JFrame {
    private Form form;
    private JTable table;


    /**
     * Create the frame.
     */
    public DetailFiche(final Form form) {
        this.form = form;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 548, 367);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Détails fiche");
        lblNewLabel.setBounds(208, 10, 165, 23);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(43, 27, 479, 257);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new ViewCriteriaModel(form.getCriterias()));
        scrollPane.setViewportView(table);

        JButton btnClose = new JButton("Fermer");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnClose.setBounds(280, 296, 93, 23);
        contentPane.add(btnClose);

        final DetailFiche frame = this;

        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifierFiche mf = new ModifierFiche(form, frame);
                mf.setVisible(true);
            }
        });
        btnModifier.setBounds(53, 296, 117, 29);
        contentPane.add(btnModifier);
    }


    /**
     * Use to update table
     */
    public void updateTable() {
        table.setModel(new ViewCriteriaModel(form.getCriterias()));
    }

}
