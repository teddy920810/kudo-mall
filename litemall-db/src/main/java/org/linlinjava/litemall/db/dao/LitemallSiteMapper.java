package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallSite;
import org.linlinjava.litemall.db.domain.LitemallSiteExample;

public interface LitemallSiteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    long countByExample(LitemallSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int insert(LitemallSite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int insertSelective(LitemallSite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    LitemallSite selectOneByExample(LitemallSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    LitemallSite selectOneByExampleSelective(@Param("example") LitemallSiteExample example, @Param("selective") LitemallSite.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    List<LitemallSite> selectByExampleSelective(@Param("example") LitemallSiteExample example, @Param("selective") LitemallSite.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    List<LitemallSite> selectByExample(LitemallSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    LitemallSite selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallSite.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    LitemallSite selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    LitemallSite selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallSite record, @Param("example") LitemallSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallSite record, @Param("example") LitemallSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallSite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallSite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_site
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}