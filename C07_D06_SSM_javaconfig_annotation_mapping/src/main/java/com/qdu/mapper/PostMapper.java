package com.qdu.mapper;

import com.qdu.entity.Post;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface PostMapper {
    @Delete({
        "delete from post",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer pid);

    @Insert({
        "insert into post (pid, puid, ",
        "pname, ptime, ",
        "pinfo, pimg)",
        "values (#{pid,jdbcType=INTEGER}, #{puid,jdbcType=INTEGER}, ",
        "#{pname,jdbcType=VARCHAR}, #{ptime,jdbcType=TIMESTAMP}, ",
        "#{pinfo,jdbcType=VARCHAR}, #{pimg,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "pid") // 获取主键值
    int insert(Post record);

    @Select({
        "select",
        "pid, puid, pname, ptime, pinfo, pimg",
        "from post",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="puid", property="puid", jdbcType=JdbcType.INTEGER),
        @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
        @Result(column="ptime", property="ptime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pinfo", property="pinfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="pimg", property="pimg", jdbcType=JdbcType.VARCHAR)
    })
    Post selectByPrimaryKey(Integer pid);

    @Select({
        "select",
        "pid, puid, pname, ptime, pinfo, pimg",
        "from post"
    })
    @Results({
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="puid", property="puid", jdbcType=JdbcType.INTEGER),
        @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
        @Result(column="ptime", property="ptime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pinfo", property="pinfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="pimg", property="pimg", jdbcType=JdbcType.VARCHAR)
    })
    List<Post> selectAll();

    @Update({
        "update post",
        "set puid = #{puid,jdbcType=INTEGER},",
          "pname = #{pname,jdbcType=VARCHAR},",
          "ptime = #{ptime,jdbcType=TIMESTAMP},",
          "pinfo = #{pinfo,jdbcType=VARCHAR},",
          "pimg = #{pimg,jdbcType=VARCHAR}",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Post record);

    @Update("UPDATE post SET pimg = #{imagePaths} WHERE pid = #{postId}")
    void updatePostImages(@Param("postId") Integer postId, @Param("imagePaths") String imagePaths);

    @Select({
            "select",
            "pid, puid, pname, ptime, pinfo, pimg",
            "from post",
            "WHERE pname LIKE #{keyword} OR pinfo LIKE #{keyword}"
    })
    @Results({
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="puid", property="puid", jdbcType=JdbcType.INTEGER),
            @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
            @Result(column="ptime", property="ptime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pinfo", property="pinfo", jdbcType=JdbcType.VARCHAR),
            @Result(column="pimg", property="pimg", jdbcType=JdbcType.VARCHAR)
    })
    List<Post> searchPostsByKeyword(@Param("keyword") String keyword);

    @Select({
            "select",
            "pid, puid, pname, ptime, pinfo, pimg",
            "from post",
            "WHERE  puid = #{puid}"
    })
    @Results({
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="puid", property="puid", jdbcType=JdbcType.INTEGER),
            @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
            @Result(column="ptime", property="ptime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pinfo", property="pinfo", jdbcType=JdbcType.VARCHAR),
            @Result(column="pimg", property="pimg", jdbcType=JdbcType.VARCHAR)
    })
    List<Post> listMyPost(@Param("puid") Integer puid);

    @Select({
            "select",
            "count(*)",
            "from post"
    })
    int countPost();
}