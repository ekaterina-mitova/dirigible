DELETE FROM DGB_SCHEMA_VERSIONS;
INSERT INTO DGB_SCHEMA_VERSIONS (
	SCHV_VERSION, SCHV_DESCRIPTION)
	VALUES (4, 'File Versions Support');

CREATE TABLE DGB_FILE_VERSIONS ( -- the file versions content
	FV_FILE_PATH $KEY_VARCHAR$ NOT NULL,
	FV_VERSION INTEGER NOT NULL,
	FV_CONTENT $BLOB$,
	FV_TYPE INTEGER NOT NULL, -- 1 document, 2 binary
	FV_CONTENT_TYPE VARCHAR(64),
	FV_CREATED_BY VARCHAR(32),
	FV_CREATED_AT $TIMESTAMP$,
	PRIMARY KEY (FV_FILE_PATH, FV_VERSION)
);

