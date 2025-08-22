-- Initial migration based on existing entities (PostgreSQL)
-- AssetEntity table
CREATE TABLE IF NOT EXISTS asset_entity (
    id VARCHAR(255) PRIMARY KEY,
    asset_class VARCHAR(50) NOT NULL,
    exchange VARCHAR(50) NOT NULL,
    symbol VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    tradable BOOLEAN NOT NULL,
    marginable BOOLEAN NOT NULL,
    maintenance_margin_requirement DOUBLE PRECISION NOT NULL,
    margin_requirement_long VARCHAR(255) NOT NULL,
    margin_requirement_short VARCHAR(255) NOT NULL,
    shortable BOOLEAN NOT NULL,
    easy_to_borrow BOOLEAN NOT NULL,
    fractionable BOOLEAN NOT NULL,
    -- Store attributes inline as a PostgreSQL text array instead of a separate table
    attributes TEXT[] NOT NULL DEFAULT '{}'::text[]
);

-- Optional helpful indexes
CREATE INDEX IF NOT EXISTS idx_asset_entity_symbol ON asset_entity(symbol);
CREATE INDEX IF NOT EXISTS idx_asset_entity_exchange ON asset_entity(exchange);

-- BarEntity table
CREATE TABLE IF NOT EXISTS bar_entity (
    id BIGSERIAL PRIMARY KEY,
    close DOUBLE PRECISION NOT NULL,
    high DOUBLE PRECISION NOT NULL,
    low DOUBLE PRECISION NOT NULL,
    number INT NOT NULL,
    open DOUBLE PRECISION NOT NULL,
    time TIMESTAMP NOT NULL,
    volume DOUBLE PRECISION NOT NULL,
    volume_weighted DOUBLE PRECISION NOT NULL
);

-- Optional helpful index on time for querying bars chronologically
CREATE INDEX IF NOT EXISTS idx_bar_entity_time ON bar_entity(time);
