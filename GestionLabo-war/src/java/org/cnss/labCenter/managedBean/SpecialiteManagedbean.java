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
import org.cnss.labCenter.domain.specialite.ISpecialite;
import org.cnss.labCenter.entities.Specialite;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class SpecialiteManagedbean implements Serializable {

    private Specialite antibiotique;
    private Specialite antibiotiqueS;
    private Specialite Selectantibiotique;
    private List<Specialite> antibiotiques;
    private List<Specialite> antibiotiquesM;
    private List<Specialite> antibiotiqueL;
    private List<Specialite> filteredAntibiotique;
    @EJB
    ISpecialite iAntibiotique;

    public SpecialiteManagedbean() {
        antibiotiqueS = new Specialite();
        antibiotiques = new ArrayList<Specialite>();
        antibiotiquesM = new ArrayList<Specialite>();
        filteredAntibiotique = new ArrayList<Specialite>();
        antibiotiqueL = new ArrayList<Specialite>();
    }

    @PostConstruct
    public void init() {
        antibiotique = new Specialite();
        Selectantibiotique = new Specialite();
        antibiotiques = this.doListerAntibiotique();
        antibiotiquesM = this.doListerAntibiotique();
        antibiotiqueL = this.doListerAntibiotique();

    }

    public void doAjouterAntibiotique() {
        if (antibiotique != null) {
            iAntibiotique.ajouterAntibiotique(antibiotique);
            ajouterMessageInfo("Nom : " + antibiotique.getNomSpecialite());
            antibiotique = new Specialite();

        }

    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Antibiotique Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Specialite> doListerAntibiotique() {
        return iAntibiotique.listeAntibiotique();
    }

    public void listeAntibiotiqueCourante() {
        antibiotiques = this.doListerAntibiotique();
        antibiotiqueL = this.doListerAntibiotique();
        antibiotiquesM = this.doListerAntibiotique();
    }

    public void doModifierAntibiotique() {
        if (antibiotiquesM != null) {
            for (Specialite a : antibiotiquesM) {
                iAntibiotique.modifierAntibiotique(a);
            }
        }
    }

    public List<Specialite> completeAntibiotique(String query) {
        List<Specialite> suggestions = new ArrayList<Specialite>();

        for (Specialite p : antibiotiques) {
            if (p.getNomSpecialite().startsWith(query)) {

                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public Specialite getAntibiotique() {
        return antibiotique;
    }

    public void setAntibiotique(Specialite antibiotique) {
        this.antibiotique = antibiotique;
    }

    public ISpecialite getiAntibiotique() {
        return iAntibiotique;
    }

    public void setiAntibiotique(ISpecialite iAntibiotique) {
        this.iAntibiotique = iAntibiotique;
    }

    public Specialite getSelectantibiotique() {
        return Selectantibiotique;
    }

    public void setSelectantibiotique(Specialite Selectantibiotique) {
        this.Selectantibiotique = Selectantibiotique;
    }

    public List<Specialite> getAntibiotiques() {
        return antibiotiques;
    }

    public void setAntibiotiques(List<Specialite> antibiotiques) {
        this.antibiotiques = antibiotiques;
    }

    public List<Specialite> getAntibiotiquesM() {
        return antibiotiquesM;
    }

    public void setAntibiotiquesM(List<Specialite> antibiotiquesM) {
        this.antibiotiquesM = antibiotiquesM;
    }

    public List<Specialite> getFilteredAntibiotique() {
        return filteredAntibiotique;
    }

    public void setFilteredAntibiotique(List<Specialite> filteredAntibiotique) {
        this.filteredAntibiotique = filteredAntibiotique;
    }

    public List<Specialite> getAntibiotiqueL() {
        return antibiotiqueL;
    }

    public void setAntibiotiqueL(List<Specialite> antibiotiqueL) {
        this.antibiotiqueL = antibiotiqueL;
    }

    public Specialite getAntibiotiqueS() {
        return antibiotiqueS;
    }

    public void setAntibiotiqueS(Specialite antibiotiqueS) {
        this.antibiotiqueS = antibiotiqueS;
    }
}
