package sqlite.test;

import java.sql.ResultSet;

interface ResultSetExtractor<T> {
    
    T extractData(ResultSet rs);

}