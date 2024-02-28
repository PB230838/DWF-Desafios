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
import sv.edu.udb.model.Disco;
import sv.edu.udb.model.DiscosDAO;

@Path("discos")
public class DiscosRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscos() {
        try {
            DiscosDAO discosDAO = new DiscosDAO();
            List<Disco> discos = discosDAO.findAll();
            return Response.status(200).entity(discos).build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al obtener discos: " + ex.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscoById(@PathParam("id") int id) {
        try {
            DiscosDAO discosDAO = new DiscosDAO();
            Disco disco = discosDAO.findById(id);
            if (disco == null) {
                return Response.status(404).entity("Disco no encontrado").build();
            }
            return Response.status(200).entity(disco).build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al obtener disco: " + ex.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDisco(Disco disco) {
        try {
            DiscosDAO discosDAO = new DiscosDAO();
            discosDAO.insert(disco);
            return Response.status(201).entity("Disco creado exitosamente").build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al crear disco: " + ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisco(@PathParam("id") int id, Disco disco) {
        try {
            disco.setIdDisco(id);
            DiscosDAO discosDAO = new DiscosDAO();
            discosDAO.update(disco);
            return Response.status(200).entity("Disco actualizado exitosamente").build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al actualizar disco: " + ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteDisco(@PathParam("id") int id) {
        try {
            DiscosDAO discosDAO = new DiscosDAO();
            discosDAO.delete(id);
            return Response.status(200).entity("Disco eliminado exitosamente").build();
        } catch (SQLException ex) {
            return Response.status(500).entity("Error al eliminar disco: " + ex.getMessage()).build();
        }
    }
}
