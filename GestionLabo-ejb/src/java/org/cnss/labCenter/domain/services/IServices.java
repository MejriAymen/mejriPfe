/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.services;

import javax.ejb.Local;
import org.cnss.labCenter.entities.Nomenclature;
import org.cnss.labCenter.entities.Visite;


/**
 *
 * @author Administrateur
 */
@Local
public interface IServices {

    public void affectationVisteNomenclature(Visite v,Nomenclature n);
 
}
