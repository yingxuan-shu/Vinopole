package vinopole.view.model;

import vinopole.model.criteria.Criteria;
import vinopole.model.form.Form;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by louen on 28/11/2016.
 */
public class ViewCriteriaModel extends AbstractTableModel {

    private static List<String> titles = new ArrayList<String>() {{
        add("");
        add("Nom du critère");
        add("Thème du critère ");
        add("Type du critère ");
    }};

    public final static int INDEX_BOOL = 0;
    public final static int INDEX_CRITERIA = 1;
    public final static int INDEX_THEME = 2;
    public final static int INDEX_TYPE = 3;

    private boolean[] selected;

    private List<Criteria> criterias;

    public ViewCriteriaModel(List<Criteria> criterias) {
        this.criterias = criterias;
        selected = new boolean[criterias.size()];
    }

    public ViewCriteriaModel(List<Criteria> criterias, Form form) {
        this.criterias = criterias;
        selected = new boolean[criterias.size()];

        for (int i = 0; i < criterias.size(); i++) {
            if (form.getCriterias().contains(criterias.get(i))) {
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
        return criterias.size();
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
            case INDEX_CRITERIA:
                return criterias.get(rowIndex);
            case INDEX_THEME:
                return criterias.get(rowIndex).getTheme();
            case INDEX_TYPE:
                return criterias.get(rowIndex).getType();
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

    public List<Criteria> getSelected() {
        List<Criteria> out = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                out.add(criterias.get(i));
            }
        }
        return out;
    }

}
