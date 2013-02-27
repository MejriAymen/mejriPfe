/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Mejri Aymen
 */
@Entity
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idMed;
    private String codeMed;
    private String nompre;
    private boolean valable;
    private String telephone;
    private Specialite specialite;
    private List<Consultation> consultations;

    public Medecin() {
     
    }

    @OneToMany(mappedBy = "medecin")
    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

   

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdMed() {
        return idMed;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    @ManyToOne
    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public boolean isValable() {
        return valable;
    }

    public void setValable(boolean valable) {
        this.valable = valable;
    }

    public String getCodeMed() {
        return codeMed;
    }

    public void setCodeMed(String codeMed) {
        this.codeMed = codeMed;
    }

    public String getNompre() {
        return nompre;
    }

    public void setNompre(String nompre) {
        this.nompre = nompre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
