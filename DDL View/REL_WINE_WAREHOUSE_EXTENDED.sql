CREATE VIEW REL_WINE_WAREHOUSE_EXTENDED
(
    WINESHOP_ID,
    WINESHOP_NAME,
    WAREHOUSE_ID,
    WAREHOUSE_NAME,
    WINE_ID,
    WINE_NAME,
    WINE_YEAR,
    WINE_PRODUCER,
    WINE_TECHNOTES,
    VINEYARD_ID,
    VINEYARD_NAME

)
AS
    SELECT
        ws.ID,
        ws.NAME,
        wh.ID,
        ws.NAME,
        w.ID,
        w.NAME,
        w.YEAR,
        w.PRODUCER,
        w.TECHNOTES,
        VY.ID,
        VY.NAME
    FROM REL_WINE_WAREHOUSE as r0
        JOIN REL_WINESHOP_WAREHOUSE AS r1 ON r1.ID_WAREHOUSE = r0.WAREHOUSE_ID
        JOIN WAREHOUSE AS wh ON wh.ID = r0.WAREHOUSE_ID
        JOIN WINESHOP AS ws ON ws.ID = r1.ID_WINESHOP
        JOIN WINE AS w ON w.ID = r0.WINE_ID AND r0.WINE_ID = w.ID
        LEFT JOIN REL_WINE_VINEYARD as r2 ON r2.WINE_ID = w.ID
        LEFT JOIN VINEYARD as VY ON VY.ID = r2.VINEYARD_ID

