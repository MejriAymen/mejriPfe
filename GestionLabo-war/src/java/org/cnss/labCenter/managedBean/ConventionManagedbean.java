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
import org.cnss.labCenter.domain.convention.IConvention;
import org.cnss.labCenter.entities.Convention;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class ConventionManagedbean implements Serializable {

    Convention convention;
    Convention selectConvention;
    Convention selectConventionV;
    List<Convention> conventions;
    List<Convention> conventionsM;
    @EJB
    IConvention iConvention;

    public ConventionManagedbean() {
        convention = new Convention();
        selectConvention = new Convention();
        selectConventionV = new Convention();
        conventions = new ArrayList<Convention>();
        conventionsM = new ArrayList<Convention>();
    }

    @PostConstruct
    public void init() {

        conventionsM = this.doListerConvention();
        conventions = this.doListerConvention();

    }

    public void doAjouterConvention() {
        if (convention != null) {
            iConvention.ajouterConvention(convention);
            conventionsM = this.doListerConvention();
            conventions = this.doListerConvention();
            ajouterMessageInfo("Analyse : " + convention.getOrganisme());
            convention = new Convention();

        }

    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Convention Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Convention> doListerConvention() {
        return iConvention.listeConvention();
    }

    public void doSupprimerConvention() {

        iConvention.supprimerConvention(selectConvention.getIdConvention());

    }

    public void doModifierConvention() {
        if (conventionsM != null) {
            for (Convention a : conventionsM) {
                iConvention.modifierConvention(a);

                conventionsM = this.doListerConvention();
                conventions = this.doListerConvention();
            }
        }
    }

    public void conventionCourant() {
        conventions = this.doListerConvention();
        conventionsM = this.doListerConvention();


    }

    public List<Convention> completeConvention(String query) {
        List<Convention> suggestions = new ArrayList<Convention>();

        for (Convention p : conventions) {
            if (p.getCode().startsWith(query)) {

                suggestions.add(p);
            }
        }

        return suggestions;
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

    public List<Convention> getConventionsM() {
        return conventionsM;
    }

    public void setConventionsM(List<Convention> conventionsM) {
        this.conventionsM = conventionsM;
    }

    public IConvention getiConvention() {
        return iConvention;
    }

    public void setiConvention(IConvention iConvention) {
        this.iConvention = iConvention;
    }

    public Convention getSelectConvention() {
        return selectConvention;
    }

    public void setSelectConvention(Convention selectConvention) {
        this.selectConvention = selectConvention;
    }

    public Convention getSelectConventionV() {
        return selectConventionV;
    }

    public void setSelectConventionV(Convention selectConventionV) {
        this.selectConventionV = selectConventionV;
    }
}
