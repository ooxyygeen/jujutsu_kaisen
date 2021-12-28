import java.io.Serializable;

public class Uniform implements ShowInfo, Serializable {
    private String name;
    private int defense;

    public Uniform() {
        this.name = "DefaultUniformName";
        this.defense = 0;
    }
    public Uniform(String aName, int aDefense) {
        this.name = aName;
        this.defense = aDefense;
    }
    @Override
    public String showInfo(){
        return "Name of the uniform: " + this.name + "\nDefense of the uniform: " + this.defense;
    }
    public int getDefense(){
        return this.defense;
    }
    public String getName() {
        return name;
    }
}
