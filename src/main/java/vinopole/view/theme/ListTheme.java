package vinopole.view.theme;

import vinopole.dao.criteria.CriteriaThemeDAO;
import vinopole.model.criteria.CriteriaTheme;
import vinopole.view.model.ViewThemeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListTheme extends JFrame {
    private ViewThemeModel vTM;
    private JTable table;

    /**
     * Create the frame.
     */
    public ListTheme() {
        vTM = new ViewThemeModel(new CriteriaThemeDAO().getAll());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 488, 438);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblModify = new JLabel("Liste des thèmes");
        lblModify.setBounds(177, 23, 123, 15);
        contentPane.add(lblModify);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 48, 368, 198);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    CriteriaTheme theme = (CriteriaTheme) table.getValueAt(table.getSelectedRow(), ViewThemeModel.INDEX_THEME);

                    ModifyTheme mt = new ModifyTheme(theme);
                    mt.setVisible(true);
                }
            }
        });
        scrollPane.setViewportView(table);
        table.setModel(vTM);

        JButton btnNewButton_1 = new JButton("Supprimer");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<CriteriaTheme> list = vTM.getSelected();
                CriteriaThemeDAO ThemeDao = new CriteriaThemeDAO();

                for (int i = list.size() - 1; i >= 0; i--) {
                    ThemeDao.remove(list.get(i));
                }
                vTM = new ViewThemeModel(new CriteriaThemeDAO().getAll());
                table.setModel(vTM);

            }
        });
        btnNewButton_1.setBounds(236, 345, 100, 23);
        contentPane.add(btnNewButton_1);

        JLabel lblDoubleClickPour = new JLabel("Double clic pour modifier");
        lblDoubleClickPour.setBounds(29, 347, 195, 16);
        contentPane.add(lblDoubleClickPour);

        JButton btnNewButton = new JButton("Monter");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != 0) {
                    CriteriaTheme theme = (CriteriaTheme) table.getValueAt(table.getSelectedRow(), ViewThemeModel.INDEX_THEME);
                    theme.setPriority(theme.getPriority() - 1);
                    CriteriaTheme themeBelow = (CriteriaTheme) table.getValueAt(table.getSelectedRow() - 1, ViewThemeModel.INDEX_THEME);
                    themeBelow.setPriority(theme.getPriority() + 1);
                }
                vTM = new ViewThemeModel(new CriteriaThemeDAO().getAll());
                table.setModel(vTM);
            }
        });
        btnNewButton.setBounds(377, 94, 100, 23);
        contentPane.add(btnNewButton);

        JButton btnDown = new JButton("Descendre");
        btnDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != vTM.getRowCount() - 1) {
                    CriteriaTheme theme = (CriteriaTheme) table.getValueAt(table.getSelectedRow(), ViewThemeModel.INDEX_THEME);
                    theme.setPriority(theme.getPriority() + 1);
                    CriteriaTheme themeUp = (CriteriaTheme) table.getValueAt(table.getSelectedRow() + 1, ViewThemeModel.INDEX_THEME);
                    themeUp.setPriority(theme.getPriority() - 1);
                }
                vTM = new ViewThemeModel(new CriteriaThemeDAO().getAll());
                table.setModel(vTM);
            }
        });
        btnDown.setBounds(377, 161, 100, 23);
        contentPane.add(btnDown);
    }
}
