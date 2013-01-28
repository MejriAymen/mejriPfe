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
import org.cnss.labCenter.domain.dossierMedical.IDossierMedical;
import org.cnss.labCenter.entities.DossierMedicale;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class DossierMedicalManagedbean implements Serializable {

    private DossierMedicale dossierMedicale;
    private DossierMedicale dossierMedicaleS;
    private List<DossierMedicale> dossierMedicales;
    private List<DossierMedicale> dossierMedicalesM;
    @EJB
    IDossierMedical iDossierMedical;

    public DossierMedicalManagedbean() {
        dossierMedicale = new DossierMedicale();
        dossierMedicaleS = new DossierMedicale();
        dossierMedicales = new ArrayList<DossierMedicale>();
        dossierMedicalesM = new ArrayList<DossierMedicale>();
    }

    @PostConstruct
    public void init() {
        dossierMedicales = this.doListerDossierMedicale();
    }

    public void doAjouterDossierMedicale() {
        if (dossierMedicale != null) {
            iDossierMedical.ajouterDossierMedicale(dossierMedicale);
            ajouterMessageInfo("Dossier Médicale : " + dossierMedicale.getNumDoss());
            dossierMedicale = new DossierMedicale();

        }

    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dossier Médicale Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<DossierMedicale> doListerDossierMedicale() {
        return iDossierMedical.listeDossierMedicale();
    }

    public void doModifierDossierMedicale() {
        if (dossierMedicalesM != null) {
            for (DossierMedicale a : dossierMedicalesM) {
                iDossierMedical.modifierDossierMedicale(a);
            }
        }
    }

    public List<DossierMedicale> completeDossierMedicale(String query) {
        List<DossierMedicale> suggestions = new ArrayList<DossierMedicale>();

        for (DossierMedicale p : dossierMedicales) {
            if (p.getNumDoss().startsWith(query)) {

                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public DossierMedicale getDossierMedicale() {
        return dossierMedicale;
    }

    public void setDossierMedicale(DossierMedicale dossierMedicale) {
        this.dossierMedicale = dossierMedicale;
    }

    public List<DossierMedicale> getDossierMedicales() {
        return dossierMedicales;
    }

    public void setDossierMedicales(List<DossierMedicale> dossierMedicales) {
        this.dossierMedicales = dossierMedicales;
    }

    public List<DossierMedicale> getDossierMedicalesM() {
        return dossierMedicalesM;
    }

    public void setDossierMedicalesM(List<DossierMedicale> dossierMedicalesM) {
        this.dossierMedicalesM = dossierMedicalesM;
    }

    public IDossierMedical getiDossierMedical() {
        return iDossierMedical;
    }

    public void setiDossierMedical(IDossierMedical iDossierMedical) {
        this.iDossierMedical = iDossierMedical;
    }

    public DossierMedicale getDossierMedicaleS() {
        return dossierMedicaleS;
    }

    public void setDossierMedicaleS(DossierMedicale dossierMedicaleS) {
        this.dossierMedicaleS = dossierMedicaleS;
    }
}
