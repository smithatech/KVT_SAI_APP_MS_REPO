INSERT INTO app_user (user_id, email, password, full_name, preferred_language, role) VALUES
('user-1', 'user@example.com', 'password', 'Demo User', 'en', 'USER'),
('admin-1', 'admin@example.com', 'password', 'Admin User', 'en', 'ADMIN');

INSERT INTO exhibition (exhibition_id, name, description, start_date, end_date, location, status) VALUES
('ex-1', 'Tech Expo 2025', 'A demo exhibition', DATE '2025-10-01', DATE '2025-10-05', 'Bengaluru', 'Published');

INSERT INTO module (module_id, name, description, assigned_team_id, exhibition_id) VALUES
('mod-1', 'AI Innovations', 'Latest in AI', 'team-42', 'ex-1');

INSERT INTO stall (stall_id, name, description, stall_number, layout, module_id) VALUES
('stall-1', 'Vision Prototypes', 'Computer vision demos', 'A12', '2x2', 'mod-1');

INSERT INTO poster_content (content_id, language_code, poster_media_url, content_text, stall_id) VALUES
('pc-1', 'en', 'https://example.com/poster.png', 'Welcome to our stall!', 'stall-1');

INSERT INTO questionnaire (questionnaire_id, name, description, status) VALUES
('q-1', 'Visitor Feedback', 'Tell us what you think', 'Active');

INSERT INTO question_item (questionnaire_id, question_text, answer_type, options_csv) VALUES
('q-1', 'How would you rate the exhibition?', 'Rating', NULL);

INSERT INTO notification (notification_id, message, link_to, is_read, user_id) VALUES
('n-1', 'Welcome to the app!', '/exhibitions', false, 'user-1');
