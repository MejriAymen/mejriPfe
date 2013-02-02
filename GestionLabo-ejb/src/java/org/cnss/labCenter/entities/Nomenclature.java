/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mejri Aymen
 */
@Entity
public class Nomenclature implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idNomenc;
    private String analyse;
    private String abreviation;
    private float valuerB;
    private float valeurAPB;
    private String code;
    private String typePre;
    private Date remiseR;
    private List<Visite> visites;
    private ValeursUsuelles valeursUsuelles;
    private Departement departement;

    public Nomenclature() {
        visites = new ArrayList<Visite>();

    }

    @ManyToMany(mappedBy = "nomenclatures", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "Nomenclature_Visite",
    joinColumns =
    @JoinColumn(name = "NomenclatureId"),
    inverseJoinColumns =
    @JoinColumn(name = "VisiteId"))
    public List<Visite> getVisites() {
        return visites;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public ValeursUsuelles getValeursUsuelles() {
        return valeursUsuelles;
    }

    public void setValeursUsuelles(ValeursUsuelles valeursUsuelles) {
        this.valeursUsuelles = valeursUsuelles;
    }

    public void setVisites(List<Visite> visites) {
        this.visites = visites;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getRemiseR() {
        return remiseR;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdNomenc() {
        return idNomenc;
    }

    public void setIdNomenc(int idNomenc) {
        this.idNomenc = idNomenc;
    }

    public void setRemiseR(Date remiseR) {
        this.remiseR = remiseR;
    }

    public String getTypePre() {
        return typePre;
    }

    public void setTypePre(String typePre) {
        this.typePre = typePre;
    }

    public float getValeurAPB() {
        return valeurAPB;
    }

    public void setValeurAPB(float valeurAPB) {
        this.valeurAPB = valeurAPB;
    }

    public float getValuerB() {
        return valuerB;
    }

    public void setValuerB(float valuerB) {
        this.valuerB = valuerB;
    }

    @ManyToOne
    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
