<%@ page import="dao.MaterialDao" %>
<%@ page import="bean.MaterialBean" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Materials</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
        a {
            text-decoration: none;
            color: blue;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>View Materials</h1>
    <a href="addmaterial.jsp">Add New Material</a>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Material Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Create an instance of MaterialDao
                MaterialDao materialDao = new MaterialDao();
                
                // Retrieve all materials from the database
                List<MaterialBean> materials = materialDao.getAllMaterials();
                
                // Iterate over the list of materials and display each one
                for (MaterialBean material : materials) {
            %>
            <tr>
                <td><%= material.getId() %></td>
                <td><%= material.getMaterialName() %></td>
                <td>
                    <a href="editmaterial.jsp?id=<%= material.getId() %>">Edit</a>
                    <a href="deletematerial.jsp?id=<%= material.getId() %>" onclick="return confirm('Are you sure you want to delete this material?')">Delete</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
