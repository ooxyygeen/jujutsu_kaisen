import java.util.ArrayList;

public class Inventory<T> {
    private ArrayList<T> inventory;

    public Inventory() {
        this.inventory = new ArrayList<T>();
    }

    private void addElement(T aElem) {
        this.inventory.add(aElem);
    }

    private void removeElement(int aIndex) {
        this.inventory.remove(aIndex);
    }
}
