package vinopole.view.serie;

import vinopole.dao.FormDAO;
import vinopole.dao.SerieDAO;
import vinopole.dao.wine.WineDAO;
import vinopole.model.Serie;
import vinopole.model.form.Form;
import vinopole.model.wine.Wine;
import vinopole.view.MainFrame;
import vinopole.view.model.ViewWineModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewSerie extends JFrame {
    private JTextField textField;
    private List<Wine> wines = new ArrayList<Wine>();

    /**
     * Create the frame.
     */
    public NewSerie(final MainFrame mainframe) {
        final ViewWineModel vWM = new ViewWineModel(new WineDAO().getAll());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 601, 423);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewSerie = new JLabel("Nouvelle Série");
        lblNewSerie.setBounds(256, 29, 121, 15);
        contentPane.add(lblNewSerie);

        final JComboBox comboBox = new JComboBox();
        FormDAO formDao = new FormDAO();
        List<Form> ls = formDao.getAll();
        comboBox.setModel(new DefaultComboBoxModel(ls.toArray()));
        comboBox.setBounds(275, 322, 230, 21);
        contentPane.add(comboBox);

        JLabel lblFiche = new JLabel("Fiche");
        lblFiche.setBounds(161, 322, 54, 15);
        contentPane.add(lblFiche);

        JLabel lblName = new JLabel("Nom");
        lblName.setBounds(161, 77, 54, 15);
        contentPane.add(lblName);

        textField = new JTextField();
        textField.setBounds(275, 73, 175, 23);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String serieName = textField.getText();
                wines = vWM.getSelected();
                Form form = (Form) comboBox.getSelectedItem();
                Serie serie = new Serie(serieName, form, wines);
                SerieDAO serieDao = new SerieDAO();
                serieDao.save(serie);
                mainframe.updateJtableData();
                dispose();
            }
        });
        btnNewButton.setBounds(171, 362, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnCancle = new JButton("Annuler");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancle.setBounds(343, 362, 93, 23);
        contentPane.add(btnCancle);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(59, 106, 495, 206);
        contentPane.add(scrollPane);

        JTable table = new JTable();
        table.setModel(new ViewWineModel(new WineDAO().getAll()));
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        scrollPane.setViewportView(table);


    }
}
