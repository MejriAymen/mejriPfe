package org.cnss.labCenter.managedBean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.cnss.labCenter.domain.nomenclature.INomenclature;
import org.cnss.labCenter.domain.resultat.IResultat;
import org.cnss.labCenter.domain.resultatECBU.IResultatECBU;
import org.cnss.labCenter.domain.services.IServices;
import org.cnss.labCenter.domain.visite.IVisite;
import org.cnss.labCenter.entities.Consultation;
import org.cnss.labCenter.entities.DossierMedicale;
import org.cnss.labCenter.entities.Nomenclature;
import org.cnss.labCenter.entities.Resultat;
import org.cnss.labCenter.entities.Secretaire;
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
    private List<Visite> visites;
    private List<Visite> visitesM;
    private DossierMedicale dossierMedicaleS;
    private Consultation consultation;
    private List<Nomenclature> Selectnomenclature;
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
    @EJB
    IResultatECBU iResultatECBU;

    public visiteManagedbean() {

        resultats = new ArrayList<Resultat>();

        visite = new Visite();
        visiteV = new Visite();
        visiteSup = new Visite();
        visitesM = new ArrayList<Visite>();
        visites = new ArrayList<Visite>();
        dossierMedicaleS = new DossierMedicale();
        Selectnomenclature = new ArrayList<Nomenclature>();
        result = new Resultat();
        consultation = new Consultation();

    }

    @PostConstruct
    public void init() {
        result = new Resultat();
        visitesM = this.doListerVisite();
        visites = this.doListerVisite();
    }

    @PreDestroy
    public void initP() {
        dossierMedicaleS = new DossierMedicale();
        Selectnomenclature = new ArrayList<Nomenclature>();
        visite = new Visite();

    }

    public void returnConvention() {
        this.consultation.getDossierMedicale().getMalade().getConvention();
    }

    public void returnNomPre() {
        this.consultation.getDossierMedicale().getMalade().getNompre();
    }

    public void retournerDossierCourant() {
        this.consultation.getDossierMedicale();
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

    public void doAjouterVisite() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;

        if (visite != null) {
            visite.setTotB(sommeB);
            visite.setTotAPB(sommeAPB);
            visite.setPrixB(prixB);
            visite.setPrixAPB(prixAPB);
            visite.setPrixTot(totalprix);
            visite.setConsultation(consultation);
            Secretaire secretaire =  (Secretaire) utulisateurManagedbean.getCs();
            visite.setSecretaire(secretaire);

            for (Nomenclature n : Selectnomenclature) {

                iServices.affectationVisteNomenclature(visite, n);
                visite.setNomenclature(n);
                result.setRes(0);
                iResultat.ajouterResultat(result);
                resultats.add(iResultat.find(result));
                visite.setResultat(result);
                iNomenclature.modifierNomenclature(n);

            }

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Visite Added", visite.getNumVis());

            visites = doListerVisite();
            prixAPB = 0;
            prixB = 0;
            sommeAPB = 0;
            sommeB = 0;
            totalprix = 0;
            consultation = new Consultation();
            dossierMedicaleS = new DossierMedicale();
            Selectnomenclature = new ArrayList<Nomenclature>();
            visite = new Visite();
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Action Failed", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public void medecinCourant() {
        visiteSup.getConsultation().getMedecin();
    }

    public void returnConsultation() {
        consultation = dossierMedicaleS.getConsultations().get(dossierMedicaleS.getConsultations().size() - 1);
    }

    public void dossierCourant() {
        visiteSup.getConsultation().getDossierMedicale().setNumDoss(visiteSup.getConsultation().getDossierMedicale().getNumDoss());
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

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public INomenclature getiNomenclature() {
        return iNomenclature;
    }

    public void setiNomenclature(INomenclature iNomenclature) {
        this.iNomenclature = iNomenclature;
    }

    public IResultat getiResultat() {
        return iResultat;
    }

    public void setiResultat(IResultat iResultat) {
        this.iResultat = iResultat;
    }

    public IResultatECBU getiResultatECBU() {
        return iResultatECBU;
    }

    public void setiResultatECBU(IResultatECBU iResultatECBU) {
        this.iResultatECBU = iResultatECBU;
    }

    public Resultat getResult() {
        return result;
    }

    public void setResult(Resultat result) {
        this.result = result;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }
}
