-- 게시글
DROP TABLE IF EXISTS app_board RESTRICT;

-- 회원
DROP TABLE IF EXISTS app_member RESTRICT;

-- 첨부파일
DROP TABLE IF EXISTS app_board_file RESTRICT;

-- 게시글
CREATE TABLE app_board (
  bno    INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  title  VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont   MEDIUMTEXT   NULL     COMMENT '내용', -- 내용
  mno    INTEGER      NULL     COMMENT '회원번호', -- 회원번호
  pwd    VARCHAR(100) NULL     COMMENT '암호', -- 암호
  cdt    DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  vw_cnt INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수' -- 조회수
)
COMMENT '게시글';

-- 게시글
ALTER TABLE app_board
  ADD CONSTRAINT PK_app_board -- 게시글 기본키
    PRIMARY KEY (
      bno -- 게시글번호
    );

ALTER TABLE app_board
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 회원
CREATE TABLE app_member (
  mno   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name  VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  email VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  pwd   VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
  cdt   DATETIME     NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '회원';

-- 회원
ALTER TABLE app_member
  ADD CONSTRAINT PK_app_member -- 회원 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_app_member
  ON app_member ( -- 회원
    email ASC -- 이메일
  );

-- 회원 인덱스
CREATE INDEX IX_app_member
  ON app_member( -- 회원
    name ASC -- 이름
  );

ALTER TABLE app_member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 첨부파일
CREATE TABLE app_board_file (
  bfno     INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  filepath VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  bno      INTEGER      NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '첨부파일';

-- 첨부파일
ALTER TABLE app_board_file
  ADD CONSTRAINT PK_app_board_file -- 첨부파일 기본키
    PRIMARY KEY (
      bfno -- 첨부파일번호
    );

ALTER TABLE app_board_file
  MODIFY COLUMN bfno INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

-- 게시글
ALTER TABLE app_board
  ADD CONSTRAINT FK_app_member_TO_app_board -- 회원 -> 게시글
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES app_member ( -- 회원
      mno -- 회원번호
    );

-- 첨부파일
ALTER TABLE app_board_file
  ADD CONSTRAINT FK_app_board_TO_app_board_file -- 게시글 -> 첨부파일
    FOREIGN KEY (
      bno -- 게시글번호
    )
    REFERENCES app_board ( -- 게시글
      bno -- 게시글번호
    );
    
insert into portfolio (ptno,mno,title,cont,cdt) values (2,2,"회원2의포트폴리오제목", "디자인 포트폴리오입니다.","2022-10-01");




-- 포트폴리오
insert into portfolio (ptno,mno,title,cont,cdt) values (1,1,"회원1의 포트폴리오제목", "디자인 포트폴리오입니다.", "2022-01-03");
insert into portfolio (ptno,mno,title,cont,cdt) values (2,2,"회원2의 포트폴리오제목", "사진포트폴리오입니다.","2022-06-27");
insert into portfolio (ptno,mno,title,cont,cdt) values (3,2,"회원2의 포트폴리오제목", "영상 편집 포트폴리오입니다.","2022-07-03");
insert into portfolio (ptno,mno,title,cont,cdt) values (4,3,"회원3의 포트폴리오제목a", "로고 제작 포트폴리오입니다.","2022-08-17");
insert into portfolio (ptno,mno,title,cont,cdt) values (5,3,"회원3의 포트폴리오제목b", "일러스트 포트폴리오입니다.","2022-09-10");
insert into portfolio (ptno,mno,title,cont,cdt) values (6,3,"회원3의 포트폴리오제목c", "배너 제작 포트폴리오입니다.","2022-09-24");
insert into portfolio (ptno,mno,title,cont,cdt) values (7,4,"회원4의 포트폴리오제목", "번역 포트폴리오입니다.","2022-10-01");
insert into portfolio (ptno,mno,title,cont,cdt) values (8,5,"회원5의 포트폴리오제목", "영상 제작 포트폴리오입니다.","2022-10-06");
insert into portfolio (ptno,mno,title,cont,cdt) values (9,2,"웨딩사진 포트폴리오(제목)", "사진포트폴리오입니다.","2022-10-07");
insert into portfolio (ptno,mno,title,cont,cdt) values (10,3,"로고 제작 포트폴리오(제목)", "로고 제작 포트폴리오입니다.","2022-10-07");
insert into portfolio (ptno,mno,title,cont,cdt) values (11,1,"멋진 디자인 포트폴리오(제목)", "디자인 포트폴리오입니다.", "2022-10-08");
insert into portfolio (ptno,mno,title,cont,cdt) values (12,2,"독립영화제 출품작 포트폴리오(제목)", "영상 편집 포트폴리오입니다.","2022-10-08");

-- 포트폴리오 첨부파일
insert into portfolio_file (ptfno, fname, fpath, ptno) values (1,"포트폴리오 첨부파일a","ab93278e-72ba-42dc-a468-010435c31d2b",1);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (2,"포트폴리오 첨부파일b","f388dc55-8ea9-4878-a8ab-66df9ffcf5f9",2);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (3,"포트폴리오 첨부파일c","99526b3f-cb9e-4c06-8d2f-b0a883f483fa",3);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (4,"포트폴리오 첨부파일d","2766082d-4b4b-40db-b874-ac2ca856b478",4);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (5,"포트폴리오 첨부파일e","e12d2e96-406e-4ca3-9913-9728ab557bf2",5);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (6,"포트폴리오 첨부파일f","8b22566d-ef10-4a24-816b-f89597b777e5",6);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (7,"포트폴리오 첨부파일g","5482da81-3c09-4a8c-9d5f-0e428d08c962",7);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (8,"포트폴리오 첨부파일a","b6496a5b-ad94-4ca0-af57-86aae3a0a64a",8);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (9,"포트폴리오 첨부파일e","eb0cacf9-f084-4797-9493-008373eb659b",9);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (10,"포트폴리오 첨부파일g","3b76ef98-5dc9-41c0-af90-60442214ded8",10);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (11,"포트폴리오 첨부파일s","b8922a36-7eb7-42f0-93c9-eef23585ba4c",11);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (12,"포트폴리오 첨부파일d","86f1f023-c1bc-401b-af98-1732aa23201f",12);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (13,"포트폴리오 첨부파일e","1ef327f7-5425-4f26-b117-e4f0b24e6800",12);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (14,"포트폴리오 첨부파일n","360ba9cb-a55b-4f2f-b879-952a8153c2f5",3);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (15,"포트폴리오 첨부파일hh","a3075014-48b9-4860-8a63-7849bde4fe5b",6);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (16,"포트폴리오 첨부파일e","5f8edfb4-e202-44d3-8673-4ce77f2d8723",6);
insert into portfolio_file (ptfno, fname, fpath, ptno) values (17,"포트폴리오 첨부파일u","70f795e7-fcd0-4f21-b8ca-4c387b4a63c5",7);
















































