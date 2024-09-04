<%@ page import="dao.MaterialDao" %>
<%@ page import="bean.MaterialBean" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="java.sql.SQLException" %>


<%
    // Get the parameters from the form submission
    String materialName = request.getParameter("material_name");
    String materialType = request.getParameter("material_dropdown");

    // Validate input
    if (materialName == null || materialName.isEmpty() || materialType == null || materialType.isEmpty()) {
        out.println("Material name or type cannot be empty.");
        return;
    }

    // Create an instance of MaterialDao
    MaterialDao materialDao = new MaterialDao();

    // Create a new MaterialBean and set its properties
    MaterialBean materialBean = new MaterialBean();
    materialBean.setMaterialName(materialName);
    materialBean.setMaterialType(materialType);

    try {
        // Add the new material to the database
        materialDao.addMaterial(materialBean);

        // Redirect to the view materials page after successful addition
        response.sendRedirect("viewmaterials.jsp");
    } catch (SQLException e) {
        // Handle SQL exceptions
        e.printStackTrace();
        out.println("Error: Unable to add material. Please try again.");
    }
%>
