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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Mejri Aymen
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateurs implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idutilisateur;
    private String nomPre;
    private String code;
    private String motPass;

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
