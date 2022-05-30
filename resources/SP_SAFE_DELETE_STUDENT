DELIMITER //
CREATE PROCEDURE SP_SAFE_DELETE_STUDENT(
    IN to_delete_id INT,
    OUT affected_sections INT
)
BEGIN

    UPDATE section 
    SET delegate_id = NULL
    WHERE delegate_id=to_delete_id;

    SELECT ROW_COUNT() INTO affected_sections;

    DELETE FROM student WHERE student_id=to_delete_id;

END //
DELIMITER ;
