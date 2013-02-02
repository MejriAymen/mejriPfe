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
import org.cnss.labCenter.domain.Departement.IDepartement;
import org.cnss.labCenter.entities.Departement;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class DepartementManagedbean implements Serializable {

    private Departement service;
    private Departement serviceS;
    private Departement seledtedservice;
    private List<Departement> serviceMod;
    private List<Departement> servicesL;
    private List<Departement> services;
    @EJB
    IDepartement iServiceLab;

    public DepartementManagedbean() {
        service = new Departement();
        serviceS = new Departement();
        seledtedservice = new Departement();

        serviceMod = new ArrayList<Departement>();
        services = new ArrayList<Departement>();
        servicesL = new ArrayList<Departement>();

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
            service = new Departement();
        }
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doModifierService() {
        if (serviceMod != null) {
            for (Departement a : serviceMod) {
                iServiceLab.modifierService(a);
            }
        }
    }

    public void doSupprimerService() {
        iServiceLab.supprimerService(serviceS);
    }

    public List<Departement> doListerService() {
        return iServiceLab.listerService();
    }

   public void listeServicesCourant() {
        serviceMod = this.doListerService();
        servicesL = this.doListerService();
        services = this.doListerService();
    }

    public List<Departement> completerService(String query) {
        List<Departement> suggestions = new ArrayList<Departement>();

        for (Departement p : services) {
            if (p.getCode().startsWith(query)) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public IDepartement getiServiceLab() {
        return iServiceLab;
    }

    public void setiServiceLab(IDepartement iServiceLab) {
        this.iServiceLab = iServiceLab;
    }

    public Departement getSeledtedservice() {
        return seledtedservice;
    }

    public void setSeledtedservice(Departement seledtedservice) {
        this.seledtedservice = seledtedservice;
    }

    public Departement getService() {
        return service;
    }

    public void setService(Departement service) {
        this.service = service;
    }

    public List<Departement> getServiceMod() {
        return serviceMod;
    }

    public void setServiceMod(List<Departement> serviceMod) {
        this.serviceMod = serviceMod;
    }

    public Departement getServiceS() {
        return serviceS;
    }

    public void setServiceS(Departement serviceS) {
        this.serviceS = serviceS;
    }

    public List<Departement> getServices() {
        return services;
    }

    public void setServices(List<Departement> services) {
        this.services = services;
    }

    public List<Departement> getServicesL() {
        return servicesL;
    }

    public void setServicesL(List<Departement> servicesL) {
        this.servicesL = servicesL;
    }
}