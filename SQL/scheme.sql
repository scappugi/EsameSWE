DROP TABLE IF EXISTS DebitCard;
DROP TABLE IF EXISTS WebUser;
DROP TABLE IF EXISTS Factory;
DROP TABLE IF EXISTS Clothes;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Contains;

CREATE TABLE WebUser (
    codUser TEXT PRIMARY KEY,
    UserName TEXT,
    Password TEXT
);


CREATE TABLE DebitCard (
    codCard TEXT,
    CVV TEXT,
    Date DATE,
    UserID TEXT,
    PRIMARY KEY (codCard, CVV)
    FOREIGN KEY (UserID) REFERENCES WebUser(codUser)
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
    taglia TEXT,
    StorageID TEXT,
    FOREIGN KEY (StorageID) REFERENCES Storage(codStorage)
);


CREATE TABLE Orders (
    codOrder INTEGER PRIMARY KEY,
    date DATE,
    shipmentDate DATE,
    UserID TEXT,
    FOREIGN KEY (UserID) REFERENCES WebUser(codUser)
);

CREATE TABLE Contains (
    OrderID INTEGER,
    ClothesID INTEGER,
    qnt INTEGER,
    PRIMARY KEY (OrderID, ClothesID),
    FOREIGN KEY (OrderID) REFERENCES Orders(codOrder),
    FOREIGN KEY (ClothesID) REFERENCES Clothes(codClothes)
);





