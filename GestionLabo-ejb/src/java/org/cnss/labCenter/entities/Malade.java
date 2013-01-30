/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Mejri Aymen
 */
@Entity
public class Malade implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idMalade;
    private String typeMalade;
    private String nompre;
    private String dateNaissance;
    private String sexeMalade;
    private String adresse;
    private String ville;
    private String tel;
    private Assure assure;
    private Convention convention;
    private DossierMedicale dossierMedicale;

    public Malade() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    public DossierMedicale getDossierMedicale() {

        return dossierMedicale;
    }

    public void dossierMedAddMalade() {
        dossierMedicale.setMalade(this);
    }

    public void setDossierMedicale(DossierMedicale dossierMedicale) {
        this.dossierMedicale = dossierMedicale;
    }

    @ManyToOne
    public Assure getAssure() {
        return assure;
    }

    public void setAssure(Assure assure) {
        this.assure = assure;
    }

    @ManyToOne
    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdMalade() {
        return idMalade;
    }

    public void setIdMalade(int idMalade) {
        this.idMalade = idMalade;
    }

    public String getNompre() {
        return nompre;
    }

    public void setNompre(String nompre) {
        this.nompre = nompre;
    }

    public String getSexeMalade() {
        return sexeMalade;
    }

    public void setSexeMalade(String sexeMalade) {
        this.sexeMalade = sexeMalade;
    }

    public String getTypeMalade() {
        return typeMalade;
    }

    public void setTypeMalade(String typeMalade) {
        this.typeMalade = typeMalade;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
