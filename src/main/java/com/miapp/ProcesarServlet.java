package com.miapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/procesar")
public class ProcesarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (nombre != null && !nombre.isEmpty() && email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = DBConnection.getConnection();
                String sql = "INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, nombre);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.executeUpdate();
                response.getWriter().println("Datos guardados en la base de datos: " + nombre + ", " + email);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.getWriter().println("Por favor, completa todos los campos.");
        }
    }
}