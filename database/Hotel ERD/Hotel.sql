-- All tables are important in the database so I wrote the DDL for all
-- I would rank the top 10 entities to be:
-- 1. Customer
-- 2. Room
-- 3. Reservation
-- 4. Bill
-- 5. Billed Party
-- 6. Event
-- 7. Facility
-- 8. Service
-- 9. Hotel
-- 10. Card


-- e.g., Patient(C_Patient_PA, T_PatientFirstName_PA, d_BirthDate_PA). 
-- Use the following naming convention for your attributes:
-- c_ indicates a key or foreign key attribute; 
-- d_ is a date variable;
-- i_ indicates an integer, 
-- n_ indicates some numeric value with decimal places,
-- and t_ indicates an attribute contains a text or string value.
-- Also, add a two character class abbreviation, 
-- e.g., _pa, indicating the class the attribute is in the Patient table. 
-- Note any assumptions. 
Address(C_Address_ID_AD, T_Street_AD, T_State_AD, T_City_AD,T_Country_AD, N_Zip_AD, N_Phone_AD)
Hotel(C_Hotel_ID_HO, T_Name_HO, C_Address_ID_HO)
Building(C_Building_ID_BU, T_Name_BU, C_Hotel_ID_BU)
Wing(C_Wing_ID_WI, N_Code_WI, T_Pool_Proximity_WI, T_Parking_Garage_Proximity_WI, T_Handicap_Access_Proximity_WI, C_Hotel_ID_WI)
Floor(C_Floor_ID_FL, N_Number_FL, T_Is_Somoking_FL, C_Wing_ID_FL)
Room(C_Room_ID_RO, T_Type_RO, T_Availability_RO, T_Condition_RO, N_Capacity_RO, N_Size_RO, N_Rate_RO, T_Is_Sutite_RO, N_First_Adjacent_Number_RO, N_Second_Adjacent_Number_RO, C_Floor_ID_RO)
Meeting_Room(C_Room_ID_ME, T_Is_Out_Doors_ME, T_Has_Wall_ME)
Sleeping_Room(C_Room_ID_SL, T_Has_Private_Door_SL, T_Phone_Number_SL)
Bed(C_Bed_ID_BE, T_Description_BE, T_Side_BE)
Sleeping_Bed(C_Bed_ID_SL, C_Room_ID_SL, N_Quantity_SL)
Room_Equipment(C_Room_Equipment_ID_RO, T_Description_RO, T_Condition_RO, C_Room_ID_RO)
Card(C_Card_ID_CA, N_Pin_CA, T_Last_Swipe_Location_CA)
Employee(C_Employe_ID_EM, T_Employe_Name_EM, C_Hotel_ID_EM, C_Card_ID_EM, C_Address_ID_EM)
Room_Card_Reader(C_Card_ID_RO, C_Room_ID_RO, D_Time_Of_Swipe_RO, T_Locaiton_RO)
Facility(C_Facility_ID_FA, T_Name_FA, T_Location_FA, C_Hotel_ID_FA)
Facility_Card_Reader(C_Card_ID_FA, C_Facility_ID_FA, D_Time_Of_Swipe_FA, T_Locaiton_FA)
Event(C_Event_ID_EV, T_Name_EV, N_Estimated_Attendance_EV, N_Estimated_Number_Of_Guest_EV, D_Event_Date_EV)
Facility_Event(C_Facility_ID_FA, C_Event_ID_FA, N_Duration_FA)
Billed_Party(C_Billed_Party_ID_BI, T_Name_BI, C_Address_ID_BI)
Customer(C_Customer_ID_CU, T_Type_CU, T_Name_CU, T_Qualification_CU, C_Billed_Party_ID_CU, C_Card_ID_CU, C_Address_ID_CU)
Guest(C_Customer_ID_GU, T_Wants_Privacy_GU)
Host(C_Customer_ID_HO, T_Wants_Meals_HO)
Reservation(C_Reservation_ID_RE, T_Type_RE, D_Requested_Date_RE, D_Reservation_Date_RE, N_Party_Size_RE, D_Start_Time_RE, D_End_Date_RE, N_Adjusted_Rate_RE, C_Customer_ID_RE, C_Room_ID_RE)
Sleeping_Room_Reservation(C_Reservation_ID_SL, N_Surcharge_SL)
Meeting_Room_Reservation(C_Reservation_ID_ME, N_Eating_Charge_ME)
Attendance(C_Event_ID_AT, C_Customer_ID_AT, T_Host_AT)
Bill(C_Bill_ID_BI, N_Total_Cost_BI)
Room_Bill(C_Billed_ID_RO, C_Reservation_ID_RO, N_Bill_Split_BI)
Inovice(C_Bill_ID_IN, C_Billed_ID_IN, D_Invoice_Date_IN, N_Amount_IN, N_Remaining_IN)
Service(C_Service_ID_SE, T_Description_SE, N_Price_SE)
Service_Use(C_Service_Use_ID_SE, D_Use_Date_SE, C_Service_ID_SE, C_Customer_ID_SE, C_Billed_ID_SE)
Facility_Use(C_Facility_Use_ID_FA, D_Use_Date_FA, C_Facility_ID_FA, C_Customer_ID_FA, C_Billed_ID_FA)
Deposit(C_Billed_ID_DE, C_Customer_ID_DE, D_Deposite_Date_DE)


CREATE TABLE Address (
	C_Address_ID_AD number (10) not null,
	T_Street_AD varchar (30) not null,
	T_State_AD char (2) not null,
	T_City_AD varchar (30) not null,
	T_Country_AD varchar (30) not null,
	N_Zip_AD number (5) not null,
	N_Phone_AD number (11) not null,
	constraint C_Address_ID_AD_pk Primary key (C_Address_ID_AD)
);

CREATE TABLE Hotel (
	C_Hotel_ID_HO number (10) not null,
	T_Name_HO varchar (30) not null,
	C_Address_ID_HO varchar (10) not null,
	constraint C_Hotel_ID_HO_pk Primary key (C_Hotel_ID_HO),
	constraint C_Address_ID_HO_Fk Foreign key (C_Address_ID_HO) references Address
);

CREATE TABLE Building (
	C_Building_ID_BU number (10) not null,
	T_Name_BU varchar (30) not null,
	C_Hotel_ID_BU number (10) not null,
	constraint C_Building_ID_BU_pk Primary key (C_Building_ID_BU),
	constraint C_Hotel_ID_BU_Fk Foreign key (C_Hotel_ID_BU) references Hotel
);

CREATE TABLE Wing (
	C_Wing_ID_WI number (10) not null,
	N_Code_WI number (5) not null,
	T_Pool_Proximity_WI varchar (10) not null,
	T_Parking_Garage_Proximity_WI varchar (10) not null,
	T_Handicap_Access_Proximity_WI varchar (10) not null,
	C_Building_ID_WI number (10) not null,
	constraint C_Wing_ID_WI_pk Primary key (C_Wing_ID_WI),
	constraint C_Building_ID_WI_fk Foreign key (C_Building_ID_WI) references Building
);

CREATE TABLE Floor (
	C_Floor_ID_FL number (10) not null,
	N_Number_FL number (2) not null,
	T_Is_Somoking_FL char (1) not null,
	C_Wing_ID_FL number (10) not null,
	constraint C_Floor_ID_FL_pk Primary key (C_Floor_ID_FL),
	constraint C_Wing_ID_FL_fk Foreign key (C_Wing_ID_FL) references Wing
);

CREATE TABLE Room (
	C_Room_ID_RO number (10) not null,
	T_Type_RO char (1) not null,
	T_Availability_RO varchar (10) not null,
	T_Condition_RO varchar (10) not null,
	N_Capacity_RO number (1) not null,
	N_Size_RO varchar (10) not null,
	N_Rate_RO number (10, 2) not null,
	T_Is_Sutite_RO char (1) not null,
	N_First_Adjacent_Number_RO number (10) not null,
	N_Second_Adjacent_Number_RO number (10),
	C_Floor_ID_RO number (10) not null,
	constraint C_Room_ID_RO_pk Primary key (C_Room_ID_RO),
	constraint C_Floor_ID_RO_fk Foreign key (C_Floor_ID_RO) references Floor
);

CREATE TABLE Meeting_Room (
	C_Room_ID_ME number (10) not null,
	T_Is_Out_Doors_ME char (1) not null,
	T_Has_Wall_ME char (1) not null,
	constraint C_Room_ID_ME_pk Primary key (C_Room_ID_ME),
	constraint C_Room_ID_ME_fk Foreign key (C_Room_ID_ME) references Room
);

CREATE TABLE Sleeping_Room (
	C_Room_ID_SL number (10) not null,
	T_Has_Private_Door_SL char (1) not null,
	T_Has_Private_Door_SL number (11) not null,
	constraint C_Room_ID_SL_pk Primary key (C_Room_ID_SL),
	constraint C_Room_ID_SL_fk Foreign key (C_Room_ID_SL) references Room
);

CREATE TABLE Bed (
	C_Bed_ID_BE number (10) not null,
	T_Description_BE varchar (10) not null,
	T_Side_BE varchar (10),
	constraint C_Bed_ID_BE_pk Primary key (C_Bed_ID_BE)
);

CREATE TABLE Sleeping_Bed (
	C_Bed_ID_SL number (10) not null,
	C_Room_ID_SL number (10) not null,
	N_Quantity_SL number (1) not null,
	constraint C_Bed_ID_SL_pk Primary key (C_Bed_ID_SL),
	constraint C_Room_ID_SL_pk Primary key (C_Room_ID_SL),
	constraint C_Bed_ID_SL_fk Foreign key (C_Bed_ID_SL) references Bed,
	constraint C_Room_ID_SL_fk Foreign key (C_Room_ID_SL) references Sleeping_Room
);

CREATE TABLE Room_Equipment (
	C_Room_Equipment_ID_RO number (10) not null,
	T_Description_RO varchar (20) not null,
	T_Condition_RO varchar (10) not null,
	C_Room_ID_RO number (10) not null,
	constraint C_Room_Equipment_ID_RO_pk Primary key (C_Room_Equipment_ID_RO),
	constraint C_Room_ID_RO_fk Foreign key (C_Room_ID_RO) references Room
);

CREATE TABLE Card (
	C_Card_ID_CA number (10) not null,
	N_Pin_CA number (10) not null,
	T_Last_Swipe_Location_CA varchar (10) ,
	constraint C_Card_ID_CA_pk Primary key (C_Card_ID_CA)
);

CREATE TABLE Employee (
	C_Employe_ID_EM number (10) not null,
	T_Employe_Name_EM varchar (20) not null,
	C_Hotel_ID_EM number (10) not null,
	C_Card_ID_EM number (10) not null,
	C_Address_ID_EM number (10) not null,
	constraint C_Employe_ID_EM_pk Primary key (C_Employe_ID_EM),
	constraint C_Hotel_ID_EM_fk Foreign key (C_Hotel_ID_EM) references Hotel,
	constraint C_Card_ID_EM_fk Foreign key (C_Card_ID_EM) references Card,
	constraint C_Address_ID_EM_Fk Foreign key (C_Address_ID_EM) references Address
);

CREATE TABLE Room_Card_Reader (
	C_Card_ID_RO number (10) not null,
	C_Room_ID_RO number (10) not null,
	D_Time_Of_Swipe_RO Date,
	T_Locaiton_RO varchar (10),
	constraint C_Card_ID_RO_pk Primary key (C_Card_ID_RO),
	constraint C_Room_ID_RO_pk Primary key (C_Room_ID_RO),
	constraint C_Card_ID_RO_fk Foreign key (C_Card_ID_RO) references Card,
	constraint C_Room_ID_RO_fk Foreign key (C_Room_ID_RO) references Room
);


CREATE TABLE Facility (
	C_Facility_ID_FA number (10) not null,
	T_Name_FA varchar (10) not null,
	T_Location_FA varchar (10) not null,
	C_Hotel_ID_FA number (10) not null,
	constraint C_Facility_ID_FA_pk Primary key (C_Facility_ID_FA),
	constraint C_Hotel_ID_FA_fk Foreign key (C_Hotel_ID_FA) references Hotel
);


CREATE TABLE Facility_Card_Reader (
	C_Card_ID_FA number (10) not null,
	C_Facility_ID_FA number (10) not null,
	D_Time_Of_Swipe_FA Date,
	T_Locaiton_FA varchar (10),
	constraint C_Card_ID_FA_pk Primary key (C_Card_ID_FA),
	constraint C_Facility_ID_FA_pk Primary key (C_Facility_ID_FA),
	constraint C_Card_ID_FA_fk Foreign key (C_Card_ID_FA) references Card,
	constraint C_Facility_ID_FA_fk Foreign key (C_Facility_ID_FA) references Facility
);

CREATE TABLE Event (
	C_Event_ID_EV number (10) not null,
	T_Name_EV varchar (20) not null,
	N_Estimated_Attendance_EV number (4) not null,
	N_Estimated_Number_Of_Guest_EV number (4) not null,
	D_Event_Date_EV Date not null,
	T_Hotst_EV varchar (20) not null,
	constraint C_Event_ID_EV_pk Primary key (C_Event_ID_EV)
);

CREATE TABLE Facility_Event (
	C_Facility_ID_FA number (10) not null,
	C_Event_ID_FA number (10) not null,
	N_Duration_FA number (2),
	constraint C_Facility_ID_FA_pk Primary key (C_Facility_ID_FA),
	constraint C_Event_ID_FA_pk Primary key (C_Event_ID_FA),
	constraint C_Facility_ID_FA_fk Foreign key (C_Facility_ID_FA) references Facility,
	constraint C_Event_ID_FA_fk Foreign key (C_Event_ID_FA) references Event
);

CREATE TABLE Billed_Party (
	C_Billed_Party_ID_BI number (10) not null,
	T_Name_BI varchar (20) not null,
	C_Address_ID_BI number (10) not null,
	constraint C_Billed_Party_ID_BI_pk Primary key (C_Billed_Party_ID_BI)
	constraint C_Address_ID_BI_fk Foreign key (C_Address_ID_BI) references Address
);

CREATE TABLE Customer (
	C_Customer_ID_CU number (10) not null,
	T_Type_CU char (1) not null,
	T_Name_CU varchar (20) not null,
	T_Qualification_CU varchar (20) not null,
	C_Billed_Party_ID_CU number (10) not null,
	C_Card_ID_CU number (10) not null,
	C_Address_ID_CU number (10) not null,
	constraint C_Customer_ID_CU_pk Primary key (C_Customer_ID_CU),
	constraint C_Billed_Party_ID_CU_pk Foreign key (C_Billed_Party_ID_CU) references Billed_Party,
	constraint C_Card_ID_CU_fk Foreign key (C_Card_ID_CU) references Card,
	constraint C_Address_ID_CU_fk Foreign key (C_Address_ID_CU) references Address
);

CREATE TABLE Guest (
	C_Customer_ID_GU number (10) not null,
	T_Wants_Privacy_GU char (1) not null,
	constraint C_Customer_ID_GU_pk Primary key (C_Customer_ID_GU),
	constraint C_Customer_ID_GU_fk Foreign key (C_Customer_ID_GU) references Customer
);

CREATE TABLE Host (
	C_Customer_ID_HO number (10) not null,
	Host_Wants_Meals char (1) not null,
	constraint C_Customer_ID_HO_pk Primary key (C_Customer_ID_HO),
	constraint C_Customer_ID_HO_fk Foreign key (C_Customer_ID_HO) references Customer
);

CREATE TABLE Reservation (
	C_Reservation_ID_RE number (10) not null,
	T_Type_RE char (1) not null,
	D_Requested_Date_RE Date not null,
	D_Reservation_Date_RE Date not null,
	N_Party_Size_RE number (4),
	D_Start_Time_RE Date (4) not null,
	D_End_Date_RE Date (4) not null,
	N_Adjusted_Rate_RE number (20,2) not null,
	C_Customer_ID_RE number (10) not null,
	C_Room_ID_RE number (10) not null,
	constraint C_Reservation_ID_RE_pk Primary key (C_Reservation_ID_RE),
	constraint C_Customer_ID_RE_fk Foreign key (C_Customer_ID_RE) references Customer,
	constraint C_Room_ID_RE_fk Foreign key (C_Room_ID_RE) references Room
);

CREATE TABLE Sleeping_Room_Reservation (
	C_Reservation_ID_SL number (10) not null,
	N_Surcharge_SL char (1) not null,
	constraint C_Reservation_ID_SL_pk Primary key (C_Reservation_ID_SL),
	constraint C_Reservation_ID_SL_fk Foreign key (C_Reservation_ID_SL) references Reservation
);

CREATE TABLE Meeting_Room_Reservation (
	C_Reservation_ID_ME number (10) not null,
	N_Eating_Charge_ME char (1) not null,
	constraint C_Reservation_ID_ME_pk Primary key (C_Reservation_ID_ME),
	constraint C_Reservation_ID_ME_fk Foreign key (C_Reservation_ID_ME) references Reservation
);


CREATE TABLE Meeting_Room_Reservation (
	Reservation_ID number (10) not null,
	Meeting_Room_Reservation_Has_Eating_Usage char (1) not null,
	constraint Reservation_ID_pk Primary key (Reservation_ID),
	constraint Reservation_ID_fk Foreign key (Reservation_ID) references Reservation
);

CREATE TABLE Attendance (
	C_Event_ID_AT number (10) not null,
	C_Customer_ID_AT number (10) not null,
	constraint C_Event_ID_AT_pk Primary key (C_Event_ID_AT),
	constraint C_Customer_ID_AT_ID_pk Primary key (C_Customer_ID_AT),
	constraint C_Event_ID_AT_fk Foreign key (C_Event_ID_AT) references Event,
	constraint C_Customer_ID_AT_fk Foreign key (C_Customer_ID_AT) references Customer
);

CREATE TABLE Bill (
	C_Bill_ID_BI number (10) not null,
	N_Total_Cost_BI number (20.2) not null,
	constraint Bill_ID_pk Primary key (C_Bill_ID_BI),
);

CREATE TABLE Room_Bill (
	C_Bill_ID_RO number (10) not null,
	C_Reservation_ID_RO number (10) not null,
	N_Bill_Split_BI number (2) not null,
	constraint C_Bill_ID_RO_pk Primary key (C_Bill_ID_RO),
	constraint C_Reservation_ID_RO_pk Primary key (C_Reservation_ID_RO),
	constraint C_Bill_ID_RO_fk Foreign key (C_Bill_ID_RO) references Bill
	constraint C_Reservation_ID_RO_fk Foreign key (C_Reservation_ID_RO) references Reservation
);

CREATE TABLE Invoice (
	C_Bill_ID_IN number (10) not null,
	C_Billed_ID_IN number (10) not null,
	D_Invoice_Date_IN Date not null,
	N_Amount_IN number (20,2) not null,
	N_Remaining_IN number (20,2) not null,
	constraint C_Bill_ID_IN_pk Primary key (C_Bill_ID_IN),
	constraint C_Billed_ID_IN_pk Primary key (C_Billed_ID_IN),
	constraint C_Bill_ID_IN_fk Foreign key (C_Bill_ID_IN) references Bill
	constraint C_Billed_ID_IN_fk Foreign key (C_Billed_ID_IN) references Billed_Party
);


CREATE TABLE Service (
	C_Service_ID_SE number (10) not null,
	T_Description_SE varchar (20) not null,
	N_Price_SE number (20,2) not null,
	constraint C_Service_ID_SE_pk Primary key (C_Service_ID_SE)
)

CREATE TABLE Service_Use (
	C_Service_Use_ID_SE number (10) not null,
	D_Use_Date_SE Date not null,
	C_Service_ID_SE number (10) not null,
	C_Customer_ID_SE number (10) not null,
	C_Billed_ID_SE number (10) not null,
	constraint C_Service_Use_ID_SE_pk Primary key (C_Service_Use_ID_SE),
	constraint C_Service_ID_SE_fk Foreign key (C_Service_ID_SE) references Service,
	constraint C_Customer_ID_SE_fk Foreign key (C_Customer_ID_SE) references Customer,
	constraint C_Billed_ID_SE_fk Foreign key (C_Billed_ID_SE) references Bill
);

CREATE TABLE Facility_Use (
	C_Facility_Use_ID_FA number (10) not null,
	D_Use_Date_FA Date not null,
	C_Facility_ID_FA number (10) not null,
	C_Customer_ID_FA number (10) not null,
	C_Billed_ID_FA number (10) not null,
	constraint C_Facility_Use_ID_FA_pk Primary key (C_Facility_Use_ID_FA),
	constraint C_Facility_ID_FA_fk Foreign key (C_Facility_ID_FA) references Facility,
	constraint C_Customer_ID_FA_fk Foreign key (C_Customer_ID_FA) references Customer,
	constraint C_Billed_ID_FA_fk Foreign key (C_Billed_ID_FA) references Bill
);

CREATE TABLE Deposit (
	C_Billed_ID_DE number (10) not null,
	C_Customer_ID_DE number (10) not null,
	D_Deposit_Date_DE Date not null,
	constraint C_Billed_ID_DE_pk Primary key (C_Billed_ID_DE),
	constraint C_Customer_ID_DE_pk Primary key (C_Customer_ID_DE),
	constraint C_Billed_ID_DE_fk Foreign key (C_Billed_ID_DE) references Bill
	constraint C_Customer_ID_DE_fk Foreign key (C_Customer_ID_DE) references Customer
);