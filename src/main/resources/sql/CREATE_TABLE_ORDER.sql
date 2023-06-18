CREATE TABLE "ORDER" (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	DESCRIPTION VARCHAR(100) NOT NULL,
	CLIENT_ID BIGINT NOT NULL,
	CONSTRAINT ORDER_PK PRIMARY KEY (ID)
	CONSTRAINT ORDER_FK FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
