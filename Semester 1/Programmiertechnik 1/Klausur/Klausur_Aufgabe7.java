// a)    
    Kapselung, Vererbung, Polymorphie, Dynamische Bindung    
    
// b)    
    Kapselung: Konstruktor der nur sinnvolle werte zulässt    
                public wuerfel(int augen) {    
                    if(augen > 0 && augen < 7) {    
                        this.augen = augen;    
                        return;    
                    }    
                }    
                
    Vererbung: Ober und Unterklassen um gleiche eigenschafften "weiterzugeben"    
                public class E-Bike extends Fahrrad {    
                  
    Polymorphie: polymorphe variabeln, bei denen erst zur laufzeit entschiden wird welchen Typ sie genau haben    
                    number n;    
                    n = 3.4;
                    
    Dynamische Bindung: es wird zur laufzeit entschieden welche (überladenen) methoden verwendet werden
                        a = max(b);
                        c = max(d,e,f);
                        