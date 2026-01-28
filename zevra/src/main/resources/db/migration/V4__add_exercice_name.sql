-- V4 - Ajout de la colonne name à exercices
ALTER TABLE exercices ADD COLUMN name VARCHAR(100) NOT NULL DEFAULT '';

UPDATE exercices SET name = 'Squats' WHERE id = 1;
UPDATE exercices SET name = 'Pompes' WHERE id = 2;
UPDATE exercices SET name = 'Tractions' WHERE id = 3;
UPDATE exercices SET name = 'Fentes' WHERE id = 4;
UPDATE exercices SET name = 'Planche' WHERE id = 5;
UPDATE exercices SET name = 'Développé couché' WHERE id = 6;
UPDATE exercices SET name = 'Curls biceps' WHERE id = 7;
UPDATE exercices SET name = 'Élévations latérales' WHERE id = 8;
UPDATE exercices SET name = 'Crunchs' WHERE id = 9;
UPDATE exercices SET name = 'Burpees' WHERE id = 10;
UPDATE exercices SET name = 'Sauts à la corde' WHERE id = 11;
UPDATE exercices SET name = 'Mountain climbers' WHERE id = 12;
UPDATE exercices SET name = 'Jumping jacks' WHERE id = 13;
UPDATE exercices SET name = 'Cercles d''épaules' WHERE id = 14;
UPDATE exercices SET name = 'Rotations de hanches' WHERE id = 15;
UPDATE exercices SET name = 'Étirements du dos' WHERE id = 16;
UPDATE exercices SET name = 'Étirement des pectoraux' WHERE id = 17;
UPDATE exercices SET name = 'Étirement des jambes' WHERE id = 18;
UPDATE exercices SET name = 'Étirement des bras' WHERE id = 19;
