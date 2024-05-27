--�Է� ���� ���̵� �ߺ� ����
CREATE OR REPLACE PROCEDURE CHECK_MEMBER_ID_OVERLAP (
    v_id IN VARCHAR2,
    v_isOverlap OUT NUMBER) 
AS
BEGIN
    SELECT COUNT(*) INTO v_isOverlap
    FROM MEMBERS
    WHERE m_id = v_id;
END;
/
--�Է� ���� �̸��� �ߺ� ����
CREATE OR REPLACE PROCEDURE CHECK_MEMBER_EMAIL_OVERLAP(
    v_email IN VARCHAR2,
    v_isOverlap OUT NUMBER)
AS
BEGIN
    SELECT COUNT(*) INTO v_isOverlap
    FROM MEMBERS
    WHERE m_email = v_email;
END;
/
--CLUB ���
CREATE OR REPLACE PROCEDURE GET_CLUB_TOTAL_LIST(
    C1 OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN C1 FOR
    SELECT c_no, c_name
    FROM CLUB
    ORDER BY c_no;
END;
/
--ȸ������
CREATE OR REPLACE PROCEDURE MEMBER_REGISTER (
    v_m_id IN VARCHAR2,
    v_m_pw IN VARCHAR2,
    v_m_email IN VARCHAR2,
    v_c_no IN NUMBER)
AS
BEGIN
    INSERT INTO MEMBERS (m_no, m_id, m_pw, m_email, c_no)
    VALUES (MEMBERS_SEQ.NEXTVAL, v_m_id, v_m_pw, v_m_email, v_c_no);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/
--�ش� ��� �Ѹ� ���� �ҷ�����
CREATE OR REPLACE PROCEDURE GET_MEMBER (
    v_id IN VARCHAR2,
    v_pw IN VARCHAR2,
    v_result OUT VARCHAR2)
AS
    v_m_no MEMBERS.m_no%TYPE;
    v_m_id MEMBERS.m_id%TYPE;
    v_m_email MEMBERS.m_email%TYPE;
    v_m_balance MEMBERS.m_balance%TYPE;
    v_c_no MEMBERS.c_no%TYPE;
BEGIN
    SELECT m_no, m_id, m_email, m_balance, c_no
    INTO v_m_no, v_m_id, v_m_email, v_m_balance, v_c_no
    FROM MEMBERS
    WHERE m_id = v_id AND m_pw = v_pw;

    v_result := 
    RPAD('No.', 6) || 
    RPAD('ID', 11) || 
    RPAD('E-mail', 21) || 
    RPAD('Balance', 10) || 
    RPAD('Team No.', 9) || CHR(10) || 
    RPAD(v_m_no, 5) || 
    RPAD(v_m_id, 10) || 
    RPAD(v_m_email, 25) || 
    RPAD(v_m_balance, 10) || 
    RPAD(v_c_no, 9);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        v_result := 'No member found with provided ID and password.';
END;
/
--�α���
CREATE OR REPLACE PROCEDURE MEMBER_LOGIN (
    v_id IN VARCHAR2,
    v_pw IN VARCHAR2,
    v_loginSuccess OUT NUMBER)
AS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM members
    WHERE m_id = v_id AND m_pw = v_pw;

    IF v_count > 0 THEN
        v_loginSuccess := 1;
    ELSE
        v_loginSuccess := 0;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        v_loginSuccess := 0;
END;
/
--ȸ���� ���� ���� ���
CREATE OR REPLACE PROCEDURE GET_MEMBER_PLAYER_LIST(
    v_member_id IN VARCHAR2,
    C1 OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN C1 FOR
    SELECT P.p_no, P.p_name, P.p_backno, P.p_position, P.p_shoot, P.p_pass, P.p_defend, P.p_price, P.c_no
    FROM OWNER O
    INNER JOIN PLAYER P ON O.p_no = P.p_no
    WHERE m_no = (SELECT m_no FROM MEMBERS WHERE m_id = v_member_id);
END;
/
--�������� �����ϱ�
CREATE OR REPLACE PROCEDURE DELETE_PLAYER_MEMBER(
    v_p_no IN NUMBER,
    v_m_id IN VARCHAR2,
    v_result OUT NUMBER)
AS
BEGIN
    DELETE FROM OWNER
    WHERE p_no = v_p_no AND m_no = (SELECT m_no FROM MEMBERS WHERE m_id = v_m_id);

    SELECT COUNT(*) INTO v_result
    FROM OWNER
    WHERE p_no = v_p_no AND m_no = (SELECT m_no FROM MEMBERS WHERE m_id = v_m_id);

    COMMIT;
END;
/
--����� �ܰ� �ҷ�����
CREATE OR REPLACE PROCEDURE GET_MEMBER_BALANCE (
    v_m_Id IN VARCHAR2,
    v_balance OUT NUMBER,
    v_message OUT VARCHAR2)
AS
BEGIN
    SELECT m_balance
    INTO v_balance
    FROM MEMBERS
    WHERE m_id = v_m_Id;
    v_message := CHR(10) || 'Your Balance: ' || v_balance;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        v_message := 'No member found with the provided ID.';
    WHEN OTHERS THEN
        v_message := 'An error occurred.';
END;
/
--������ ������ �ҷ���
CREATE OR REPLACE PROCEDURE GET_PLAYER_PRICE (
    v_p_no IN NUMBER,
    v_price OUT NUMBER)
AS
BEGIN
    SELECT p_price INTO v_price
    FROM player
    WHERE p_no = v_p_no;
END;
/
--����� �ܰ� ������Ʈ
CREATE OR REPLACE PROCEDURE SET_MEMBER_BALANCE (
    v_new_balance IN NUMBER,
    v_m_id IN VARCHAR2)
AS
BEGIN
    UPDATE MEMBERS
    SET m_balance = v_new_balance
    WHERE m_id = v_m_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/
--�������̺� ����� ���� ���� ����
CREATE OR REPLACE PROCEDURE SAVE_PLAYER_MEMBER (
    v_p_no IN NUMBER,
    v_m_id IN VARCHAR2,
    v_result OUT NUMBER)
AS
BEGIN
    INSERT INTO OWNER (o_no, m_no, p_no)
    VALUES (OWNER_SEQ.NEXTVAL, (SELECT m_no FROM members WHERE m_id = v_m_id), v_p_no);
    COMMIT;
    
    SELECT COUNT(*) INTO v_result FROM OWNER
    WHERE p_no = v_p_no AND m_no = (SELECT m_no FROM members WHERE m_id = v_m_id);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/
--���� ��й�ȣ ����
CREATE OR REPLACE PROCEDURE CHANGE_PASSWORD (
    v_newPW IN VARCHAR2,
    v_m_Id IN VARCHAR2,
    v_result OUT NUMBER)
AS
BEGIN
    UPDATE MEMBERS
    SET m_pw = v_newPW
    WHERE m_id = v_m_Id;

    v_result := SQL%ROWCOUNT;
END;
/
--��� ��й�ȣ ��������
CREATE OR REPLACE PROCEDURE GET_MEMBER_PW (
    v_m_Id IN VARCHAR2,
    v_password OUT VARCHAR2)
AS
BEGIN
    SELECT m_pw INTO v_password
    FROM MEMBERS
    WHERE m_id = v_m_Id;
END;
/
--��� ���� �����ϱ�
CREATE OR REPLACE PROCEDURE DELETE_MEMBER (
    v_m_Id IN VARCHAR2,
    v_result OUT NUMBER)
AS
BEGIN
    DELETE FROM MEMBERS WHERE m_id = v_m_Id;
    v_result := SQL%ROWCOUNT; -- ������ ���� ���� ����
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/
--������ ������ ����ϴ� ���̺� ����
CREATE TABLE deleted_members(
    m_id VARCHAR2(16),
    m_pw VARCHAR2(16),
    m_email VARCHAR2(30),
    m_balance NUMBER(20)
);
--������ ������ ����ϴ� Ʈ����
CREATE OR REPLACE TRIGGER member_delete_trigger
BEFORE DELETE ON MEMBERS
FOR EACH ROW
BEGIN
    INSERT INTO deleted_members
    VALUES (:OLD.m_id, :OLD.m_pw, :OLD.m_email, :OLD.m_balance);
END;
/

select * from club;
select * from player;
select * from owner;
select * from members;
select * from FBV;
select * from deleted_members;