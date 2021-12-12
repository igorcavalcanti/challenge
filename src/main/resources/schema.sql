create table dna_check (id bigint not null, hash varchar(255), result boolean, primary key (id));
create table dna_check_stats (id bigint not null, humans_count decimal(19,2), simians_count decimal(19,2), primary key (id));
create sequence hibernate_sequence start with 1 increment by 1;