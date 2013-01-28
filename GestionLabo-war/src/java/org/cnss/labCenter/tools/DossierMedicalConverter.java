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
import org.cnss.labCenter.domain.dossierMedical.IDossierMedical;
import org.cnss.labCenter.entities.DossierMedicale;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "dossierMed")
public class DossierMedicalConverter implements Converter {

    private DossierMedicale dossierMedicale = null;
    private List<DossierMedicale> dossierMedicales;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IDossierMedical iDossierMedical = (IDossierMedical) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/DossierMedicalServices");
                dossierMedicale = iDossierMedical.DossierMedicaleConverter(number);
                dossierMedicales = iDossierMedical.listeDossierMedicale();
                for (DossierMedicale a : dossierMedicales) {
                    if (a.getIdDoss() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "dossier pas valide"));

            }
        }
        return dossierMedicale;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((DossierMedicale) value).getIdDoss());
        }

    }

    public DossierMedicale getDossierMedicale() {
        return dossierMedicale;
    }

    public void setDossierMedicale(DossierMedicale dossierMedicale) {
        this.dossierMedicale = dossierMedicale;
    }

    public List<DossierMedicale> getDossierMedicales() {
        return dossierMedicales;
    }

    public void setDossierMedicales(List<DossierMedicale> dossierMedicales) {
        this.dossierMedicales = dossierMedicales;
    }
}
