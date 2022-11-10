package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoSelecciones extends BaseDao{


    public ArrayList<Seleccion> listarSelecciones(){

        ArrayList<Seleccion> lista = new ArrayList<>();


        String sql = "select * from seleccion";
        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Seleccion seleccion_escoger = new Seleccion();

                seleccion_escoger.setIdSeleccion(rs.getInt(1));
                seleccion_escoger.setNombre(rs.getString(2));
                seleccion_escoger.setTecnico(rs.getString(3));

                Estadio estadio = new Estadio();

                estadio.setIdEstadio(rs.getInt(4));
                seleccion_escoger.setEstadio(estadio);


                lista.add(seleccion_escoger);

            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
