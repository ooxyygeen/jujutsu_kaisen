import java.io.Serializable;
import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class Inventory<Object> implements Serializable {
    private ArrayList<Object> inventory;
    public int countOfObjects(Class aClass){
        return Collections.frequency(inventory, aClass);
    }
    public Inventory() {
        this.inventory = new ArrayList<>();
    }
    public String addElement(Object element) {
        if (element != null){
            this.inventory.add(element);
            return element.getClass().getName()+" has been successfully added to inventory";
        }
        return "ItemNullPointerException";
    }
    public String removeElement(String elementName) {
        ListIterator<Object> it = this.inventory.listIterator();
        while (it.hasNext()) {
            if (it.getClass().getName().equals(elementName)) {
                this.inventory.remove(it.nextIndex()-1);
                return "Item has been successfully removed";
            }
            it.next();
        }
        return "Item not found";
    }
    public boolean findElement(String elementName){
        ListIterator<Object> it = this.inventory.listIterator();
        while (it.hasNext()) {
            if (it.getClass().getName().equals(elementName)) {
                return true;
            }
            it.next();
        }
        return false;
    }
    public Object getItem(String nameOfItem){
        ListIterator<Object> it = this.inventory.listIterator();
        while (it.hasNext()) {
            if (it.getClass().getName().equals(nameOfItem))
                return this.inventory.remove(it.nextIndex()-1);
        }
        return null;
    }
}
