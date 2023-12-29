/*Aufgabe 1 a*/
db.auftrag.find({
        "jahr" : 2021,
        $or : [{"beschreibung":"Installation Webserver"},{"beschreibung":"Entwicklung Onlineshop"}]      
});

/*Aufgabe 1 b*/
db.auftrag.find(
    { "jahr": 2021 },
    { _id: 0, beschreibung: 1 }
);
/*Aufgabe 1 c*/
db.kunde.find(
    {"auftraege.kosten" : {$gt : 5000} },
    { _id: 0, name: 1 }
);

/*Aufgabe 1 d*/
db.kunde.find(
    {
        "auftraege.details.$id": db.auftrag.findOne({ "auftragsnummer": 1222 }, { _id: 1 })._id
    },
    { _id: 0, name: 1 }
);

/*Aufgabe 1 e*/
db.kunde.aggregate([
  {$unwind: "$auftraege"},
  {
    $group: {
      _id: "$_id",
      name: { $first: "$name" },
      gesamtkosten: { $sum: "$auftraege.kosten" }
    }
  },
  {
    $project: {
      _id: 0,
      name: 1,
      gesamtkosten: 1
    }
  }
]);

/*Aufgabe 1 f*/
db.auftrag.aggregate([
  {
    $match: {
      "beschreibung": "Entwicklung Onlineshop"
    }
  },
  {
    $group: {
      _id: "$jahr",
      gesamtkosten: { $sum: "$kosten" }
    }
  },
  { $sort: { "gesamtkosten": -1 } },
  { $limit: 1 },
  {
    $project: {
      jahr: 1,
      gesamtkosten: 1
    }
  }
]);

/*Aufgabe 1 g*/
db.auftrag.aggregate([
  {
    $match: {
      "jahr": 2021
    }
  },
  {
    $group: {
      _id: "$beschreibung",
      gesamtkosten: { $sum: "$kosten" }
    }
  },
  { $sort: { "gesamtkosten": -1 } },
  {
    $project: {
      jahr: 1,
      gesamtkosten: 1
    }
  }
]);

/*Aufgabe 2 a Zeigt noch keine Ergebnisse an*/
db.pers.aggregate([
 {
    $match: {
      "vorgesetzter": { $exists: true }
    }
  },
  {
    $lookup: {
      from: "pers",
      localField: "vorgesetzter.$id",
      foreignField: "_id",
      as: "vorgesetzterInfo"
    }
  },
  {
    $unwind: "$vorgesetzterInfo"
  },
  {
    $match: {
      "gehalt": { $gt: "$vorgesetzterInfo.gehalt"}
    }
  },
  {
    $project: {
      _id: 0,
      name: 1,
      gehalt: 1,
      vorgesetzter: {
        name: "$vorgesetzterInfo.name",
        gehalt: "$vorgesetzterInfo.gehalt"
      }
    }
  }
]);

/* Aufgabe2 b*/
db.pers.aggregate([
  {
    $lookup: {
      from: "abt",
      localField: "abteilung.$id",
      foreignField: "_id",
      as: "abteilungInfo"
    }
  },
  {
    $unwind: "$abteilungInfo"
  },
  {
    $group: {
      _id: "$abteilungInfo.ort",
      gesamtgehalt: { $sum: "$gehalt" }
    }
  },
  {
    $sort: {
      gesamtgehalt: -1
    }
  },
  {
    $limit: 1
  },
  {
    $project: {
      _id: 0,
      ort: "$_id",
      gesamtgehalt: 1
    }
  }
]);



