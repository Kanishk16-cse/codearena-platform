INSERT INTO CHALLENGE (id, name) VALUES (1, 'Sample Challenge');
INSERT INTO QUESTION (id, challenge_id, title, input_data, expected_output) VALUES
 (1, 1, 'Add Numbers', '2 3', '5'),
 (2, 1, 'Print Hello', '', 'Hello, World');