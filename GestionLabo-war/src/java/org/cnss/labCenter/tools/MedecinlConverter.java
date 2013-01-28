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
import org.cnss.labCenter.domain.medecin.IMedecin;
import org.cnss.labCenter.entities.Medecin;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "medecin")
public class MedecinlConverter implements Converter {

    private Medecin medecin = null;
    private List<Medecin> medecins;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IMedecin iMedecin = (IMedecin) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/MedecinServices");
                medecin = iMedecin.medecinConverter(number);
                medecins = iMedecin.listerMedecin();
                for (Medecin a : medecins) {
                    if (a.getIdMed() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Medecin pas valide"));

            }
        }
        return medecin;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Medecin) value).getIdMed());
        }

    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

   
}
