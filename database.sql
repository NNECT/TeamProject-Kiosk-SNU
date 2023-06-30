use kiosk;


# 계정 데이터
create table account (
    id                  int unsigned    primary key auto_increment,
    username            varchar(100)    not null unique,
    password            varchar(100)    not null,
    phoneNumber         varchar(13)     not null,
    point               int             not null default 0,
    remainTime          int             not null default 0
);

# 정기권 사용 데이터
create table usageCommutationTicket (
    id                  int unsigned    primary key auto_increment,
    account_id          int unsigned    not null,
    startDate           date            not null default (current_date),
    endDate             date            not null,
    foreign key (account_id) references account(id)
);


# 시간권 데이터
create table timeTicket (
    id                  int unsigned    primary key auto_increment,
    time                int             not null,
    price               int             not null,
    active              boolean         not null default true
);

# 정기권 데이터
create table commutationTicket (
    id                  int unsigned    primary key auto_increment,
    day                 int             not null,
    price               int             not null,
    active              boolean         not null default true
);

# 자리 데이터
create table seat (
    id                  int unsigned    primary key auto_increment,
    seatNumber          int             not null unique,
    usable              boolean         not null default true
);

# 자리 사용 데이터
create table usageSeat (
    id                  int unsigned    primary key auto_increment,
    seat_id             int unsigned    not null,
    account_id          int unsigned    not null,
    startDateTime       datetime        not null default now(),
    endDateTime         datetime        null,
    foreign key (seat_id) references seat(id),
    foreign key (account_id) references account(id)
);


# 룸 종류 데이터
create table roomType (
    id                  int unsigned    primary key auto_increment,
    name                varchar(100)    not null,
    price               int             not null
);

# 룸 데이터
create table room (
    id                  int unsigned    primary key auto_increment,
    roomNumber          int             not null unique,
    roomType_id         int unsigned    not null,
    usable              boolean         not null default true,
    foreign key (roomType_id) references roomType(id)
);

# 룸 사용 데이터
create table UsageRoom (
    id                  int unsigned    primary key auto_increment,
    room_id             int unsigned    not null,
    account_id          int unsigned    not null,
    startDateTime       datetime        not null default now(),
    endDateTime         datetime        not null,
    foreign key (room_id) references room(id),
    foreign key (account_id) references account(id)
);


# 사물함 사용권 데이터
create table lockerTicket (
    id                  int unsigned    primary key auto_increment,
    day                 int             not null,
    price               int             not null,
    active              boolean         not null default true
);

# 사물함 데이터
create table locker (
    id                  int unsigned    primary key auto_increment,
    lockerNumber        int             not null unique,
    usable              boolean         not null default true
);

# 사물함 사용 데이터
create table usageLocker (
    id                  int unsigned    primary key auto_increment,
    locker_id           int unsigned    not null,
    account_id          int unsigned    not null,
    startDate           date            not null default (current_date),
    endDate             date            not null,
    foreign key (locker_id) references locker(id),
    foreign key (account_id) references account(id)
);


# 결제 데이터
create table payment (
    id                  int unsigned    primary key auto_increment,
    account_id          int unsigned    not null,
    dateTime            datetime        not null default now(),
    amount              int             not null,
    log                 varchar(200)    not null,
    foreign key (account_id) references account(id)
);

# 포인트 내역
create table pointHistory (
    id                  int unsigned    primary key auto_increment,
    account_id          int unsigned    not null,
    dateTime            datetime        not null default now(),
    amount              int             not null,
    log                 varchar(200)    not null,
    foreign key (account_id) references account(id)
);


# 챌린지 데이터
create table challenge (
    id                  int unsigned    primary key auto_increment,
    title               varchar(100)    not null,
    description         varchar(200)    not null,
    titleColor          char(6)         not null,
    descriptionColor    char(6)         not null,
    backgroundColor     char(6)         not null,
    imageSrc            varchar(200)    not null,
    activeStartTime     time            not null default '00:00:00',
    activeEndTime       time            not null default '23:59:59',
    periodDays          int             not null default 0,
    periodHours         int             not null default 0,
    goalDay             int             not null default 0,
    goalHour            int             not null default 0,
    rewardPoint         int             not null,
    active              boolean         not null default true,
    visible             boolean         not null default true
);

# 챌린지 참여 데이터
create table participationChallenge (
    id                  int unsigned    primary key auto_increment,
    account_id          int unsigned    not null,
    challenge_id        int unsigned    not null,
    startDateTime       datetime        not null default now(),
    periodDays          int             not null,
    periodHours         int             not null,
    goalDay             int             not null,
    goalHour            int             not null,
    rewardPoint         int             not null,
    active              boolean         not null default true,
    result              boolean         null,
    foreign key (account_id) references account(id),
    foreign key (challenge_id) references challenge(id)
);


# 관리자 데이터
create table admin (
    id              int unsigned    primary key auto_increment,
    username        varchar(100)    not null unique,
    password        varchar(100)    not null
);

# 공지사항 데이터
create table notice (
    id              int unsigned    primary key auto_increment,
    content         text            not null,
    dateTime        datetime        not null default now(),
    outside         boolean         not null default false,
    active          boolean         not null default true
);