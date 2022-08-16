insert into checkout.product values (null, "Baguette", 15, 1.35, true);
insert into checkout.product values (null, "Semita", 5, 2.15, true);
insert into checkout.product values (null, "Peperechas", 6, 0.65, true);
insert into checkout.product values (null, "Relampagos", 11, 0.75, true);
insert into checkout.product values (null, "Empanadas", 13, 1.00, true);
insert into checkout.product values (null, "Canelones", 25, 1.35, false);

insert into checkout.users values("3fd86b73-19d4-4b44-8664-f16e324e6133", null, null, null, null);
insert into checkout.users values("6b4ce797-010b-4893-af28-1d3af150c9f3", "Maria", "Ramirez", "maria@mail.com", "+503 7076 7411");
insert into checkout.users values("855d8a2d-7f58-4f27-a728-c1444e3c72fa", "Juan", "Perez", "juan@mail.com", "+503 7176 7411");
insert into checkout.users values("83929f5a-ee0d-410e-9f0f-47f9847c436f", null, null, null, null);

insert into checkout.useraddress values (null, "855d8a2d-7f58-4f27-a728-c1444e3c72fa", "Casa Juan", "San Salvador", "Soyapango", "14", "Cerca de la plaza", true);
insert into checkout.useraddress values (null, "6b4ce797-010b-4893-af28-1d3af150c9f3", "Casa Maria", "La Libertad", "Santa Tecla", "54", "Por el Santa Cecilia", true);
insert into checkout.useraddress values (null, "3fd86b73-19d4-4b44-8664-f16e324e6133", "Casa Raul", "Cuscatlan", "Suchitoto", "3", "Por la iglesia", true);

insert into checkout.userpayment values (null, "855d8a2d-7f58-4f27-a728-c1444e3c72fa", "4455", "Juan Perez", "nZbV7aEwEsI7Z4zKArF2DHNDlWysaGrHpot6yG02Tp4a6LKlxI580kX851OBhrtg", "s3NGmMYSSEwIKUtvw1PIA78Q0G8RpEqFBcPwqSKyAV6gkonRI10LeglylRUihytg", "NSnpSmKQG1uHa0G7ztjQwbuHAgUbs3wog1XCKuZNtZYXKFkdQM3wzGtJvSPo2w3e", true);
insert into checkout.userpayment values (null, "6b4ce797-010b-4893-af28-1d3af150c9f3", "5555", "Maria Ramirez", "bAR0Li8tfjNOTIGWzxuRDHNDlWysaGrHpot6yG02Tp4a6LKlxI580kX851OBhrtg", "uy56COsAXWWGrzl2MegZA78Q0G8RpEqFBcPwqSKyAV6gkonRI10LeglylRUihytg", "LLEeXSwk0EjvdQoR4OvDwbuHAgUbs3wog1XCKuZNtZYXKFkdQM3wzGtJvSPo2w3e", true);
insert into checkout.userpayment values (null, "3fd86b73-19d4-4b44-8664-f16e324e6133", "1155", "Raul Martinez", "mt9eY6VkbUVcPgvTcCcqDHNDlWysaGrHpot6yG02Tp4a6LKlxI580kX851OBhrtg", "scMlhxOk5SHLl685x4l8A78Q0G8RpEqFBcPwqSKyAV6gkonRI10LeglylRUihytg", "POoKLodYINYFJjXgBKZEwbuHAgUbs3wog1XCKuZNtZYXKFkdQM3wzGtJvSPo2w3e", true);

insert into checkout.checkout values ("5f3969b1-00e6-4fe5-85df-1eff931516b7", "855d8a2d-7f58-4f27-a728-c1444e3c72fa", 1, 1, 22.15, "InProgress");
insert into checkout.checkout values ("7f9729b1-1273-4a0b-9cec-53b49adde8b1", "6b4ce797-010b-4893-af28-1d3af150c9f3", 2, 2, 5.70, "InProgress");
insert into checkout.checkout values ("97c97a6a-9185-488d-91f6-de58b04abcd3", "3fd86b73-19d4-4b44-8664-f16e324e6133", 3, 3, 2.70, "InProgress");

insert into checkout.productorder values (null, "5f3969b1-00e6-4fe5-85df-1eff931516b7", 2, 10, 2.15, 21.50);
insert into checkout.productorder values (null, "5f3969b1-00e6-4fe5-85df-1eff931516b7", 3, 1, 0.65, 0.65);
insert into checkout.productorder values (null, "7f9729b1-1273-4a0b-9cec-53b49adde8b1", 6, 2, 1.35, 2.70);
insert into checkout.productorder values (null, "7f9729b1-1273-4a0b-9cec-53b49adde8b1", 5, 3, 1.00, 3.00);
insert into checkout.productorder values (null, "97c97a6a-9185-488d-91f6-de58b04abcd3", 1, 2, 1.35, 2.70);