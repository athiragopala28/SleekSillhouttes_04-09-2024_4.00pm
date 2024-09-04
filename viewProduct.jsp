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
            padding: 5px 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f4f4f4;
            display: inline-block;
            margin-right: 5px;
        }
        a:hover {
            background-color: #ddd;
        }
        .btn-edit {
            color: green;
        }
        .btn-delete {
            color: red;
        }
        .btn-edit:hover {
            background-color: #d4edda;
        }
        .btn-delete:hover {
            background-color: #f8d7da;
        }
        .btn-new {
            color: white;
            background-color: #007bff;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            border-radius: 5px;
            text-align: center;
            display: block;
            margin: 20px auto;
            width: fit-content;
        }
        .btn-new:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>View Materials</h1>
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
                    <a href="editmaterial.jsp?id=<%= material.getId() %>" class="btn-edit">Edit</a>
                    <a href="deletematerial.jsp?id=<%= material.getId() %>" class="btn-delete" onclick="return confirm('Are you sure you want to delete this material?')">Delete</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <!-- New Material Button -->
    <a href="addmaterial.jsp" class="btn-new">Add New Material</a>
</body>
</html>
