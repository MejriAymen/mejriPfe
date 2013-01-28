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
import org.cnss.labCenter.domain.serviceLab.IServiceLab;
import org.cnss.labCenter.entities.Service;

/**
 *
 * @author Mejri Aymen
 */
@FacesConverter(value = "service")
public class ServicesConverter implements Converter {

    private Service service = null;
    private List<Service> services;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        InitialContext initialContext;

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                initialContext = new InitialContext();

                IServiceLab iServiceLab = (IServiceLab) initialContext.lookup("java:global/GestionLabo/GestionLabo-ejb/ServicesLabServices");
                service = iServiceLab.serviceConverter(number);
                services = iServiceLab.listerService();
                for (Service a : services) {
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
            return String.valueOf(((Service) value).getIdService());
        }

    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
