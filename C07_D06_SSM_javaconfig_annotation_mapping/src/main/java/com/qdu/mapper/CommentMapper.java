package com.qdu.mapper;

import com.qdu.entity.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CommentMapper {
    @Delete({
        "delete from comment",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cid);

    @Insert({
        "insert into comment (cid, cpid, ",
        "cuid, cinfo)",
        "values (#{cid,jdbcType=INTEGER}, #{cpid,jdbcType=INTEGER}, ",
        "#{cuid,jdbcType=INTEGER}, #{cinfo,jdbcType=VARCHAR})"
    })
    int insert(Comment record);

    @Select({
        "select",
        "cid, cpid, cuid, cinfo, ctime",
        "from comment",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cpid", property="cpid", jdbcType=JdbcType.INTEGER),
        @Result(column="cuid", property="cuid", jdbcType=JdbcType.INTEGER),
        @Result(column="cinfo", property="cinfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP)
    })
    Comment selectByPrimaryKey(Integer cid);

    @Select({
        "select",
        "cid, cuid, cinfo, ctime",
        "from comment",
        "where cpid = #{cpid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cuid", property="cuid", jdbcType=JdbcType.INTEGER),
        @Result(column="cinfo", property="cinfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Comment> selectByPostId(Integer cpid);

    @Update({
        "update comment",
        "set cpid = #{cpid,jdbcType=INTEGER},",
          "cuid = #{cuid,jdbcType=INTEGER},",
          "cinfo = #{cinfo,jdbcType=VARCHAR},",
          "ctime = #{ctime,jdbcType=TIMESTAMP}",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);

    @Select({
            "select count(*)",
            "from comment"
    })
    int countComment();
}