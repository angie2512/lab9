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
        String sql = "select * from arbitro where pais like ?";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt1 = conn.prepareStatement(sql);){
            pstmt1.setString(1,"%"+pais+"%");
            try(ResultSet rs = pstmt1.executeQuery();){
                while(rs.next()){
                    Arbitro arbitros1 = new Arbitro();
                    arbitros1.setIdArbitro(rs.getInt("idArbitros"));
                    arbitros1.setNombre(rs.getString("nombre"));
                    arbitros1.setPais(rs.getString("pais"));
                    arbitros.add(arbitros1);
                }}
        }catch (SQLException e){
            e.printStackTrace();
        }

        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {


        String sql = "select * from arbitro where nombre like ?";
        ArrayList<Arbitro> listaarbitros = new ArrayList<>();
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt1 = conn.prepareStatement(sql);){
            pstmt1.setString(1,"%"+nombre+"%");
            try(ResultSet rs = pstmt1.executeQuery();){
                while(rs.next()){
                    Arbitro arbitros = new Arbitro();
                    arbitros.setIdArbitro(rs.getInt("idArbitros"));
                    arbitros.setNombre(rs.getString("nombre"));
                    arbitros.setPais(rs.getString("pais"));
                    listaarbitros.add(arbitros);
                }}
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaarbitros;
    }



    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(int id) {

        String sql = "UPDATE partido SET arbitro = null WHERE arbitro = ?";
        String sql1 = "delete from arbitro where idArbitro= ?";


        try (Connection connb = this.getConnection();
             PreparedStatement pstmtb = connb.prepareStatement(sql);) {

            pstmtb.setInt(1,id);
            pstmtb.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connd = this.getConnection();
             PreparedStatement pstmtd = connd.prepareStatement(sql1)) {

            pstmtd.setInt(1,id);
            pstmtd.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
