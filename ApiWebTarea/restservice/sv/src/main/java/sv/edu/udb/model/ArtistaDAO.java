package sv.edu.udb.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArtistaDAO extends AppConnection {

    /**
     * Inserta un nuevo artista en la tabla artistas.
     * 
     * @param artista El objeto Artista a insertar.
     * @throws SQLException
     */
    public void insert(Artista artista) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("INSERT INTO artistas (nombre_artista, descripcion) VALUES (?, ?)");
        pstmt.setString(1, artista.getNombreArtista());
        pstmt.setString(2, artista.getDescripcion());
        pstmt.execute();
        close();
    }

    /**
     * Actualiza todos los campos de un artista en la tabla artistas usando su ID.
     * 
     * @param artista El objeto Artista con los datos actualizados.
     * @throws SQLException
     */
    public void update(Artista artista) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE artistas SET nombre_artista = ?, descripcion = ? WHERE id_artista = ?");
        pstmt.setString(1, artista.getNombreArtista());
        pstmt.setString(2, artista.getDescripcion());
        pstmt.setInt(3, artista.getIdArtista());
        pstmt.execute();
        close();
    }

    /**
     * Elimina un artista de la tabla artistas por su ID.
     * 
     * @param id El ID del artista a eliminar.
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM artistas WHERE id_artista = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    /**
     * Obtiene una lista de todos los artistas en la tabla artistas.
     * 
     * @return Una lista de objetos Artista.
     * @throws SQLException
     */
    public ArrayList<Artista> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT id_artista, nombre_artista, descripcion FROM artistas");
        ArrayList<Artista> artistas = new ArrayList<>();

        while (resultSet.next()) {
            Artista tmp = new Artista();
            tmp.setIdArtista(resultSet.getInt(1));
            tmp.setNombreArtista(resultSet.getString(2));
            tmp.setDescripcion(resultSet.getString(3));

            artistas.add(tmp);
        }

        close();

        return artistas;
    }

    /**
     * Busca un artista por su ID y lo devuelve.
     * 
     * @param id El ID del artista a buscar.
     * @return El objeto Artista encontrado o null si no se encuentra.
     * @throws SQLException
     */
    public Artista findById(int id) throws SQLException {
        Artista artista = null;

        connect();
        pstmt = conn.prepareStatement("SELECT id_artista, nombre_artista, descripcion FROM artistas WHERE id_artista = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            artista = new Artista();
            artista.setIdArtista(resultSet.getInt(1));
            artista.setNombreArtista(resultSet.getString(2));
            artista.setDescripcion(resultSet.getString(3));
        }

        close();

        return artista;
    }
}
