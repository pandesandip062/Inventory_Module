CREATE TABLE products (

    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(200) NOT NULL,

    description VARCHAR(2000),

    purchase_desc VARCHAR(2000),

    note VARCHAR(2000),

    product_number VARCHAR(100) NOT NULL,

    is_taxable BOOLEAN,

    unit_name VARCHAR(50),

    min_qty_low_stock_unit VARCHAR(50),

    max_qty_reorder_unit VARCHAR(50),

    min_qty_low_stock DECIMAL(19,4),

    max_qty_reorder DECIMAL(19,4),

    is_free_product BOOLEAN,

    purchase_price DECIMAL(19,4),

    sale_price DECIMAL(19,4),

    is_published BOOLEAN,

    published_date DATETIME,

    default_category_name VARCHAR(200),

    sub_category_name VARCHAR(200),

    can_sale BOOLEAN,

    is_unique BOOLEAN,

    is_disabled BOOLEAN,

    disabled_reason VARCHAR(500),

    product_type VARCHAR(50),

    item_tracking_type VARCHAR(50),

    parent_item_id BIGINT,

    lot_number_editable BOOLEAN,

    lot_number_unique BOOLEAN,

    serial_editable BOOLEAN,

    last_direct_cost DECIMAL(19,4),

    markup_percentage DECIMAL(19,4),

    margin_percentage DECIMAL(19,4),

    organization_id BIGINT,

    created_at DATETIME NOT NULL,

    updated_at DATETIME,

    CONSTRAINT uk_product_number
        UNIQUE (product_number)
);


CREATE TABLE stocks (

    stock_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    product_id BIGINT NOT NULL,

    location_id BIGINT NOT NULL,

    location_name VARCHAR(200),

    quantity_on_hand DECIMAL(19,4) NOT NULL DEFAULT 0,

    available_quantity DECIMAL(19,4) NOT NULL DEFAULT 0,

    quantity_booked DECIMAL(19,4) NOT NULL DEFAULT 0,

    quantity_in_po DECIMAL(19,4) NOT NULL DEFAULT 0,

    quantity_in_so DECIMAL(19,4) NOT NULL DEFAULT 0,

    back_order_quantity DECIMAL(19,4) NOT NULL DEFAULT 0,

    unit_name VARCHAR(50),

    last_adjustment_note VARCHAR(1000),

    created_at DATETIME NOT NULL,

    updated_at DATETIME,

    CONSTRAINT fk_stock_product
        FOREIGN KEY (product_id)
        REFERENCES products(product_id),

    CONSTRAINT uk_product_location
        UNIQUE (product_id, location_id)
);