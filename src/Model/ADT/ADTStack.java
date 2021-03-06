package Model.ADT;
import Model.ADT.StackInterface;
import Model.exceptions.ADTException;

import java.util.*;

public class ADTStack<T> implements StackInterface<T>{
    ArrayList<T> elements;

    public ADTStack()
    {
        this.elements = new ArrayList<T>();
    }

    @Override
    public T pop() throws ADTException{
        if(this.isEmpty())
            throw new ADTException("The stack is empty");
        //return this.elements.remove(elements.size() - 1);
        return this.elements.remove(0);
    }

    @Override
    public void push(T element) {
        this.elements.add(element);
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    @Override
    public String toString() {
        //Collections.reverse(this.elements);
        Iterator<T> iterator = this.elements.iterator();
        String message = "{";
        while(iterator.hasNext())
            message += iterator.next().toString() + " | ";

        if(this.elements.size() > 0)
            message = message.substring(0, message.length() - 3); //remove the last | and the spaces
        message += "}";
        //Collections.reverse(this.elements);

        return message;
    }

    @Override
    public String toStringFileFormat() {
        //Collections.reverse(this.elements);
        Iterator<T> iterator = this.elements.iterator();
        String message = "";
        while(iterator.hasNext())
            message += iterator.next().toString() + "\n";
        //Collections.reverse(this.elements);

        return message;
    }

    @Override
    public ArrayList<T> getContent() {
        return this.elements;
    }

    @Override
    public void setContent(List<T> content) {
        this.elements = (ArrayList<T>) content;
    }
}
