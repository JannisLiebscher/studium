DROP TABLE Verkauf_2DC;
create Table Verkauf_2DC
AS
SELECT COALESCE(p.produkt_gruppe,'alle') as Gruppe, COALESCE(TO_CHAR(k.kunde_alter), 'alle') as "ALTER", SUM(v.verkauf_Anzahl) as anzahl
FROM Kunde k, Produkt p, Verkauf v
WHERE v.verkauf_datum >= TO_DATE('2020-01-01', 'YYYY-MM-DD')
AND v.produkt_ID = p.Produkt_ID
AND v.kunde_ID = k.Kunde_ID
GROUP BY CUBE (p.produkt_gruppe, k.kunde_alter);

select * FROM Verkauf_2DC;

SELECT "ALTER", COALESCE(Bekleidung, 0) AS Bekleidung, COALESCE(Elektronik, 0) AS Elektronik
FROM (
    SELECT *
    FROM Verkauf_2DC
    PIVOT (
        SUM(anzahl)
        FOR gruppe
        IN (
            'Bekleidung' AS Bekleidung,
            'Elektronik' AS Elektronik
        )
    )
)

DROP TABLE Verkauf_3DC;
create Table Verkauf_3DC
AS
SELECT COALESCE(p.produkt_gruppe,'alle') as Gruppe, COALESCE(TO_CHAR(CAST(MONTHS_BETWEEN( SysDate,k.verkaeufer_geburtsdatum)/12 as int)), 'alle') as "ALTER", EXTRACT(YEAR FROM v.verkauf_datum) as datum,
sum(v.verkauf_anzahl * p.produkt_preis ) as umsatz
FROM Verkaeufer k, Produkt p, Verkauf v
where v.produkt_ID = p.Produkt_ID
AND v.verkaeufer_ID = k.verkaeufer_ID
group by rollup (k.verkaeufer_geburtsdatum,p.produkt_gruppe, EXTRACT(YEAR FROM v.verkauf_datum));
select * FROM Verkauf_3DC;