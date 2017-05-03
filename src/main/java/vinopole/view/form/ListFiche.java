package vinopole.view.form;

import vinopole.dao.FormDAO;
import vinopole.model.form.Form;
import vinopole.view.model.ViewFormModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListFiche extends JFrame {
    private ViewFormModel vFM;
    private JTable table;

    /**
     * Create the frame.
     */
    public ListFiche() {
        vFM = new ViewFormModel(new FormDAO().getAll());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 564, 378);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblListFicheNon = new JLabel("Toutes les fiches");
        lblListFicheNon.setBounds(201, 25, 209, 15);
        contentPane.add(lblListFicheNon);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 52, 482, 263);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Form form = (Form) table.getValueAt(table.getSelectedRow(), ViewFormModel.INDEX_FORM);

                    ModifierFiche mf = new ModifierFiche(form);
                    mf.setVisible(true);
                }
            }
        });
        table.setModel(vFM);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        scrollPane.setViewportView(table);

        JButton btnDelete = new JButton("Supprimer");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Form> list = vFM.getSelected();
                if (list.size() < 1) {
                    JOptionPane.showMessageDialog(null, "Merci de sélectionner les lignes que vous souhaitez supprimer !");
                } else {
                    FormDAO formDao = new FormDAO();

                    for (int i = list.size() - 1; i >= 0; i--) {
                        formDao.remove(list.get(i));
                    }
                }
                vFM = new ViewFormModel(new FormDAO().getAll());
                table.setModel(vFM);
            }
        });
        btnDelete.setBounds(283, 325, 93, 23);
        contentPane.add(btnDelete);

        JLabel lblDoubleClickPour = new JLabel("Double clic pour modifier");
        lblDoubleClickPour.setBounds(42, 327, 209, 16);
        contentPane.add(lblDoubleClickPour);

        JButton btnCancle = new JButton("Annuler");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancle.setBounds(407, 322, 117, 29);
        contentPane.add(btnCancle);
    }
}
