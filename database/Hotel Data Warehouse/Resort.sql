-- The Resort dimension table is unique by its id and has multiple fields to describe a particular resort
CREATE table ResortDim (
	ResortID number (10) not null,
	ResortName varchar (30) not null,
	ResortStreetAddress varchar (30) not null,
	ResortCity varchar (30) not null,
	ResortState varchar (2) not null,
	ResortZip varchar (5) not null, 
	ResortCountry varchar (30) not null
	constraint ResortID_pk Primary key (ResortID)
);

-- Procedure that will extract and load the resort data from two separate databases into a resort dimension table.
CREATE OR REPLACE PROCEDURE ExtractAndInsertResort
DECLARE
	lvResortID number(10) := 0
  	lvResortName varchar(30) := "";
	lvResortStreetAddress varchar(30) := ""; 
	lvResortCity varchar(30) := "";
	lvResortState varchar(2) := "";
	lvResortZip varchar(5) := "";
	lvResortCountry varchar(30) := ""; 

	-- Corp 1
   	CURSOR curCorp1Resort IS
    	SELECT
    		r.C_ResortID_RS,
	      	r.T_ResortName_RS,
	      	r.T_StreetAddress_CR,
	      	r.T_City_CR,
	      	r.T_State_CR,
	      	r.I_ZipCode_CR,
	      	c.T_CountryName_CY
		FROM Country c JOIN Resort r USING c.C_Country_CY = r.C_CountryID_RS;

	-- Corp 2
   	CURSOR curCorp2Resort IS
		SELECT 
			r.C_ResortCode_Rs,
			r.T_ResortName_Rs, 
			r.T_StreetAddress_Rs,
			r.C_CityName_Rs,
			r.T_State_Rs,
			r.C_ZipCode_Rs,
			co.C_CountryName_Co
		FROM 
			Country co JOIN
			Region re USING co.C_CountryName_Co = re.C_CountryName_Re JOIN 
			City ci USING re.C_RegionID_Re = ci.C_RegionID_Ci JOIN 
			Resort r USING ci.C_CityName_Ci = r.C_CityName_Rs;

BEGIN
	-- Corp 1
   	OPEN curCorp1Resort;
	   	LOOP 
	    	FETCH curCorp1Resort 
	    	INTO lvResortID, lvResortName, lvResortStreetAddress, lvResortCity, lvResortState, lvResortZip lvResortCountry;
     		EXIT WHEN curCorp1Resort%NOTFOUND;
				insert into ResortDim
				values (lvResortID, lvResortName, lvResortStreetAddress, lvResortCity, lvResortState, lvResortZip, lvResortCountry);
	   	END LOOP;
   	CLOSE curCorp1Resort;
   	DBMS_OUTPUT.PUT_LINE('Finished Resort Corp 1');

	-- Corp 2
   	OPEN curCorp2Resort;
	   	LOOP 
	    	FETCH curCorp2Resort 
	    	INTO lvResortID, lvResortName, lvResortStreetAddress, lvResortCity, lvResortState, lvResortZip, lvResortCountry;
     		EXIT WHEN curCorp2Resort%NOTFOUND;
				insert into ResortDim
				values (lvResortID, lvResortName, lvResortStreetAddress, lvResortCity, lvResortState, lvResortZip, lvResortCountry);
	   	END LOOP;
   	CLOSE curCorp2Resort;
   	DBMS_OUTPUT.PUT_LINE('Finished Resort Corp 2');
END;