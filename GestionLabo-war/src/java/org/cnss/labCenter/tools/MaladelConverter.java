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
import org.cnss.labCenter.domain.malade.IMalde;
import org.cnss.labCenter.entities.Malade;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "malade")
public class MaladelConverter implements Converter {

    private Malade malade = null;
    private List<Malade> malades;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IMalde iMalde = (IMalde) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/MaladeServices");
                malade = iMalde.MaladeConverter(number);
                malades = iMalde.listerMalade();
                for (Malade a : malades) {
                    if (a.getIdMalade() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Malade pas valide"));

            }
        }
        return malade;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Malade) value).getIdMalade());
        }

    }

    public Malade getMalade() {
        return malade;
    }

    public void setMalade(Malade malade) {
        this.malade = malade;
    }

    public List<Malade> getMalades() {
        return malades;
    }

    public void setMalades(List<Malade> malades) {
        this.malades = malades;
    }
}
