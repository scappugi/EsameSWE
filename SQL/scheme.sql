DROP TABLE IF EXISTS DebitCard;
DROP TABLE IF EXISTS WebUser;
DROP TABLE IF EXISTS Factory;
DROP TABLE IF EXISTS Clothes;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Contains;


CREATE TABLE WebUser (
    userName TEXT PRIMARY KEY,
    password TEXT
);


CREATE TABLE DebitCard (
    codCard TEXT,
    CVV TEXT,
    date DATE,
    user TEXT,
    PRIMARY KEY (codCard, CVV),
    FOREIGN KEY (user) REFERENCES WebUser(userName)
);

CREATE TABLE Factory (
    name TEXT PRIMARY KEY
);

CREATE TABLE Clothes (
    codClothes INTEGER PRIMARY KEY ,
    color TEXT,
    category TEXT,
    brand TEXT,
    size TEXT,
    factory TEXT,
    qty integer,
    price float,
    FOREIGN KEY (factory) REFERENCES Factory(name)
);


CREATE TABLE Orders (
    codOrder INTEGER PRIMARY KEY,
    date DATE,
    shipmentDate DATE,
    user TEXT,
    FOREIGN KEY (user) REFERENCES WebUser(userName)
);

CREATE TABLE Contains (
    orderID INTEGER,
    clothesID INTEGER,
    qnt INTEGER,
    PRIMARY KEY (orderID, clothesID),
    FOREIGN KEY (orderID) REFERENCES Orders(codOrder),
    FOREIGN KEY (clothesID) REFERENCES Clothes(codClothes)
);





