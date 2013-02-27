/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.Departement;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.Departement;

/**
 *
 * @author Administrateur
 */
@Local
public interface IDepartement {

    public void ajouterService(Departement service);

    public void modifierService(Departement service);

    public void supprimerService(Departement service);

    public List<Departement> listerService();

    public Departement serviceConverter(int i);

    public void supprimerDepartement(int nls);
}
