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

    private Secretaire cs;
    private List<Utilisateurs> utilisateurses;
    private String pass;
    private Secretaire secretaire;
    private Biologiste biologiste;
    private ChefService chefService;
    private SousChefService sousChefService;
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
        secretaire = new Secretaire();
        biologiste = new Biologiste();
        sousChefService = new SousChefService();
        chefService = new ChefService();
        utilisateurses = new ArrayList<Utilisateurs>();
    }

    @PostConstruct
    public void init() {
        utilisateurses = this.doListerUtilisateurs();
    }

    public List<Utilisateurs> doListerUtilisateurs() {
        return iProgramme.listeProgramme();
    }

    public void utilisateursCourant() {
        utilisateurses = this.doListerUtilisateurs();
    }

    public void doAjouterSecretaire() {
        if (secretaire != null) {
            iSecretaire.ajouterSecretaire(secretaire);
            ajouterMessageInfo("Secretaire : " + secretaire.getNomPre());
            secretaire = new Secretaire();
        }
    }

    public void doAjouterBiologiste() {
        if (biologiste != null) {
            iBiologiste.ajouterBiologiste(biologiste);
            ajouterMessageInfo("Biologiste : " + biologiste.getNomPre());
        }
    }

    public void doAjouterSousChefService() {
        if (sousChefService != null) {
            iSousChefService.ajouterSousChefService(sousChefService);
            ajouterMessageInfo("Sous Chef Service : " + sousChefService.getNomPre());
        }
    }

    public void doAjouterChefService() {
        if (chefService != null) {
            iChefService.ajouterChefService(chefService);
            ajouterMessageInfo("Chef Service : " + chefService.getNomPre());
        }
    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nouveau Utilisateur Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;

        cs = iProgramme.rechercherUtilisateur(pass);
        if (cs != null) {
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentification Réuissite", cs.getNomPre());
        } else {

            loggedIn = false;

            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Echeck D'Authentification", "Mot De Passe Invalide");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);

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

    public void setiSecretaire(ISecretaire iSecretaire) {
        this.iSecretaire = iSecretaire;
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

    public List<Utilisateurs> getUtilisateurses() {
        return utilisateurses;
    }

    public void setUtilisateurses(List<Utilisateurs> utilisateurses) {
        this.utilisateurses = utilisateurses;
    }

    public Secretaire getCs() {
        return cs;
    }

    public void setCs(Secretaire cs) {
        this.cs = cs;
    }

 

    public Biologiste getBiologiste() {
        return biologiste;
    }

    public void setBiologiste(Biologiste biologiste) {
        this.biologiste = biologiste;
    }

    public ChefService getChefService() {
        return chefService;
    }

    public void setChefService(ChefService chefService) {
        this.chefService = chefService;
    }

    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public SousChefService getSousChefService() {
        return sousChefService;
    }

    public void setSousChefService(SousChefService sousChefService) {
        this.sousChefService = sousChefService;
    }
}
