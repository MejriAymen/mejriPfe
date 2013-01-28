/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Convention implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idConvention;
    private String organisme;
    private float prixB;
    private float prixAPB;
    private String code;
    private String telFax;
    private String adresse;
    private List<Malade> malades;

    public Convention() {
        malades = new ArrayList<Malade>();
    }

    
    @OneToMany(mappedBy = "convention")
    public List<Malade> getMalades() {
        return malades;
    }

    public void setMalades(List<Malade> malades) {
        this.malades = malades;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdConvention() {
        return idConvention;
    }

    public void setIdConvention(int idConvention) {
        this.idConvention = idConvention;
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

    public String getTelFax() {
        return telFax;
    }

    public void setTelFax(String telFax) {
        this.telFax = telFax;
    }
}
