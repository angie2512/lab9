package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos extends BaseDao{
    public ArrayList<Partido> listaDePartidos() {


        ArrayList<Partido> lista = new ArrayList<>();


        String sql = "select p.numeroJornada, p.fecha, s.nombre as SeleccionLocal, s2.nombre as SeleccionVisitante, e.nombre as NombreEstadio, a.nombre as nombreArbitro  \n" +
                "from partido p, seleccion s, seleccion s2, estadio e, arbitro a\n" +
                "where s.idseleccion = p.seleccionLocal\n" +
                "and s2.idseleccion = p.seleccionVisitante \n" +
                "and e.idEstadio = s.estadio_idEstadio \n" +
                "and a.idArbitro = p.arbitro";
        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Partido partido = new Partido();
                partido.setNumeroJornada(rs.getInt(1));
                partido.setFecha(rs.getString(2));

                Seleccion seleccion_local = new Seleccion();

                seleccion_local.setNombre(rs.getString(3));

                Seleccion seleccion_visitante = new Seleccion();

                seleccion_visitante.setNombre(rs.getString(4));
                partido.setSeleccionVisitante(seleccion_visitante);

                Estadio estadio = new Estadio();

                estadio.setNombre(rs.getString(5));
                seleccion_local.setEstadio(estadio);
                partido.setSeleccionLocal(seleccion_local);


                Arbitro arbitro = new Arbitro();

                arbitro.setNombre(rs.getString(6));
                partido.setArbitro(arbitro);

                lista.add(partido);

            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void crearPartido(Partido partido){


        String sql = "INSERT INTO partido (seleccionLocal, seleccionVisitante, arbitro,fecha,numeroJornada) VALUES (?,?,?,?,?)";

        try (Connection connection2 = this.getConnection();
             PreparedStatement pstmt = connection2.prepareStatement(sql)) {

            if (partido.getSeleccionLocal().getIdSeleccion() == 0){
                pstmt.setNull(1,Types.INTEGER);
            }
            else{
                pstmt.setInt(1, partido.getSeleccionLocal().getIdSeleccion());
            }

            if (partido.getSeleccionVisitante().getIdSeleccion() == 0){
                pstmt.setNull(2,Types.INTEGER);
            }
            else{
                pstmt.setInt(2, partido.getSeleccionVisitante().getIdSeleccion());
            }


            if (partido.getArbitro().getIdArbitro() == 0){
                pstmt.setNull(3,Types.INTEGER);
            }
            else{
                pstmt.setInt(3, partido.getArbitro().getIdArbitro());
            }



            if (partido.getFecha() == null){
                pstmt.setNull(4,Types.DATE);
            }
            else{
                pstmt.setString(4, partido.getFecha());
            }


            if (partido.getNumeroJornada() == 0){
                pstmt.setNull(5,Types.INTEGER);
            }
            else{
                pstmt.setInt(5, partido.getNumeroJornada());
            }




            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}