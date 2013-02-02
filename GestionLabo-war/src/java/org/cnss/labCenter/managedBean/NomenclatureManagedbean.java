package org.cnss.labCenter.managedBean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.cnss.labCenter.domain.nomenclature.INomenclature;
import org.cnss.labCenter.entities.Departement;
import org.cnss.labCenter.entities.Nomenclature;
import org.cnss.labCenter.entities.ValeursUsuelles;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Mejri Aymen
 */
@ManagedBean
@SessionScoped
public class NomenclatureManagedbean implements Serializable {

    private Nomenclature nomenclature;
    private Nomenclature SelectnomenclatureO;
    private List<Nomenclature> Selectnomenclature;
    private List<Nomenclature> nomenclatures;
    private List<Nomenclature> nomenclatureM;
    private List<Nomenclature> nomenclatureL;
    private Departement selectedDept;
    private Nomenclature nomenclatureSM;
    private Nomenclature nomenclatureSV;
    private List<Nomenclature> filterednomenclature;
    private ValeursUsuelles valeursUsuelle;
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
    @EJB
    INomenclature iNomenclature;

    public NomenclatureManagedbean() {

        nomenclatures = new ArrayList<Nomenclature>();
        nomenclatureM = new ArrayList<Nomenclature>();
        filterednomenclature = new ArrayList<Nomenclature>();
        nomenclatureL = new ArrayList<Nomenclature>();
        nomenclatureSV = new Nomenclature();
        nomenclatureSM = new Nomenclature();
        valeursUsuelle = new ValeursUsuelles();
        selectedDept = new Departement();
    }

    @PostConstruct
    public void init() {
        nomenclature = new Nomenclature();
        Selectnomenclature = new ArrayList<Nomenclature>();
        SelectnomenclatureO = new Nomenclature();
        nomenclatures = this.doListerNomenclature();
        nomenclatureM = this.doListerNomenclature();
        nomenclatureL = this.doListerNomenclature();
    }

    public void nomenclatureCourante() {
        nomenclatures = this.doListerNomenclature();
        nomenclatureM = this.doListerNomenclature();
        nomenclatureL = this.doListerNomenclature();
        nomenclatureSM = this.getNomenclatureSM();
        nomenclatureSV = this.getNomenclatureSV();
    }

    public void doAjouterNomenclature() {
        if (nomenclature != null) {
            nomenclature.setDepartement(selectedDept);

            iNomenclature.ajouterNomenclature(nomenclature);
            ajouterMessageInfo("Analyse Ajouté: ", "" + nomenclature.getAnalyse());
            nomenclature = new Nomenclature();
            selectedDept = new Departement();
        }
    }

    public void update() {
        if (nomenclatureSM != null) {
            nomenclatureSM.setValeursUsuelles(valeursUsuelle);
            iNomenclature.modifierNomenclature(nomenclatureSM);
            ajouterMessageInfo("Valeur Usulle Ajouté: ", "Analyse :" + nomenclatureSM.getAnalyse());
            valeursUsuelle = new ValeursUsuelles();
            nomenclatureSM = new Nomenclature();
        }
    }

    public void ajouterMessageInfo(String msg, String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Nomenclature> doListerNomenclature() {
        return iNomenclature.listeNomenclature();
    }

    public void doModifierNomenclature() {
        if (nomenclatureM != null) {
            for (Nomenclature a : nomenclatureM) {
                iNomenclature.modifierNomenclature(a);
            }
        }
    }

    public void coefficientCourant() {
        this.getValeursUsuelle().getCoefficient();
        this.nomenclatureSV.getValeursUsuelles().getCoefficient();
    }

    public void converstionVisualisation() {

        vHUsuelle = nomenclatureSV.getValeursUsuelles().getvHUsuelle() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vHMin = nomenclatureSV.getValeursUsuelles().getvHMin() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vHMax = nomenclatureSV.getValeursUsuelles().getvHMax() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vFUsuelle = nomenclatureSV.getValeursUsuelles().getvFUsuelle() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vFMin = nomenclatureSV.getValeursUsuelles().getvFMin() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vFMax = nomenclatureSV.getValeursUsuelles().getvFMax() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vEUsuelle = nomenclatureSV.getValeursUsuelles().getvEUsuelle() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vEMin = nomenclatureSV.getValeursUsuelles().getvEMin() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vEMax = nomenclatureSV.getValeursUsuelles().getvEMax() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vBUsuelle = nomenclatureSV.getValeursUsuelles().getvBUsuelle() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vBMin = nomenclatureSV.getValeursUsuelles().getvBMin() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vBMax = nomenclatureSV.getValeursUsuelles().getvBMax() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vNUsuelle = nomenclatureSV.getValeursUsuelles().getvNUsuelle() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vNMin = nomenclatureSV.getValeursUsuelles().getvNMin() * nomenclatureSV.getValeursUsuelles().getCoefficient();
        vNMax = nomenclatureSV.getValeursUsuelles().getvNMax() * nomenclatureSV.getValeursUsuelles().getCoefficient();
    }

    public void converstion() {
        vHUsuelle = valeursUsuelle.getvHUsuelle() * valeursUsuelle.getCoefficient();
        vHMin = valeursUsuelle.getvHMin() * valeursUsuelle.getCoefficient();
        vHMax = valeursUsuelle.getvHMax() * valeursUsuelle.getCoefficient();
        vFUsuelle = valeursUsuelle.getvFUsuelle() * valeursUsuelle.getCoefficient();
        vFMin = valeursUsuelle.getvFMin() * valeursUsuelle.getCoefficient();
        vFMax = valeursUsuelle.getvFMax() * valeursUsuelle.getCoefficient();
        vEUsuelle = valeursUsuelle.getvEUsuelle() * valeursUsuelle.getCoefficient();
        vEMin = valeursUsuelle.getvEMin() * valeursUsuelle.getCoefficient();
        vEMax = valeursUsuelle.getvEMax() * valeursUsuelle.getCoefficient();
        vBUsuelle = valeursUsuelle.getvBUsuelle() * valeursUsuelle.getCoefficient();
        vBMin = valeursUsuelle.getvBMin() * valeursUsuelle.getCoefficient();
        vBMax = valeursUsuelle.getvBMax() * valeursUsuelle.getCoefficient();
        vNUsuelle = valeursUsuelle.getvNUsuelle() * valeursUsuelle.getCoefficient();
        vNMin = valeursUsuelle.getvNMin() * valeursUsuelle.getCoefficient();
        vNMax = valeursUsuelle.getvNMax() * valeursUsuelle.getCoefficient();
    }

    public List<Nomenclature> completeNomenclature(String query) {
        List<Nomenclature> suggestions = new ArrayList<Nomenclature>();

        for (Nomenclature p : nomenclatures) {
            if (p.getAnalyse().startsWith(query)) {

                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public void handleUnselect(UnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected:" + event.getObject().toString(), null);

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Nomenclature> getSelectnomenclature() {
        return Selectnomenclature;
    }

    public void setSelectnomenclature(List<Nomenclature> Selectnomenclature) {
        this.Selectnomenclature = Selectnomenclature;
    }

    public List<Nomenclature> getFilterednomenclature() {
        return filterednomenclature;
    }

    public void setFilterednomenclature(List<Nomenclature> filterednomenclature) {
        this.filterednomenclature = filterednomenclature;
    }

    public INomenclature getiNomenclature() {
        return iNomenclature;
    }

    public void setiNomenclature(INomenclature iNomenclature) {
        this.iNomenclature = iNomenclature;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public List<Nomenclature> getNomenclatureL() {
        return nomenclatureL;
    }

    public void setNomenclatureL(List<Nomenclature> nomenclatureL) {
        this.nomenclatureL = nomenclatureL;
    }

    public List<Nomenclature> getNomenclatureM() {
        return nomenclatureM;
    }

    public void setNomenclatureM(List<Nomenclature> nomenclatureM) {
        this.nomenclatureM = nomenclatureM;
    }

    public List<Nomenclature> getNomenclatures() {
        return nomenclatures;
    }

    public void setNomenclatures(List<Nomenclature> nomenclatures) {
        this.nomenclatures = nomenclatures;
    }

    public Nomenclature getSelectnomenclatureO() {
        return SelectnomenclatureO;
    }

    public void setSelectnomenclatureO(Nomenclature SelectnomenclatureO) {
        this.SelectnomenclatureO = SelectnomenclatureO;
    }

    public Nomenclature getNomenclatureSM() {
        return nomenclatureSM;
    }

    public void setNomenclatureSM(Nomenclature nomenclatureSM) {
        this.nomenclatureSM = nomenclatureSM;
    }

    public ValeursUsuelles getValeursUsuelle() {
        return valeursUsuelle;
    }

    public void setValeursUsuelle(ValeursUsuelles valeursUsuelle) {
        this.valeursUsuelle = valeursUsuelle;
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

    public Nomenclature getNomenclatureSV() {
        return nomenclatureSV;
    }

    public void setNomenclatureSV(Nomenclature nomenclatureSV) {
        this.nomenclatureSV = nomenclatureSV;
    }

    public Departement getSelectedDept() {
        return selectedDept;
    }

    public void setSelectedDept(Departement selectedDept) {
        this.selectedDept = selectedDept;
    }
}
