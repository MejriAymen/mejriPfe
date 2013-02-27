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
import javax.persistence.OneToMany;

/**
 *
 * @author Mejri Aymen
 */
@Entity
public class Specialite implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idSpecialite;
    private String nomSpecialite;
    private String libSpecialite;
    private List<Medecin> medecins;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    @OneToMany(mappedBy = "specialite")
    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

   
    public String getLibSpecialite() {
        return libSpecialite;
    }

    public void setLibSpecialite(String libSpecialite) {
        this.libSpecialite = libSpecialite;
    }

    public String getNomSpecialite() {
        return nomSpecialite;
    }

    public void setNomSpecialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }
}
