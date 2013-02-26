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
import javax.faces.event.ActionEvent;
import javax.persistence.PreUpdate;
import org.cnss.labCenter.domain.resultat.IResultat;
import org.cnss.labCenter.domain.resultatECBU.IResultatECBU;
import org.cnss.labCenter.domain.visite.IVisite;
import org.cnss.labCenter.entities.Resultat;
import org.cnss.labCenter.entities.ResultatECBU;
import org.cnss.labCenter.entities.Visite;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class ResultatManagedbean implements Serializable {

    Visite selectedVisite;
    int i = 0;
    int y = 0;
    List<Visite> visites;
    Resultat result;
    ResultatECBU resultatECBU;
    List<Resultat> resultats;
    @EJB
    IVisite iVisite;
    @EJB
    IResultat iResultat;
    @EJB
    IResultatECBU iResultatECBU;

    public ResultatManagedbean() {
        selectedVisite = new Visite();
        visites = new ArrayList<Visite>();
        result = new Resultat();
        resultats = new ArrayList<Resultat>();
        resultatECBU = new ResultatECBU();

    }

    @PostConstruct
    public void init() {
        visites = doListerVisite();
    }

    @PreUpdate
    public void initt() {
        selectedVisite = new Visite();
        visites = new ArrayList<Visite>();
    }

    public void visiteCourant() {
        visites = doListerVisite();
    }

    public void doModifierVisiteVerification(ActionEvent actionEvent) {

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        boolean logo = false;


        if (visites.get(0).getResultat().getRes() > visites.get(0).getNomenclature().getValeursUsuelles().getvHMax() || visites.get(0).getResultat().getRes() < visites.get(0).getNomenclature().getValeursUsuelles().getvHMin()) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Result Must be Between", visites.get(0).getNomenclature().getValeursUsuelles().getvHMin() + " and " + visites.get(0).getNomenclature().getValeursUsuelles().getvHMax());
            loggedIn = false;

        } else {

            this.doModifierVisite();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Result Added", "");

            loggedIn = true;
            if (i == 0) {
                logo = false;
            } else {
                logo = true;
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("logo", logo);
    }

    public void showPartie() {

        if ("ECBU".equals(visites.get(0).getNomenclature().getAbreviation())) {
            iVisite.VisiteConverter(visites.get(0).getIdVisite());
            visites.get(0).getResultat().setResultatECBU(resultatECBU);
            resultatECBU.setResultat(iVisite.VisiteConverter(visites.get(0).getIdVisite()).getResultat());
            iResultatECBU.ajouterResultat(resultatECBU);
            i++;
            resultatECBU = new ResultatECBU();
        }

    }

    public void doModifierVisite() {

        i++;
        converstionVisualisation();
        i++;
        converstionVisualisation();

        

        for (Visite visite : visites) {
            iVisite.modifierVisite(visite);

        }

        if (i == y) {
            i = 0;
            selectedVisite = new Visite();
        }

        visites = new ArrayList<Visite>();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Result Added", "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public List<Visite> doListerVisite() {

        List<Visite> l = iVisite.listeVisite();

        List<Visite> vs = new ArrayList<Visite>();
        List<Visite> vsl = new ArrayList<Visite>();

        if (selectedVisite != null) {
            for (Visite visite : l) {
                visite = iVisite.VisiteConverter(visite.getIdVisite());
                if (visite.getNumVis().equals(selectedVisite.getNumVis())) {

                    vs.add(visite);
                }
            }
            y = vs.size();
            if (!vs.isEmpty()) {
                vsl.add(vs.get(i));
            }

        }
        return vsl;
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
        this.setSelectedVisite(this.getSelectedVisite());
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public IResultatECBU getiResultatECBU() {
        return iResultatECBU;
    }

    public void setiResultatECBU(IResultatECBU iResultatECBU) {
        this.iResultatECBU = iResultatECBU;
    }

    public ResultatECBU getResultatECBU() {
        return resultatECBU;
    }

    public void setResultatECBU(ResultatECBU resultatECBU) {
        this.resultatECBU = resultatECBU;
    }

    
}
