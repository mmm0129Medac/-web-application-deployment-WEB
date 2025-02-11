<%@ page import="java.sql.*, com.miapp.DBConnection" %>ge import="java.sql.*, com.miapp.DBConnection" %>
<html>
<body>
<h2>Hello World!</h2>

<form action="index.jsp" method="post">
    <input type="text" name="nombre" placeholder="Nombre">
    <input type="submit" value="Enviar">
</form>

<%
    String nombre = request.getParameter("nombre");
    if (nombre != null && !nombre.isEmpty()) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO usuarios (nombre) VALUES (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            out.println("Nombre guardado en la base de datos: " + nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
%>

</body>
</html>
