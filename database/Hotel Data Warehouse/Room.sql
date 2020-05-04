-- The Room dimension table is unique by its id and has multiple fields to describe a particular room
CREATE table RoomDim (
	RoomID number (10) not null,
	RoomType varchar (30) not null,
	RoomPrice number (8, 2) not null,
	RoomDaysUsed number (3) not null,
	constraint ResortID_pk Primary key (ResortID)
);

-- Procedure that will extract and load the room data from two separate databases into a room dimension table.
CREATE OR REPLACE PROCEDURE ExtractAndInsertRoom
DECLARE
	lvRoomID number(10) := 0;
	lvRoomType varchar(30) := ""; 
	lvRoomPrice number (8, 2) := 0;
	lvRoomDaysUsed number(3) := 0;

	-- Corp 1
	-- Assumption that the RoomID has a field called RoomType that will allow the a room to be specified and categorized.
	-- Currenrtly from the Data dictionary, SleepingRoom and SuiteRoom are described as orphan tables 
	-- and this fix should join those tables with RoomID.
   	CURSOR curCorp1MeetingRoom IS
    	SELECT
    		rid.C_RoomID_Ri,
    		rid.T_RoomType_Ri,
    		rr.T_RoomRateAmount_RR,
	      	TO_NUMBER(rc.I_NightCount_RC, '999')
		FROM 
				(RoomID rid join 
				ResRoomID ro USING rid.C_RoomID_Ri = ro.C_RoomID_Ro JOIN
				RoomCharges rc USING  rr.C_ResRoomID_RO  = rc.C_ResRoomID_RC) 
			INNER JOIN 
				(RoomRate rr JOIN 
				Reservation r using rr.C_RoomRateID_RR = r.C_RoomRateID_RN)
			USING ro.C_ReservationID_RO = r.C_ReservationID_RN;

	-- Corp 2
   	CURSOR curCorp2Room IS
		SELECT 
			r.C_RoomNumber_Ro,
			rt.C_RoomType_Rt,
			rt.N_RoomPrice_Rt,
			TRUNC(rr.D_ArrivalDate_Rr) - TRUNC(rr.D_DepartureDate_Rr) as TotalNightStay
		FROM  
			Room r JOIN 
			Room_Type rt USING r.C_RoomType_Ro = rt.C_RoomType_Rt JOIN 
			Room_Reservation rr USING  rt.C_RoomType_Rt = rr.C_RoomType_Rr;

BEGIN
	-- Corp 1
   	OPEN curCorp1MeetingRoom;
	   	LOOP 
	    	FETCH curCorp1MeetingRoom 
	    	INTO lvRoomID, lvRoomType, lvRoomPrice, lvRoomDaysUsed;
     		EXIT WHEN curCorp1MeetingRoom%NOTFOUND;
				insert into RoomDim
				values (lvRoomID, lvRoomType, lvRoomPrice, lvRoomDaysUsed);
	   	END LOOP;
   	CLOSE curCorp1MeetingRoom;
   	DBMS_OUTPUT.PUT_LINE('Finished Room Corp 1');

	-- Corp 2
   	OPEN curCorp2Room;
	   	LOOP 
	    	FETCH curCorp2Room 
	    	INTO lvRoomID, lvRoomType, lvRoomPrice, lvRoomDaysUsed;
     		EXIT WHEN curCorp2Room%NOTFOUND;
				insert into RoomDim
				values (lvRoomID, lvRoomType, lvRoomPrice, lvRoomDaysUsed);
	   	END LOOP;
   	CLOSE curCorp2Room;
   	DBMS_OUTPUT.PUT_LINE('Finished  Room Corp 2');
END;