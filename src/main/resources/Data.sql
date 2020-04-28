INSERT INTO application (application_id,app_name,owner,description)
VALUES (1, 'Trackzilla', 'Kesha Williams', 'Application for tracking bugs.'),
	   (2, 'Expenses', 'Mary Jones', 'Application to track expense reports.'),
	   (3, 'Notifications', 'Karen Kane', 'Application to send alerts and notifications to users.');
	   
	   
INSERT INTO release (id,release_date,description)
VALUES (1, '2030-02-14', 'Q1 Release Containing High Priority Bugs'),
	   (2, '2030-05-27', 'Q2 Release Containing High Priority Enhancement'),
	   (3, '2030-09-14', 'Q3 Release Conatining Bugs'),
	   (4, '2030-12-10', 'Q4 Release Containing Enhancement');
	   

INSERT INTO ticket (id,description,status,title,application_id)
VALUES (1, 'Add the ability to sort tickets by severity', 'OPEN', 'Sort Feature', 1),
	   (2, 'Add the ability to search by invoice date', 'IN PROGRESS', 'Search Feature', 3),
	   (3, 'Add the ability to audit by year', 'CLOSED', 'Audit', 2),
	   (4, 'Add the ability to book online tickets', 'OPEN', 'Booking Feature', 1);
	   

INSERT INTO ticket_release (ticket_fk,release_fk)
VALUES (1, 1),
	   (2, 3),
	   (3, 2),
	   (4, 1);
