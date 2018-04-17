package com.company.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionManager {
    private Connection conn;

    protected TransactionManager(Connection conn) {
        this.conn = conn;
    }




    /** 开启事务 */
    public void beginTransaction() throws Exception{
        try {
            conn.setAutoCommit(false);  //把事务提交方式改为手工提交
        } catch (SQLException e) {
            throw new Exception("开启事务时出现异常",e);
        }
    }

    /** 提交事务并关闭连接 */
    public void commitAndClose(ResultSet rs, PreparedStatement pstmt) throws Exception{
        try {
            conn.commit(); //提交事务
        } catch (SQLException e) {
            throw new Exception("提交事务时出现异常",e);
        }finally{
            JdbcUtils.closeAll(rs,pstmt,conn);
        }
    }

    /** 回滚并关闭连接 */
    public void rollbackAndClose(ResultSet rs, PreparedStatement pstmt)throws Exception{
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new Exception("回滚事务时出现异常",e);
        }finally{
            JdbcUtils.closeAll(rs,pstmt,conn);
        }
    }
}
