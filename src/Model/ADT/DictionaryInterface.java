package Model.ADT;

public interface DictionaryInterface<T1, T2> {
    void add(T1 key, T2 value);
    void update(T1 key, T2 value);
    T2 lookUp(T1 key);
    boolean isDefined(T1 key);
    String toString();
}
