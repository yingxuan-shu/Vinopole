package vinopole.view.wine;

import vinopole.dao.wine.WineDAO;
import vinopole.model.wine.Wine;
import vinopole.view.model.ViewWineModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListWine extends JFrame {
    private ViewWineModel vWM;
    private JTable table;

    /**
     * Create the frame.
     */
    public ListWine() {
        vWM = new ViewWineModel(new WineDAO().getAll());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 566, 526);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnDelete = new JButton("Supprimer");

        btnDelete.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                List<Wine> list = vWM.getSelected();
                if (list.size() < 1) {
                    JOptionPane.showMessageDialog(null, "Merci de sélectionner les lignes que vous souhaitez supprimer !");
                } else {
                    WineDAO wineDao = new WineDAO();
                    for (int i = list.size() - 1; i >= 0; i--) {
                        wineDao.remove(list.get(i));
                    }
                }
                vWM = new ViewWineModel(new WineDAO().getAll());
                table.setModel(vWM);

            }
        });

        btnDelete.setBounds(202, 350, 100, 23);
        contentPane.add(btnDelete);

        JLabel lblNewLabel = new JLabel("Liste des vins");
        lblNewLabel.setBounds(199, 22, 160, 15);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 46, 513, 253);
        contentPane.add(scrollPane);

        table = new JTable();


        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Wine wine = (Wine) table.getValueAt(table.getSelectedRow(), ViewWineModel.INDEX_WINE);
                    ModifyWine mw = new ModifyWine(wine);
                    mw.setVisible(true);
                }
            }
        });
        table.setModel(vWM);

        scrollPane.setViewportView(table);

        JLabel lblDoubleClickPour = new JLabel("Double clic pour modifier");
        lblDoubleClickPour.setBounds(10, 352, 187, 16);
        contentPane.add(lblDoubleClickPour);
    }
}
