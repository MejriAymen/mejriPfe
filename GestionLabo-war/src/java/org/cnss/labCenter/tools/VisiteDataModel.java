package org.cnss.labCenter.tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.faces.model.ListDataModel;
import org.cnss.labCenter.entities.Visite;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author bysez
 */
public class VisiteDataModel extends ListDataModel<Visite> implements SelectableDataModel<Visite> {

    public VisiteDataModel() {
    }

    public VisiteDataModel(List<Visite> list) {
        super(list);
    }

    @Override
    public Object getRowKey(Visite t) {
        return t.getIdVisite();
    }

    @Override
    public Visite getRowData(String string) {
        List<Visite> visites;
        visites = (List<Visite>) getWrappedData();

        for (Visite v : visites) {
            if (v.getIdVisite() == Integer.parseInt(string)) {
                return v;
            }
        }

        return null;
    }
}
