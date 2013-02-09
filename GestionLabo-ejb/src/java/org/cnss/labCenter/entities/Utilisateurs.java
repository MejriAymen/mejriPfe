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
public class Utilisateurs implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idutilisateur;
    private String nomPre;
    private String code;
    private String motPass;
    private List<Visite> visites;

    public Utilisateurs() {
        visites = new ArrayList<Visite>();
    }

    
    @OneToMany(mappedBy="utilisateur")
    public List<Visite> getVisites() {
        return visites;
    }

    public void setVisites(List<Visite> visites) {
        this.visites = visites;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getMotPass() {
        return motPass;
    }

    public void setMotPass(String motPass) {
        this.motPass = motPass;
    }

    public String getNomPre() {
        return nomPre;
    }

    public void setNomPre(String nomPre) {
        this.nomPre = nomPre;
    }
}
