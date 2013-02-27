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
import org.cnss.labCenter.domain.medecin.IMedecin;
import org.cnss.labCenter.entities.Medecin;
import org.cnss.labCenter.entities.Specialite;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class MedecinManagedbean implements Serializable {

    private Medecin medecin;
    private Medecin seledtedMedecin;
    private Medecin medecinS;
    private Specialite selectedSpecialite;
    private List<Medecin> medecinMod;
    private List<Medecin> medecinsL;
    private List<Medecin> medecins;
    @EJB
    IMedecin iMedecin;

    public MedecinManagedbean() {
        medecin = new Medecin();
        medecinS = new Medecin();
        selectedSpecialite = new Specialite();
        seledtedMedecin = new Medecin();
        medecinMod = new ArrayList<Medecin>();
        medecins = new ArrayList<Medecin>();
        medecinsL = new ArrayList<Medecin>();
    }

    @PostConstruct
    public void init() {
        medecins = this.doListerMedecin();
        medecinsL = this.doListerMedecin();
        medecinMod = this.doListerMedecin();
    }

    public void doAjouterMedecin() {
        if (medecin != null) {
            System.out.println("lool"+selectedSpecialite.getNomSpecialite());
            medecin.setSpecialite(selectedSpecialite);
            iMedecin.ajouterMedecin(medecin);
            ajouterMessageInfo(medecin.getNompre());
            medecin = new Medecin();
        }
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Medecin Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doModifierMedecin() {
        if (medecinMod != null) {
            for (Medecin a : medecinMod) {
                iMedecin.modifierMedecin(a);
            }
        }
    }

    public void doSupprimerMedecin() {
        iMedecin.supprimerMedecin(medecinS);
    }

    public List<Medecin> doListerMedecin() {
        return iMedecin.listerMedecin();
    }

    public void listeMedecinCourant() {
        medecinMod = this.doListerMedecin();
        medecins = this.doListerMedecin();
        medecinsL = this.doListerMedecin();
    }

    public List<Medecin> completerMedecin(String query) {
        List<Medecin> suggestions = new ArrayList<Medecin>();

        for (Medecin p : medecins) {
            if (p.getCodeMed().startsWith(query)) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public Medecin getMedecinS() {
        return medecinS;
    }

    public void setMedecinS(Medecin medecinS) {
        this.medecinS = medecinS;
    }

    public IMedecin getiMedecin() {
        return iMedecin;
    }

    public void setiMedecin(IMedecin iMedecin) {
        this.iMedecin = iMedecin;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    public Medecin getSeledtedMedecin() {
        return seledtedMedecin;
    }

    public void setSeledtedMedecin(Medecin seledtedMedecin) {
        this.seledtedMedecin = seledtedMedecin;
    }

    public List<Medecin> getMedecinsL() {
        return medecinsL;
    }

    public void setMedecinsL(List<Medecin> medecinsL) {
        this.medecinsL = medecinsL;
    }

    public List<Medecin> getMedecinMod() {
        return medecinMod;
    }

    public void setMedecinMod(List<Medecin> medecinMod) {
        this.medecinMod = medecinMod;
    }

    public Specialite getSelectedSpecialite() {
        return selectedSpecialite;
    }

    public void setSelectedSpecialite(Specialite selectedSpecialite) {
        this.selectedSpecialite = selectedSpecialite;
    }
}
