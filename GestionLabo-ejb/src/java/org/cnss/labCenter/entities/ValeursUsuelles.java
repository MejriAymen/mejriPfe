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
import javax.persistence.OneToOne;

/**
 *
 * @author Administrateur
 */
@Entity
public class ValeursUsuelles implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idValeurUsuelle;
    private float vHUsuelle;
    private float vHMin;
    private float vHMax;
    private float vFUsuelle;
    private float vFMin;
    private float vFMax;
    private float vEUsuelle;
    private float vEMin;
    private float vEMax;
    private float vBUsuelle;
    private float vBMin;
    private float vBMax;
    private float vNUsuelle;
    private float vNMin;
    private float vNMax;
    private float coefficient;
    private String unite1;
    private String unite2;
    private Nomenclature nomenclature;

    public ValeursUsuelles() {
    }

    @OneToOne(mappedBy = "valeursUsuelles")
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdValeurUsuelle() {
        return idValeurUsuelle;
    }

    public void setIdValeurUsuelle(int idValeurUsuelle) {
        this.idValeurUsuelle = idValeurUsuelle;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public String getUnite1() {
        return unite1;
    }

    public void setUnite1(String unite1) {
        this.unite1 = unite1;
    }

    public String getUnite2() {
        return unite2;
    }

    public void setUnite2(String unite2) {
        this.unite2 = unite2;
    }

    public float getvBMax() {
        return vBMax;
    }

    public void setvBMax(float vBMax) {
        this.vBMax = vBMax;
    }

    public float getvBMin() {
        return vBMin;
    }

    public void setvBMin(float vBMin) {
        this.vBMin = vBMin;
    }

    public float getvBUsuelle() {
        return vBUsuelle;
    }

    public void setvBUsuelle(float vBUsuelle) {
        this.vBUsuelle = vBUsuelle;
    }

    public float getvEMax() {
        return vEMax;
    }

    public void setvEMax(float vEMax) {
        this.vEMax = vEMax;
    }

    public float getvEMin() {
        return vEMin;
    }

    public void setvEMin(float vEMin) {
        this.vEMin = vEMin;
    }

    public float getvEUsuelle() {
        return vEUsuelle;
    }

    public void setvEUsuelle(float vEUsuelle) {
        this.vEUsuelle = vEUsuelle;
    }

    public float getvFMax() {
        return vFMax;
    }

    public void setvFMax(float vFMax) {
        this.vFMax = vFMax;
    }

    public float getvFMin() {
        return vFMin;
    }

    public void setvFMin(float vFMin) {
        this.vFMin = vFMin;
    }

    public float getvFUsuelle() {
        return vFUsuelle;
    }

    public void setvFUsuelle(float vFUsuelle) {
        this.vFUsuelle = vFUsuelle;
    }

    public float getvHMax() {
        return vHMax;
    }

    public void setvHMax(float vHMax) {
        this.vHMax = vHMax;
    }

    public float getvHMin() {
        return vHMin;
    }

    public void setvHMin(float vHMin) {
        this.vHMin = vHMin;
    }

    public float getvHUsuelle() {
        return vHUsuelle;
    }

    public void setvHUsuelle(float vHUsuelle) {
        this.vHUsuelle = vHUsuelle;
    }

    public float getvNMax() {
        return vNMax;
    }

    public void setvNMax(float vNMax) {
        this.vNMax = vNMax;
    }

    public float getvNMin() {
        return vNMin;
    }

    public void setvNMin(float vNMin) {
        this.vNMin = vNMin;
    }

    public float getvNUsuelle() {
        return vNUsuelle;
    }

    public void setvNUsuelle(float vNUsuelle) {
        this.vNUsuelle = vNUsuelle;
    }
}
