package Model.ADT;

import Model.value.ValueInterface;

import java.util.Map;

public interface HeapInterface<T1, T2> {
    void add(T2 value);
    T1 getAddress(T2 value);
    void update(T1 key, T2 value);
//    void delete(T1 key);
    T2 getValue(T1 key);
    boolean isDefined(T1 key);
    String toString();
    String toStringFileFormat();

    void setContent(Map<Integer, ValueInterface> heapAfterCallingGarbageCollector);
    Map<T1, T2> getContent();
}
