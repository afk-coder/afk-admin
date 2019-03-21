package net.fux.support.core;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 * 查询结果的Key完全转换为小写
 * Created by fuxj on 2019/3/8
 */
public class LowerCaseRowMapper implements RowMapper<Map<String, Object>> {

    private static LowerCaseRowMapper instance = new LowerCaseRowMapper();

    private LowerCaseRowMapper() {
    }

    public static LowerCaseRowMapper getInstance() {
        return instance;
    }

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Map<String, Object> mapOfColValues = createColumnMap(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
            Object obj = getColumnValue(rs, i);
            mapOfColValues.put(key, obj);
        }
        return mapOfColValues;
    }

    /**
     * Create a Map instance to be used as column map.
     * <p>
     * By default, a linked case-insensitive Map will be created.
     *
     * @param columnCount the column count, to be used as initial capacity for the Map
     * @return the new Map instance
     * @see org.springframework.util.LinkedCaseInsensitiveMap
     */
    protected Map<String, Object> createColumnMap(int columnCount) {
        return new LinkedCaseInsensitiveMap<Object>(columnCount);
    }

    /**
     * Determine the key to use for the given column in the column Map.
     *
     * @param columnName the column name as returned by the ResultSet
     * @return the column key to use
     * @see ResultSetMetaData#getColumnName
     */
    protected String getColumnKey(String columnName) {
        return columnName.toLowerCase();
    }

    /**
     * Retrieve a JDBC object value for the specified column.
     * <p>
     * The default implementation uses the {@code getObject} method.
     * Additionally, this implementation includes a "hack" to get around Oracle
     * returning a non standard object for their TIMESTAMP datatype.
     *
     * @param rs    is the ResultSet holding the data
     * @param index is the column index
     * @return the Object returned
     * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue
     */
    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index);
    }
}
