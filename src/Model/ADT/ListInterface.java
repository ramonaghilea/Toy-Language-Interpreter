package Model.ADT;

import Model.exceptions.ADTException;

import java.util.ArrayList;

public interface ListInterface<T> {
    void add(T value);
    T pop() throws ADTException;
    T getLast() throws ADTException;
    String toString();
    String toStringFileFormat();
    ArrayList<T> getContent();
}
