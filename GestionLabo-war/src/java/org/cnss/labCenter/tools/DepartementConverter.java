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
import org.cnss.labCenter.domain.Departement.IDepartement;
import org.cnss.labCenter.entities.Departement;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "service")
public class DepartementConverter implements Converter {

    private Departement service = null;
    private List<Departement> services;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IDepartement iServiceLab = (IDepartement) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/DepartementServices");
                service = iServiceLab.serviceConverter(number);
                services = iServiceLab.listerService();
                for (Departement a : services) {
                    if (a.getIdService() == number) {
                        return a;
                    }
                }
            } catch (NamingException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Service pas valide"));

            }
        }
        return service;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Departement) value).getIdService());
        }

    }

    public Departement getService() {
        return service;
    }

    public void setService(Departement service) {
        this.service = service;
    }

    public List<Departement> getServices() {
        return services;
    }

    public void setServices(List<Departement> services) {
        this.services = services;
    }
}
