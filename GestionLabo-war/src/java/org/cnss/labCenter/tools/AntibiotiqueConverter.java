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
import org.cnss.labCenter.domain.antibiotique.IAntibiotique;
import org.cnss.labCenter.entities.Antibiotique;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "antibiotique")
public class AntibiotiqueConverter implements Converter {

    private Antibiotique antibiotique = null;
    private List<Antibiotique> antibiotiques;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IAntibiotique iAntibiotique = (IAntibiotique) initialContext.lookup("java:global/LocationVoiture/LocationVoiture-ejb/AntibiotiqueServices");
                antibiotique = iAntibiotique.antibiotiqueConverter(number);
                antibiotiques = iAntibiotique.listeAntibiotique();
                for (Antibiotique a : antibiotiques) {
                    if (a.getId() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
               

            }
        }
        return antibiotique;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Antibiotique) value).getId());
        }

    }

    public Antibiotique getAntibiotique() {
        return antibiotique;
    }

    public void setAntibiotique(Antibiotique antibiotique) {
        this.antibiotique = antibiotique;
    }

    public List<Antibiotique> getAntibiotiques() {
        return antibiotiques;
    }

    public void setAntibiotiques(List<Antibiotique> antibiotiques) {
        this.antibiotiques = antibiotiques;
    }
}
