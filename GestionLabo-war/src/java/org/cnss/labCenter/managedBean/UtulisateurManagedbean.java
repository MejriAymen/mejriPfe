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
import org.cnss.labCenter.domain.Utilisateur.IUtilisateur;
import org.cnss.labCenter.entities.Utilisateurs;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class UtulisateurManagedbean implements Serializable {

    private Utilisateurs utulisateur;
    private Utilisateurs cs;
    private List<Utilisateurs> utilisateurses;
    private String pass;
    @EJB
    IUtilisateur iProgramme;

    public UtulisateurManagedbean() {
        cs = new Utilisateurs();
        utulisateur = new Utilisateurs();
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

    public void doAjouterUtulisateur() {
        if (utulisateur != null) {
            iProgramme.ajouterProgramme(utulisateur);
            ajouterMessageInfo("Nom : " + utulisateur.getNomPre());
            utulisateur = new Utilisateurs();
        }

    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Programme Ajouter", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        cs = iProgramme.rechercherUtilisateur(pass);
        if ( cs!= null) {
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentification Réussie", cs.getNomPre());
        } else {

            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec D'Authentification", "Mot De Passe Invalide");
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

    public Utilisateurs getUtulisateur() {
        return utulisateur;
    }

    public void setUtulisateur(Utilisateurs utulisateur) {
        this.utulisateur = utulisateur;
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

    public Utilisateurs getCs() {
        return cs;
    }

    public void setCs(Utilisateurs cs) {
        this.cs = cs;
    }
}
