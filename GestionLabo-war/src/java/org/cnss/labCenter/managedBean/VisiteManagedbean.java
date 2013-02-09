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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.cnss.labCenter.domain.nomenclature.INomenclature;
import org.cnss.labCenter.domain.resultat.IResultat;
import org.cnss.labCenter.domain.services.IServices;
import org.cnss.labCenter.domain.visite.IVisite;
import org.cnss.labCenter.entities.Convention;
import org.cnss.labCenter.entities.DossierMedicale;
import org.cnss.labCenter.entities.Medecin;
import org.cnss.labCenter.entities.Nomenclature;
import org.cnss.labCenter.entities.Resultat;
import org.cnss.labCenter.entities.Visite;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class visiteManagedbean implements Serializable {

    private float sommeB = 0;
    private float sommeAPB = 0;
    private float prixB = 0;
    private float prixAPB = 0;
    private float totalprix = 0;
    private Visite visite;
    private Visite visiteV;
    private Visite visiteSup;
    private Convention selectConvention;
    private List<Visite> visites;
    private List<Visite> visitesM;
    private DossierMedicale dossierMedicaleS;
    private List<Nomenclature> Selectnomenclature;
    private Medecin seledtedMedecin;
    List<Resultat> resultats;
    Resultat result;
    @ManagedProperty(value = "#{utulisateurManagedbean}")
    UtulisateurManagedbean utulisateurManagedbean;
    @EJB
    IVisite iVisite;
    @EJB
    IServices iServices;
    @EJB
    INomenclature iNomenclature;
    @EJB
    IResultat iResultat;

    public visiteManagedbean() {

        resultats = new ArrayList<Resultat>();

        visite = new Visite();
        visiteV = new Visite();
        visiteSup = new Visite();
        selectConvention = new Convention();
        visitesM = new ArrayList<Visite>();
        visites = new ArrayList<Visite>();
        dossierMedicaleS = new DossierMedicale();
        Selectnomenclature = new ArrayList<Nomenclature>();
        seledtedMedecin = new Medecin();
        result = new Resultat();

    }

    @PostConstruct
    public void init() {
        result = new Resultat();
        visitesM = this.doListerVisite();
        visites = this.doListerVisite();
    }

    public void returnConvention() {
        this.dossierMedicaleS.getMalade().getConvention().getOrganisme();
    }

    public void returnNomPre() {
        this.dossierMedicaleS.getMalade().getNompre();
    }

    public void retournerDossierCourant() {
        this.getDossierMedicaleS();
    }

    public void retournerAnalyseqCourant() {
        this.getSelectnomenclature();
    }

    public float totalPrix() {
        totalprix = 0;
        totalprix = prixAPB() + prixB();
        return totalprix;

    }

    public float prixB() {
        prixB = 0;
        if (dossierMedicaleS.getMalade().getConvention().getPrixB() != 0) {
            prixB = dossierMedicaleS.getMalade().getConvention().getPrixB() * totalB();
        }
        return prixB;
    }

    public float prixAPB() {
        prixAPB = 0;
        if (dossierMedicaleS.getMalade().getConvention().getPrixAPB() != 0) {
            prixAPB = dossierMedicaleS.getMalade().getConvention().getPrixAPB() * totalAPB();
        }

        return prixAPB;
    }

    public float totalB() {

        sommeB = 0;
        if (Selectnomenclature != null) {
            for (Nomenclature n : Selectnomenclature) {
                sommeB = sommeB + n.getValuerB();
            }
        }
        return sommeB;

    }

    public float totalAPB() {

        sommeAPB = 0;
        if (Selectnomenclature != null) {

            for (Nomenclature n : Selectnomenclature) {
                sommeAPB = sommeAPB + n.getValeurAPB();
            }
        }
        return sommeAPB;

    }

    public void maladeCourant() {
        this.dossierMedicaleS.getMalade().getNompre();
    }

    public void visitesCourantes() {

        visites = this.doListerVisite();

    }

    public void doAjouterVisite(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        if (visite != null) {
            visite.setTotB(sommeB);
            visite.setTotAPB(sommeAPB);
            visite.setPrixB(prixB);
            visite.setPrixAPB(prixAPB);
            visite.setPrixTot(totalprix);
            visite.setMedecin(seledtedMedecin);
            visite.setDossierMedicale(dossierMedicaleS);
            visite.setUtilisateur(utulisateurManagedbean.getCs());

            for (Nomenclature n : Selectnomenclature) {

                iServices.affectationVisteNomenclature(visite, n);
                visite.setNomenclature(n);
                result.setRes(0);
                iResultat.ajouterResultat(result);
                resultats.add(iResultat.find(result));
                visite.setResultat(result);
                iNomenclature.modifierNomenclature(n);

            }

  msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Visite Added", visite.getDossierMedicale().getNumDoss());
            loggedIn = true;
            prixAPB = 0;
            prixB = 0;
            totalprix = 0;
            dossierMedicaleS = new DossierMedicale();
            Selectnomenclature = new ArrayList<Nomenclature>();
            seledtedMedecin = new Medecin();
            visite = new Visite();
          
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Action Failed", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);

    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Visite Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Visite> doListerVisite() {

        int i;

        List<Visite> vs = iVisite.listeVisite();
        List<Visite> vs1 = new ArrayList<Visite>();
        if (!vs.isEmpty()) {
            String trouve = vs.get(0).getNumVis();
            vs1.add(vs.get(0));
            i = 0;
            for (Visite visto : vs) {
                i++;
                if (!visto.getNumVis().equals(trouve)) {
                    vs1.add(visto);
                    trouve = visto.getNumVis();
                }
            }
        }
        return vs1;
    }

    public void doModifierNomenclature() {
        if (visitesM != null) {
            for (Visite a : visitesM) {
                iVisite.modifierVisite(a);
            }
        }
    }

    public List<Nomenclature> doListerNomenclature() {

        List<Visite> vs = iVisite.listeVisite();
        List<Visite> vso = new ArrayList<Visite>();
        List<Nomenclature> nos = new ArrayList<Nomenclature>();
        if (visiteSup != null) {

            for (Visite v : vs) {
                if (v.getNumVis().equals(visiteSup.getNumVis())) {
                    vso.add(v);
                }
            }

            for (Visite visite1 : vso) {
                nos.add(visite1.getNomenclature());
            }
        }
        return nos;
    }

    public void doSupVisite() {

        List<Visite> vs = iVisite.listeVisite();
        String numVis = visiteSup.getNumVis();
        for (Visite v : vs) {
            if (v.getNumVis().equals(numVis)) {
                iVisite.supprimerVisite(v.getIdVisite());
            }

        }
        visites = this.doListerVisite();
    }

    public void AssureCourant() {
        dossierMedicaleS.getMalade().getAssure().setMatriculeAssure(this.dossierMedicaleS.getMalade().getAssure().getMatriculeAssure());
    }

    public void medecinCourant() {

        visiteSup.setMedecin(visiteSup.getMedecin());
    }

    public void dossierCourant() {
        visiteSup.getDossierMedicale().setNumDoss(visiteSup.getDossierMedicale().getNumDoss());
    }

    public List<Visite> completeVisite(String query) {
        List<Visite> suggestions = new ArrayList<Visite>();

        for (Visite p : visites) {
            if (p.getNumVis().startsWith(query)) {

                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public IVisite getiVisite() {
        return iVisite;
    }

    public void setiVisite(IVisite iVisite) {
        this.iVisite = iVisite;
    }

    public Visite getVisite() {
        return visite;
    }

    public void setVisite(Visite visite) {
        this.visite = visite;
    }

    public List<Visite> getVisites() {
        return visites;
    }

    public void setVisites(List<Visite> visites) {
        this.visites = visites;
    }

    public List<Visite> getVisitesM() {
        return visitesM;
    }

    public void setVisitesM(List<Visite> visitesM) {
        this.visitesM = visitesM;
    }

    public DossierMedicale getDossierMedicaleS() {
        return dossierMedicaleS;
    }

    public void setDossierMedicaleS(DossierMedicale dossierMedicaleS) {
        this.dossierMedicaleS = dossierMedicaleS;
    }

    public Medecin getSeledtedMedecin() {
        return seledtedMedecin;
    }

    public void setSeledtedMedecin(Medecin seledtedMedecin) {
        this.seledtedMedecin = seledtedMedecin;
    }

    public List<Nomenclature> getSelectnomenclature() {
        return Selectnomenclature;
    }

    public void setSelectnomenclature(List<Nomenclature> Selectnomenclature) {

        this.Selectnomenclature = Selectnomenclature;
    }

    public float getSommeB() {
        return sommeB;
    }

    public void setSommeB(float sommeB) {
        this.sommeB = sommeB;
    }

    public float getSommeAPB() {
        return sommeAPB;
    }

    public void setSommeAPB(float sommeAPB) {
        this.sommeAPB = sommeAPB;
    }

    public IServices getiServices() {
        return iServices;
    }

    public void setiServices(IServices iServices) {
        this.iServices = iServices;
    }

    public Convention getSelectConvention() {
        return selectConvention;
    }

    public void setSelectConvention(Convention selectConvention) {
        this.selectConvention = selectConvention;
    }

    public Visite getVisiteSup() {
        return visiteSup;
    }

    public void setVisiteSup(Visite visiteSup) {
        this.visiteSup = visiteSup;
    }

    public Visite getVisiteV() {
        return visiteV;
    }

    public void setVisiteV(Visite visiteV) {
        this.visiteV = visiteV;
    }

    public float getPrixAPB() {
        return prixAPB;
    }

    public void setPrixAPB(float prixAPB) {
        this.prixAPB = prixAPB;
    }

    public float getPrixB() {
        return prixB;
    }

    public void setPrixB(float prixB) {
        this.prixB = prixB;
    }

    public float getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(float totalprix) {
        this.totalprix = totalprix;
    }

    public UtulisateurManagedbean getUtulisateurManagedbean() {
        return utulisateurManagedbean;
    }

    public void setUtulisateurManagedbean(UtulisateurManagedbean utulisateurManagedbean) {
        this.utulisateurManagedbean = utulisateurManagedbean;
    }
}
