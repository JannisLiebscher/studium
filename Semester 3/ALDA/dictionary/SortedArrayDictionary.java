package dictionary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    private final int DEFAULT = 8;
    private Entry<K, V>[] ar = new Dictionary.Entry[DEFAULT];


    @Override
    public V insert(K key, V value) {
        int pos = searchKeyPos(key);                                //check for element with specified key
        if(pos < 0) {
            if(size() == ar.length) {                               //check if array is full
                ar = Arrays.copyOf(ar, ar.length * 2);
            }
            ar[size()] = new Entry(key, value);                     //add new Entry
            Arrays.sort(ar,0,size());
        } else {
            V tmp = ar[pos].getValue();                             //update value of specified key
            ar[pos].setValue(value);
            return tmp;
        }
        return null;
    }

    @Override
    public V search(K key) {
        int pos = searchKeyPos(key);            //Entry position suchen
        if(pos < 0) {                           //Entry nicht gefunden
            return null;
        } else {                                //Entry gefunden
            return ar[pos].getValue();
        }
    }
    /**
     * Returns the index of the specified key.
     * @return the index of the specified key , or -1 if this map contains no mapping for the key.
     */
    private int searchKeyPos(K key) {           //Indexbestimmung mit binärer suche
        int li = 0;
        int re = ar.length - 1;
        while (re >= li) {
            int m = (li + re)/2;
            if (ar[m] == null) {
                re = m - 1;
            } else if (key.compareTo(ar[m].getKey()) < 0) {
                re = m - 1;
            } else if (key.compareTo(ar[m].getKey()) > 0) {
                li = m + 1;
            } else {
                return m; // key found
            }
        }
        return -1; // key not found
    }

    @Override
    public V remove(K key) {
        int pos = searchKeyPos(key);
        if (pos < 0) {
            return null;
        } else {
            V tmp = ar[pos].getValue();             //safe value of the element with specified key
            ar[pos] = ar[size()];                   //overwrite found element with last element in the map
            ar[size()] = null;                      //delete last element in the map
            Arrays.sort(ar,0,size());
            return tmp;
        }
    }

    @Override
    public int size() {
        for (int i = ar.length - 1; i >= 0; i--) {  //solange ar[i] !0 null ist i hochzählen
            if (ar[i] != null) {
                return i + 1;
            }
        }
        return 0;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        Iterator<Entry<K, V>> it = new Iterator<Entry<K, V>>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                if(index == ar.length - 1) {                //max index: no next element
                    return false;
                } else if(ar[index] == null) {          //next element is null: no next element
                    return false;
                }                                           //there is a non null next element
                return true;
            }
            @Override
            public Entry<K, V> next() {
                if(! hasNext()) {
                    throw new NoSuchElementException();
                }
                return ar[index++];
            }
        };
        return it;
    }
}