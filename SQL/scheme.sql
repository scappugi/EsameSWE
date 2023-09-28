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
    PRIMARY KEY (codCard, CVV)
    FOREIGN KEY (userID) REFERENCES WebUser(codUser)
);

CREATE TABLE Factory (
    codStorage TEXT,
    qnt INTEGER,
    PRIMARY KEY (codStorage)
);

CREATE TABLE Clothes (
    codClothes INTEGER PRIMARY KEY,
    color TEXT,
    category TEXT,
    brand TEXT,
    size TEXT,
    storageID TEXT,
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





