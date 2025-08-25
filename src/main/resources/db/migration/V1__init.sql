CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

INSERT INTO roles (name) VALUES
    ('USER'),
    ('BARISTA'),
    ('ADMIN');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email TEXT,
    phone TEXT,
    password_hash TEXT,
    name TEXT NOT NULL,
    avatar_url TEXT,
    role_id INT NOT NULL REFERENCES roles(id),
    bonus_balance INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE oauth_accounts (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    provider TEXT NOT NULL,
    provider_user_id TEXT NOT NULL,
    access_token TEXT,
    refresh_token TEXT,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE menu_categories (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE menu_items (
    id SERIAL PRIMARY KEY,
    category_id INT REFERENCES menu_categories(id),
    name TEXT NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    image_url TEXT,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE menu_item_options (
    id SERIAL PRIMARY KEY,
    menu_item_id INT REFERENCES menu_items(id),
    name TEXT NOT NULL,
    additional_price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE order_statuses (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

INSERT INTO order_statuses (name) VALUES
    ('CREATED'),
    ('PAID'),
    ('IN_PROGRESS'),
    ('READY'),
    ('DONE'),
    ('CANCELLED');

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    status_id INT NOT NULL REFERENCES order_statuses(id),
    total_price DECIMAL(10, 2) NOT NULL,
    payment_method TEXT NOT NULL,
    bonus_used INT DEFAULT 0,
    bonus_earned INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    menu_item_id INT REFERENCES menu_items(id),
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10, 2) NOT NULL,
    options JSONB,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE bonus_transactions (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    type TEXT NOT NULL,
    amount INT NOT NULL,
    order_id INT REFERENCES orders(id),
    created_at TIMESTAMP DEFAULT now()
);
