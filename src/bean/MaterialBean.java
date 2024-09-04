package bean;  // Adjust the package name as needed

public class MaterialBean {
    private int id;
    private String material_name;
    private String materialType; // Add this field


    public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	// Default constructor
    public MaterialBean() {
    }

    // Parameterized constructor
    public MaterialBean(int id, String materialName) {
        this.id = id;
        this.material_name = materialName;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for materialName
    public String getMaterialName() {
        return material_name;
    }

    // Setter for materialName
    public void setMaterialName(String materialName) {
        this.material_name = materialName;
    }

    // Optional: Override toString() for better readability
    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", materialName='" + material_name + '\'' +
                '}';
    }
}
