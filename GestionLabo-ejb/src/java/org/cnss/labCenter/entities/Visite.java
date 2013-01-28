/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mejri Aymen
 */
@Entity
public class Visite implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idVisite;
    private String numVis;
    private float totB;
    private float prixB;
    private float prixAPB;
    private float totAPB;
    private float prixTot;
    private float MaPayer;
    private Medecin medecin;
    private Utilisateurs utilisateur;
    private DossierMedicale dossierMedicale;
    private List<Nomenclature> nomenclatures;

    public Visite() {
        nomenclatures = new ArrayList<Nomenclature>();
    }

   
    @ManyToOne
    public Utilisateurs getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateurs utilisateur) {
        this.utilisateur = utilisateur;
    }

    @ManyToOne
    public DossierMedicale getDossierMedicale() {
        return dossierMedicale;
    }

    public void setDossierMedicale(DossierMedicale dossierMedicale) {
        this.dossierMedicale = dossierMedicale;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdVisite() {
        return idVisite;
    }

    public void setIdVisite(int idVisite) {
        this.idVisite = idVisite;
    }

    @ManyToOne
    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Visite_Nomenclature",
    joinColumns =
    @JoinColumn(name = "VisiteId"),
    inverseJoinColumns =
    @JoinColumn(name = "NomenclatureId"))
    public List<Nomenclature> getNomenclatures() {
        return nomenclatures;
    }

    public void setNomenclatures(List<Nomenclature> nomenclatures) {
        this.nomenclatures = nomenclatures;
    }

    public String getNumVis() {
        return numVis;
    }

    public void setNumVis(String numVis) {
        this.numVis = numVis;
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

    public float getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(float prixTot) {
        this.prixTot = prixTot;
    }

    public float getTotAPB() {
        return totAPB;
    }

    public void setTotAPB(float totAPB) {
        this.totAPB = totAPB;
    }

    public float getTotB() {
        return totB;
    }

    public void setTotB(float totB) {
        this.totB = totB;
    }

    public float getMaPayer() {
        return MaPayer;
    }

    public void setMaPayer(float MaPayer) {
        this.MaPayer = MaPayer;
    }
}
