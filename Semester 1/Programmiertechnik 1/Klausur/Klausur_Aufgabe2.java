
    // a)
        a = 1 / 2       -> int, 0  
        b = 3 - 4.5     -> double, -1,5
        c = 6 == 7      -> boolean, false
        d = "89" + '0'  -> String, "890"
        
    // b)
        Integer a
            _______        
           | Wert  |
           
        String b 
            __________           ________
            |Referenz|  ------> |Wert    |
         (Speicheradresse)
         
    // c)
        
        
        
        
        
        
        
    // d)
        int a;
        String s;
        s.valueOf(a);
        
        Object o;
        String t = o.toString();
        