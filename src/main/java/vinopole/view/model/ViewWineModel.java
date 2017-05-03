package vinopole.view.model;

import vinopole.model.Serie;
import vinopole.model.wine.Wine;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by louen on 28/11/2016.
 */
public class ViewWineModel extends AbstractTableModel {

    private static List<String> titles = new ArrayList<String>() {{
        add("");
        add("Nom du vin");
        add("Type du vin ");
    }};

    public final static int INDEX_BOOL = 0;
    public final static int INDEX_WINE = 1;
    public final static int INDEX_TYPE = 2;

    private boolean[] selected;

    private List<Wine> wines;

    public ViewWineModel(List<Wine> wines) {
        this.wines = wines;
        selected = new boolean[wines.size()];
    }

    public ViewWineModel(List<Wine> wines, Serie serie) {
        this.wines = wines;
        selected = new boolean[wines.size()];
        for (int i = 0; i < wines.size(); i++) {
            if (serie.getWines().contains(wines.get(i))) {
                selected[i] = true;
            }
        }
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
        return wines.size();
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
            case INDEX_WINE:
                return wines.get(rowIndex);
            case INDEX_TYPE:
                return wines.get(rowIndex).getType();
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

    public List<Wine> getSelected() {
        List<Wine> out = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                out.add(wines.get(i));
            }
        }
        return out;
    }
}
