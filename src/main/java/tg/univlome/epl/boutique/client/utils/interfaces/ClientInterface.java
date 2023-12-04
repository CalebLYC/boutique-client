/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tg.univlome.epl.boutique.client.utils.interfaces;

import java.util.List;

/**
 *
 * @author Caleb Lyc
 * @param <T>
 */
public interface ClientInterface<T> {
    public List<T> lister();
    public void ajouter(T t);
    public T recuperer(long id);
    public void modifier(T t);
    public void supprimer(long id);
}
