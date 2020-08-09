package com.test;

import com.bean.DeptList;
import com.bean.EmpList;
import com.dao.IDeptListDao;
import com.dao.IEmpListDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    /**
     * 基于注解版的一对一
     */
    @Test
    public void test01() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        IEmpListDao mapper = session.getMapper(IEmpListDao.class);
        List<EmpList> list = mapper.findAllEmp();
        for (EmpList empList : list) {
            System.out.println(empList);
        }
        session.close();
        inputStream.close();
    }
    /**
     * 基于注解版的一对多
     */
    @Test
    public void test02() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        IDeptListDao mapper = session.getMapper(IDeptListDao.class);
        List<DeptList> list = mapper.findAllDept();
        for (DeptList deptList : list) {
            System.out.println(deptList);
        }
        session.close();
        inputStream.close();
    }

}
