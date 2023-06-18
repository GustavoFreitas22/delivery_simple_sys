CREATE TABLE "USER" (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	LOGIN VARCHAR(254) NOT NULL,
	PASSWORD VARCHAR(254) NOT NULL,
	CONSTRAINT USER_PK PRIMARY KEY (ID)
);