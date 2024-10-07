package lib;

import java.util.Enumeration;
import java.util.Hashtable;

public class mHashtable {
    public Hashtable h = new Hashtable();

    public Object get(Object k) {
        return this.h.get(k);
    }

    public void clear() {
        this.h.clear();
    }

    public Enumeration keys() {
        return this.h.keys();
    }

    public boolean isEmpty() {
        return this.h.isEmpty();
    }

    public boolean equals(Object obj0) {
        return this.h.equals(obj0);
    }

    public Enumeration elements() {
        return this.h.elements();
    }

    public int size() {
        return this.h.size();
    }

    public void put(Object k, Object v) {
        this.h.put(k, v);
    }

    public void remove(Object k) {
        this.h.remove(k);
    }

    public boolean containsKey(Object key) {
        return this.h.containsKey(key);
    }

    public boolean contains(Object key) {
        return this.h.contains(key);
    }
}
