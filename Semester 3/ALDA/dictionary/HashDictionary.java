package dictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashDictionary<K extends Comparable<? super K>, V> implements Dictionary<K,V> {
    private int size;
    private LinkedList<Entry<K, V>>[] ar;


    public HashDictionary(int size) {
        this.size = size;
        ar = new LinkedList[size];          // Erstellung von ar ein Array von Listen mit der Größe size

        for (int i = 0; i < size; i++) {
            ar[i] = new LinkedList<>();     // mit leeren listen füllen
        }
    }


    @Override
    public V insert(K key, V value) {
        Entry<K, V> e = new Entry(key, value);  //einzufügendes element
        if (size() / ar.length > 2) {           //falls load fakto 2 überschritten vergrößern
            int newSize = ar.length * 2;        // neue size = alte size * 2
            while (!isPrime(newSize)) {         // um 1 erhöhen, bis newSize eine Primzahl ist
                newSize++;
            }
            LinkedList<Entry<K, V>>[] old = ar; //ar "befreien"
            ar = new LinkedList[newSize];       //ar neues leeres array zuweisen
            for (int i = 0; i < size; i++) {    //ar mit leeren listen füllen
                ar[i] = new LinkedList<>();
            }
            for (var x : old) {                 //jede liste aus dem alten array nehmen
                for (var y : x) {               //aus jeder liste alle elemente in neues array einfügen
                    insert(y.getKey(), y.getValue());
                }
            }
        }
        int index = key.hashCode() % ar.length; //hash berechnen
        if (index < 0) index = -index;

        if (ar[index] == null) {                //keine liste unter diesem index
            ar[index] = new LinkedList<>();
            ar[index].add(e);
            return null;
        } else if (ar[index] != null) {         //liste vorhanden unter diesem index
            if (ar[index].contains(e)) {        //e schon in der liste enthalten
                V tmp = ar[index].get(ar[index].indexOf(e)).getValue(); //value zur rückgabe sichern
                ar[index].get(ar[index].indexOf(e)).setValue(value);    //neuen value eintragen
                return tmp;
            }
        }
        ar[index].add(e);                       //liste vorhanden, aber Entry nicht in liste enthalten
        return null;
    }


    private boolean isPrime(int nonPrim) {
        if (nonPrim <= 1) return false;
        for (int i = 2; i < nonPrim; i++) {
            if (nonPrim % i == 0) return false;
        }
        return true;
    }


    @Override
    public V search(K key) {
        Entry<K, V> e = new Entry(key, 0);          //neuer Entry für contains()
        int index = key.hashCode() % ar.length;     //hash berechnen
        if (index < 0) index = - index;
        if(ar[index].contains(e)) {                 //Entry ist in liste voprhanden
            return ar[index].get(ar[index].indexOf(e)).getValue();
        } else return null;                         //Entry ist nicht in liste vorhanden
    }


    @Override
    public V remove(K key) {
        Entry<K, V> e = new Entry(key, 0);          //neuer Entry für contains()
        int index = key.hashCode() % ar.length;     //hash berechnen
        if (index < 0) index = - index;
        if(ar[index].contains(e)) {                 //Entry vorhanden
            V tmp = ar[index].get(ar[index].indexOf(e)).getValue();     //RÜckgabewert sichern
            ar[index].remove(e);                    //Entry löschen
            return tmp;
        } else return null;                         //entry nicht vorhanden
    }


    @Override
    public int size() {
        int sum = 0;
        for (var x : ar) {                          //jede liste in ar untersuchen
            if(x == null) sum += 0;
            else sum += x.size();                   //sum um liste.size() erhöhen
        }
        return sum;
    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {

            private int index = 0;                  //position im array
            private int listPos = 0;                //position in der liste
            @Override

            public boolean hasNext() {
                if (ar[index].size() > listPos) {   // elemente in der liste übrig
                    return true;                    // ja
                }
                listPos = 0;                        // Reset des Listenzeigers

                while (index < ar.length - 1) {     // solange der Arrayszeiger kleiner als die Größe - 1 ist
                    index++;                        // inkrementiere den Arrayzeiger
                    if (ar[index].size() > 0) return true;  //hat die Liste an dieser Stelle einträge ?
                }
                return false;
            }
            @Override
            public Entry<K, V> next() {
                if(! hasNext()) {
                    throw new NoSuchElementException();
                }
                return ar[index].get(listPos++);    //incerementieren für nächstes element
            }
        };
    }
}
