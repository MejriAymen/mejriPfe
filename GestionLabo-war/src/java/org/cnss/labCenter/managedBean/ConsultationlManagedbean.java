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
import org.cnss.labCenter.domain.consultation.IConsultation;
import org.cnss.labCenter.entities.Consultation;
import org.cnss.labCenter.entities.DossierMedicale;
import org.cnss.labCenter.entities.Medecin;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class ConsultationlManagedbean implements Serializable {

    private Consultation consultation;
    private DossierMedicale dossierMedicaleS;
    private Medecin seledtedMedecin;
    private List<Consultation> consultations;
    @EJB
    IConsultation iConsultation;

    public ConsultationlManagedbean() {
        consultation = new Consultation();
        dossierMedicaleS = new DossierMedicale();
        seledtedMedecin = new Medecin();
        consultations = new ArrayList<Consultation>();
    }

    @PostConstruct
    public void init() {
        consultations = doListerConsultation();
    }

    public void doAjouterConsultation() {

        if (consultation != null) {
            consultation.setDossierMedicale(dossierMedicaleS);
            consultation.setMedecin(seledtedMedecin);
            iConsultation.ajouterConsultation(consultation);
            consultations = doListerConsultation();
            consultation = new Consultation();
            dossierMedicaleS = new DossierMedicale();
            seledtedMedecin = new Medecin();
            ajouterMessageInfo("");
        }


    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consultation Ajouté", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Consultation> doListerConsultation() {
        return iConsultation.listerConsultation();
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public DossierMedicale getDossierMedicaleS() {
        return dossierMedicaleS;
    }

    public void setDossierMedicaleS(DossierMedicale dossierMedicaleS) {
        this.dossierMedicaleS = dossierMedicaleS;
    }

    public IConsultation getiConsultation() {
        return iConsultation;
    }

    public void setiConsultation(IConsultation iConsultation) {
        this.iConsultation = iConsultation;
    }

    public Medecin getSeledtedMedecin() {
        return seledtedMedecin;
    }

    public void setSeledtedMedecin(Medecin seledtedMedecin) {
        this.seledtedMedecin = seledtedMedecin;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
