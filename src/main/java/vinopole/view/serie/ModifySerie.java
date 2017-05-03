package vinopole.view.serie;

import vinopole.dao.ResponseFormDAO;
import vinopole.dao.SerieDAO;
import vinopole.dao.wine.WineDAO;
import vinopole.model.Serie;
import vinopole.model.form.Form;
import vinopole.model.wine.Wine;
import vinopole.view.form.DetailFiche;
import vinopole.view.model.ViewWineModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ModifySerie extends JFrame {
    private JTextField textField;
    private List<Wine> wines = new ArrayList<Wine>();
    private ViewWineModel vWM;

    /**
     * Create the frame.
     */
    public ModifySerie(final Serie serie) {
        vWM = new ViewWineModel(new WineDAO().getAll(), serie);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 479, 464);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Modifier la série");
        lblNewLabel.setBounds(172, 10, 132, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(65, 36, 68, 15);
        contentPane.add(lblNom);

        textField = new JTextField();
        textField.setText(serie.getName());
        textField.setBounds(143, 35, 200, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 83, 443, 158);
        contentPane.add(scrollPane);

        JTable table = new JTable();
        table.setModel(vWM);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        scrollPane.setViewportView(table);

        JLabel lblVin = new JLabel("Vin");
        lblVin.setBounds(10, 68, 54, 15);
        contentPane.add(lblVin);

        final Form form = serie.getForm();
        JButton btnDetailFiche = new JButton("Détail fiche");
        btnDetailFiche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DetailFiche df = new DetailFiche(form);
                df.setVisible(true);
            }
        });
        btnDetailFiche.setBounds(211, 302, 132, 23);
        contentPane.add(btnDetailFiche);

        JLabel lblNbReponse = new JLabel("Nb Réponses");
        lblNbReponse.setBounds(65, 268, 68, 15);
        contentPane.add(lblNbReponse);

        JTextField textField_1 = new JTextField();
        String nbReponde = Integer.toString(new ResponseFormDAO().getAll().size());
        textField_1.setText(nbReponde);
        textField_1.setBounds(143, 265, 200, 21);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String serieName = textField.getText();
                wines = vWM.getSelected();

                SerieDAO serieDao = new SerieDAO();
                serie.setName(serieName);
                serie.setWines(wines);
                serieDao.update(serie);
                dispose();


            }
        });
        btnOk.setBounds(342, 377, 117, 29);
        contentPane.add(btnOk);
    }
}
