/* 
The time dimension table is just an ID, a string description and possibly a date 
or a representation of date as day month year individual fields (see both below).
It is populated to have the days/months/quarters/years
as needed for the fact tables that relate to it.
*/

CREATE table TimeDim (
	TimeID	number (10) not null,
	TimeDesc varchar (11) not null,
	TimeDate date,
	TimeDay varchar (2),
	TimeMonth varchar(2),
	TimeYear varchar(4),
	constraint TimeID_pk Primary key (TimeID)
);

-- Time sequence to generate an ID
CREATE SEQUENCE TimeDimSequence
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10

/* 
Once the table is created it is populated with simple insert statements.
One for every period for which facts are created.

Assuming a daily fact table, we need one row for each day for 5 years, i.e., 1836 rows (5 * 365) + 1 for leap year.
*/	

Insert into TimeDim (TimeDimSequence.nextval, "01-JAN-2010", 01-JAN-2010, "01", "JAN", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "02-JAN-2010", 02-JAN-2010, "01", "JAN", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "03-JAN-2010", 03-JAN-2010, "01", "JAN", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "04-JAN-2010", 04-JAN-2010, "01", "JAN", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "05-JAN-2010", 05-JAN-2010, "01", "JAN", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "06-JAN-2010", 06-JAN-2010, "01", "JAN", "2010");
.....
Insert into TimeDim (TimeDimSequence.nextval, "31-DEC-2014", 31-DEC-2014, "31", "DEC", "2014");

-- Assuming a fact table that is monthly, we need one row for each month for 5 years, i.e., 60 rows more rows.
Insert into TimeDim (TimeDimSequence.nextval, "JAN-2010", 31-JAN-2010,, "JAN", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "FEB-2010", 31-FEB-2010,, "FEB", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "MAR-2010", 31-MAR-2010,, "MAR", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "APR-2010", 31-APR-2010,, "APR", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "MAY-2010", 31-MAY-2010,, "MAY", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "JUN-2010", 31-JUN-2010,, "JUN", "2010");
.....
Insert into TimeDim (TimeDimSequence.nextval, "DEC-2014", 31-DEC-2014,, "DEC", "2014");


-- If we have a quarterly fact table we would have 20 more rows.
Insert into TimeDim (TimeDimSequence.nextval, "Q1-2010", 31-MAR-2010,, "MAR", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "Q2-2010", 30-JUN-2010,, "JUN", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "Q3-2010", 31-SEP-2010,, "SEP", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "Q4-2010", 31-DEC-2010,, "DEC", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "Q1-2011", 31-MAR-2010,, "MAR", "2010");
Insert into TimeDim (TimeDimSequence.nextval, "Q2-2011", 31-JUN-2010,, "JUN", "2010");
.....
Insert into TimeDim (TimeDimSequence.nextval, "Q4-2014", 31-DEC-2014,, "DEC", "2014");