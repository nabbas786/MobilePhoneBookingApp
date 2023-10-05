CREATE TABLE MOBILE_PHONE(
		PHONE_ID INT PRIMARY KEY,
		BRAND VARCHAR(200),
		MODEL varchar2(200),
		AVAILABLE boolean,
		booked_by varchar2(200) DEFAULT NULL,
		booked_date date DEFAULT NULL,
		technology varchar2(200) DEFAULT NULL,
		band_2 varchar2(10) DEFAULT NULL,
		band_3 varchar2(10) DEFAULT NULL,
		band_4 varchar2(10) DEFAULT NULL
		);