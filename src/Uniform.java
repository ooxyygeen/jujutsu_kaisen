public class Uniform implements ShowInfo{
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
}
