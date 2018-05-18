CREATE TABLE korisnik (
	id int identity primary key,
	ime varchar(50) not null,
	prezime varchar(50) not null,
	korisnicko_ime varchar(50) not null,
	lozinka varchar(120) not null,
	aktivan tinyint not null default 1
);

CREATE TABLE korisnik_uloga (
	id int identity primary key,
	korisnik varchar(50) not null,
	uloga varchar(50) not null,
	foreign key (korisnik) references korisnik(korisnicko_ime)
);

CREATE TABLE projekt (
	id int IDENTITY PRIMARY KEY,
	ime VARCHAR(50) NOT NULL,
	opis VARCHAR(200)
);

CREATE TABLE korisnik_projekt(
	korisnik_id INT NOT NULL,
	projekt_id INT NOT NULL,
	FOREIGN KEY (korisnik_id) REFERENCES korisnik(id),
	FOREIGN KEY (projekt_id) REFERENCES projekt(id)
);

CREATE TABLE zadatak(
	id int IDENTITY PRIMARY KEY,
	ime VARCHAR(50) NOT NULL,
	opis VARCHAR(500),
	tip VARCHAR(50),
	prioritet VARCHAR(50),
	datum_stvoren TIMESTAMP NOT NULL,
	datum_uredivan TIMESTAMP,
	datum_rjesen TIMESTAMP,
	datum_ocekivano TIMESTAMP,
	stanje VARCHAR(50),
	reporter int,
	assigne int,
	projekt int,
	FOREIGN KEY (reporter) REFERENCES korisnik(id),
	FOREIGN KEY (assigne) REFERENCES korisnik(id),
	FOREIGN KEY (projekt) REFERENCES projekt(id)
);

CREATE TABLE komentar(
	id int IDENTITY PRIMARY KEY,
	tekst VARCHAR(500),
	zadatak int NOT NULL,
	korisnik int NOT NULL,
	vrijeme TIMESTAMP NOT NULL,
	FOREIGN KEY (korisnik) REFERENCES korisnik(id),
	FOREIGN KEY (zadatak) REFERENCES zadatak(id),
);

CREATE TABLE filter(
	id int IDENTITY PRIMARY KEY,
	ime VARCHAR(50),
	opis VARCHAR(500),
	korisnik int,
	datum_od TIMESTAMP,
	datum_do TIMESTAMP,
	FOREIGN KEY (korisnik) REFERENCES korisnik(id)
);

CREATE TABLE filter_korisnik(
	filter_id int NOT NULL,
	korisnik_id int NOT NULL,
	FOREIGN KEY (filter_id) REFERENCES filter(id)
	FOREIGN KEY (korisnik_id) REFERENCES korisnik(id)
);

CREATE TABLE filter_projekt(
	filter_id int NOT NULL,
	projekt_id int NOT NULL,
	FOREIGN KEY (filter_id) REFERENCES filter(id)
	FOREIGN KEY (projekt_id) REFERENCES projekt(id)
);

CREATE TABLE filter_stanja(
	filter_id int NOT NULL,
	stanje int NOT NULL,
	FOREIGN KEY (filter_id) REFERENCES filter(id)
);

--korisnici
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan) 
	values('Ime1', 'Prezime1', 'korisnik1', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1);
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan) 
	values('Ime2', 'Prezime2', 'korisnik2', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1);
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan) 
	values('Ime3', 'Prezime3', 'korisnik3', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1);
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan) 
	values('Ime4', 'Prezime4', 'korisnik4', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1);
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan) 
	values('Ime5', 'Prezime5', 'korisnik5', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1);
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan) 
	values('Ime6', 'Prezime6', 'admin', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1);

--uloge
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik1', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik2', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik3', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik4', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik5', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('admin', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('admin', 'ROLE_ADMIN');
	
--projekti
insert into projekt(ime, opis)
	values('Projekt1', 'Vestibulum ac orci vitae lacus posuere dictum.');
insert into projekt(ime, opis)
	values('Projekt2', 'Nunc vel augue eget mi ullamcorper dictum in sed metus.');
insert into projekt(ime, opis)
	values('Projekt3', 'Praesent faucibus nisi faucibus elit lacinia, tempus aliquet libero dapibus.');
insert into projekt(ime, opis)
	values('Projekt4', 'Vivamus nec risus malesuada, placerat dui et, consectetur lacus.');
insert into projekt(ime, opis)
	values('Projekt5', 'Sed scelerisque justo eget elit laoreet congue.');
	
--korisnik projekt
insert into korisnik_projekt(korisnik_id, projekt_id)
	values(1, 1);
insert into korisnik_projekt(korisnik_id, projekt_id)
	values(2, 1);
insert into korisnik_projekt(korisnik_id, projekt_id)
	values(3, 1);
insert into korisnik_projekt(korisnik_id, projekt_id)
	values(4, 3);
insert into korisnik_projekt(korisnik_id, projekt_id)
	values(5, 3);
	
--zadaci
insert into zadatak(
		ime,
		opis,
		tip,
		prioritet,
		datum_stvoren,
		datum_uredivan,
		datum_rjesen,
		datum_ocekivano,
		stanje,
		reporter_id,
		assigne_id,
		projekt_id)
	values(
		'Zadatak1',
		'Vestibulum ac orci vitae lacus posuere dictum.',
		'Feature',
		'Medium',
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		'TO DO',
		2,
		1,
		2
	);
insert into zadatak(
		ime,
		opis,
		tip,
		prioritet,
		datum_stvoren,
		datum_uredivan,
		datum_rjesen,
		datum_ocekivano,
		stanje,
		reporter_id,
		assigne_id,
		projekt_id)
	values(
		'Zadatak2',
		'Vestibulum ac orci vitae lacus posuere dictum.',
		'Feature',
		'Medium',
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		'TO DO',
		1,
		2,
		1
	);
insert into zadatak(
		ime,
		opis,
		tip,
		prioritet,
		datum_stvoren,
		datum_uredivan,
		datum_rjesen,
		datum_ocekivano,
		stanje,
		reporter_id,
		assigne_id,
		projekt_id)
	values(
		'Zadatak3',
		'Vestibulum ac orci vitae lacus posuere dictum.',
		'Feature',
		'Medium',
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		'TO DO',
		4,
		1,
		3
	);
insert into zadatak(
		ime,
		opis,
		tip,
		prioritet,
		datum_stvoren,
		datum_uredivan,
		datum_rjesen,
		datum_ocekivano,
		stanje,
		reporter_id,
		assigne_id,
		projekt_id)
	values(
		'Zadatak4',
		'Vestibulum ac orci vitae lacus posuere dictum.',
		'Feature',
		'Medium',
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'),
		'TO DO',
		4,
		3,
		3
	);

--komentari
insert into komentar(
		tekst,
		zadatak_id,
		korisnik_id,
		vrijeme)
	values(
		'Vestibulum ac orci vitae lacus posuere dictum.',
		1,
		2,
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss')
	);
insert into komentar(
		tekst,
		zadatak_id,
		korisnik_id,
		vrijeme)
	values(
		'Vestibulum ac orci vitae lacus posuere dictum.',
		2,
		2,
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss')
	);
insert into komentar(
		tekst,
		zadatak_id,
		korisnik_id,
		vrijeme)
	values(
		'Vestibulum ac orci vitae lacus posuere dictum.',
		3,
		2,
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss')
	);
insert into komentar(
		tekst,
		zadatak_id,
		korisnik_id,
		vrijeme)
	values(
		'Vestibulum ac orci vitae lacus posuere dictum.',
		3,
		1,
		parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss')
	);