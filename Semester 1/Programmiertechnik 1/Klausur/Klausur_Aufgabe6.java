
// a)
Aufgabe.java
package aufgabe6;
     
public final class Aufgabe { 
    
    private String name;
    private int[] aufgaben;
    
    public Aufgabe(String name,int... aufgaben) {
        if (aufgaben.length == 0) {
            throw new illegalArgumentException;
        } else {
            for(int i = 0,aufgaben.length -1, ++i) {
                if(aufgaben[i] == 0) {
                    throw new illegalArgumentException;
                }
            }
            this.aufgaben = aufgaben;
            this.name = name;
            return;
        }
    }
    
    public int getAnzahlTeile() {
        return this.aufgaben.length;
    }
    public String getTitel() {
        return this.name;
    }
    public int getGesamtpunkte() {
        int n = 0;
        for(int i = 0,this.aufgaben.length -1, ++i) {
            n += this.aufgabe[i];
        }
        return n;
    }
    
    
    
    
// b)
    public Aufgabe(String this.name,int... this.aufgaben) {
        als heimlicher Parameter im Kontruktor, wird benutzt um 
        Namenkonflikte zu verhindern
        
// c)
     new StringBuilder().append("Aufgabe ").append((i + 1)).toString();
    
    