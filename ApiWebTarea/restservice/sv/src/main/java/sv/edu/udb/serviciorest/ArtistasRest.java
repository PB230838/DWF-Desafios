package sv.edu.udb.serviciorest;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.udb.model.Artista;
import sv.edu.udb.model.ArtistaDAO;

@Path("artistas")
public class ArtistasRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistas() {
        try {
            ArtistaDAO artistasDAO = new ArtistaDAO();
            List<Artista> artistas = artistasDAO.findAll();
            return Response.status(200).entity(artistas).build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al obtener artistas: " + ex.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistaById(@PathParam("id") int id) {
        try {
            ArtistaDAO artistasDAO = new ArtistaDAO();
            Artista artista = artistasDAO.findById(id);
            if (artista == null) {
                return Response.status(404).entity("Artista no encontrado").build();
            }
            return Response.status(200).entity(artista).build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al obtener artista: " + ex.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addArtista(Artista artista) {
        try {
            ArtistaDAO artistasDAO = new ArtistaDAO();
            artistasDAO.insert(artista);
            return Response.status(201).entity("Artista creado exitosamente").build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al crear artista: " + ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateArtista(@PathParam("id") int id, Artista artista) {
        try {
            artista.setIdArtista(id);
            ArtistaDAO artistasDAO = new ArtistaDAO();
            artistasDAO.update(artista);
            return Response.status(200).entity("Artista actualizado exitosamente").build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al actualizar artista: " + ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteArtista(@PathParam("id") int id) {
        try {
            ArtistaDAO artistasDAO = new ArtistaDAO();
            artistasDAO.delete(id);
            return Response.status(200).entity("Artista eliminado exitosamente").build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al eliminar artista: " + ex.getMessage()).build();
        }
    }
}
