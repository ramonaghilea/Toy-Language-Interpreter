package Model.ADT;

import Model.exceptions.ADTException;

public interface StackInterface<T>{
    T pop() throws ADTException;
    void push(T element);
    boolean isEmpty();
    String toString();
    String toStringFileFormat();
}
