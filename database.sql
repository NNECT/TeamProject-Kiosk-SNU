use kiosk;


### 계정 데이터
#   username            아이디
#   password            비밀번호
#   phoneNumber         전화번호
#   point               포인트
#   remainTime          남은 시간
create table account (
    id                  int unsigned    primary key auto_increment,
    username            varchar(100)    not null unique,
    password            varchar(100)    not null,
    phoneNumber         varchar(13)     not null,
    point               int             not null default 0,
    remainTime          int             not null default 0
);

### 정기권 사용 데이터
#   account_id          외부키 (계정 데이터)
#   startDate           시작 날짜
#   endDate             종료 날짜
create table usageCommutationTicket (
    id                  int unsigned    primary key auto_increment,
    account_id          int unsigned    not null,
    startDate           date            not null default (current_date),
    endDate             date            not null,
    foreign key (account_id) references account(id)
);

create index account_endDate_idx
on usageCommutationTicket (account_id, endDate desc);

create index endDate_idx
on usageCommutationTicket (endDate desc);


### 시간권 데이터
#   time                시간
#   price               가격
#   active              활성화 여부
create table timeTicket (
    id                  int unsigned    primary key auto_increment,
    time                int             not null,
    price               int             not null,
    active              boolean         not null default true
);

### 정기권 데이터
#   day                 일
#   price               가격
#   active              활성화 여부
create table commutationTicket (
    id                  int unsigned    primary key auto_increment,
    day                 int             not null,
    price               int             not null,
    active              boolean         not null default true
);

### 자리 데이터
#   seatNumber          자리 번호
#   usable              사용 가능 여부
create table seat (
    id                  int unsigned    primary key auto_increment,
    seatNumber          int             not null unique,
    usable              boolean         not null default true
);

### 자리 사용 데이터
#   seat_id             외부키 (자리 데이터)
#   account_id          외부키 (계정 데이터)
#   startDateTime       시작 날짜/시간
#   endDateTime         종료 날짜/시간
create table usageSeat (
    id                  int unsigned    primary key auto_increment,
    seat_id             int unsigned    not null,
    account_id          int unsigned    not null,
    startDateTime       datetime        not null default now(),
    endDateTime         datetime        null,
    foreign key (seat_id) references seat(id),
    foreign key (account_id) references account(id)
);

create index seat_endDateTime_idx
on usageSeat (seat_id, endDateTime desc);

create index account_endDateTime_idx
on usageSeat (account_id, endDateTime desc);


### 룸 종류 데이터
#   name                이름
#   price               가격
create table roomType (
    id                  int unsigned    primary key auto_increment,
    name                varchar(100)    not null,
    price               int             not null
);

### 룸 데이터
#   roomNumber          룸 번호
#   roomType_id         외부키 (룸 종류 데이터)
#   usable              사용 가능 여부
create table room (
    id                  int unsigned    primary key auto_increment,
    roomNumber          int             not null unique,
    roomType_id         int unsigned    not null,
    usable              boolean         not null default true,
    foreign key (roomType_id) references roomType(id)
);

create index usable_room_idx
on room (usable);

### 룸 사용 데이터
#   room_id             외부키 (룸 데이터)
#   account_id          외부키 (계정 데이터)
#   startDateTime       시작 날짜/시간
#   endDateTime         종료 날짜/시간
create table usageRoom (
    id                  int unsigned    primary key auto_increment,
    room_id             int unsigned    not null,
    account_id          int unsigned    not null,
    startDateTime       datetime        not null default now(),
    endDateTime         datetime        not null,
    foreign key (room_id) references room(id),
    foreign key (account_id) references account(id)
);

create index room_endDateTime_idx
on usageRoom (room_id, endDateTime desc);

create index account_endDateTime_idx
on usageRoom (account_id, endDateTime desc);


### 사물함 사용권 데이터
#   day                 일수
#   price               가격
#   active              활성화 여부
create table lockerTicket (
    id                  int unsigned    primary key auto_increment,
    day                 int             not null,
    price               int             not null,
    active              boolean         not null default true
);

### 사물함 데이터
#   lockerNumber        사물함 번호
#   usable              사용 가능 여부
create table locker (
    id                  int unsigned    primary key auto_increment,
    lockerNumber        int             not null unique,
    usable              boolean         not null default true
);

### 사물함 사용 데이터
#   locker_id           외부키 (사물함 데이터)
#   account_id          외부키 (계정 데이터)
#   startDate           시작 날짜
#   endDate             종료 날짜
create table usageLocker (
    id                  int unsigned    primary key auto_increment,
    locker_id           int unsigned    not null,
    account_id          int unsigned    not null,
    startDate           date            not null default (current_date),
    endDate             date            not null,
    foreign key (locker_id) references locker(id),
    foreign key (account_id) references account(id)
);

create index locker_endDate_idx
on usageLocker (locker_id, endDate desc);

create index account_endDate_idx
on usageLocker (account_id, endDate desc);


### 결제 데이터
#   account_id          외부키 (계정 데이터)
#   dateTime            결제 날짜/시간
#   amount              결제 금액
#   usedPoint           사용 포인트
#   log                 결제 내역   (예시 : {"result":"결제 성공","type":"creditCard","code":"27850045147977655297","amount":6000,"point":500,"pay":5500,"breakdown":[{"type":"Time Ticket","time":3,"unit":"hour","price":6000}]} )
create table payment (
    id                  int unsigned    primary key auto_increment,
    account_id          int unsigned    not null,
    dateTime            datetime        not null default now(),
    amount              int             not null,
    usedPoint           int             not null default 0,
    log                 text            not null,
    foreign key (account_id) references account(id)
);

create index dateTime_idx
on payment (dateTime desc);

create index account_dateTime_idx
on payment (account_id, dateTime desc);


### 챌린지 데이터
#   title               제목
#   description         설명
#   titleColor          제목 색상
#   descriptionColor    설명 색상
#   backgroundColor     배경 색상
#   imageSrc            이미지 경로
#   activeStartTime     활성화 시작 시간
#   activeEndTime       활성화 종료 시간
#   periodDays          기간 일수   (기간 시간이 있을 경우 기간 일수는 0)
#   periodHours         기간 시간   (기간 일수가 있을 경우 기간 시간은 0)
#   goalDay             목표 일수
#   goalHour            목표 시간
#   rewardPoint         보상 포인트
#   active              활성화 여부
#   visible             표시 여부
create table challenge (
    id                  int unsigned    primary key auto_increment,
    title               varchar(100)    not null,
    description         varchar(200)    not null,
    titleColor          char(7)         not null,
    descriptionColor    char(7)         not null,
    backgroundColor     char(7)         not null,
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

create index active_idx
on challenge (active);

create index visible_active_idx
on challenge (visible, active);

### 챌린지 참여 데이터
#   account_id          외부키 (계정 데이터)
#   challenge_id        외부키 (챌린지 데이터)
#   startDateTime       시작 날짜/시간
#   endDateTime         종료 날짜/시간    (시작 날짜/시간 + 기간 일수 또는 기간 시간)
#   goalDay             목표 일수    (기존 챌린지 데이터가 변경되어도 이미 참여 시작한 데이터는 변동되지 않음)
#   goalHour            목표 시간    (기존 챌린지 데이터가 변경되어도 이미 참여 시작한 데이터는 변동되지 않음)
#   rewardPoint         보상 포인트  (기존 챌린지 데이터가 변경되어도 이미 참여 시작한 데이터는 변동되지 않음)
#   active              활성화 여부
#   result              결과
create table participationChallenge (
    id                  int unsigned    primary key auto_increment,
    account_id          int unsigned    not null,
    challenge_id        int unsigned    not null,
    startDateTime       datetime        not null default now(),
    endDateTime         datetime        not null,
    goalDay             int             not null,
    goalHour            int             not null,
    rewardPoint         int             not null,
    active              boolean         not null default true,
    result              boolean         null,
    foreign key (account_id) references account(id),
    foreign key (challenge_id) references challenge(id)
);

create index account_endDateTime_idx
on participationChallenge (account_id, endDateTime desc);

create index challenge_active_idx
on participationChallenge (challenge_id, active);


### 관리자 데이터
#   username            아이디
#   password            비밀번호
create table admin (
    id                  int unsigned    primary key auto_increment,
    username            varchar(100)    not null unique,
    password            varchar(100)    not null
);

### 공지사항 데이터
#   title               제목
#   content             내용
#   dateTime            날짜/시간
#   outside             외부 키오스크 공지 여부   (외부 키오스크 공지는 1개 제한)
#   active              활성화 여부
create table notice (
    id                  int unsigned    primary key auto_increment,
    title               varchar(100)    not null,
    content             text            not null,
    dateTime            datetime        not null default now(),
    outside             boolean         not null default false,
    active              boolean         not null default true
);

create index dateTime_idx
on notice (dateTime desc);
