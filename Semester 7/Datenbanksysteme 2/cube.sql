SELECT COALESCE(p.produkt_gruppe,'alle'), COALESCE(k.kunde_alter,0), SUM(v.verkauf_Anzahl) 
FROM Kunde k, Produkt p, Verkauf v
WHERE v.verkauf_datum >= TO_DATE('2020-01-01', 'YYYY-MM-DD')
AND v.produkt_ID = p.Produkt_ID
AND v.kunde_ID = k.Kunde_ID
GROUP BY CUBE (p.produkt_gruppe, k.kunde_alter)