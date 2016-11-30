INSERT INTO public.goal_user (id, display_name, password, username) VALUES (-1, 'Reeva', 'reeva', 'reeva');


INSERT INTO public.goal (id, accomplished, comments, date_to_be_completed, item) VALUES (-1, FALSE , 'Must see Los Angeles', '12/21/2016', 'Go on a trip to California');
INSERT INTO public.goal (id, accomplished, comments, date_to_be_completed, item) VALUES (-2, FALSE , 'See it at least once', '11/30/2016', 'Watch "Fantastic Beasts and Where To Find Them"');
INSERT INTO public.goal (id, accomplished, comments, date_to_be_completed, item) VALUES (-3, FALSE , 'Learn how to make chocolate ones first', '12/21/2016', 'Learn how to make cupcakes');



INSERT INTO public.permission (id, description, key) VALUES (-1, 'Admin Users', 'ADMIN_ADD_USER');


INSERT INTO public.goal_user_abilities (goal_user_id, abilities_id) VALUES (-1, -1);