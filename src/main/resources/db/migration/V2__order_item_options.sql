CREATE TABLE sizes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    extra_price NUMERIC(10,2) DEFAULT 0
);

CREATE TABLE additives (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    extra_price NUMERIC(10,2) DEFAULT 0
);

CREATE TABLE order_item_options (
    id SERIAL PRIMARY KEY,
    order_item_id INT NOT NULL REFERENCES order_items(id) ON DELETE CASCADE,
    size_id INT REFERENCES sizes(id),
    additive_id INT REFERENCES additives(id),
    quantity INT DEFAULT 1
);

ALTER TABLE order_items DROP COLUMN IF EXISTS options;
