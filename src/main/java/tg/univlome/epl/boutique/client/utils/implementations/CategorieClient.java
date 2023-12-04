/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.client.utils.implementations;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tg.univlome.epl.boutique.api.entites.Categorie;
import tg.univlome.epl.boutique.client.utils.interfaces.ClientInterface;

/**
 *
 * @author Caleb Lyc
 */
public class CategorieClient implements ClientInterface<Categorie>{
    private static final CategorieClient instance = new CategorieClient();
    private static final String URL = "http://localhost:8080/boutique2/rs";
    private static final Client client = ClientBuilder.newClient();
    
    private CategorieClient(){
    }

    public static CategorieClient getInstance() {
        return instance;
    }
    
    @Override
    public List<Categorie> lister(){
        Response response = client.target(URL)
                .path("categorie")
                .request()
                .get();
        if(response.getStatus() == 200){
            List<Categorie> categories = response.readEntity(new GenericType<List<Categorie>>() {});
            Collections.reverse(categories);
            return categories;
        }else{
            try {
                throw new Exception("Erreur" + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(CategorieClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @Override
    public Categorie recuperer(long id) {
        Response response = client.target(URL)
                .path("categorie/" + id)
                .request()
                .get();

        if (response.getStatus() == 200) {
            return response.readEntity(Categorie.class);
        } else {
            try {
                throw new Exception("Erreur lors de la récupération : " + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(CategorieClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @Override
    public void ajouter(Categorie c) {
        Response response = client.target(URL)
                .path("categorie")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(c, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 201) {
            try {
                throw new Exception("Erreur lors de l'ajout : " + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(ProduitClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void modifier(Categorie c) {
        Response response = client.target(URL)
                .path("categorie")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(this, MediaType.WILDCARD_TYPE).entity(c, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            try {
                throw new Exception("Erreur lors de la modification : " + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(ProduitClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void supprimer(long categorieId) {
        Response response = client.target(URL)
                .path("categorie")
                .queryParam("id", categorieId)
                .request()
                .delete();

        if (response.getStatus() != 204) {
            try {
                throw new Exception("Erreur lors de la suppression : " + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(ProduitClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
