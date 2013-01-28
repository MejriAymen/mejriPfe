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
public class Assure implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idAssure;
    private String matriculeAssure;
    List<Malade> malades;

    public Assure() {
        malades = new ArrayList<Malade>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdAssure() {
        return idAssure;
    }

    @OneToMany(mappedBy = "assure")
    public List<Malade> getMalades() {
        return malades;
    }

    public void setMalades(List<Malade> malades) {
        this.malades = malades;
    }

    public void setIdAssure(int idAssure) {
        this.idAssure = idAssure;
    }

    public String getMatriculeAssure() {
        return matriculeAssure;
    }

    public void setMatriculeAssure(String matriculeAssure) {
        this.matriculeAssure = matriculeAssure;
    }
}
