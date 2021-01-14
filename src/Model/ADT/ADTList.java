package Model.ADT;
import Model.ADT.ListInterface;
import Model.exceptions.ADTException;

import java.util.ArrayList;

public class ADTList<T> implements ListInterface<T> {
    ArrayList<T> elements;

    public ADTList()
    {
        this.elements = new ArrayList<T>();
    }

    @Override
    public void add(T value) {
        this.elements.add(value);
    }

    @Override
    public T pop() throws ADTException{
//        T lastElement = this.elements.get(elements.size() - 1);
//        this.elements.remove(elements.size() - 1);
//        return lastElement;
        if(this.elements.isEmpty())
            throw new ADTException("The list is empty");
        return this.elements.remove(elements.size() - 1);
    }

    @Override
    public T getLast() throws ADTException {
        if(this.elements.isEmpty())
            throw new ADTException("The list is empty");
        return this.elements.get(elements.size() - 1);
    }

    @Override
    public String toString() {
        String message = "{";
        for(int index = 0; index < this.elements.size(); index++)
            message += this.elements.get(index).toString() + ",";

        if(this.elements.size() > 0)
            message = message.substring(0, message.length() - 1); //remove the last comma if there are elements

        message += "}";

        return message;
    }

    @Override
    public String toStringFileFormat() {
        String message = "";
        for(int index = 0; index < this.elements.size(); index++)
            message += this.elements.get(index).toString() + "\n";

        return message;
    }

    @Override
    public ArrayList<T> getContent() {
        return this.elements;
    }
}
