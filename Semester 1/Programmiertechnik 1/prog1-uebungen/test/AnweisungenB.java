package klausurAufgabe4;

public final class AnweisungenB {
    private AnweisungenB() {}
    public static void main(String[]args) {
        System.out.println(max());
        System.out.println(max(1));
        System.out.println(max(2, 3));
        System.out.println(max(4, 5, 6));
    }

    private static Integer max(Integer n, Integer m) {
        return n.intValue() > m.intValue() ? n : m;
    }

    private static Integer max(Integer...zahlen) {
        Integer m;
        if (zahlen.length == 0) {
            return null;
        }    
        else if (zahlen.length == 1) {
            return zahlen[0];
        }
        else {
            m = zahlen[0];
            for (int i = 0;i < zahlen.length;i++) {
                m = max(m,zahlen[i]);
            } 
        return m;
        }  
    }    
}

