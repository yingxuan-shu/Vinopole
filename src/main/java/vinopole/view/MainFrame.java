package vinopole.view;

import com.google.gson.Gson;
import vinopole.HibernateUtil;
import vinopole.dao.JudgeDAO;
import vinopole.dao.ResponseFormDAO;
import vinopole.dao.SerieDAO;
import vinopole.model.Judge;
import vinopole.model.ResponseForm;
import vinopole.model.Serie;
import vinopole.tool.GeneratorCsv;
import vinopole.tool.GeneratorGson;
import vinopole.view.criteria.ListCriteria;
import vinopole.view.criteria.NewCriteria;
import vinopole.view.form.ListFiche;
import vinopole.view.form.NewForm;
import vinopole.view.model.ViewSerieModel;
import vinopole.view.serie.ModifySerie;
import vinopole.view.serie.NewSerie;
import vinopole.view.theme.ListTheme;
import vinopole.view.theme.NewTheme;
import vinopole.view.wine.ListWine;
import vinopole.view.wine.NewWine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame {

    private JFrame frame;
    private JTable table;
    private static MainFrame window;
    private ViewSerieModel vSM;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    window = new MainFrame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        HibernateUtil.initDB();
        vSM = new ViewSerieModel(new SerieDAO().getAll());
        frame = new JFrame();
        frame.setBounds(100, 100, 584, 464);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Fiches");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewQuestionnaire = new JMenuItem("Ajouter");
        mntmNewQuestionnaire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewForm nq = new NewForm();
                nq.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewQuestionnaire);

        JMenuItem mntmModifyFile = new JMenuItem("Modifier");
        mntmModifyFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListFiche mf = new ListFiche();
                mf.setVisible(true);
            }
        });
        mnNewMenu.add(mntmModifyFile);

        JMenu mnNewMenu_1 = new JMenu("Critères");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmAdd_1 = new JMenuItem("Ajouter");
        mntmAdd_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewCriteria nc = new NewCriteria();
                nc.setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmAdd_1);

        JMenuItem mntmModify = new JMenuItem("Modifier");
        mntmModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListCriteria lc = new ListCriteria();
                lc.setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmModify);

        JMenu mnTheme = new JMenu("Thèmes");
        menuBar.add(mnTheme);

        JMenuItem mntmAdd_4 = new JMenuItem("Ajouter");
        mntmAdd_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewTheme nt = new NewTheme();
                nt.setVisible(true);
            }
        });
        mnTheme.add(mntmAdd_4);

        JMenuItem mntmModifydelete = new JMenuItem("Modifier");
        mntmModifydelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListTheme mt = new ListTheme();
                mt.setVisible(true);
            }
        });
        mnTheme.add(mntmModifydelete);

        JMenu mnVin = new JMenu("Vins");
        menuBar.add(mnVin);

        JMenuItem mntmAdd_3 = new JMenuItem("Ajouter");
        mntmAdd_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewWine nw = new NewWine();
                nw.setVisible(true);
            }
        });
        mnVin.add(mntmAdd_3);

        JMenuItem mntmModify_2 = new JMenuItem("Modifier");
        mntmModify_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListWine mw = new ListWine();
                mw.setVisible(true);
            }
        });
        mnVin.add(mntmModify_2);

        JMenu mnJuge = new JMenu("Aide");
        menuBar.add(mnJuge);

        JMenuItem mntmAbout = new JMenuItem("A propos");
        mntmAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, (Object) "Polytech Tours");
            }
        });
        mnJuge.add(mntmAbout);
        frame.getContentPane().setLayout(null);

        JLabel lblSerie = new JLabel("Séries");
        lblSerie.setBounds(32, 54, 54, 15);
        frame.getContentPane().add(lblSerie);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 71, 514, 267);
        frame.getContentPane().add(scrollPane);

        table = new JTable();

        /**
         * Handle click on table double clik to open ModifySerie
         */
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Serie serie = (Serie) table.getValueAt(table.getSelectedRow(), ViewSerieModel.INDEX_SERIE);
                    ModifySerie ms = new ModifySerie(serie);
                    ms.setVisible(true);
                }
            }
        });
        table.setModel(vSM);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        scrollPane.setViewportView(table);

        JButton button = new JButton("+");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                NewSerie ns = new NewSerie(window);
                ns.setVisible(true);
            }
        });
        button.setBounds(98, 46, 54, 23);
        frame.getContentPane().add(button);

        JButton btnImport = new JButton("Import réponses");
        /**
         * Button to import Response in DB
         */
        btnImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser dialogue = new JFileChooser();
                dialogue.setDialogTitle("Dossier contenant les réponses :");
                dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int retour = dialogue.showOpenDialog(null);
                File dossier = null;
                if (retour == JFileChooser.APPROVE_OPTION) {
                    dossier = new File(dialogue.getSelectedFile().getAbsolutePath());
                    String[] listefichiers = dossier.list();
                    Gson resp = new Gson();
                    ResponseFormDAO responseFormDAO = new ResponseFormDAO();
                    JudgeDAO judgeDAO = new JudgeDAO();
                    int nbImport = 0;
                    if (listefichiers != null) {
                        for (String fichier : listefichiers) {
                            if (fichier.endsWith(".json")) {
                                try (BufferedReader br = new BufferedReader(new FileReader(dossier + "\\" + fichier))) {
                                    ResponseForm[] responses = resp.fromJson(br, ResponseForm[].class);
                                    Judge juge = new Judge();
                                    judgeDAO.save(juge);
                                    for (ResponseForm r : responses) {
                                        r.setJudge(juge);
                                        responseFormDAO.save(r);
                                        nbImport++;
                                    }
                                    JOptionPane.showMessageDialog(null, nbImport + " réponse(s) importée(s)", "Réponses importées", JOptionPane.INFORMATION_MESSAGE);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }

                            }
                        }
                    }
                }
            }
        });
        btnImport.setBounds(164, 46, 133, 23);
        frame.getContentPane().add(btnImport);

        JButton btnExport = new JButton("Export fiches");
        btnExport.setBounds(295, 46, 153, 23);
        frame.getContentPane().add(btnExport);
        /**
         * Export Button to json
         */
        btnExport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Serie> series = new ArrayList<>();
                for (int i = 0; i < table.getRowCount(); i++) {
                    if ((boolean) vSM.getValueAt(i, ViewSerieModel.INDEX_BOOL)) {
                        series.add((Serie) table.getValueAt(i, ViewSerieModel.INDEX_SERIE));
                    }
                }
                if (series.size() > 0) {
                    JFileChooser dialogue = new JFileChooser();
                    dialogue.setDialogTitle("Dossier contenant le(s) fiches(s) :");
                    dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int retour = dialogue.showOpenDialog(null);
                    if (retour == JFileChooser.APPROVE_OPTION) {
                        String file = dialogue.getSelectedFile().getAbsolutePath() + "\\input";
                        GeneratorGson.getGson(series, file);
                        JOptionPane.showMessageDialog(null, "Fichier contenant le(s) fiches(s) : " + file + ".json", "Fiche(s) exportée(s)", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        JButton exportCSV = new JButton("Export CSV");
        exportCSV.setBounds(445, 46, 133, 23);
        frame.getContentPane().add(exportCSV);
        exportCSV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Serie> series = new ArrayList<>();
                for (int i = 0; i < table.getRowCount(); i++) {
                    if ((boolean) vSM.getValueAt(i, ViewSerieModel.INDEX_BOOL)) {
                        series.add((Serie) table.getValueAt(i, ViewSerieModel.INDEX_SERIE));
                    }
                }
                if (series.size() > 0) {
                    JFileChooser dialogue = new JFileChooser();
                    dialogue.setDialogTitle("Dossier contenant les réponses au format CSV :");
                    dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int retour = dialogue.showOpenDialog(null);
                    if (retour == JFileChooser.APPROVE_OPTION) {
                        for (Serie s : series) {
                            GeneratorCsv.GenerateCsv(s, dialogue.getSelectedFile().getAbsolutePath());
                        }
                        JOptionPane.showMessageDialog(null, "Réponses exportées dans : " + dialogue.getSelectedFile().getAbsolutePath(), "Réponses(s) exportée(s)", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
        });
        JButton btnDelete = new JButton("Supprimer");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Serie> list = vSM.getSelected();
                if (list.size() < 1) {
                    JOptionPane.showMessageDialog(null, "Merci de sélectionner les lignes que vous souhaitez supprimer !");
                } else {
                    SerieDAO serieDao = new SerieDAO();

                    for (int i = list.size() - 1; i >= 0; i--) {
                        serieDao.remove(list.get(i));
                    }
                }
                vSM = new ViewSerieModel(new SerieDAO().getAll());
                table.setModel(vSM);
            }
        });
        btnDelete.setBounds(427, 363, 117, 29);
        frame.getContentPane().add(btnDelete);

        JLabel lblDoubleClickPour = new JLabel("Double clic pour modifier");
        lblDoubleClickPour.setBounds(135, 368, 220, 16);
        frame.getContentPane().add(lblDoubleClickPour);
    }

    /**
     * Update table model
     */
    public void updateJtableData() {
        //reload jtable's data
        ViewSerieModel serieModel = new ViewSerieModel(new SerieDAO().getAll());
        table.setModel(serieModel);
        this.vSM = serieModel;
    }
}
