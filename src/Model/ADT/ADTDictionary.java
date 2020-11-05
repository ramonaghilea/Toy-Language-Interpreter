package Model.ADT;
import Model.ADT.DictionaryInterface;

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
            message += entry.getKey().toString() + "->" + entry.getValue().toString() + ",";

        if(this.elements.size() > 0)
            message = message.substring(0, message.length() - 1); //remove the last comma if there are elements

        message += "}";

        return message;
    }
}
