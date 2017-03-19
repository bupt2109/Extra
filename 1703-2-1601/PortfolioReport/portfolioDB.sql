DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS Emails;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS AssetDeposit;
DROP TABLE IF EXISTS AssetStock;
DROP TABLE IF EXISTS AssetPrivate;
DROP TABLE IF EXISTS Asset;
DROP TABLE IF EXISTS PortfolioAsset;
DROP TABLE IF EXISTS Portfolio;


CREATE TABLE IF NOT EXISTS Person(
PersonCode varchar(20) NOT NULL,
LastName varchar(255) NOT NULL,
FirstName varchar(255) NOT NULL,
Brokerdata char(1),
SECID varchar(20),
PRIMARY KEY (PersonCode)
);

CREATE TABLE IF NOT EXISTS Emails(
id int NOT NULL AUTO_INCREMENT,
Email varchar(255) NOT NULL,
PersonCode varchar(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (PersonCode) REFERENCES Person(PersonCode)
);

CREATE TABLE IF NOT EXISTS Addresses(
id int NOT NULL AUTO_INCREMENT,
Street varchar(255) NOT NULL,
City varchar(255) NOT NULL,
State varchar(255) NOT NULL,
Zip varchar(255),
Country varchar(255) NOT NULL,
PersonCode varchar(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (PersonCode) REFERENCES Person(PersonCode)
);

CREATE TABLE IF NOT EXISTS Asset(
AssetCode varchar(20) NOT NULL,
AssetType char(1) NOT NULL,
AssetLabel varchar(255) NOT NULL,
PRIMARY KEY (AssetCode)
);

CREATE TABLE IF NOT EXISTS AssetDeposit(
id int NOT NULL AUTO_INCREMENT,
Apr double(9,4) NOT NULL,
AssetCode varchar(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (AssetCode) REFERENCES Asset(AssetCode)
);

CREATE TABLE IF NOT EXISTS AssetStock(
id int NOT NULL AUTO_INCREMENT,
QuarterlyDividend double(9,4) NOT NULL,
BaseRateOfReturn double(9,4) NOT NULL,
BetaMeasure double(9,4) NOT NULL,
StockSymbol varchar(255) NOT NULL,
SharePrice double(9,4) NOT NULL,
AssetCode varchar(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (AssetCode) REFERENCES Asset(AssetCode)
);

CREATE TABLE IF NOT EXISTS AssetPrivate(
id int NOT NULL AUTO_INCREMENT,
QuarterlyDividend double(9,4) NOT NULL,
BaseRateOfReturn double(9,4) NOT NULL,
OmegaMeasure double(9,4) NOT NULL,
TotalValue double(12,2) NOT NULL,
AssetCode varchar(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (AssetCode) REFERENCES Asset(AssetCode)
);


CREATE TABLE IF NOT EXISTS Portfolio(
Code varchar(20) NOT NULL,
Owner varchar(20) NOT NULL,
Manager varchar(20) NOT NULL,
Beneficiary varchar(20),
PRIMARY KEY (Code)
);

CREATE TABLE IF NOT EXISTS PortfolioAsset(
id int NOT NULL AUTO_INCREMENT,
PortfolioCode varchar(20) NOT NULL,
AssetCode varchar(20) NOT NULL,
AssetValue double(9,2) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (PortfolioCode) REFERENCES Portfolio(Code)
);



INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('944c', 'Castro', 'Starlin', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('306a', 'Sampson', 'Brock', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('2342', 'O&apos;Brien', 'Miles', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('aef1', 'Gekko', 'Gordon', 'E', 'sec001');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('321f', 'Fox', 'Bud', 'J', 'sec991');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('ma12', 'Sveum', 'Dale', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('321nd', 'Hartnell', 'William', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('nf32a', 'Troughton', 'Patrick', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('321na', 'Pertwee', 'Jon', 'E', 'sec125');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('231', 'Baker', 'Tom', 'E', 'sec221');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('6doc', 'Hurndall', 'Richard', 'J', 'sec982');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('321dr', 'Baker', 'C.', 'J', 'sec543');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('1svndr', 'McCoy', 'Sylvester', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('123lst', 'McGann', 'Paul', 'E', 'sec31x');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('nwdoc1', 'Eccleston', 'Chris', 'J', 'sec953');
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('2ndbestd', 'Tennant', 'David', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('wrddoc', 'Smith', 'Matt', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('bbchar', 'Ehrmantraut', 'Kaylee', null, null);
INSERT INTO Person(PersonCode, LastName, FirstName, Brokerdata, SECID) VALUES ('doc05', 'Davison', 'Peter', null, null);

INSERT INTO Emails(Email,PersonCode) VALUES ('scastro@cubs.com', '944c');
INSERT INTO Emails(Email,PersonCode) VALUES ('starlin_castro13@gmail.com', '944c');
INSERT INTO Emails(Email,PersonCode) VALUES ('brock_f_sampson@gmail.com', '306a');
INSERT INTO Emails(Email,PersonCode) VALUES ('bsampson@venture.com', '306a');
INSERT INTO Emails(Email,PersonCode) VALUES ('bsampson@venture.com', '306a');
INSERT INTO Emails(Email,PersonCode) VALUES ('bfox@gmail.com', '321f');
INSERT INTO Emails(Email,PersonCode) VALUES ('csheen@crazy.net', '321f');
INSERT INTO Emails(Email,PersonCode) VALUES ('sveum@cubs.com', 'ma12');
INSERT INTO Emails(Email,PersonCode) VALUES ('whartnell@doctors.com', '321nd');
INSERT INTO Emails(Email,PersonCode) VALUES ('dr@who.com', '321nd');
INSERT INTO Emails(Email,PersonCode) VALUES ('ptroug@cse.unl.edu', 'nf32a');
INSERT INTO Emails(Email,PersonCode) VALUES ('jpet@whofan.com', '321na');
INSERT INTO Emails(Email,PersonCode) VALUES ('famousdoc@who.com', '231');
INSERT INTO Emails(Email,PersonCode) VALUES ('tbaker@cse.unl.edu', '231');
INSERT INTO Emails(Email,PersonCode) VALUES ('mostfamous@whovian.com', '231');
INSERT INTO Emails(Email,PersonCode) VALUES ('thedoctor@bbc.com', '231');
INSERT INTO Emails(Email,PersonCode) VALUES ('rhurndall@cse.unl.edu', '6doc');
INSERT INTO Emails(Email,PersonCode) VALUES ('richard@unl.edu', '6doc');
INSERT INTO Emails(Email,PersonCode) VALUES ('dr@baker.com', '321dr');
INSERT INTO Emails(Email,PersonCode) VALUES ('slyguy@hotmail.com', '1svndr');
INSERT INTO Emails(Email,PersonCode) VALUES ('mccoy@whofan.com', '1svndr');
INSERT INTO Emails(Email,PersonCode) VALUES ('pmcgann@mlb.com', '123lst');
INSERT INTO Emails(Email,PersonCode) VALUES ('foo@bar.com', '123lst');
INSERT INTO Emails(Email,PersonCode) VALUES ('pmc@unl.edu', '123lst');
INSERT INTO Emails(Email,PersonCode) VALUES ('newguy@whovian.com', 'nwdoc1');
INSERT INTO Emails(Email,PersonCode) VALUES ('actor@shakespeare.com', '2ndbestd');
INSERT INTO Emails(Email,PersonCode) VALUES ('tdavid@unl.edu', '2ndbestd');
INSERT INTO Emails(Email,PersonCode) VALUES ('msmith@who.com', 'wrddoc');
INSERT INTO Emails(Email,PersonCode) VALUES ('thedoc@cse.unl.edu', 'wrddoc');

INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1060 West Addison Street', 'Chicago', 'IL', '60613', 'USA','944c');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('123 N 1st Street', 'Omaha', 'NE', '68116', 'USA','944c');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('123 Friendly Street', 'Ottawa', 'ON', 'K1A 0G9', 'Canada','2342');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1 Wall Street', 'New York', 'NY', '10005-0012', 'USA','aef1');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('321 Bronx Street', 'New York', 'NY', '10004', 'USA','321f');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1060 West Addison Street', 'Chicago', 'IL', '60613', 'USA','ma12');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1060 West Addison Street', 'Chicago', 'IL', '60613', 'USA','321nd');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1060 West Addison Street', 'Chicago', 'IL', '60613', 'USA','nf32a');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('301 Front St W', 'Toronto', 'ON', 'M5V 2T6', 'Canada','321na');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1 Blue Jays Way', 'Toronto', 'ON', 'M5V 1J1', 'Canada','231');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('Campos El290', 'Mexico City', 'FD', null, 'Mexico','6doc');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('Avery Hall,Lincoln', 'Lincoln', 'NE', '68503', 'USA','321dr');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('126-01 Roosevelt Ave', 'Flushing', 'NY', '11368', 'USA','1svndr');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1 MetLife Stadium Dr', 'East Rutherford', 'NJ', '07073', 'USA','123lst');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('1 E 161st St', 'Bronx', 'NY', '10451', 'USA','nwdoc1');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('700 E Grand Ave', 'Chicago', 'IL', '60611', 'USA','2ndbestd');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('333 W 35th St', 'Chicago', 'IL', '60616', 'USA','wrddoc');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('800 West 7th Street', 'Albuquerque', 'NM', '87105', 'USA','bbchar');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('123 Cabo San Lucas', 'Los Cabos', 'BCS', null, 'Mexico','doc05');
INSERT INTO Addresses(Street,City,State,Zip,Country,PersonCode) VALUES ('123 N 1st Street', 'Omaha', 'NE', '68116', 'USA','306a');


INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('AGTSAV', 'D', 'Savings Account');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('BX001-23', 'D', 'Money Market Savings');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('CD99312', 'D', '5-year CD');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('SAVE01', 'D', 'Online Savings A+');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('CD1Y3X', 'D', '1-year Rollover CD');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('RIRA01', 'D', 'Roth IRA');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('GOOG001', 'S', 'Google Inc.');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('DIAGEO12', 'S', 'Diageo PLC');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('123APP', 'S', 'Apple');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('321CC', 'S', 'Coca-Cola');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('No1BURR', 'S', 'Chipotle Mexican Grill');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('B0oWM', 'S', 'Costco');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('N0TPIX', 'S', '3D Systems');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('wOOWoo1S', 'S', 'Canadian National Railway');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('BERK-B', 'S', 'Berkshire Hathaway');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('RENT445', 'P', 'Rental Property');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('CMPROP0121', 'P', 'Commercial Property-Red Oaks Mall');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('FOOD12', 'P', 'Cluckin&apos;s restaurant chain');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('REALStr12', 'P', 'Fred&apos;s Fabulous Hardware Store');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('IND321', 'P', 'Industrial Widget Manufacturers Inc.');
INSERT INTO Asset(AssetCode, AssetType, AssetLabel) VALUES ('RSTOR322', 'P', '7&quot; Vinyl Records');

INSERT INTO AssetDeposit(AssetCode, Apr) VALUES ('AGTSAV', 0.25);
INSERT INTO AssetDeposit(AssetCode, Apr) VALUES ('BX001-23', 1.05);
INSERT INTO AssetDeposit(AssetCode, Apr) VALUES ('CD99312', 4.35);
INSERT INTO AssetDeposit(AssetCode, Apr) VALUES ('SAVE01', 1.90);
INSERT INTO AssetDeposit(AssetCode, Apr) VALUES ('CD1Y3X', 2.75);
INSERT INTO AssetDeposit(AssetCode, Apr) VALUES ('RIRA01', 3.4);

INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('GOOG001', 0.0, 5.6, 1.15, 'GOOG', 814.708);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('DIAGEO12', 12.00, 3.2, 1.04, 'DEO', 122.75);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('123APP', 24.50, 4.3, 1.25, 'AAPL', 429.80);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('321CC', 5.45, 3.1, 0.68, 'KO', 41.08);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('No1BURR', 45.57, 3.19, .94, 'CMG', 341.91);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('B0oWM', 10.00, 2.25, 0.82, 'COST', 106.13);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('N0TPIX', 0.0, 1.25, 1.25, 'DDD', 35.40);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('wOOWoo1S', 2.75, 4.6, 0.79, 'CNI', 97.75);
INSERT INTO AssetStock(AssetCode, QuarterlyDividend, BaseRateOfReturn, BetaMeasure, StockSymbol, SharePrice) 
VALUES ('BERK-B', 0.0, 7.2, 0.29, 'BRK-B', 107.04);

INSERT INTO AssetPrivate(AssetCode, QuarterlyDividend, BaseRateOfReturn, OmegaMeasure, TotalValue) 
VALUES ('RENT445', 2400.00, 0.25, 0.15, 120000);
INSERT INTO AssetPrivate(AssetCode, QuarterlyDividend, BaseRateOfReturn, OmegaMeasure, TotalValue) 
VALUES ('CMPROP0121', 14240, 5.35, 0.25, 10456000);
INSERT INTO AssetPrivate(AssetCode, QuarterlyDividend, BaseRateOfReturn, OmegaMeasure, TotalValue) 
VALUES ('FOOD12', 35000.00, 3.0, 0.32, 1212500.00);
INSERT INTO AssetPrivate(AssetCode, QuarterlyDividend, BaseRateOfReturn, OmegaMeasure, TotalValue) 
VALUES ('REALStr12', 1232, 1.23, 0.13, 54300.00);
INSERT INTO AssetPrivate(AssetCode, QuarterlyDividend, BaseRateOfReturn, OmegaMeasure, TotalValue) 
VALUES ('IND321', 10500, 2, -0.15, 4213333);
INSERT INTO AssetPrivate(AssetCode, QuarterlyDividend, BaseRateOfReturn, OmegaMeasure, TotalValue) 
VALUES ('RSTOR322', 2453.21, 2.1, 0.54, 13500.00);




INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PT001', '944c', 'aef1', 'ma12');
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF001', '2342', 'aef1', null);
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PD102', '321dr', '321f', 'aef1');
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF002', 'wrddoc', '231', '1svndr');
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF003', '2ndbestd', '231', '1svndr');
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF004', '2ndbestd', '6doc', 'bbchar');
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF006', '1svndr', '231', null);
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF007', '231', '231', 'bbchar');
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF200', '6doc', 'nwdoc1', 'bbchar');
INSERT INTO Portfolio(Code, Owner, Manager, Beneficiary) VALUES ('PF300', '1svndr', '6doc', null);

INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PT001', 'BX001-23', 2403.32);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PT001', 'AGTSAV', 26534.21);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PT001', 'B0oWM', 125);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF001', 'BX001-23', 124.32);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF001', 'BERK-B', 1050);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PD102', 'RENT445', 100);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PD102', 'FOOD12', 35.0);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF002', '321CC', 25.5);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF003', 'N0TPIX', 21);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF004', 'RIRA01', 1032.12);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF007', '321CC', 10000);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF007', 'CMPROP0121', 23.5);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF007', 'REALStr12', 12);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF007', 'wOOWoo1S', 50);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF300', 'IND321', 25);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF300', 'RSTOR322', 100);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF300', '123APP', 1000);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF300', 'GOOG001', 100);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF300', 'CD1Y3X', 250000);
INSERT INTO PortfolioAsset(PortfolioCode, AssetCode, AssetValue) VALUES ('PF300', 'RIRA01', 120000);





