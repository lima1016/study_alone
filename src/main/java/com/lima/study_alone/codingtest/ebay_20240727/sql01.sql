WITH MonthlySalaries AS (
    SELECT
        W.ID,
        W.NAME,
        SUM(
                CASE
                    WHEN CEIL(TIMESTAMPDIFF(MINUTE, WL.ENTER_AT, WL.LEAVE_AT) / 60) <= 8 THEN CEIL(TIMESTAMPDIFF(MINUTE, WL.ENTER_AT, WL.LEAVE_AT) / 60) * W.WAGE
                    ELSE 8 * W.WAGE + (CEIL(TIMESTAMPDIFF(MINUTE, WL.ENTER_AT, WL.LEAVE_AT) / 60) - 8) * W.WAGE * 1.5
                    END
        ) AS SALARY
    FROM
        WORKER W
            JOIN WORKING_LOG WL ON W.ID = WL.WORKER_ID
    WHERE
    MONTH(WL.ENTER_AT) = 3 AND YEAR(WL.ENTER_AT) = 2023
GROUP BY
    W.ID, W.NAME
    )
SELECT
    MS.ID,
    MS.NAME,
    MS.SALARY
FROM
    MonthlySalaries MS
ORDER BY
    MS.SALARY DESC, MS.ID;
