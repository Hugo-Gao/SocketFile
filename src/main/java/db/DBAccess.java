package db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public class DBAccess
{
    public static SqlSession getSqlSession() throws IOException
    {
        Reader reader = Resources.getResourceAsReader("Configuration.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sessionFactory.openSession();
    }
}
