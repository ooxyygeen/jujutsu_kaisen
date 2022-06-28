package com.engine.javacoursework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class Inventory<Object extends ShowInfo> implements Serializable {
    private ArrayList<Object> inventory;

    public int countOfObjects(Class aClass) {
        return Collections.frequency(inventory, aClass);
    }

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public String addElement(Object element) {
        if (element != null) {
            this.inventory.add(element);
//            inventory.forEach(item -> System.out.println(item.showInfo()));
            return element.showInfo() + " has been successfully added to inventory";
        }
        return "ItemNullPointerException";
    }

    public ArrayList<Object> getInventory() {
        return this.inventory;
    }

    public String removeElement(String elementName) {
//        ListIterator<Object> it = this.inventory.listIterator();
//        while (it.hasNext()) {
//            if (it.getClass().getName().equals(elementName)) {
//                String message = it.getClass().getName() + " has been successfully removed";
//                this.inventory.remove(it.nextIndex()-1);
//                return message;
//            }
//            it.next();
//        }
        for (Object item : this.inventory) {
            if (item.showInfo().equals(elementName)) {
                String message = item.getClass().getName() + " has been successfully removed";
                this.inventory.remove(item);
                return message;
            }
        }
        return "Item not found";
    }

    public boolean findElement(String elementName) {
//        ListIterator<Object> it = this.inventory.listIterator();
//        while (it.hasNext()) {
//            if (it.getClass().getName().equals(elementName)) {
//                return true;
//            }
//            it.next();
//        }
        for (Object item : this.inventory) {
            if (item.showInfo().equals(elementName)) {
                return true;
            }
        }
        return false;
    }

    public Object getItem(String elementName) {
//        ListIterator<Object> it = this.inventory.listIterator();
//        while (it.hasNext()) {
//            if (it.getClass().getName().equals(elementName))
//                return this.inventory.remove(it.nextIndex() - 1);
//        }
        int i = 0;
        for (Object item : this.inventory) {
            System.out.println(item.showInfo());
            if (item.showInfo().equals(elementName)) {
                return this.inventory.remove(i);
            }
            i++;
        }
        return null;
    }
}
