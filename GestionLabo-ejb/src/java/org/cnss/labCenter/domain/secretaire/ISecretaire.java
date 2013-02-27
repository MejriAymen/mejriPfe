/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.secretaire;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.Secretaire;

/**
 *
 * @author Administrateur
 */
@Local
public interface ISecretaire {

    public void ajouterSecretaire(Secretaire u);

    public List<Secretaire> listeSecretaire();
}
