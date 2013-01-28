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
import org.cnss.labCenter.domain.convention.IConvention;
import org.cnss.labCenter.entities.Convention;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "convention")
public class ConventionConverter implements Converter {

    private Convention convention = null;
    private List<Convention> conventions;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IConvention iConvention = (IConvention) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/ConventionServices");
                convention = iConvention.ConventionConverter(number);
                conventions = iConvention.listeConvention();
                for (Convention a : conventions) {
                    if (a.getIdConvention() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "convention pas valide"));

            }
        }
        return convention;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Convention) value).getIdConvention());
        }

    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public void setConventions(List<Convention> conventions) {
        this.conventions = conventions;
    }
}
