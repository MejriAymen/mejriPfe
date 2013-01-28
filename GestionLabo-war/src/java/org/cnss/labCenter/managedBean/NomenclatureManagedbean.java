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
import org.cnss.labCenter.entities.Nomenclature;
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
    private List<Nomenclature> filterednomenclature;
    @EJB
    INomenclature iNomenclature;

    public NomenclatureManagedbean() {
        nomenclatures = new ArrayList<Nomenclature>();
        nomenclatureM = new ArrayList<Nomenclature>();
        filterednomenclature = new ArrayList<Nomenclature>();
        nomenclatureL = new ArrayList<Nomenclature>();
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
    }

    public void doAjouterNomenclature() {
        if (nomenclature != null) {
            iNomenclature.ajouterNomenclature(nomenclature);
            ajouterMessageInfo("Analyse : " + nomenclature.getAnalyse());
            nomenclature = new Nomenclature();

        }

    }

    public void ajouterMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Antibiotique Ajouter", summary);
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
}
