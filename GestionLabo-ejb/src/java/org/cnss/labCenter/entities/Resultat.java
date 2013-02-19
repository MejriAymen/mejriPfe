/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Administrateur
 */
@Entity
public class Resultat implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idResultat;
    private float res;
    private float resCoi;
    private List<Visite> visites;

    private ResultatECBU resultatECBU;
    
    public Resultat() {
        visites = new ArrayList<Visite>();
    }

    @OneToOne(mappedBy = "resultat",cascade= CascadeType.REMOVE)
    public ResultatECBU getResultatECBU() {
        return resultatECBU;
    }

    public void setResultatECBU(ResultatECBU resultatECBU) {
        this.resultatECBU = resultatECBU;
    }

   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdResultat() {
        return idResultat;
    }

    public void setIdResultat(int idResultat) {
        this.idResultat = idResultat;
    }

    @OneToMany(mappedBy = "resultat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Resultat_Visite",
    joinColumns =
    @JoinColumn(name = "ResultatId"),
    inverseJoinColumns =
    @JoinColumn(name = "VisiteId"))
    public List<Visite> getVisites() {
        return visites;
    }

    public void setVisites(List<Visite> visites) {
        this.visites = visites;
    }

    public float getRes() {
        return res;
    }

    public void setRes(float res) {
        this.res = res;
    }

    public float getResCoi() {
        return resCoi;
    }

    public void setResCoi(float resCoi) {
        this.resCoi = resCoi;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resultat other = (Resultat) obj;
        if (this.idResultat != other.idResultat) {
            return false;
        }
        if (Float.floatToIntBits(this.res) != Float.floatToIntBits(other.res)) {
            return false;
        }
        if (Float.floatToIntBits(this.resCoi) != Float.floatToIntBits(other.resCoi)) {
            return false;
        }
        if (this.visites != other.visites && (this.visites == null || !this.visites.equals(other.visites))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idResultat;
        hash = 83 * hash + Float.floatToIntBits(this.res);
        hash = 83 * hash + Float.floatToIntBits(this.resCoi);
        hash = 83 * hash + (this.visites != null ? this.visites.hashCode() : 0);
        return hash;
    }
}
