CREATE TABLE prices
(
    id UUID PRIMARY KEY,
    brand_id INTEGER NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    price_list INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    priority INTEGER NOT NULL DEFAULT 0,
    price DECIMAL NOT NULL DEFAULT 0.0,
    curr VARCHAR(3) NOT NULL DEFAULT 'EUR',
    CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

COMMENT ON TABLE prices IS 'Stores all prices.';
COMMENT ON COLUMN prices.id IS 'Unique identifier for each price.';
COMMENT ON COLUMN prices.brand_id IS 'Foreign key of the brand that is part of Inditex (1 = ZARA).';
COMMENT ON COLUMN prices.price_list IS 'Applicable price list for the product.';
COMMENT ON COLUMN prices.start_date IS 'Start date on which the indicated rate applies.';
COMMENT ON COLUMN prices.end_date IS 'End date on which the indicated rate applies.';
COMMENT ON COLUMN prices.product_id IS 'Foreign key for each product';
COMMENT ON COLUMN prices.priority IS 'Price application differentiator. If two rates coincide in a range of dates, the one with the higher priority (higher numerical value) is applied.';
COMMENT ON COLUMN prices.price IS 'Final sale price.';
COMMENT ON COLUMN prices.curr IS 'ISO code for currency.';

INSERT INTO prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES
    (gen_random_uuid(), 1, TIMESTAMP '2020-06-14 00:00:00', TIMESTAMP '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (gen_random_uuid(), 1, TIMESTAMP '2020-06-14 15:00:00', TIMESTAMP '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
    (gen_random_uuid(), 1, TIMESTAMP '2020-06-15 00:00:00', TIMESTAMP '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
    (gen_random_uuid(), 1, TIMESTAMP '2020-06-15 16:00:00', TIMESTAMP '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');
