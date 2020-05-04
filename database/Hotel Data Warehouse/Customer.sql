-- The customer dimension table is unique by its id and has multiple fields to describe a particular customer

CREATE table CustomerDim (
	CustomerID number (10) not null,
	CustomerFirstName varchar (30) not null,
	CustomerLastName varchar (30) not null,
	CustomerPhoneNumber varchar (10) not null,
	CustomerAddress varchar (80) not null, 	-- Corp 2 and Corp 1 concat
	CustomerResortOccurance number (3) not null,
	constraint CustomerID_pk Primary key (CustomerID)
);

-- Procedure that will extract and load the customer data from two separate databases into a customer dimension table.
CREATE OR REPLACE PROCEDURE ExtractAndInsertCustomer
DECLARE
	lvCustomerID number(10) := 0;
  	lvFirstName varchar(30) := "";
	lvLastName varchar(30) := "";
	lvPhoneNumber varchar(10) := "";
	lvAddress varchar(80) := "";
	lvResortOccurance number(3) := 0;

	-- Corp 1
   	CURSOR curCorp1Customer IS
    	SELECT
    		c.C_PersonID_CR,
	      	p.T_PersonFName_PR,
			p.T_PersonLName_PR, p.I_PhoneNumber_PR,
			CONTACT(p.T_StreetAddress_PR ||  ' ' || p.T_City_PR || ' ' || 
				p.T_State_PR || ' ' ||  p.I_ZipCode_PR ' ' || p.T_Country_PR) as Address,
			COUNT(b.C_CustomerID_BP)
		FROM
			Person p JOIN 
			Customer c USING  p.C_PersonID_PR = c.C_PersonID_CR
			BilledParty b USING c.C_CustomerID_CR = b.C_CustomerID_BP
		GROUP BY (c.C_PersonID_CR, p.T_PersonFName_PR, p.T_PersonLName_PR, p.I_PhoneNumber_PR, Address);

	-- Corp 2
	-- Assuption that customer and billing_party have the correct relationship described from the
   	CURSOR curCorp2Customer IS
		SELECT C_CustomerID_Cu, T_CustomerFirstName_Cu, T_CustomerLastName_Cu, I_CustomerPhoneNumber_Cu, T_CustomerAddress_Cu, COUNT(C_BillingPartyID_Cu)
		FROM Customer
		GROUP BY (C_CustomerID_Cu, T_CustomerFirstName_Cu, T_CustomerLastName_Cu, I_CustomerPhoneNumber_Cu, T_CustomerAddress_Cu);

BEGIN
	-- Corp 1
   	OPEN curCorp1Customer;
	   	LOOP 
	    	FETCH curCorp1Customer 
	    	INTO lvCustomerID, lvFirstName, lvLastName, lvPhoneNumber, lvAddress, lvResortOccurance;
     		EXIT WHEN curCorp1Customer%NOTFOUND;
				insert into CustomerDim 
				values (lvCustomerID, lvFirstName, lvLastName, lvPhoneNumber,  lvAddress, lvResortOccurance);
	   	END LOOP;
   	CLOSE curCorp1Customer;
   	DBMS_OUTPUT.PUT_LINE('Finished Customer Corp 1');

	-- Corp 2
   	OPEN curCorp2Customer;
	   	LOOP 
	    	FETCH curCorp1Customer 
	    	INTO lvCustomerID, lvFirstName, lvLastName, lvPhoneNumber, lvAddress, lvResortOccurance;
     		EXIT WHEN curCorp1Customer%NOTFOUND;
				insert into CustomerDim
				values (lvCustomerID, lvFirstName, lvLastName, lvPhoneNumber,  lvAddress, lvResortOccurance);
	   	END LOOP;
   	CLOSE curCorp2Customer;
   	DBMS_OUTPUT.PUT_LINE('Finished Customer Corp 2');
END;