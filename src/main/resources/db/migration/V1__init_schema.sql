# =============================================
# 고객 기본 테이블
# =============================================

# 테이블 생성
CREATE TABLE CUSTOMER
(
    CUSTOMER_ID        VARCHAR(30)                          NOT NULL COMMENT '고객_아이디' PRIMARY KEY,
    CUSTOMER_NUMBER    VARCHAR(12)                          NOT NULL COMMENT '고객_번호',
    KOR_FIRST_NAME     VARCHAR(30)                          NOT NULL COMMENT '한글_이름',
    KOR_LAST_NAME      VARCHAR(30)                          NOT NULL COMMENT '한글_성',
    ENG_FIRST_NAME     VARCHAR(30)                          NOT NULL COMMENT '영문_이름',
    ENG_LAST_NAME      VARCHAR(30)                          NOT NULL COMMENT '영문_성',
    BIRTHDAY           INT                                  NOT NULL COMMENT '생년월일',
    GENDER_CD          VARCHAR(6)                           NOT NULL COMMENT '성별_코드',
    PHONE_COUNTRY_CD   VARCHAR(6)                           NOT NULL COMMENT '휴대폰_국가_코드',
    PHONE_NUMBER       VARCHAR(100)                         NOT NULL COMMENT '휴대폰_번호',
    EMAIL              VARCHAR(100)                         NOT NULL COMMENT '이메일',
    PREFERRED_LANG_CD  VARCHAR(6)                                    COMMENT '선호_언어_코드',
    DORMANT_YN         VARCHAR(1)                           NOT NULL COMMENT '휴면_여부',
    WITHDRAWAL_YN      VARCHAR(1)                           NOT NULL COMMENT '탈퇴_여부',
    DEL_YN             VARCHAR(1)                           NOT NULL COMMENT '삭제_여부',
    REG_DT             DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '등록_일시',
    RGTR_ID            VARCHAR(30)                          NOT NULL COMMENT '등록자_아이디',
    MDFCN_DT           DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '수정_일시',
    MDFR_ID            VARCHAR(30)                          NOT NULL COMMENT '수정자_아이디'
) COMMENT '고객' COLLATE = UTF8MB4_UNICODE_CI;

# 인덱스
ALTER TABLE CUSTOMER ADD UNIQUE INDEX UK_CUSTOMER_CUSTOMER_NUMBER (CUSTOMER_NUMBER);

# =============================================
# 고객 주소 테이블
# =============================================

# 테이블 생성
CREATE TABLE CUSTOMER_ADDRESS
(
    CUSTOMER_ID             VARCHAR(30)                          NOT NULL COMMENT '고객_아이디' PRIMARY KEY,
    RESIDENCE_COUNTRY_CD    VARCHAR(12)                          NOT NULL COMMENT '거주_국가_코드',
    PREFERRED_ADDRESS_CD    VARCHAR(30)                          NOT NULL COMMENT '선호_주소_코드',
    ADDRESS                 VARCHAR(100)                                  COMMENT '주소',
    ZIP_CD                  VARCHAR(10)                          NOT NULL COMMENT '우편_번호',
    DETAIL_ADDRESS          VARCHAR(100)                         NOT NULL COMMENT '상세_주소',
    COMPANY_NAME            VARCHAR(100)                                  COMMENT '회사_이름',
    DEPARTMENT_NAME         VARCHAR(100)                                  COMMENT '부서_이름',
    POSITION_NAME           VARCHAR(100)                                  COMMENT '직위_이름',
    REG_DT                  DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '등록_일시',
    RGTR_ID                 VARCHAR(30)                          NOT NULL COMMENT '등록자_아이디',
    MDFCN_DT                DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '수정_일시',
    MDFR_ID                 VARCHAR(30)                          NOT NULL COMMENT '수정자_아이디'
) COMMENT '고객_주소' COLLATE = UTF8MB4_UNICODE_CI;

# FK 설정
ALTER TABLE CUSTOMER_ADDRESS ADD CONSTRAINT FK_CUSTOMER_ADDRESS_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID);