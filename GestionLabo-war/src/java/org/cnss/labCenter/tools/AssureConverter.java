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
import org.cnss.labCenter.domain.assure.IAssure;
import org.cnss.labCenter.entities.Assure;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "assure")
public class AssureConverter implements Converter {

    private Assure assure = null;
    private List<Assure> assures;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IAssure iAssure = (IAssure) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/AssureServices");
                assure = iAssure.AssureConverter(number);
                assures = iAssure.listerAssure();
                for (Assure a : assures) {
                    if (a.getIdAssure() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Assure pas valide"));

            }
        }
        return assure;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Assure) value).getIdAssure());
        }

    }

    public Assure getAssure() {
        return assure;
    }

    public void setAssure(Assure assure) { 
        this.assure = assure;
    }

    public List<Assure> getAssures() {
        return assures;
    }

    public void setAssures(List<Assure> assures) {
        this.assures = assures;
    }
}
