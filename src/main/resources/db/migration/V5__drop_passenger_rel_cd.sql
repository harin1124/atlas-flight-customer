# =============================================
# PASSENGER 유령 컬럼 REL_CD 제거
# =============================================
# 관계 코드 컬럼의 정식 이름은 V4에서 만든 RELATION_CD 다.
# 과거 엔티티가 REL_CD 로 잘못 매핑돼 있었고, hibernate.ddl-auto=update 가
# 이에 맞춰 REL_CD 컬럼을 별도로 추가해 두 컬럼이 공존했다.
# 엔티티를 RELATION_CD 로 바로잡았으므로, 남은 REL_CD 를 제거한다.
# (ddl-auto 로만 생성돼 아예 없는 환경도 있으니 IF EXISTS 로 방어한다.)
ALTER TABLE PASSENGER DROP COLUMN IF EXISTS REL_CD;
