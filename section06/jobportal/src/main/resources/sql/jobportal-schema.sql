CREATE TABLE companies (
   id BIGINT IDENTITY PRIMARY KEY,
   name VARCHAR(255) NOT NULL UNIQUE,
   logo VARCHAR(500) NULL,
   industry VARCHAR(100) NOT NULL,
   size VARCHAR(50) NOT NULL,
   rating NUMERIC(3,2) NOT NULL,
   locations VARCHAR(1000) NULL,
   founded INT NOT NULL,
   description VARCHAR(MAX) NULL,
   employees INT NULL,
   website VARCHAR(500) NULL,
   created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
   created_by VARCHAR(20) NOT NULL,
   updated_at DATETIME2 NULL,
   updated_by VARCHAR(20) NULL
);

