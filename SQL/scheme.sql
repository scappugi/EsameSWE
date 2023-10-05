DROP TABLE IF EXISTS DebitCard;
DROP TABLE IF EXISTS WebUser;
DROP TABLE IF EXISTS Factory;
DROP TABLE IF EXISTS Clothes;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Contains;


CREATE TABLE WebUser (
    codUser integer PRIMARY KEY AUTOINCREMENT,
    userName TEXT,
    password TEXT
);


CREATE TABLE DebitCard (
    codCard TEXT,
    CVV TEXT,
    date DATE,
    userID TEXT,
    PRIMARY KEY (codCard, CVV),
    FOREIGN KEY (userID) REFERENCES WebUser(codUser)
);

CREATE TABLE Factory (
    codStorage integer PRIMARY KEY AUTOINCREMENT,
    name TEXT
);

CREATE TABLE Clothes (
    codClothes INTEGER PRIMARY KEY AUTOINCREMENT ,
    color TEXT,
    category TEXT,
    brand TEXT,
    size TEXT,
    storageID TEXT,
    qty integer,
    price float,
    FOREIGN KEY (StorageID) REFERENCES Storage(codStorage)
);


CREATE TABLE Orders (
    codOrder INTEGER PRIMARY KEY,
    date DATE,
    shipmentDate DATE,
    userID TEXT,
    FOREIGN KEY (userID) REFERENCES WebUser(codUser)
);

CREATE TABLE Contains (
    orderID INTEGER,
    clothesID INTEGER,
    qnt INTEGER,
    PRIMARY KEY (orderID, clothesID),
    FOREIGN KEY (orderID) REFERENCES Orders(codOrder),
    FOREIGN KEY (clothesID) REFERENCES Clothes(codClothes)
);





