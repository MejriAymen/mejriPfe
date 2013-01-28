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
import org.cnss.labCenter.domain.malade.IMalde;
import org.cnss.labCenter.entities.Assure;
import org.cnss.labCenter.entities.Convention;
import org.cnss.labCenter.entities.DossierMedicale;
import org.cnss.labCenter.entities.Malade;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class MaladeManagedbean implements Serializable {

    private Malade malade;
    private Malade seledtedMalade;
    private Malade maladeS;
    private List<Malade> maladeMod;
    private List<Malade> maladeL;
    private List<Malade> malades;
    private Convention selectedConvention;
    private Assure setectedAssure;
    private DossierMedicale dossierMedicale;
    @EJB
    IMalde iMalde;

    public MaladeManagedbean() {
        malade = new Malade();
        dossierMedicale = new DossierMedicale();
        seledtedMalade = new Malade();
        maladeS = new Malade();
        maladeMod = new ArrayList<Malade>();
        maladeL = new ArrayList<Malade>();
        malades = new ArrayList<Malade>();
        selectedConvention = new Convention();
        setectedAssure = new Assure();

    }

    @PostConstruct
    public void init() {
        maladeL = this.doListerMalade();
        maladeMod = this.doListerMalade();
        malades = this.doListerMalade();
    }

    public void doAjouterMalade() {
        if (malade != null) {
            malade.setAssure(setectedAssure);
            malade.setConvention(selectedConvention);
            malade.setDossierMedicale(dossierMedicale);

            iMalde.ajouterMalade(malade);
            ajouterMessageInfo(malade.getNompre());
            setectedAssure = new Assure();
            selectedConvention = new Convention();
            dossierMedicale = new DossierMedicale();
            malade = new Malade();
        }
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Malade Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doModifierMalade() {
        if (maladeMod != null) {
            for (Malade a : maladeMod) {
                iMalde.modifierMalade(a);
            }
        }
    }

    public void doSupprimerMalade() {
        iMalde.supprimerMalade(maladeS);
    }

    public List<Malade> doListerMalade() {
        return iMalde.listerMalade();
    }

    public void listeMaladeCourant() {
        maladeL = this.doListerMalade();
        maladeMod = this.doListerMalade();
        malades = this.doListerMalade();
    }

    public List<Malade> completerMalade(String query) {
        List<Malade> suggestions = new ArrayList<Malade>();
        for (Malade p : malades) {
            if (p.getNompre().startsWith(query)) {
                suggestions.add(p);
            }
        }
        return suggestions;
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

    public IMalde getiMalde() {
        return iMalde;
    }

    public void setiMalde(IMalde iMalde) {
        this.iMalde = iMalde;
    }

    public Malade getMalade() {
        return malade;
    }

    public void setMalade(Malade malade) {
        this.malade = malade;
    }

    public List<Malade> getMaladeL() {
        return maladeL;
    }

    public void setMaladeL(List<Malade> maladeL) {
        this.maladeL = maladeL;
    }

    public List<Malade> getMaladeMod() {
        return maladeMod;
    }

    public void setMaladeMod(List<Malade> maladeMod) {
        this.maladeMod = maladeMod;
    }

    public Malade getMaladeS() {
        return maladeS;
    }

    public void setMaladeS(Malade maladeS) {
        this.maladeS = maladeS;
    }

    public List<Malade> getMalades() {
        return malades;
    }

    public void setMalades(List<Malade> malades) {
        this.malades = malades;
    }

    public Malade getSeledtedMalade() {
        return seledtedMalade;
    }

    public void setSeledtedMalade(Malade seledtedMalade) {
        this.seledtedMalade = seledtedMalade;
    }

    public Convention getSelectedConvention() {
        return selectedConvention;
    }

    public void setSelectedConvention(Convention selectedConvention) {
        this.selectedConvention = selectedConvention;
    }

    public Assure getSetectedAssure() {
        return setectedAssure;
    }

    public void setSetectedAssure(Assure setectedAssure) {
        this.setectedAssure = setectedAssure;
    }

    public DossierMedicale getDossierMedicale() {
        return dossierMedicale;
    }

    public void setDossierMedicale(DossierMedicale dossierMedicale) {
        this.dossierMedicale = dossierMedicale;
    }
}
