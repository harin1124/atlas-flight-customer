# =============================================
# 고객 기본 테이블
# =============================================

# 시퀀스
CREATE SEQUENCE CUSTOMER_NUMBER_SEQ
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 999999999999
NOCYCLE
CACHE 20;