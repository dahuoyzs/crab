package cn.bigfire.crab.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：dahuo
* @ Date   ：2020-01-11
* @ Desc   ：
*/
@Data
@Accessors(chain = true)
@ApiModel(value="AppUser对象", description="")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "9位会员号")
    private String member;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    private String userSign;

    @ApiModelProperty(value = "性别0未知1男2女")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "年")
    private Integer year;

    @ApiModelProperty(value = "月")
    private Integer month;

    @ApiModelProperty(value = "日")
    private Integer day;

    private String constellation;

    @ApiModelProperty(value = "用户状态0未激活1已激活2已失效3已删除")
    private Integer userStatus;

    @ApiModelProperty(value = "手机号状态0未激活1已激活2已失效")
    private Integer phoneStatus;

    @ApiModelProperty(value = "手机号状态0未激活1已激活2已失效")
    private Integer emailStatus;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "个人信件是否公开1公开2不公开")
    private Integer letterPublic;

    @ApiModelProperty(value = "三方登录微信openId")
    private String wxOpenId;

    @ApiModelProperty(value = "防止微信token失效.可通过相关接口刷新token.")
    private String wxRefreshToken;

    @ApiModelProperty(value = "三方登录微博uid")
    private String wbUid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
