package Model.ADT;

import Model.value.ValueInterface;

import java.util.ArrayList;
import java.util.Map;

public interface DictionaryInterface<T1, T2> {
    void add(T1 key, T2 value);
    void update(T1 key, T2 value);
    void delete(T1 key);
    T2 lookUp(T1 key);
    boolean isDefined(T1 key);
    String toString();
    String toStringFileFormat();

    Map<T1, T2> getContent();
    DictionaryInterface<T1, T2> deepCopy();

    ArrayList<T1> getKeysContent();
}
