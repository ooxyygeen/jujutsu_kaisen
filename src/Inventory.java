import java.util.ArrayList;
import java.util.Iterator;

public class Inventory<T> {
    private ArrayList<T> inventory;

    public Inventory() {
        this.inventory = new ArrayList<T>();
    }

    private void addElement(T aElem) {
        this.inventory.add(aElem);
    }

    private void removeElement(String elementName) {
        /*
        Iterator it = this.inventory.iterator();
        for (int i = 0; i < this.inventory.toArray().length; i++){
            if (getName(it.getClass()).equals("elementName")){
            this.inventory.remove(this.inventory.indexOf(it));
            break;
            }
            it.next();
        //нижче якась бебра, я хз як ти збирався прибирать правильний предмет
        this.inventory.remove(aIndex);
        this.inventory.i
        }
        */
    }
}
