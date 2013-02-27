package org.cnss.labCenter.managedBean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.cnss.labCenter.domain.Utilisateur.IUtilisateur;
import org.cnss.labCenter.domain.biologiste.IBiologiste;
import org.cnss.labCenter.domain.chefService.IChefService;
import org.cnss.labCenter.domain.secretaire.ISecretaire;
import org.cnss.labCenter.domain.sousChefService.ISousChefService;
import org.cnss.labCenter.entities.Biologiste;
import org.cnss.labCenter.entities.ChefService;
import org.cnss.labCenter.entities.Secretaire;
import org.cnss.labCenter.entities.SousChefService;
import org.cnss.labCenter.entities.Utilisateurs;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class UtulisateurManagedbean implements Serializable {


    private Utilisateurs cs;
    int i;
   
    private List<Utilisateurs> utilisateurses;
    private String pass;
    private Secretaire secretaire;
    private Biologiste biologiste;
    private ChefService chefService;
    private SousChefService sousChefService;
    private List<Secretaire> secretaires;
    private List<Biologiste> biologistes;
    private List<ChefService> chefServices;
    private List<SousChefService> sousChefServices;
    
      private List<Secretaire> secretairesM;
    private List<Biologiste> biologistesM;
    private List<ChefService> chefServicesM;
    private List<SousChefService> sousChefServicesM;
    
    
      private List<Secretaire> secretairesS;
    private List<Biologiste> biologistesS;
    private List<ChefService> chefServicesS;
    private List<SousChefService> sousChefServicesS;
    
    
    @EJB
    IUtilisateur iProgramme;
    @EJB
    ISecretaire iSecretaire;
    @EJB
    IBiologiste iBiologiste;
    @EJB
    IChefService iChefService;
    @EJB
    ISousChefService iSousChefService;

    public UtulisateurManagedbean() {
        i = 0;
        secretaire = new Secretaire();
        biologiste = new Biologiste();
        sousChefService = new SousChefService();
        chefService = new ChefService();
        secretaires = new ArrayList<Secretaire>();
        biologistes = new ArrayList<Biologiste>();
        sousChefServices = new ArrayList<SousChefService>();
        chefServices = new ArrayList<ChefService>();
        utilisateurses = new ArrayList<Utilisateurs>();
    }

    @PostConstruct
    public void init() {
        utilisateurses = this.doListerUtilisateurs();
        secretaires = this.doListerSecretaire();
        biologistes = this.doListerBiologiste();
        chefServices = this.doListerChefService();
        sousChefServices = this.doListerSousChefService();
    }

    public List<Utilisateurs> doListerUtilisateurs() {
        return iProgramme.listeProgramme();
    }

    public List<Secretaire> doListerSecretaire() {
        return iSecretaire.listeSecretaire();
    }

    public List<Biologiste> doListerBiologiste() {
        return iBiologiste.listeBiologiste();
    }

    public List<SousChefService> doListerSousChefService() {
        return iSousChefService.listeSousChefService();
    }

    public List<ChefService> doListerChefService() {
        return iChefService.listeChefService();
    }
    
    
    public void doSupprimerChefService(){
    iChefService.supprimerChefService(chefService);
    }
    
    public void doSupprimerSousChefServie(){
    iSousChefService.supprimerSousChefService(sousChefService);
    }
    
    public void doSupprimerBiologiste(){
    iBiologiste.supprimerBiologiste(biologiste);
    }

    public void doSupprimerSecretaire(){
    iSecretaire.supprimerSecretaire(secretaire);
    }
    
    public void utilisateursCourant() {
        utilisateurses = this.doListerUtilisateurs();
    }

    public void doAjouterSecretaire() {
        if (secretaire != null) {
            iSecretaire.ajouterSecretaire(secretaire);
            secretaires=this.doListerSecretaire();
            ajouterMessageInfo("Secretaire : " + secretaire.getNomPre());
            secretaire = new Secretaire();
        }
    }

    public void doAjouterBiologiste() {
        if (biologiste != null) {
            iBiologiste.ajouterBiologiste(biologiste);
            biologistes = this.doListerBiologiste();
            ajouterMessageInfo("Biologiste : " + biologiste.getNomPre());
            biologiste = new Biologiste();
        }
    }

    public void doAjouterSousChefService() {
        if (sousChefService != null) {
            iSousChefService.ajouterSousChefService(sousChefService);
            sousChefServices=this.doListerSousChefService();
            ajouterMessageInfo("Sous Chef Service : " + sousChefService.getNomPre());
            sousChefService=new SousChefService();
        }
    }

   

    public void doAjouterChefService() {
        if (chefService != null) {
            iChefService.ajouterChefService(chefService);
            chefServices=this.doListerChefService();
            ajouterMessageInfo("Chef Service : " + chefService.getNomPre());
            chefService = new ChefService();
        }
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        boolean log = false;

        cs = iProgramme.rechercherUtilisateur(pass);

        if (cs instanceof ChefService || cs instanceof SousChefService) {
            i = 0;
        }

        if (i < 3) {
 
            if (cs != null) {
                loggedIn = true;
                log = true;
                i = 0;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentification Réussie", cs.getNomPre());
            } else {

                loggedIn = false;
                i++;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Echeck D'Authentification", "Mot De Passe Invalide");
             
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "programme bloqué", "pour des raisons de sécurité");
               log = false;
        }    

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("log", log);
    
    }

    public IUtilisateur getiProgramme() {
        return iProgramme;
    }

    public void setiProgramme(IUtilisateur iProgramme) {
        this.iProgramme = iProgramme;
    }

    public IBiologiste getiBiologiste() {
        return iBiologiste;
    }

    public void setiBiologiste(IBiologiste iBiologiste) {
        this.iBiologiste = iBiologiste;
    }

    public IChefService getiChefService() {
        return iChefService;
    }

    public void setiChefService(IChefService iChefService) {
        this.iChefService = iChefService;
    }

    public ISecretaire getiSecretaire() {
        return iSecretaire;
    }

    public Biologiste getBiologiste() {
        return biologiste;
    }

    public void setBiologiste(Biologiste biologiste) {
        this.biologiste = biologiste;
    }

    public List<Biologiste> getBiologistes() {
        return biologistes;
    }

    public void setBiologistes(List<Biologiste> biologistes) {
        this.biologistes = biologistes;
    }

    public ChefService getChefService() {
        return chefService;
    }

    public void setChefService(ChefService chefService) {
        this.chefService = chefService;
    }

    public List<ChefService> getChefServices() {
        return chefServices;
    }

    public void setChefServices(List<ChefService> chefServices) {
        this.chefServices = chefServices;
    }

    public Utilisateurs getCs() {
        return cs;
    }

    public void setCs(Utilisateurs cs) {
        this.cs = cs;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public ISousChefService getiSousChefService() {
        return iSousChefService;
    }

    public void setiSousChefService(ISousChefService iSousChefService) {
        this.iSousChefService = iSousChefService;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public List<Secretaire> getSecretaires() {
        return secretaires;
    }

    public void setSecretaires(List<Secretaire> secretaires) {
        this.secretaires = secretaires;
    }

    public SousChefService getSousChefService() {
        return sousChefService;
    }

    public void setSousChefService(SousChefService sousChefService) {
        this.sousChefService = sousChefService;
    }

    public List<SousChefService> getSousChefServices() {
        return sousChefServices;
    }

    public void setSousChefServices(List<SousChefService> sousChefServices) {
        this.sousChefServices = sousChefServices;
    }

    public List<Utilisateurs> getUtilisateurses() {
        return utilisateurses;
    }

    public void setUtilisateurses(List<Utilisateurs> utilisateurses) {
        this.utilisateurses = utilisateurses;
    }

    
    
    }

