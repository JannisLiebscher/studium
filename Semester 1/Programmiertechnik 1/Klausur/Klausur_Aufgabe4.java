 	 
	// a)
	
	public static Integer max(Integer n, Integer m) {	 
	if (n.intValue() <m.intValue()) {	 
		return m.intvalue();	
	}	 
    else {     
       retunrn.intValue();     
    }     
    
    // b)
    
    public static integer max(Integer... zahlen) {
        Integer m;
        if (zahlen.length == 0) {
            return null;
        else if(zahlen.lenght == 1) {
            return zahlen[0];
        else {
            for(int i = 0,i < zahlen.length, ++i) {
                m = max(m, zahlen[i]);
            }
        }
    }
    
    // c)
    
    public static integer max(Integer... zahlen) {
        Integer m;
        /*
        if (zahlen.length == 0) {
            return null;
        else if(zahlen.lenght == 1) {
            return m;
        */
        else {
            int i = zahlen.length - 1;
            while (i>=0) {
                m = max(m, zahlen[i]);
                i--;
            }
        }
    }
    
    // d)
    public final class Anweisungen {
    private Anweisungen() { }

        public static void main(String[] args) {
            System.out.println(max());                  private static Integer max(Integer... zahlen) {     , liefert "null"
            System.out.println(max(1));                 private static Integer max(Integer... zahlen) {     , liefert 1
            System.out.println(max(2, 3));              private static Integer max(Integer n, Integer m) {  , liefert 3
            System.out.println(max(4, 5, 6));           private static Integer max(Integer... zahlen) {     , liefert 6
    }
    
    // e)
        
       Die Argumente werden in ein Integer-Array übergeben     

























            