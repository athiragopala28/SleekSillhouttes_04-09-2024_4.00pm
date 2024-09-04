<%@ page import="dao.MaterialDao" %>
<%@ page import="bean.MaterialBean" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Material</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input[type="text"],
        select {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 10px;
        }
        button {
            background-color: #5cb85c;
            border: none;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px 0;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #4cae4c;
        }
        .button-link {
            background-color: #337ab7;
            border: none;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        .button-link:hover {
            background-color: #286090;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Add New Material</h1>
    <form action="addmaterial_action.jsp" method="post">
        <label for="material_name">Material Name:</label>
        <input type="text" id="material_name" name="material_name" required>
        
        <label for="material_dropdown">Select Material Type:</label>
        <select id="material_dropdown" name="material_dropdown">
            <option value="">-- Select Material Type --</option>
            <option value="1">Linen</option>
            <option value="2">Chiffon</option>
            <option value="3">Satin</option>
            <option value="4">Georgette</option>
            <option value="5">Silk</option>
            <option value="6">Crepe</option>
            <option value="7">Nylon</option>
            <option value="8">Velvet</option>
            <option value="9">Rayon</option>
            <% 
                // Create an instance of MaterialDao
                MaterialDao materialDao = new MaterialDao();
                
                // Retrieve all materials from the database
                List<MaterialBean> materials = materialDao.getAllMaterials();
                
                // Populate the dropdown with the materials from the database
                for (MaterialBean material : materials) {
            %>
                <option value="<%= material.getId() %>"><%= material.getMaterialName() %></option>
            <% } %>
        </select>
        
        <button type="submit">Add Material</button>
    </form>
    <div class="button-container">
        <a href="viewmaterials.jsp" class="button-link">Back to View Materials</a>
    </div>
</body>
</html>
