INSERT INTO categories(id, timestamp, name, parent_category) VALUES ('ac64f54e-c8dd-45b3-9e30-ba09f05cbf76', NOW(), 'Sladkarije', null);
INSERT INTO categories(id, timestamp, name, parent_category) VALUES ('1d9a797f-1be7-4776-87e7-3cfb7095972e', NOW(), 'Čokolade', 'ac64f54e-c8dd-45b3-9e30-ba09f05cbf76');
INSERT INTO categories(id, timestamp, name, parent_category) VALUES ('9dbeb3c1-4f56-441d-8fed-80be0c02f9be', NOW(), 'Bonboni', 'ac64f54e-c8dd-45b3-9e30-ba09f05cbf76');
INSERT INTO categories(id, timestamp, name, parent_category) VALUES ('89841bd7-b8c9-48a1-b2c2-19ad30e774b4', NOW(), 'Mlečni izdelki', null);


INSERT INTO products(id, timestamp, name, description, price, visible, category, image_path) VALUES ('fbace5c1-653c-42c0-aa02-78cc4ea4fac1', NOW(), 'Milka s celimi lešniki', 'Izvrstna čokolada, ki je zelo sladka.', 1.84, true, '1d9a797f-1be7-4776-87e7-3cfb7095972e', 'slika1');
INSERT INTO products(id, timestamp, name, description, price, visible, category, image_path) VALUES ('66100dac-ff08-4ac2-9c1a-fa5120ff4838', NOW(), 'Milka z Oreo piškoti', 'Slaba čokolada, ki je še bolj sladka..', 1.98, true, '1d9a797f-1be7-4776-87e7-3cfb7095972e', 'slika2');
INSERT INTO products(id, timestamp, name, description, price, visible, category, image_path) VALUES ('d519b320-80a3-40e2-ad96-7edd8c878630', NOW(), 'Haribo medvedki', 'Legendarni bonboni.', 1.34, true, '9dbeb3c1-4f56-441d-8fed-80be0c02f9be', 'slika3');
INSERT INTO products(id, timestamp, name, description, price, visible, category, image_path) VALUES ('18c18291-6541-4317-a374-b9a03b65b90a', NOW(), 'Alpsko mleko z bregov', 'Mleko z bregov, ki je dobro.', 0.89, true, '89841bd7-b8c9-48a1-b2c2-19ad30e774b4', 'slika4');
