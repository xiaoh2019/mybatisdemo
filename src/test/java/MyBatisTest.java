import com.cyzs.mapper.DeptMapper;
import com.cyzs.mapper.UserMapper;
import entity.Department;
import entity.User;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author xiaoH
 * @create 2019-06-10-18:09
 */
public class MyBatisTest {
    SqlSessionFactory sqlSessionFactory=null;

    @Before
    public void test() throws IOException {
        //new Cache();
        String source="mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(source);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    @Test
    public void test1() throws IOException {
        String source="mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(source);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*User o = sqlSession.selectOne("com.cyzs.mapper.UserMapper.selectOne", 1);
        System.out.println(o);*/
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getOne(1);
        System.out.println(user);
        sqlSession.close();


    }

    @Test
    public void test2() throws IOException {
        String source="mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(source);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        /*User o = sqlSession.selectOne("com.cyzs.mapper.UserMapper.selectOne", 1);
        System.out.println(o);*/
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("王");
        user.setSex(1);
        user.setBirthday(new Date());
        user.setAddress("北京");
        mapper.addUser(user);
        System.out.println(user.getId());
        //sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void delect() throws IOException {
        String source="mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(source);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUserById(18);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void update() throws IOException {
        String source="mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(source);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(19);
        user.setAddress("南京");
        user.setUsername("李");
        user.setSex(0);
        mapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test05(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User userAndDept = mapper.getUserAndDept(1);
            System.out.println(userAndDept);
        }finally{
            sqlSession.close();
        }

    }

    @Test
    public void test06(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = mapper.getOne(1);
            System.out.println(user.getUsername());
            System.out.println(user.getDept().getDepartmentName());
        }finally{
            sqlSession.close();
        }

    }

    @Test
    public void test07(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        try {
            Department department = mapper.getDeptByIdPlus(1);
            System.out.println(department);
            System.out.println(department.getUsers());
        }finally{
            sqlSession.close();
        }

    }

    @Test
    public void test08(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        try {
            Department department = mapper.getDeptByIdStep(1);
            System.out.println(department.getDepartmentName());
            //System.out.println(department.getUsers());
        }finally{
            sqlSession.close();
        }

    }
    @Test
    public void test09(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = new User();
            user.setId(1);
            user.setUsername("张三");
            userMapper.updateUser(user);
        }finally{
            sqlSession.close();
        }

    }

    @Test
    public void test10(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            List ids=Arrays.asList(22,23,24);
           userMapper.deleteByIds(ids);
        }finally{
            sqlSession.close();
        }

    }

    @Test
    public void test11(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            ArrayList<User> users = new ArrayList<User>();
            User user = new User();
            user.setUsername("王");
            user.setSex(1);
            user.setAddress("北京");
            user.setBirthday(new Date());
            user.setDept(new Department(1,"开发部"));
            User user1 = new User();
            user1.setUsername("李");
            user1.setSex(1);
            user1.setAddress("北京");
            user1.setBirthday(new Date());
            user1.setDept(new Department(2,""));
            users.add(user);
            users.add(user1);
            userMapper.addUsers(users);
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void test12(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = userMapper.getOne(1);
            System.out.println(user);
        }finally{
            sqlSession.close();
        }

    }

}
