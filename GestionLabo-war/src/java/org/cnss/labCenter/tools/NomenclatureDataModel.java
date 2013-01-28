package org.cnss.labCenter.tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.faces.model.ListDataModel;
import org.cnss.labCenter.entities.Nomenclature;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author bysez
 */
public class NomenclatureDataModel extends ListDataModel<Nomenclature> implements SelectableDataModel<Nomenclature> {

    public NomenclatureDataModel() {
    }

    public NomenclatureDataModel(List<Nomenclature> list) {
        super(list);
    }

    @Override
    public Object getRowKey(Nomenclature t) {
        return t.getIdNomenc();
    }

    @Override
    public Nomenclature getRowData(String string) {
        List<Nomenclature> agences;
        agences = (List<Nomenclature>) getWrappedData();

        for (Nomenclature agence : agences) {
            if (agence.getIdNomenc() == Integer.parseInt(string)) {
                return agence;
            }
        }

        return null;
    }
}
