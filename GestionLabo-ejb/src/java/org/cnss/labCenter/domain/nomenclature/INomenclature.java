/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.nomenclature;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.Nomenclature;

/**
 *
 * @author Administrateur
 */
@Local
public interface INomenclature {

    public void ajouterNomenclature(Nomenclature nomenclature);

    public void modifierNomenclature(Nomenclature nomenclature);

    public Nomenclature NomenclatureConverter(int number);

    public List<Nomenclature> listeNomenclature();
}
