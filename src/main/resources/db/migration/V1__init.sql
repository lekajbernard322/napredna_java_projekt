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