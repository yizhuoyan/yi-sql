package test;

import com.yizhuoyan.common.sql.Sql;
import com.yizhuoyan.common.sql.select.SelectSql;
import com.yizhuoyan.common.sql.select.sqlpart.AbstractEndPartSql;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Administrator on 2018/9/7 0007.
 */

public class TestSelectSql {

    @Test
    public void testSelectPart1(){
        Assertions.assertEquals(Sql.select("a,b","c").toString(),"SELECT a,b,c");
    }
    @Test
    public void testSelectPart2(){
        Assertions.assertEquals(Sql.selectDistinct("a,b","c").toString(),"SELECT DISTINCT a,b,c");
    }

    @Test
    public void testFromPart1(){
        Assertions.assertEquals(
                Sql.from("sys_user").toString(),
                "FROM sys_user");
    }
    @Test
    public void testFromPart2(){
        Assertions.assertEquals(
                Sql.from("sys_user","u").toString(),
                "FROM sys_user u");
    }
    @Test
    public void testFromPart3(){
        Assertions.assertEquals(
                Sql.from(
                        Sql.select("id,name").from("sys_user"),"u").toString(),
                "FROM (SELECT id,name FROM sys_user) u");
    }
    @Test
    public void testFromPart4(){
        Assertions.assertEquals(
                Sql.from("sys_user","u").innerJoin("sys_role","r").toString(),
                "FROM sys_user u INNER JOIN sys_role r");
    }
    @Test
    public void testFromPart4a(){
        Assertions.assertEquals(
                Sql.from("sys_user").innerJoin("sys_role").toString(),
                "FROM sys_user INNER JOIN sys_role");
    }
    @Test
    public void testFromPart5(){
        Assertions.assertEquals(
                Sql.from("sys_user","u").leftJoin("sys_role","r").toString(),
                "FROM sys_user u LEFT JOIN sys_role r");
    }
    @Test
    public void testFromPart5a(){
        Assertions.assertEquals(
                Sql.from("sys_user").leftJoin("sys_role").toString(),
                "FROM sys_user LEFT JOIN sys_role");
    }
    @Test
    public void testFromPart6(){
        Assertions.assertEquals(
                Sql.from("sys_user","u").rightJoin("sys_role","r").toString(),
                "FROM sys_user u RIGHT JOIN sys_role r");
    }
    @Test
    public void testFromPart6a(){
        Assertions.assertEquals(
                Sql.from("sys_user").rightJoin("sys_role").toString(),
                "FROM sys_user RIGHT JOIN sys_role");
    }
    @Test
    public void testFromPart7(){
        Assertions.assertEquals(
                Sql.from("sys_user","u").fullJoin("sys_role","r").toString(),
                "FROM sys_user u FULL JOIN sys_role r");
    }
    @Test
    public void testFromPart7a(){
        Assertions.assertEquals(
                Sql.from("sys_user").fullJoin("sys_role").toString(),
                "FROM sys_user FULL JOIN sys_role");
    }

    @Test
    public void testFromJoinOn(){
        Assertions.assertEquals(
                Sql.from("sys_user","u")
                        .fullJoin("sys_role","r")
                        .on("u.id=r.id")
                        .toString(),
                "FROM sys_user u FULL JOIN sys_role r ON u.id=r.id");
    }
    @Test
    public void testFromJoinOn2(){
        Assertions.assertEquals(
                Sql.from("sys_user","u")
                        .fullJoin("sys_role","r")
                        .on("u.id=r.id")
                        .leftJoin("sys_menu","m")
                        .on("m.id=r.id")

                        .toString(),
                "FROM sys_user u FULL JOIN sys_role r ON u.id=r.id LEFT JOIN sys_menu m ON m.id=r.id");
    }

    @Test
    public void testWhereFragment1(){
        Assertions.assertEquals(
                Sql.where("1=1").toString(),
                "WHERE 1=1");
    }
    @Test
    public void testWhereFragment2(){
        Assertions.assertEquals(
                Sql.where("1=1").and("1=2").toString(),
                "WHERE 1=1 AND 1=2");
    }
    @Test
    public void testWhereFragment3(){
        Assertions.assertEquals(
                Sql.where("1=1").or("1=2").toString(),
                "WHERE 1=1 OR 1=2");
    }

    @Test
    public void testWhereCondition1(){
        Assertions.assertEquals(

        Sql.where(Sql.condition("1=1").and("1=2"))
                .or("1=1").toString(),
                "WHERE (1=1 AND 1=2) OR 1=1");
    }
    public static void main(String[] aa){
        new TestSelectSql().testWhereCondition1();
    }
    @Test
    public void testWhereCondition2(){
        Assertions.assertEquals(
                Sql.where(Sql.condition("a").and("b"))
                        .or(Sql.condition("c").or("d"))
                        .and("1")
                        .toString(),
                "WHERE (a AND b) OR (c OR d) AND 1") ;
    }
    @Test
    public void testBetweenAnd(){
        Assertions.assertEquals(
                Sql.where("name").betweenAnd(1,2)
                        .toString(),
                "WHERE name BETWEEN 1 AND 2") ;
    }
    @Test
    public void testBetweenAnd2(){
        Assertions.assertEquals(
                Sql.where("name=?").and("age").betweenAnd(1,2)
                        .toString(),
                "WHERE name=? AND age BETWEEN 1 AND 2") ;
    }
    @Test
    public void testBetweenAnd3(){
        Assertions.assertEquals(
                Sql.where("name=?").and("age").betweenAnd(1,2)
                        .toString(),
                "WHERE name=? AND age BETWEEN 1 AND 2") ;
    }

    @Test
    public void testNotBetweenAnd(){
        Assertions.assertEquals(
                Sql.where("name").notBetweenAnd(1,2)
                        .toString(),
                "WHERE name NOT BETWEEN 1 AND 2") ;
    }
    @Test
    public void testNotBetweenAnd2(){
        Assertions.assertEquals(
                Sql.where("name=?").and("age").notBetweenAnd(1,2)
                        .toString(),
                "WHERE name=? AND age NOT BETWEEN 1 AND 2") ;
    }

    @Test
    public void testIn(){
        Assertions.assertEquals(
                Sql.where("age").in("1,2,3")
                        .toString(),
                "WHERE age IN(1,2,3)") ;
    }
    @Test
    public void testNotIn(){
        Assertions.assertEquals(
                Sql.where("age").notIn("1,2,3")
                        .toString(),
                "WHERE age NOT IN(1,2,3)") ;
    }
    @Test
    public void testNotLike(){
        Assertions.assertEquals(
                Sql.where("name").notLike("?")
                        .toString(),
                "WHERE name NOT LIKE ?") ;
    }

    @Test
    public void testLike(){
        Assertions.assertEquals(
                Sql.where("name").like("?")
                        .toString(),
                "WHERE name LIKE ?") ;
    }
    @Test
    public void testIf(){
        Assertions.assertEquals(
                Sql.condition("name").like("?").andIf(false,"a=?")
                        .toString(),
                "name LIKE ?") ;
    }
    @Test
    public void testIf2(){
        Assertions.assertEquals(
                Sql.condition("name").like("?").andIf(true,"a=?")
                        .toString(),
                "name LIKE ? AND a=?") ;
    }
    @Test
    public void testIf3(){
        Assertions.assertEquals(
                Sql.condition("name").like("?").andIf(false,"a").in(1,2)
                        .toString(),
                "name LIKE ?") ;
    }
    @Test
    public void testIf4(){
        Assertions.assertEquals(
                Sql.condition("name").like("?").andIf(true,"a").in(1,2)
                        .toString(),
                "name LIKE ? AND a IN(1,2)") ;
    }
    @Test
    public void testUnion4(){
        Assertions.assertEquals(
                Sql.select("1").from("a")
                        .union(Sql.select("1").from("b"))
                        .toString(),
                "SELECT 1 FROM a\nUNION\nSELECT 1 FROM b") ;
    }
}
