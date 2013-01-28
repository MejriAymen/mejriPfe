/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mejri Aymen
 */
@Entity
public class Antibiotique implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nomAntibiotique;
    private String abreviation;

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAntibiotique() {
        return nomAntibiotique;
    }

    public void setNomAntibiotique(String nomAntibiotique) {
        this.nomAntibiotique = nomAntibiotique;
    }
}
