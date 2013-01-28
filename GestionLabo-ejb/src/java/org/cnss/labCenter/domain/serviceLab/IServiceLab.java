/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.serviceLab;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.Service;

/**
 *
 * @author Administrateur
 */
@Local
public interface IServiceLab {

    public void ajouterService(Service service);

    public void modifierService(Service service);

    public void supprimerService(Service service);

    public List<Service> listerService();

    public Service serviceConverter(int i);
}
