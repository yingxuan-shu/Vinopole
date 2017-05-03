package vinopole.view.criteria;

import vinopole.dao.criteria.CriteriaDAO;
import vinopole.model.criteria.Criteria;
import vinopole.view.model.ViewCriteriaModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListCriteria extends JFrame {
    private ViewCriteriaModel vCM;
    private JTable table;


    /**
     * Create the frame.
     */
    public ListCriteria() {
        vCM = new ViewCriteriaModel(new CriteriaDAO().getAll());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 602, 394);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Liste des critères");
        lblNewLabel.setBounds(227, 23, 182, 15);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 54, 566, 240);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Criteria c = (Criteria) table.getValueAt(table.getSelectedRow(), ViewCriteriaModel.INDEX_CRITERIA);

                    ModifyCriteria mc = new ModifyCriteria(c);
                    mc.setVisible(true);
                }
            }
        });
        table.setModel(vCM);
        scrollPane.setViewportView(table);

        JButton btnNewButton_1 = new JButton("Supprimer");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Criteria> list = vCM.getSelected();
                if (list.size() < 1) {
                    JOptionPane.showMessageDialog(null, (Object) "Merci de supprimer les lignes que vous souhaitez supprimer !");
                } else {
                    CriteriaDAO criteriaDao = new CriteriaDAO();

                    for (int i = list.size() - 1; i >= 0; i--) {
                        criteriaDao.remove(list.get(i));
                    }
                }
                vCM = new ViewCriteriaModel(new CriteriaDAO().getAll());
                table.setModel(vCM);
            }
        });
        btnNewButton_1.setBounds(240, 323, 93, 23);
        contentPane.add(btnNewButton_1);

        JButton btnCancle = new JButton("Annuler");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancle.setBounds(412, 323, 93, 23);
        contentPane.add(btnCancle);

        JLabel lblDoubleClickPour = new JLabel("Double clic pour modifier");
        lblDoubleClickPour.setBounds(24, 325, 204, 16);
        contentPane.add(lblDoubleClickPour);
    }
}
