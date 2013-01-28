package org.cnss.labCenter.tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.cnss.labCenter.domain.nomenclature.INomenclature;
import org.cnss.labCenter.entities.Nomenclature;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "nomenclature")
public class NomenclatureConverter implements Converter {

    private Nomenclature nomenclature = null;
    private List<Nomenclature> nomenclatures;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                INomenclature  iNomenclature = (INomenclature) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/NomenclatureServices");
                nomenclature = iNomenclature.NomenclatureConverter(number);
                nomenclatures = iNomenclature.listeNomenclature();
                for (Nomenclature a : nomenclatures) {
                    if (a.getIdNomenc() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "nomenclature pas valide"));

            }
        }
        return nomenclature;
    }

    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Nomenclature) value).getIdNomenc());
        }

    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public List<Nomenclature> getNomenclatures() {
        return nomenclatures;
    }

    public void setNomenclatures(List<Nomenclature> nomenclatures) {
        this.nomenclatures = nomenclatures;
    }
    
    
}
