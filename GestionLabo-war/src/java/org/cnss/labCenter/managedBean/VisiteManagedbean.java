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
import org.cnss.labCenter.domain.services.IServices;
import org.cnss.labCenter.domain.visite.IVisite;
import org.cnss.labCenter.entities.Convention;
import org.cnss.labCenter.entities.DossierMedicale;
import org.cnss.labCenter.entities.Medecin;
import org.cnss.labCenter.entities.Nomenclature;
import org.cnss.labCenter.entities.Visite;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class VisiteManagedbean implements Serializable {
    
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
    
      @ManagedProperty(value = "#{utulisateurManagedbean}")
      UtulisateurManagedbean utulisateurManagedbean;
      
      
    @EJB
    IVisite iVisite;
    @EJB
    IServices iServices;
    
    public VisiteManagedbean() {
        
        visite = new Visite();
        visiteV = new Visite();
        visiteSup = new Visite();
        selectConvention = new Convention();
        visitesM = new ArrayList<Visite>();
        visites = new ArrayList<Visite>();
        dossierMedicaleS = new DossierMedicale();
        Selectnomenclature = new ArrayList<Nomenclature>();
        seledtedMedecin = new Medecin();
        
    }
    
    @PostConstruct
    public void init() {
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
    
    public void visitesCourantes() {
        
        visites = this.doListerVisite();
        
    }
    
    public boolean doAjouterVisite() {
        if (visite != null) {
            visite.setTotB(sommeB);
            visite.setTotAPB(sommeAPB);
            visite.setPrixB(prixB);
            visite.setPrixAPB(prixAPB);
            visite.setPrixTot(totalprix);
            visite.setMedecin(seledtedMedecin);
            visite.setDossierMedicale(dossierMedicaleS);
            System.out.println("lll"+utulisateurManagedbean.getCs().getNomPre());
            visite.setUtilisateur(utulisateurManagedbean.getCs());
          
            
            for (Nomenclature n : Selectnomenclature) {
                iServices.affectationVisteNomenclature(visite, n);
            }
            iVisite.ajouterVisite(visite);
            ajouterMessageInfo(visite.getDossierMedicale().getNumDoss());
            prixAPB = 0;
            prixB = 0;
            totalprix = 0;
            
            dossierMedicaleS = new DossierMedicale();
            Selectnomenclature = new ArrayList<Nomenclature>();
            seledtedMedecin = new Medecin();
            visite = new Visite();
        }
        return true;
    }
    
    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Visite Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Visite> doListerVisite() {
        return iVisite.listeVisite();
    }
    
    public void doModifierNomenclature() {
        if (visitesM != null) {
            for (Visite a : visitesM) {
                iVisite.modifierVisite(a);
            }
        }
    }
    
    public void doSupVisite() {
        
        iVisite.supprimerVisite(visiteSup.getIdVisite());
        visites = this.doListerVisite();
        
        
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
