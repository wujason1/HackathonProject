SELECT COUNT(*) FROM prize;

INSERT INTO prize (type) SELECT 'prize1' WHERE NOT EXISTS (SELECT 1 FROM prize WHERE type = 'prize1');
INSERT INTO prize (type) SELECT 'prize2' WHERE NOT EXISTS (SELECT 1 FROM prize WHERE type = 'prize2');
INSERT INTO prize (type) SELECT 'prize3' WHERE NOT EXISTS (SELECT 1 FROM prize WHERE type = 'prize3');


