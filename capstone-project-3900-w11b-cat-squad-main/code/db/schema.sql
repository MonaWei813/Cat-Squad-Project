create table if not exists Users (
	id          integer primary key autoincrement,
	email       text not null unique,
    first_name    text not null,
	last_name 		text not null,
	password    text not null

);

--- education degree need to get the check choice
create table if not exists JobSeekers(
    users_id    integer primary key,
    sex         text not null check 
    birthday    date,
    education_degree   text not null,
    mobile_contact  number not null,
    email_address   text not null,
    foreign key (users_id) references users(id)
);

create table if not exists Employers (
    users_id    integer primary key,
    mobile_contact  number not null,
    email_address   text not null,
    company text not null,
    foreign key (users_id) references users(id)
);
--- Jobs are offered by company
create table if not exists Jobs (
    id integer primary key autoincrement,
    job_title text not null,
    location text not null,
    requirement_skill text not null,
    description text not null,
    employment_type text not null check (employment_type in ('casual', 'full-time', 'part-time')),
    closing_date text not null
);

create table if not exists Posts (
    user_id integer references Users(id),
    job_id integer references Jobs(id),
    primary key(user_id, job_id)
);
--- Saved_employer function from the jobseeskers
create table if not exists Saved_employer(
    user_id integer references Users(id),
    employer integer reference Users(id),
    primary key(user_id, employer)
);

--- Saved_job function from the jobseeskers
create table if not exists Saved_job(
    user_id integer references Users(id),
    job_id integer references Jobs(id),
    primary key(user_id, job_id)
);

---need to write the check classfication
create table if not exists Resumes(
    id integer primary key autoincrement,
    user_id integer references Users(id),
    experience  text not null,
    description text not null,
    classification text not null,
    skills text
);
create table if not exists Provide(
    user_id integer references JobSeekers(users_id),
    Resume_id integer references Resumes(id)
    primary key(user_id, Resume_id)    
);

create table if not exists SearchedbyJ(
    user_id integer references Users(id),
    job_id integer references Jobs(id),
    primary key(user_id, job_id)
);

create table if not exists SearchedbyE(
    user_id integer references Employers(id),
    resume_id integer references Resumes(id),
    primary key(user_id, resume_id)
);

create table if not exists Offers (
    id integer primary key autoincrement,
    message text not null,
    kind text not null check (kind in ('offer', 'interview'))
);

create table if not exists Sends(
    user_id integer references Employers(id),
    offer_id integer references Offers(id),
    primary key(user_id, offer_id)
);

create table if not exists Agents(
    users_id    integer primary key,
    foreign key (users_id) references users(id) 
);

create table if not exists SearchedRbyA(
    user_id integer references Employers(id),
    resume_id integer references Resumes(id),
    primary key(user_id, resume_id)
);

create table if not exists SearchedJbyA(
    user_id integer references Employers(id),
    job_id integer references Jobs(id),
    primary key(user_id, job_id)
);