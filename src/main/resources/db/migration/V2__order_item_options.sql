-- 1. Создаём таблицу размеров
CREATE TABLE sizes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    extra_price NUMERIC(10,2) DEFAULT 0
);

-- 2. Создаём таблицу добавок
CREATE TABLE additives (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    extra_price NUMERIC(10,2) DEFAULT 0
);

-- 3. Связующая таблица для опций заказа
CREATE TABLE order_item_options (
    id SERIAL PRIMARY KEY,
    order_item_id INT NOT NULL REFERENCES order_items(id) ON DELETE CASCADE,
    size_id INT REFERENCES sizes(id),
    additive_id INT REFERENCES additives(id),
    quantity INT DEFAULT 1
);

-- 4. (опционально) удалить колонку options из order_items
ALTER TABLE order_items DROP COLUMN IF EXISTS options;
