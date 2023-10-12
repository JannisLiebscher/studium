DROP TABLE Verkauf_2DC;
create Table Verkauf_2DC
AS
SELECT COALESCE(p.produkt_gruppe,'alle') as Gruppe, COALESCE(TO_CHAR(k.kunde_alter), 'alle') as "ALTER", SUM(v.verkauf_Anzahl) as anzahl
FROM Kunde k, Produkt p, Verkauf v
WHERE v.verkauf_datum >= TO_DATE('2020-01-01', 'YYYY-MM-DD')
AND v.produkt_ID = p.Produkt_ID
AND v.kunde_ID = k.Kunde_ID
GROUP BY CUBE (p.produkt_gruppe, k.kunde_alter);

select * FROM Verkauf_2DC