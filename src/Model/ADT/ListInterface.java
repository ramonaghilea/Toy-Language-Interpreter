package Model.ADT;

import Model.exceptions.ADTException;

public interface ListInterface<T> {
    void add(T value);
    T pop() throws ADTException;
    String toString();
}
