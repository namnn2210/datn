-- Role Table Data
INSERT INTO role(role_name) VALUES ('USER');
INSERT INTO role(role_name) VALUES ('MANAGER');
INSERT INTO role(role_name) VALUES ('ADMIN');

--User Table Data
INSERT INTO `user` ( `address`, `created_at`, `dob`, `email`, `full_name`, `gender`, `password`, `phone`, `status`, `updated_at`, `username`, `role_id`) VALUES
('9B Le Quy Don', '2019-10-31 10:33:51', '1997-10-22', 'ngongocnam22101997@gmail.com', 'Ngo Ngoc Nam', 2, '$2a$10$JrfQrN9sNr1nqJ7qFt55oOPjHcLIztzr2px6yLpeNrL1GGuNMVcHS', '0373924314', 1, '2019-10-31 10:33:51', 'namngocngo22', 3);
INSERT INTO `user` ( `address`, `created_at`, `dob`, `email`, `full_name`, `gender`, `password`, `phone`, `status`, `updated_at`, `username`, `role_id`) VALUES
('9B Le Quy Don', '2019-10-31 10:33:51', '1997-10-22', 'namngocngo22@gmail.com', 'Ngo Ngoc Nam', 2, '$2a$10$JrfQrN9sNr1nqJ7qFt55oOPjHcLIztzr2px6yLpeNrL1GGuNMVcHS', '0373924314', 1, '2019-10-31 10:33:51', 'n31997', 1);


-- Category Table Data
INSERT INTO category(cat_name, description,status) VALUES ('Clothing','Clothing',true);
INSERT INTO category(cat_name, description,status) VALUES ('Activewear', 'Activewear',true);
INSERT INTO category(cat_name, description,status) VALUES ('Accessories', 'Accessories',true);
-- Team Table Data
INSERT INTO type(type_name,cat_id, status) VALUES ('Coats',1,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Denim',1,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Jacket',1,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Polo shirts',1,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Shirts',1 ,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Shorts', 1,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Suits',1,true);


INSERT INTO type(type_name,cat_id, status) VALUES ('Cycling', 2,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Gym & training', 2,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Outdoors', 2,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Running', 2,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Ski & snowboard', 2,true);


INSERT INTO type(type_name,cat_id, status) VALUES ('Betls', 3,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Gloves', 3,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Hats', 3,true);
INSERT INTO type(type_name,cat_id, status) VALUES ('Glasses & frames', 3,true);


-- Product Table Data
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('BARBOUR Bedale wax jacket','https://cdn-images.farfetch-contents.com/14/18/35/50/14183550_20515844_1000.jpg&https://cdn-images.farfetch-contents.com/14/18/35/50/14183550_20515855_1000.jpg&https://cdn-images.farfetch-contents.com/14/18/35/50/14183550_20515866_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,1,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Moncler logo-print padded jacket','https://cdn-images.farfetch-contents.com/15/52/02/70/15520270_27747919_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,1,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Balenciaga double sleeve zip-up jacket','https://cdn-images.farfetch-contents.com/14/66/45/65/14664565_23284586_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,1,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Moncler colour-block jacket','https://cdn-images.farfetch-contents.com/15/16/97/29/15169729_26001427_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,1,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Dsquared2 distressed skinny-fit jeans','https://cdn-images.farfetch-contents.com/14/97/59/03/14975903_25299218_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,2,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Off-White stripe detail slim jeans','https://cdn-images.farfetch-contents.com/14/70/34/82/14703482_24573850_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,2,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Sunflower straight-leg jeans','https://cdn-images.farfetch-contents.com/14/80/77/32/14807732_26806942_480.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,2,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Off-White bleached-effect slim-fit jeans','https://cdn-images.farfetch-contents.com/15/28/20/73/15282073_27248871_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,2,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Gucci Horsebit striped polo shirt','https://cdn-images.farfetch-contents.com/15/46/49/53/15464953_27696329_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,3,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Burberry Checker short-sleeved polo shirt','https://cdn-images.farfetch-contents.com/15/47/52/12/15475212_27438048_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,3,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Missoni striped polo shirt','https://cdn-images.farfetch-contents.com/14/72/62/96/14726296_24505797_1000.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,3,true);
INSERT INTO product(product_name,product_images_url,product_price, description, sold, discount,cat_id,type_id,status) VALUES ('Nanushka stripe knitted short sleeve top','https://cdn-images.farfetch-contents.com/15/23/07/53/15230753_27464786_480.jpg',4.5, 'This black cotton Bedale wax jacket from Barbour features a cord detail, a round neck, a button over front zip fastening, long sleeves, flap pockets and a waxed finish.Made in United Kingdom',0,0,1,3,true);


