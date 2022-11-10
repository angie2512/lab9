package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;

import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        DaoPartidos daoPartidos = new DaoPartidos();

        switch (action) {

            case "guardar":

                String jornada = request.getParameter("jornada");
                int jornadaID = Integer.parseInt(jornada);

                String fecha = request.getParameter("fecha");


                String local = request.getParameter("local");
                int localID = Integer.parseInt(local);


                String visitante = request.getParameter("visitante");
                int visitanteID = Integer.parseInt(visitante);

                String arbitro = request.getParameter("arbitro");
                int arbitroID = Integer.parseInt(arbitro);

                Partido partido = new Partido();

                /*if(jornada.equals("") == true){


                }else{

                }*/






                /*partido.setNumeroJornada(request.getParameter("jornada").equals("") ? null : (Integer.parseInt(request.getParameter("jornada"))));


                partido.setFecha(request.getParameter("fecha").equals("") ? null : (request.getParameter("fecha")));*/





                //partido.setIdPartido();

                /*partido.setNumeroJornada(jornada);

                partido.setFecha(fecha);*/

                Seleccion seleccion1 = new Seleccion();
                seleccion1.setIdSeleccion(localID);

                Seleccion seleccion2 = new Seleccion();
                seleccion2.setIdSeleccion(visitanteID);

                Arbitro arbitro1 = new Arbitro();
                arbitro1.setIdArbitro(arbitroID);

                /*seleccion1.setIdSeleccion(request.getParameter("local").equals("") ? null : (Integer.parseInt(request.getParameter("local"))));
                seleccion2.setIdSeleccion(request.getParameter("visitante").equals("") ? null : (Integer.parseInt(request.getParameter("visitante"))));
                arbitro1.setIdArbitro(request.getParameter("arbitro").equals("") ? null : (Integer.parseInt(request.getParameter("arbitro"))));*/




                partido.setSeleccionLocal(seleccion1);
                partido.setSeleccionVisitante(seleccion2);
                partido.setArbitro(arbitro1);



                try{
                    if (partido.getSeleccionLocal().getIdSeleccion() == partido.getSeleccionVisitante().getIdSeleccion()) {
                        view = request.getRequestDispatcher("partidos/form.jsp");
                        view.forward(request, response);
                    } else if (partido.getSeleccionLocal().getIdSeleccion() == 0) {
                        view = request.getRequestDispatcher("partidos/form.jsp");
                        view.forward(request, response);
                    }else if (partido.getSeleccionVisitante().getIdSeleccion() == 0) {
                        view = request.getRequestDispatcher("partidos/form.jsp");
                        view.forward(request, response);
                    }else if (partido.getArbitro().getIdArbitro() == 0) {
                        view = request.getRequestDispatcher("partidos/form.jsp");
                        view.forward(request, response);
                    }
                    else if (partido.getFecha().equals("")) {
                        view = request.getRequestDispatcher("partidos/form.jsp");
                        view.forward(request, response);
                    }
                    else if (partido.getNumeroJornada() ==0) {
                        view = request.getRequestDispatcher("partidos/form.jsp");
                        view.forward(request, response);
                    }
                    else {
                        daoPartidos.crearPartido(partido);
                        response.sendRedirect(request.getContextPath() + "/PartidoServlet");
                    }
                } catch (ServletException e) {
                    response.sendRedirect(request.getContextPath() + "/PartidoServlet");;
                } catch (IOException e) {
                    response.sendRedirect(request.getContextPath() + "/PartidoServlet");;
                }catch(NullPointerException e){
                    response.sendRedirect(request.getContextPath() + "/PartidoServlet");;
                }


                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;

        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones1 = new DaoSelecciones();
        DaoSelecciones daoSelecciones2 = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {
            case "lista":

                request.setAttribute("lista", daoPartidos.listaDePartidos());


                view = request.getRequestDispatcher("index2.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaSelecciones", daoSelecciones1.listarSelecciones());
                request.setAttribute("listaSelecciones2", daoSelecciones2.listarSelecciones());
                request.setAttribute("listaArbitros", daoArbitros.listarArbitros());
                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);
                break;

        }

    }
}
