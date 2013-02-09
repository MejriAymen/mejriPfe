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
import org.cnss.labCenter.domain.resultat.IResultat;
import org.cnss.labCenter.domain.visite.IVisite;
import org.cnss.labCenter.entities.Resultat;
import org.cnss.labCenter.entities.Visite;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class ResultatManagedbean implements Serializable {

    Visite selectedVisite;
    int i = 0;
    public static int y = 0;
    List<Visite> visites;
    Resultat result;
    List<Resultat> resultats;
    private float vHMin;
    private float vHMax;
    @EJB
    IVisite iVisite;
    @EJB
    IResultat iResultat;

    public ResultatManagedbean() {
        selectedVisite = new Visite();
        visites = new ArrayList<Visite>();
        result = new Resultat();
        resultats = new ArrayList<Resultat>();

    }

    @PostConstruct
    public void init() {
        visites = doListerVisite();
    }

    public void visiteCourant() {
        visites = doListerVisite();
    }

    public void doModifierVisite() {


        converstionVisualisation();
        
        for (Visite visite : visites) {

            iVisite.modifierVisite(visite);
            
        }

        visites = new ArrayList<Visite>();
       
    }

    public List<Visite> doListerVisite() {

        List<Visite> l = iVisite.listeVisite();
        List<Visite> vs = new ArrayList<Visite>();

        if (selectedVisite != null) {
            for (Visite visite : l) {
                visite = iVisite.VisiteConverter(visite.getIdVisite());
                if (visite.getNumVis().equals(selectedVisite.getNumVis())) {

                    vs.add(visite);
                }
            }
        }

        return vs;
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Antibiotique Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void converstionVisualisation() {

        for (Visite visite : visites) {
            visite.getResultat().setResCoi(visite.getResultat().getRes() * visite.getNomenclature().getValeursUsuelles().getCoefficient());
            visite.getNomenclature().getValeursUsuelles().setV1Max(visite.getNomenclature().getValeursUsuelles().getvHMax() * visite.getNomenclature().getValeursUsuelles().getCoefficient());
            visite.getNomenclature().getValeursUsuelles().setV2Min(visite.getNomenclature().getValeursUsuelles().getvHMin() * visite.getNomenclature().getValeursUsuelles().getCoefficient());
        }

    }

    public IVisite getiVisite() {
        return iVisite;
    }

    public void setiVisite(IVisite iVisite) {
        this.iVisite = iVisite;
    }

    public Visite getSelectedVisite() {
        return selectedVisite;
    }

    public void setSelectedVisite(Visite selectedVisite) {
        this.selectedVisite = selectedVisite;
    }

    public void retournerNompreCourant() {
        this.selectedVisite.getDossierMedicale().getMalade().setNompre(selectedVisite.getDossierMedicale().getMalade().getNompre());
    }

    public void retournerDossierCourant() {
        this.selectedVisite.getDossierMedicale().setNumDoss(selectedVisite.getDossierMedicale().getNumDoss());
    }

    public Resultat getResult() {
        return result;
    }

    public void setResult(Resultat result) {
        this.result = result;
    }

    public List<Visite> getVisites() {
        return visites;
    }

    public void setVisites(List<Visite> visites) {
        this.visites = visites;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    public IResultat getiResultat() {
        return iResultat;
    }

    public void setiResultat(IResultat iResultat) {
        this.iResultat = iResultat;
    }

    public float getvHMax() {
        return vHMax;
    }

    public void setvHMax(float vHMax) {
        this.vHMax = vHMax;
    }

    public float getvHMin() {
        return vHMin;
    }

    public void setvHMin(float vHMin) {
        this.vHMin = vHMin;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        ResultatManagedbean.y = y;
    }
}
