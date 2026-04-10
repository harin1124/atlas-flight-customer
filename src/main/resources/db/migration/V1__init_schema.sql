# =============================================
# 고객 기본 테이블
# =============================================

# 테이블 생성
CREATE TABLE CUSTOMER_BASIC
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
    DEL_YN             VARCHAR(1)                           NOT NULL COMMENT '삭제',
    REG_DT             DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '등록_일시',
    RGTR_ID            VARCHAR(30)                          NOT NULL COMMENT '등록자_아이디',
    MDFCN_DT           DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '수정_일시',
    MDFR_ID            VARCHAR(30)                          NOT NULL COMMENT '수정자_아이디'
) COMMENT '고객_기본' COLLATE = UTF8MB4_UNICODE_CI;

# 인덱스
ALTER TABLE CUSTOMER_BASIC ADD UNIQUE INDEX UK_CUSTOMER_BASIC_CUSTOMER_NUMBER (CUSTOMER_NUMBER);