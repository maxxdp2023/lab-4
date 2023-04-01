package Servlets;

import Crud.CRUDInterface;
import Crud.SqlCRUD;
import Entities.CarEntity;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/cars/*")
public class ServletCar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    CRUDInterface<CarEntity> crud = new SqlCRUD();

    public void init(ServletConfig config) throws ServletException {
        crud = new SqlCRUD();
    }

    public void destroy() {
        try {
            ((SqlCRUD) crud).getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carsJson = new Gson().toJson(crud.read());
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(carsJson);
        out.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarEntity car = Helpers.carParse(request);
        crud.create(car);
        doGet(request, response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarEntity car = Helpers.carParse(request);
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        response.setContentType("application/json");
        crud.update(id, car);
        doGet(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        response.setContentType("application/json");
        crud.delete(id);
        doGet(request, response);
    }
}

