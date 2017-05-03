package vinopole.view.model;

import vinopole.dao.ResponseFormDAO;
import vinopole.model.Serie;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by louen on 28/11/2016.
 */
public class ViewSerieModel extends AbstractTableModel {

    private static List<String> titles = new ArrayList<String>() {{
        add("");
        add("Nom de la série");
        add("Nombre de réponses ");
    }};

    public final static int INDEX_BOOL = 0;
    public final static int INDEX_SERIE = 1;
    public final static int INDEX_NUMBER_OF_RESP = 2;

    private boolean[] selected;

    private List<Serie> series;

    public ViewSerieModel(List<Serie> series) {
        this.series = series;
        selected = new boolean[series.size()];
    }

    @Override
    public String getColumnName(int column) {
        return titles.get(column);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_BOOL:
                selected[rowIndex] = (Boolean) aValue;
                break;
            default:
                break;
        }
    }

    @Override
    public int getRowCount() {
        return series.size();
    }

    @Override
    public int getColumnCount() {
        return titles.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_BOOL:
                return selected[rowIndex];
            case INDEX_SERIE:
                return series.get(rowIndex);
            case INDEX_NUMBER_OF_RESP:
                return new ResponseFormDAO().getAll().size();
            default:
                break;
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case INDEX_BOOL:
                return Boolean.class;
            case INDEX_NUMBER_OF_RESP:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_BOOL:
                return true;
            default:
                return false;
        }
    }

    public List<Serie> getSelected() {
        List<Serie> out = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                out.add(series.get(i));
            }
        }
        return out;
    }
}
