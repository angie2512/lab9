package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros arbitrosDao1 = new DaoArbitros();


        switch (action) {

            case "buscar":
                /*
                Inserte su código aquí
                */
                request.setAttribute("Opciones",opciones);
                String tipo = request.getParameter("tipo");
                String buscar = request.getParameter("buscar");
                ArrayList<Arbitro> listaArbitros = null;
                if(tipo.equals("nombre")){
                   listaArbitros = arbitrosDao1.busquedaNombre(buscar);
                }else{
                    listaArbitros = arbitrosDao1.busquedaPais(buscar);
                }
                request.setAttribute("ListaArbitros", listaArbitros);
                view = request.getRequestDispatcher("arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "guardar":
                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");
                System.out.println(arbitrosDao1.busquedaNombre(nombre).size());
                if(nombre.isBlank()){
                    request.getSession().setAttribute("infotodo","nombre vacio");
                    response.sendRedirect(request.getContextPath() + "/ArbitroServlet?action=crear");
                }else if(arbitrosDao1.busquedaNombre(nombre).size()==0){
                    try{
                        Arbitro newarbitro = new Arbitro();
                        newarbitro.setNombre(nombre);
                        newarbitro.setPais(pais);
                        arbitrosDao1.crearArbitro(newarbitro);
                        response.sendRedirect(request.getContextPath() + "/ArbitroServlet?");
                    } catch (NumberFormatException | SQLException e) {
                        request.getSession().setAttribute("infotodo","error al crear");
                        response.sendRedirect(request.getContextPath() + "/ArbitroServlet?action=crear");
                    }
                }else{
                    request.getSession().setAttribute("infotodo","nombre repetido");
                    response.sendRedirect(request.getContextPath() + "/ArbitroServlet?action=crear");
                }
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoArbitros arbitrodao = new DaoArbitros();

        RequestDispatcher view;

        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");

        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        switch (action) {
            case "lista":
                request.setAttribute("Opciones",opciones);
                request.setAttribute("ListaArbitros", arbitrodao.listarArbitros());
                view = request.getRequestDispatcher("arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "crear":
                /*
                Inserte su código aquí
                */
                request.setAttribute("listaPaises",paises);
                view = request.getRequestDispatcher("arbitros/form.jsp");
                view.forward(request,response);
                break;
            case "borrar":
                /*
                Inserte su código aquí
                */
                String spell = request.getParameter("id");
                try{
                    int spelli = Integer.parseInt(spell);
                    arbitrodao.borrarArbitro(spelli);
                    response.sendRedirect(request.getContextPath()+"/ArbitroServlet");

                }catch (NumberFormatException e){
                    response.sendRedirect(request.getContextPath()+ "/ArbitroServlet");
                }
                break;
        }
    }
}
