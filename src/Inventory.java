import java.util.ArrayList;

public class Inventory<T> {
    private ArrayList<T> inventory;

    public Inventory() {
        inventory = new ArrayList<T>();
    }

    public void addElement(T aElem) {
        inventory.add(aElem);
    }

    public void removeElement(int aIndex) {
        inventory.remove(aIndex);
    }
}
