package Model.ADT;
import Model.ADT.DictionaryInterface;
import Model.value.ValueInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ADTDictionary<T1, T2> implements DictionaryInterface<T1, T2>{
    HashMap<T1, T2> elements;

    public ADTDictionary()
    {
        elements = new HashMap<T1, T2>();
    }

    @Override
    public void add(T1 key, T2 value) {
        if(!isDefined(key))
            this.elements.put(key, value);
    }

    @Override
    public void update(T1 key, T2 value) {
        if(isDefined(key))
            this.elements.put(key, value);
    }

    @Override
    public void delete(T1 key) {
        if(isDefined(key))
            this.elements.remove(key);
    }

    @Override
    public T2 lookUp(T1 key) {
        if(isDefined(key))
            return this.elements.get(key);
        return null;
    }

    @Override
    public boolean isDefined(T1 key) {
        return this.elements.containsKey(key);
    }

    @Override
    public String toString() {
        String message = "{";
        for(Map.Entry<T1, T2> entry : elements.entrySet())
            if(entry.getValue() != null)
                message += entry.getKey().toString() + "->" + entry.getValue().toString() + ",";
            else
                message += entry.getKey().toString();

        if(this.elements.size() > 0)
            message = message.substring(0, message.length() - 1); //remove the last comma if there are elements

        message += "}";

        return message;
    }

    @Override
    public String toStringFileFormat() {
        String message = "";
        for(Map.Entry<T1, T2> entry : elements.entrySet())
            if(entry.getValue() != null)
                message += entry.getKey().toString() + "->" + entry.getValue().toString() + "\n";
            else
                message += entry.getKey().toString() + "\n";

        return message;
    }

    @Override
    public Map<T1, T2> getContent() {
//        Map<T1, T2> result = new HashMap<T1, T2>();
//        // copy all the elements from the heap in the new map
//        for(Map.Entry<T1, T2> entry : elements.entrySet())
//            result.put(entry.getKey(), entry.getValue());
//
//        return result;
        return this.elements;
    }

    @Override
    public DictionaryInterface<T1, T2> deepCopy() {
        DictionaryInterface<T1, T2> copyDictionary = new ADTDictionary<T1, T2>();
//        for(Map.Entry<T1, T2> entry : elements.entrySet())
//            copyDictionary.add(entry.getKey(), entry.getValue());
        for(T1 elem: elements.keySet())
            copyDictionary.add(elem, elements.get(elem));

        return copyDictionary;
    }

    @Override
    public ArrayList<T1> getKeysContent() {
        ArrayList<T1> result = new ArrayList<T1>();
        result.addAll(this.elements.keySet());
        return result;
    }
}
