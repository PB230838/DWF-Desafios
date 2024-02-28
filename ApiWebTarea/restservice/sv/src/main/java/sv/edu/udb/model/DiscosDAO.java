package sv.edu.udb.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class DiscosDAO extends AppConnection {

    /**
     * Inserta un nuevo disco en la tabla discos.
     * 
     * @param disco El objeto Disco a insertar.
     * @throws SQLException
     */
    public void insert(Disco disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("INSERT INTO discos (nombre_disco, id_artista, numero_canciones, precio) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getIdArtista());
        pstmt.setInt(3, disco.getNumeroCanciones());
        pstmt.setDouble(4, disco.getPrecio());
        pstmt.execute();
        close();
    }

    /**
     * Actualiza todos los campos de un disco en la tabla discos usando su ID.
     * 
     * @param disco El objeto Disco con los datos actualizados.
     * @throws SQLException
     */
    public void update(Disco disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE discos SET nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? WHERE id_disco = ?");
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getIdArtista());
        pstmt.setInt(3, disco.getNumeroCanciones());
        pstmt.setDouble(4, disco.getPrecio());
        pstmt.setInt(5, disco.getIdDisco());
        pstmt.execute();
        close();
    }

    /**
     * Elimina un disco de la tabla discos por su ID.
     * 
     * @param id El ID del disco a eliminar.
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    /**
     * Obtiene una lista de todos los discos en la tabla discos.
     * 
     * @return Una lista de objetos Disco.
     * @throws SQLException
     */
    public ArrayList<Disco> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT id_disco, nombre_disco, id_artista, numero_canciones, precio FROM discos");
        ArrayList<Disco> discos = new ArrayList<>();

        while (resultSet.next()) {
            Disco tmp = new Disco();
            tmp.setIdDisco(resultSet.getInt(1));
            tmp.setNombreDisco(resultSet.getString(2));
            tmp.setIdArtista(resultSet.getInt(3));
            tmp.setNumeroCanciones(resultSet.getInt(4));
            tmp.setPrecio(resultSet.getDouble(5));

            discos.add(tmp);
        }

        close();

        return discos;
    }

    /**
     * Busca un disco por su ID y lo devuelve.
     * 
     * @param id El ID del disco a buscar.
     * @return El objeto Disco encontrado o null si no se encuentra.
     * @throws SQLException
     */
    public Disco findById(int id) throws SQLException {
        Disco disco = null;

        connect();
        pstmt = conn.prepareStatement("SELECT id_disco, nombre_disco, id_artista, numero_canciones, precio FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            disco = new Disco();
            disco.setIdDisco(resultSet.getInt(1));
            disco.setNombreDisco(resultSet.getString(2));
            disco.setIdArtista(resultSet.getInt(3));
            disco.setNumeroCanciones(resultSet.getInt(4));
            disco.setPrecio(resultSet.getDouble(5));
        }

        close();

        return disco;
    }
}
