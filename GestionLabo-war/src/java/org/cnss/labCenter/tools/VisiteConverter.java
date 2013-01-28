package org.cnss.labCenter.tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.cnss.labCenter.domain.visite.IVisite;
import org.cnss.labCenter.entities.Visite;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "visite")
public class VisiteConverter implements Converter {

    private Visite visite = null;
    private List<Visite> visites;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IVisite iVisite = (IVisite) initialContext.lookup("java:global/LocationVoiture/LocationVoiture-ejb/VisiteServices");
                visite = iVisite.VisiteConverter(number);
                visites = iVisite.listeVisite();
                for (Visite a : visites) {
                    if (a.getIdVisite() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {

            }
        }
        return visite;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Visite) value).getIdVisite());
        }

    }

    public Visite getVisite() {
        return visite;
    }

    public void setVisite(Visite visite) {
        this.visite = visite;
    }

    public List<Visite> getVisites() {
        return visites;
    }

    public void setVisites(List<Visite> visites) {
        this.visites = visites;
    }
}
