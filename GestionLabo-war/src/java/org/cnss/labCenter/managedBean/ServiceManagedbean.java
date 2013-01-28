package org.cnss.labCenter.managedBean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.cnss.labCenter.domain.serviceLab.IServiceLab;
import org.cnss.labCenter.entities.Service;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class ServiceManagedbean implements Serializable {

    private Service service;
    private Service serviceS;
    private Service seledtedservice;
    private List<Service> serviceMod;
    private List<Service> servicesL;
    private List<Service> services;
    @EJB
    IServiceLab iServiceLab;

    public ServiceManagedbean() {
        service = new Service();
        serviceS = new Service();
        seledtedservice = new Service();

        serviceMod = new ArrayList<Service>();
        services = new ArrayList<Service>();
        servicesL = new ArrayList<Service>();

    }

    @PostConstruct
    public void init() {
        services = this.doListerService();
        servicesL = this.doListerService();
        serviceMod = this.doListerService();
    }

    public void doAjouterService() {
        if (service != null) {
            iServiceLab.ajouterService(service);
            ajouterMessageInfo(service.getNom());
            service = new Service();
        }
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doModifierService() {
        if (serviceMod != null) {
            for (Service a : serviceMod) {
                iServiceLab.modifierService(a);
            }
        }
    }

    public void doSupprimerService() {
        iServiceLab.supprimerService(serviceS);
    }

    public List<Service> doListerService() {
        return iServiceLab.listerService();
    }

   public void listeServicesCourant() {
        serviceMod = this.doListerService();
        servicesL = this.doListerService();
        services = this.doListerService();
    }

    public List<Service> completerService(String query) {
        List<Service> suggestions = new ArrayList<Service>();

        for (Service p : services) {
            if (p.getCode().startsWith(query)) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public IServiceLab getiServiceLab() {
        return iServiceLab;
    }

    public void setiServiceLab(IServiceLab iServiceLab) {
        this.iServiceLab = iServiceLab;
    }

    public Service getSeledtedservice() {
        return seledtedservice;
    }

    public void setSeledtedservice(Service seledtedservice) {
        this.seledtedservice = seledtedservice;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServiceMod() {
        return serviceMod;
    }

    public void setServiceMod(List<Service> serviceMod) {
        this.serviceMod = serviceMod;
    }

    public Service getServiceS() {
        return serviceS;
    }

    public void setServiceS(Service serviceS) {
        this.serviceS = serviceS;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Service> getServicesL() {
        return servicesL;
    }

    public void setServicesL(List<Service> servicesL) {
        this.servicesL = servicesL;
    }
}