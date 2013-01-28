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
import org.cnss.labCenter.domain.antibiotique.IAntibiotique;
import org.cnss.labCenter.entities.Antibiotique;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class AntibiotiqueManagedbean implements Serializable {

    private Antibiotique antibiotique;
    private Antibiotique antibiotiqueS;
    private Antibiotique Selectantibiotique;
    private List<Antibiotique> antibiotiques;
    private List<Antibiotique> antibiotiquesM;
    private List<Antibiotique> antibiotiqueL;
    private List<Antibiotique> filteredAntibiotique;
    @EJB
    IAntibiotique iAntibiotique;

    public AntibiotiqueManagedbean() {
        antibiotiqueS = new Antibiotique();
        antibiotiques = new ArrayList<Antibiotique>();
        antibiotiquesM = new ArrayList<Antibiotique>();
        filteredAntibiotique = new ArrayList<Antibiotique>();
        antibiotiqueL = new ArrayList<Antibiotique>();
    }

    @PostConstruct
    public void init() {
        antibiotique = new Antibiotique();
        Selectantibiotique = new Antibiotique();
        antibiotiques = this.doListerAntibiotique();
        antibiotiquesM = this.doListerAntibiotique();
        antibiotiqueL = this.doListerAntibiotique();

    }

  

    public void doAjouterAntibiotique() {
        if (antibiotique != null) {
            iAntibiotique.ajouterAntibiotique(antibiotique);
            ajouterMessageInfo("Nom : " + antibiotique.getNomAntibiotique());
            antibiotique = new Antibiotique();

        }

    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Antibiotique Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Antibiotique> doListerAntibiotique() {
        return iAntibiotique.listeAntibiotique();
    }
    
    public void listeAntibiotiqueCourante(){
    antibiotiques = this.doListerAntibiotique();
    antibiotiqueL = this.doListerAntibiotique();
    antibiotiquesM = this.doListerAntibiotique();
    }

    public void doModifierAntibiotique() {
        if (antibiotiquesM != null) {
            for (Antibiotique a : antibiotiquesM) {
                iAntibiotique.modifierAntibiotique(a);
            }
        }
    }

    public List<Antibiotique> completeAntibiotique(String query) {
        List<Antibiotique> suggestions = new ArrayList<Antibiotique>();

        for (Antibiotique p : antibiotiques) {
            if (p.getAbreviation().startsWith(query)) {

                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public Antibiotique getAntibiotique() {
        return antibiotique;
    }

    public void setAntibiotique(Antibiotique antibiotique) {
        this.antibiotique = antibiotique;
    }

    public IAntibiotique getiAntibiotique() {
        return iAntibiotique;
    }

    public void setiAntibiotique(IAntibiotique iAntibiotique) {
        this.iAntibiotique = iAntibiotique;
    }

    public Antibiotique getSelectantibiotique() {
        return Selectantibiotique;
    }

    public void setSelectantibiotique(Antibiotique Selectantibiotique) {
        this.Selectantibiotique = Selectantibiotique;
    }

    public List<Antibiotique> getAntibiotiques() {
        return antibiotiques;
    }

    public void setAntibiotiques(List<Antibiotique> antibiotiques) {
        this.antibiotiques = antibiotiques;
    }

    public List<Antibiotique> getAntibiotiquesM() {
        return antibiotiquesM;
    }

    public void setAntibiotiquesM(List<Antibiotique> antibiotiquesM) {
        this.antibiotiquesM = antibiotiquesM;
    }

    public List<Antibiotique> getFilteredAntibiotique() {
        return filteredAntibiotique;
    }

    public void setFilteredAntibiotique(List<Antibiotique> filteredAntibiotique) {
        this.filteredAntibiotique = filteredAntibiotique;
    }

    public List<Antibiotique> getAntibiotiqueL() {
        return antibiotiqueL;
    }

    public void setAntibiotiqueL(List<Antibiotique> antibiotiqueL) {
        this.antibiotiqueL = antibiotiqueL;
    }

    public Antibiotique getAntibiotiqueS() {
        return antibiotiqueS;
    }

    public void setAntibiotiqueS(Antibiotique antibiotiqueS) {
        this.antibiotiqueS = antibiotiqueS;
    }
}
