CREATE table ResortRevenueFact (
	ResortRevFactID number (10) not null,
	TimeID 	number (10) not null,
	ResortID number (10) not null,
	RoomID number (10) not null,
	CustomerID number (10) not null,
	ReseortRevenue	number (12,2),
	constraint ResortRevFactID_pk Primary key (C_ResortRevFactID_rrv)
	constraint ResortID_fk Foreign key (ResortID) references ResortDim
	constraint IimeID_fk Foreign key (TimeID) references TimeDim
	constraint RoomID_fk Foreign key (RoomID) references RoomDim
	constraint CustomerID_fk Foreign key (CustomerID) references CustomerDim
);

CREATE OR REPLACE PROCEDURE insertIntoFactTable
DECLARE
	lvTimeDimID number(10) := 0;
	lvResortID number(10) := 0;
	lvRoomID number(10) := 0;
	lvCustomerID number := 0;
	lvRevenue number(12,2);

	lvDate date;
	lvResort number := 0;
	lvRoom number := 0;
	lvCustomer number := 0;

	/* 
	Assumption that the invoice table will contain information that is relevant for this operation
	Currently, the Corp 1 data base is missing the invoice date, invoice total, and invoice remaining fields.
	These fields are critual to allow Hookie resorts to create a data warehouse that will answer the three important questions.
	*/
	CURSOR Corp1StaySummaryFact IS
    	SELECT i.D_IssuedDate_IV, r.C_ResortID_RS, ro.C_RoomID_RI, c.C_CustomerID_CR SUM (i.N_InvoiceTotal_IV - i.N_InvoiceRemaining_IV) AS N_InvoiceNet_temp
		FROM 
		  	Resort r JOIN 
		  	RoomID rid USING r.C_ResortID_RS = rid.C_ResortID_RI JOIN
		  	ResRoomID rrid USING b.C_RoomID_RI = rid.C_RoomID_RO JOIN
		  	Invoice i USING rid.C_ResRoomID_RO = i.C_ResRoomID_IV JOIN
		  	Customer c USING i.C_CustomerID_IV = c.C_CustomerID_CR
      	GROUP BY (i.D_IssuedDate_IV, r.C_ResortID_RS, ro.C_RoomID_RI, c.C_CustomerID_CR);

	CURSOR Corp2StaySummaryFact IS
    	SELECT i.D_IssuedDate_In, r.C_ResortCode_Rs, ro.C_RoomNumber_Ro, c.C_CustomerID_Cu SUM (i.N_InvoiceTotal_In - i.N_InvoiceRemaining_In) AS N_InvoiceNet_temp
		FROM 
		  	Resort r JOIN 
		  	Building b USING r.C_ResortCode_Rs = b.C_ResortCode_Bu JOIN
		  	Wing w USING b.C_BuildingCode_Bu = w.C_BuildingCode_Wi JOIN
		  	Floor f USING w.C_WingCode_Wi = f.C_WingCode_Fl JOIN
		  	Room ro USING f.C_FloorNumber_Fl = ro.C_FloorNumber_Ro JOIN
		  	Room_Type rt USING ro.C_RoomType_Ro = rt.C_RoomType_Rt JOIN
		  	Room_Reservation rres USING rt.C_RoomType_Rt = rres.C_RoomType_Rr JOIN
		  	Customer c USING rres.C_CustomerID_Rr = c.C_CustomerID_Cu JOIN
		  	Billing_Party bp USING c.C_billingPartyID_Cu = bp.C_billingPartyID_Bp JOIN
	  		Invoice i USING  bp.C_billingPartyID_Bp = i.C_billingPartyID_in
      	GROUP BY (i.D_IssuedDate_In, r.C_ResortCode_Rs, ro.C_RoomNumber_Ro, c.C_CustomerID_Cu);
BEGIN
	OPEN Corp1StaySummaryFact;
		LOOP
			FETCH Corp1StaySummaryFact CURSOR INTO lvDate, lvResort, lvRoom, lvCustomer, lvRevenue;
			EXIT WHEN Corp1StaySummaryFact%NOTFOUND
			
			SELECT TimeID
			INTO lvTimeDimID
			FROM TimeDim
			WHERE TimeDate = lvDate;

			SELECT ResortID
			INTO lvResortID
			FROM ResortDim
			WHERE ResortID = lvResort;

			SELECT RoomID
			INTO lvRoomID
			FROM RoomDim
			WHERE RoomID = lvRoom;

			SELECT CustomerID 
			INTO lvCustomerID 
			FROM CustomerDim
			WHERE CustomerID = lvCustomer;
			
			/* insert the summarized data for this resort into the DW with the timeID*/
			INSERT INTO ResortRevenueFact (ResortRevenueSequence.nextval, lvTimeDimID, lvResortID, lvRoomID, lvCustomerID,  lvRevenue);
		END LOOP;
   	CLOSE Corp1StaySummaryFact;
DBMS_OUTPUT.PUT_LINE("Finished inserting data into the fact table from Corp 2");

	OPEN Corp2StaySummaryFact;
		LOOP
			FETCH Corp2StaySummaryFact CURSOR INTO lvDate, lvResort, lvRoom, lvCustomer, lvRevenue;
			EXIT WHEN Corp2StaySummaryFact%NOTFOUND
			
			SELECT TimeID
			INTO lvTimeDimID
			FROM TimeDim
			WHERE TimeDate = lvDate;

			SELECT ResortID
			INTO lvResortID
			FROM ResortDim
			WHERE ResortID = lvResort;

			SELECT RoomID
			INTO lvRoomID
			FROM RoomDim
			WHERE RoomID = lvRoom;

			SELECT CustomerID 
			INTO lvCustomerID 
			FROM CustomerDim
			WHERE CustomerID = lvCustomer;
			
			/* insert the summarized data for this resort into the DW with the timeID*/
			INSERT INTO ResortRevenueFact (ResortRevenueSequence.nextval, lvTimeDimID, lvResortID, lvRoomID, lvCustomerID,  lvRevenue);
		END LOOP;
   	CLOSE Corp2StaySummaryFact;
DBMS_OUTPUT.PUT_LINE("Finished inserting data into the fact table from Corp 2");
END;




select 
	t.TimeDesc,
	t. TimeYear,
	rd.ResortName,
	max(r.ReseortRevenue)
from 
	TimeDim t join 
	ResortRevenueFact r using (TimeID) join
	ResortDim rd using (ResortID)
Group by (t.TimeDesc, t.TimeYear, rd.ResortName);


select
	c.CustomerFirstName,
	c.CustomerLastName,
	c.CustomerAddress,
	rd.ResortName
from
	CustomerDim c 
	ResortRevenueFact r using (CustomerID) join
	ResortDim rd using (ResortID)
where c.CustomerResortOccurance > 1;

select
	rom.RoomType,
	rom.RoomPrice,
	AVG(rom.RoomDaysUsed),
	rd.ResortName
from
	RoomDim rom 
	ResortRevenueFact r using (RoomID) join
	ResortDim rd using (ResortID)
Group by (rom.RoomType, rom.RoomPric, rd.ResortName);