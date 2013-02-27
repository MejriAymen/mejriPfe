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
import org.cnss.labCenter.domain.specialite.ISpecialite;
import org.cnss.labCenter.entities.Specialite;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "antibiotique")
public class SpecialiteConverter implements Converter {

    private Specialite antibiotique = null;
    private List<Specialite> antibiotiques;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                ISpecialite iAntibiotique = (ISpecialite) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/SpecialiteServices");
                antibiotique = iAntibiotique.antibiotiqueConverter(number);
                antibiotiques = iAntibiotique.listeAntibiotique();
                for (Specialite a : antibiotiques) {
                    if (a.getIdSpecialite() == number) {
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
            return String.valueOf(((Specialite) value).getIdSpecialite());
        }

    }

    public Specialite getAntibiotique() {
        return antibiotique;
    }

    public void setAntibiotique(Specialite antibiotique) {
        this.antibiotique = antibiotique;
    }

    public List<Specialite> getAntibiotiques() {
        return antibiotiques;
    }

    public void setAntibiotiques(List<Specialite> antibiotiques) {
        this.antibiotiques = antibiotiques;
    }
}
