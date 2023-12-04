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
import tg.univlome.epl.boutique.api.entites.Produit;

/**
 *
 * @author Caleb Lyc
 */
public class ProduitClient {

    private static final ProduitClient instance = new ProduitClient();
    private static final String URL = "http://localhost:8080/boutique2/rs";
    private static final Client client = ClientBuilder.newClient();

    private ProduitClient() {
    }

    public static ProduitClient getInstance() {
        return instance;
    }

    public List<Produit> lister() {
        Response response = client.target(URL)
                .path("produit")
                .request()
                .get();
        if (response.getStatus() == 200) {
            List<Produit> produits = response.readEntity(new GenericType<List<Produit>>() {
            });
            Collections.reverse(produits);
            return produits;
        } else {
            try {
                throw new Exception("Erreur" + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(ProduitClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Produit recuperer(long id) {
        Response response = client.target(URL)
                .path("produit/" + id)
                .request()
                .get();

        if (response.getStatus() == 200) {
            return response.readEntity(Produit.class);
        } else {
            try {
                throw new Exception("Erreur lors de la récupération : " + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(CategorieClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void ajouter(Produit p) {
        Response response = client.target(URL)
                .path("produit")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(p, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 201) {
            try {
                throw new Exception("Erreur lors de l'ajout : " + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(ProduitClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void modifier(Produit p) {
        Response response = client.target(URL)
                .path("produit")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(this, MediaType.WILDCARD_TYPE).entity(p, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            try {
                throw new Exception("Erreur lors de la modification : " + response.getStatus());
            } catch (Exception ex) {
                Logger.getLogger(ProduitClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void supprimer(int produitId) {
        Response response = client.target(URL)
                .path("produit")
                .queryParam("id", produitId)
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
