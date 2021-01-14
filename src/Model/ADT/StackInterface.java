package Model.ADT;

import Model.exceptions.ADTException;
import Model.statement.StatementInterface;

import java.util.ArrayList;
import java.util.List;

public interface StackInterface<T>{
    T pop() throws ADTException;
    void push(T element);
    boolean isEmpty();
    String toString();
    String toStringFileFormat();

    ArrayList<T> getContent();
    void setContent(List<T> content);
}
