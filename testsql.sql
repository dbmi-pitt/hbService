create table hb_person (
    load_source varchar2(20) not null,
    load_date date,
    research_id number(38),
    source_id varchar2(20),
    source_type varchar2(20),
    source_name varchar2(50)
);

-- 1) Normal 
insert into hb_person (load_source, load_date, research_id, source_id, source_type, source_name) values ('MOCK', '08-AUG-17', '1', '1', 'Test', 'Case 1: Normal');

insert into hb_person (load_source, load_date, research_id, source_id, source_type, source_name) values ('MOCK', '08-AUG-17', '2', '2', 'Test', 'Case 1: Normal');

insert into hb_person (load_source, load_date, research_id, source_id, source_type, source_name) values ('MOCK', '08-AUG-17', '3', '3', 'Test', 'Case 1: Normal');

-- 2) Mulitple source
insert into hb_person (load_source, load_date, research_id, source_id, source_type, source_name) values ('MOCK', '08-AUG-17', '1', '4', 'EPIC', 'Case 2: Multiple source');

insert into hb_person (load_source, load_date, research_id, source_id, source_type, source_name) values ('MOCK', '08-AUG-17', '1', '5', 'MARS', 'Case 2: Multiple source');

insert into hb_person (load_source, load_date, research_id, source_id, source_type, source_name) values ('MOCK', '08-AUG-17', '2', '5', 'EMPI', 'Case 2: Multiple source');
