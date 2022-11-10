package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.sql.*;
import java.util.ArrayList;

public class DaoArbitros extends BaseDao {

    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> listaArbitros = new ArrayList<>();
        String sql = "select * from arbitro";
        /*
        Inserte su código aquí
        */
        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt("idArbitro"));
                arbitro.setNombre(rs.getString("nombre"));
                arbitro.setPais(rs.getString("pais"));
                listaArbitros.add(arbitro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaArbitros;
    }




    public void crearArbitro(Arbitro arbitro) throws SQLException {
        String sql = "insert into arbitro (idArbitro,nombre,pais) values (?,?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, arbitro.getIdArbitro());
            pstmt.setString(2,arbitro.getNombre());
            pstmt.setString(3,arbitro.getPais());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(int id) {
        /*
        Inserte su código aquí
        */
    }
}
