    
    // a)
        Name        : Beispiel.java
        Speicherort : ...\aufgabe5
        
    // b)
        Beispiel.java
        package aufgabe5;
        
        public final class Beispiel {
                private Beispiel() { }
                
                public final int KONSTANTE = 1;
                public int variable;
                 
                public static int methde() {
                    return 1;
                }
                
                public static int mnethiode(int i) {
                    return i++;
                }
    
    
    
    // c)
        Methoden, wie  "aufgabe5.Beispiel.methode()" dürfen den selben Namen haben, 
        solange sich die Parameterliste im Kopf {methode() <> methode(i)} so 
        unterscheidet, dass der Compiler eine eindeutige Zuordung machen kann. 
        
    // d)
        - import aufgabe5.Beispiel
        - import (static) aufgabe5.Beispiel.Konstante;
    
                
            
                    
                
                