package com.qdu.mapper;

import com.qdu.entity.User;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer uid);

    @Insert({
        "insert into user (uid, uname, ",
        "upwd, uemail, uimg, ",
        "uinfo, ugender)",
        "values (#{uid,jdbcType=INTEGER}, #{uname,jdbcType=VARCHAR}, ",
        "#{upwd,jdbcType=VARCHAR}, #{uemail,jdbcType=VARCHAR}, #{uimg,jdbcType=VARCHAR}, ",
        "#{uinfo,jdbcType=VARCHAR}, #{ugender,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @Select({
        "select",
        "uid, uname, upwd, uemail, uimg, uinfo, ugender",
        "from user",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uname", property="uname", jdbcType=JdbcType.VARCHAR),
        @Result(column="upwd", property="upwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="uemail", property="uemail", jdbcType=JdbcType.VARCHAR),
        @Result(column="uimg", property="uimg", jdbcType=JdbcType.VARCHAR),
        @Result(column="uinfo", property="uinfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ugender", property="ugender", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer uid);

    @Select({
        "select",
        "uid, uname, upwd, uemail, uimg, uinfo, ugender",
        "from user"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uname", property="uname", jdbcType=JdbcType.VARCHAR),
        @Result(column="upwd", property="upwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="uemail", property="uemail", jdbcType=JdbcType.VARCHAR),
        @Result(column="uimg", property="uimg", jdbcType=JdbcType.VARCHAR),
        @Result(column="uinfo", property="uinfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ugender", property="ugender", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set uname = #{uname,jdbcType=VARCHAR},",
          "upwd = #{upwd,jdbcType=VARCHAR},",
          "uemail = #{uemail,jdbcType=VARCHAR},",
          "uimg = #{uimg,jdbcType=VARCHAR},",
          "uinfo = #{uinfo,jdbcType=VARCHAR},",
          "ugender = #{ugender,jdbcType=VARCHAR}",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "select",
            "uid, uname, upwd, uemail, uimg, uinfo, ugender",
            "from user",
            "where uemail = #{uemail,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="uname", property="uname", jdbcType=JdbcType.VARCHAR),
            @Result(column="upwd", property="upwd", jdbcType=JdbcType.VARCHAR),
            @Result(column="uemail", property="uemail", jdbcType=JdbcType.VARCHAR),
            @Result(column="uimg", property="uimg", jdbcType=JdbcType.VARCHAR),
            @Result(column="uinfo", property="uinfo", jdbcType=JdbcType.VARCHAR),
            @Result(column="ugender", property="ugender", jdbcType=JdbcType.VARCHAR)
    })
    User selectByEmail(String email);

    @Update({
            "update user",
            "set uimg = #{uimg,jdbcType=VARCHAR}",
            "where uid = #{uid,jdbcType=INTEGER}"
    })
    int updateAvatar(@Param("uid") Integer uid, @Param("uimg") String uimg);

    @Select({
            "select count(*)",
            "from user"
    })
    int countUser();
}