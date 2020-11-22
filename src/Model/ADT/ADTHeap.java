package Model.ADT;

import Model.value.ValueInterface;

import java.util.HashMap;
import java.util.Map;

public class ADTHeap<T1, T2> implements HeapInterface<T1, T2> {
    private HashMap<T1, T2> elements;
    private Integer firstEmpty;

    public ADTHeap()
    {
        this.elements = new HashMap<T1, T2>();
        this.firstEmpty = 1; // addresses start from one
    }
    @Override
    public void add(T2 value) {
        this.elements.put((T1)this.firstEmpty, value);
        this.firstEmpty++;
    }

    @Override
    public T1 getAddress(T2 value) {
        Integer key = 0;
        if(!this.elements.isEmpty())
            key = 1;
        for(Map.Entry<T1, T2> entry : elements.entrySet())
            if(entry.getValue().equals(value))
                key = (Integer)entry.getKey();

        return (T1)key;
    }

    @Override
    public void update(T1 key, T2 value) {
        if(isDefined(key))
            this.elements.put(key, value);
    }

    @Override
    public T2 getValue(T1 key) {
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

    @Override
    public String toStringFileFormat()
    {
        String message = "";
        for(Map.Entry<T1, T2> entry : elements.entrySet())
            message += entry.getKey().toString() + "->" + entry.getValue().toString() + "\n";

        return message;
    }

    @Override
    public void setContent(Map<Integer, ValueInterface> heapAfterCallingGarbageCollector) {
//        HashMap<T1, T2> newElements = new HashMap<T1, T2>();
//        int newFirstEmpty = 1;
        this.elements.clear();

        for(Map.Entry<Integer, ValueInterface> entry : heapAfterCallingGarbageCollector.entrySet())
            this.elements.put((T1)entry.getKey(), (T2)entry.getValue());
        //this.elements = (HashMap<T1, T2>) heapAfterCallingGarbageCollector;
    }

    @Override
    public Map<T1, T2> getContent() {
//        Map<T1, T2> result = new HashMap<T1, T2>();
//        // copy all the elements from the heap in the new map
//        for(Map.Entry<T1, T2> entry : elements.entrySet())
//            result.put(entry.getKey(), entry.getValue());

        return this.elements;
    }

}
