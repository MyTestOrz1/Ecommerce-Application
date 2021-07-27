CREATE TABLE IF NOT EXISTS public.user
(
    id serial   NOT NULL,
    deleted boolean,
    deleted_ts bigint,
    password character varying(255) NOT NULL,
    username character varying(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.role
(
    id character varying(36) NOT NULL,
    description character varying(128),
    name character varying(64)  NOT NULL,
    deleted boolean,
    deleted_ts bigint,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.user_role
(
    user_id serial  NOT NULL,
    role_id character varying(36) NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES public.user (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (role_id)
        REFERENCES public.role (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

insert into public.user (id,username,password,deleted)
values (1,'admin','$2a$04$UDQWU8myYBaj.FQunbWdLObd8/wBgRRc0dLng3NndYYiEUPqND0LS', false);

insert into public.role (id, description, name,deleted) values
 ('33552257-a556-49e2-8068-3330bb2415e6','Admin', 'Admin',false);

insert into public.user_role (user_id,role_id) values ((SELECT id FROM public.user WHERE username='admin'),
(SELECT id FROM public.role WHERE name='Admin'));

