package vinopole.view.model;

import vinopole.model.form.Form;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by louen on 28/11/2016.
 */
public class ViewFormModel extends AbstractTableModel {

    private static List<String> titles = new ArrayList<String>() {{
        add("");
        add("Nom de la fiche");

    }};

    public final static int INDEX_BOOL = 0;
    public final static int INDEX_FORM = 1;


    private boolean[] selected;

    private List<Form> forms;

    public ViewFormModel(List<Form> forms) {
        this.forms = forms;
        selected = new boolean[forms.size()];
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
        return forms.size();
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
            case INDEX_FORM:
                return forms.get(rowIndex);
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

    public List<Form> getSelected() {
        List<Form> out = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                out.add(forms.get(i));
            }
        }
        return out;
    }

}
