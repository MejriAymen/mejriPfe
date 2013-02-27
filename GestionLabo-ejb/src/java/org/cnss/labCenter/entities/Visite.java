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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private Nomenclature nomenclature;
    private Resultat resultat;
    private Consultation consultation;
    private Secretaire secretaire;

    public Visite() {
    }

    @OneToOne
    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @ManyToOne
    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdVisite() {
        return idVisite;
    }

    public void setIdVisite(int idVisite) {
        this.idVisite = idVisite;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "Visite_Nomenclature",
    joinColumns =
    @JoinColumn(name = "VisiteId"),
    inverseJoinColumns =
    @JoinColumn(name = "NomenclatureId"))
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Visite_Resultat",
    joinColumns =
    @JoinColumn(name = "VisiteId"),
    inverseJoinColumns =
    @JoinColumn(name = "ResultatId"))
    public Resultat getResultat() {
        return resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
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
