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
import javax.persistence.PrePersist;
import org.cnss.labCenter.domain.assure.IAssure;
import org.cnss.labCenter.entities.Assure;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class AssureManagedbean implements Serializable {

    private Assure assure;
    private Assure seledtedAssure;
    private Assure assureS;
    private List<Assure> assureMod;
    private List<Assure> assureL;
    private List<Assure> assures;
    @EJB
    IAssure iAssure;

    public AssureManagedbean() {
        assure = new Assure();
        seledtedAssure = new Assure();
        assureS = new Assure();
        assureMod = new ArrayList<Assure>();
        assureL = new ArrayList<Assure>();
        assures = new ArrayList<Assure>();
    }

    @PostConstruct
    public void init() {
        assureL = this.doListerAssure();
        assureMod = this.doListerAssure();
        assures = this.doListerAssure();
    }
    
    @PrePersist
    public void preInit(){
    assureL = this.doListerAssure();
        assureMod = this.doListerAssure();
        assures = this.doListerAssure();
    }

    public void doAjouterAssure() {
        if (assure != null) {
            iAssure.ajouterAssure(assure);
            ajouterMessageInfo(assure.getMatriculeAssure());
            assure = new Assure();
        }
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Assure Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doModifierAssure() {
        if (assureMod != null) {
            for (Assure a : assureMod) {
                iAssure.modifierAssure(a);
            }
        }
    }

    public void doSupprimerAssure() {
        iAssure.supprimerAssure(assureS);
    }

    public List<Assure> doListerAssure() {
        return iAssure.listerAssure();
    }

    public void listeAssureCourant() {

        assureL = this.doListerAssure();
        assureMod = this.doListerAssure();
        assures = this.doListerAssure();
    }

    public List<Assure> completerAssure(String query) {
        List<Assure> suggestions = new ArrayList<Assure>();

        for (Assure p : assures) {
            if (p.getMatriculeAssure().startsWith(query)) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public Assure getAssure() {
        return assure;
    }

    public void setAssure(Assure assure) {
        this.assure = assure;
    }

    public List<Assure> getAssureL() {
        return assureL;
    }

    public void setAssureL(List<Assure> assureL) {
        this.assureL = assureL;
    }

    public List<Assure> getAssureMod() {
        return assureMod;
    }

    public void setAssureMod(List<Assure> assureMod) {
        this.assureMod = assureMod;
    }

    public Assure getAssureS() {
        return assureS;
    }

    public void setAssureS(Assure assureS) {
        this.assureS = assureS;
    }

    public List<Assure> getAssures() {
        return assures;
    }

    public void setAssures(List<Assure> assures) {
        this.assures = assures;
    }

    public IAssure getiAssure() {
        return iAssure;
    }

    public void setiAssure(IAssure iAssure) {
        this.iAssure = iAssure;
    }

    public Assure getSeledtedAssure() {
        return seledtedAssure;
    }

    public void setSeledtedAssure(Assure seledtedAssure) {
        this.seledtedAssure = seledtedAssure;
    }
}
