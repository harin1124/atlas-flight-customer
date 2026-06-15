# =============================================
# 탑승자 테이블 (회원 전용 · 방향성 링크, 복합키)
# =============================================

# 테이블 생성
# PK = (소유자, 탑승자) 자연 복합키 → 같은 방향 중복 등록 자체가 불가, 별도 서러게이트 불필요
CREATE TABLE PASSENGER
(
    OWNER_CUSTOMER_ID       VARCHAR(30)                          NOT NULL COMMENT '소유_고객_아이디',
    PASSENGER_CUSTOMER_ID   VARCHAR(30)                          NOT NULL COMMENT '탑승자_본인_고객_아이디(회원)',
    RELATION_CD             VARCHAR(6)                           NOT NULL COMMENT '관계_코드(SELF/SPOUSE/CHILD/PARENT/SIBL/ETC)',
    DEL_YN                  VARCHAR(1)                           NOT NULL COMMENT '삭제_여부',
    REG_DT                  DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '등록_일시',
    RGTR_ID                 VARCHAR(30)                          NOT NULL COMMENT '등록자_아이디',
    MDFCN_DT                DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '수정_일시',
    MDFR_ID                 VARCHAR(30)                          NOT NULL COMMENT '수정자_아이디',
    PRIMARY KEY (OWNER_CUSTOMER_ID, PASSENGER_CUSTOMER_ID),
    # 본인행(SELF)만 자기참조 허용, 그 외 관계는 다른 회원만 등록 가능
    CONSTRAINT CK_PASSENGER_SELF CHECK (
        (RELATION_CD =  'SELF' AND OWNER_CUSTOMER_ID =  PASSENGER_CUSTOMER_ID) OR
        (RELATION_CD <> 'SELF' AND OWNER_CUSTOMER_ID <> PASSENGER_CUSTOMER_ID)
    )
) COMMENT '탑승자' COLLATE = UTF8MB4_UNICODE_CI;

# FK 대상 인덱스
# (OWNER_CUSTOMER_ID 는 PK 좌측 프리픽스로 커버되지만, PASSENGER_CUSTOMER_ID 는 별도 인덱스 필요)
ALTER TABLE PASSENGER ADD INDEX IDX_PASSENGER_PERSON (PASSENGER_CUSTOMER_ID);

# FK 설정 (소유자 / 탑승자 본인 모두 CUSTOMER 참조)
ALTER TABLE PASSENGER ADD CONSTRAINT FK_PASSENGER_OWNER
    FOREIGN KEY (OWNER_CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID);
ALTER TABLE PASSENGER ADD CONSTRAINT FK_PASSENGER_PERSON
    FOREIGN KEY (PASSENGER_CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID);
