public class Uniform implements ShowInfo{
    private String name;
    private int defense;

    public Uniform() {
        this.name = "DeafultUniformName";
        this.defense = 0;
    }

    public Uniform(String aName, int aDefense) {
        this.name = aName;
        this.defense = aDefense;
    }
    @Override
    public void showInfo(){
        System.out.println("Name of the uniform: "+name);
        System.out.println("Defense of the weapon: "+defense);
    }
}
