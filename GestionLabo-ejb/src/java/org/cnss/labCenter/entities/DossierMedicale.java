/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Administrateur
 */
@Entity
public class DossierMedicale implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idDoss;
    private String numDoss;
    private Malade malade;
    private List<Consultation> consultations;

    public DossierMedicale() {
    }

    @OneToMany(mappedBy = "dossierMedicale")
    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @OneToOne(mappedBy = "dossierMedicale", cascade = CascadeType.ALL)
    public Malade getMalade() {
        return malade;
    }

    public void MaladeAdddossierMedical() {
        malade.setDossierMedicale(this);
    }

    public void setMalade(Malade malade) {
        this.malade = malade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdDoss() {
        return idDoss;
    }

    public void setIdDoss(int idDoss) {
        this.idDoss = idDoss;
    }

    public String getNumDoss() {
        return numDoss;
    }

    public void setNumDoss(String numDoss) {
        this.numDoss = numDoss;
    }
}
