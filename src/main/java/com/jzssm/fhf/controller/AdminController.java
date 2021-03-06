package com.jzssm.fhf.controller;

import com.github.pagehelper.PageInfo;
import com.jzssm.fhf.common.Params;
import com.jzssm.fhf.common.ResultUtil;
import com.jzssm.fhf.entity.DomainUser;
import com.jzssm.fhf.service.AdminService;
import com.jzssm.fhf.service.UserService;
import com.jzssm.fhf.utils.MD5Util;
import com.jzssm.fhf.utils.UuidTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author ：Angular
 * @ProjectName: JZSSM
 * @Package: com.jzssm.fhf.controller
 * @ClassName: AdminController
 * @date ：Created in 2021/2/6 9:46
 * @description：管理员类
 * @modified By：
 * @version: v1.0.0$
 */
@Controller
@RequestMapping("adminController")
@Api(value = "adminController", tags = "管理员操作接口", description = "管理员相关业务接口")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    /**
     * 首页，并且分页查询
     *
     * @return
     */
    @RequestMapping(value = "/findAllUserData")
    @ApiOperation(value = "分页查询用户列表", httpMethod = "GET", notes = "分页查询用户列表")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public ModelAndView index(@ApiIgnore Params params, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        //一开始第一页，查询10条
        params.setPageNo(1);
        params.setPageSize(10);
        PageInfo<DomainUser> pageInfo = userService.finds(params);
        List<DomainUser> userlist = pageInfo.getList();
        //查询数量
        long couts = userService.counts();

        modelAndView.addObject("userlist", userlist);
        //当前页
        modelAndView.addObject("currentPage", pageInfo.getPageNum());
        //每页的数量
        modelAndView.addObject("pageSize", pageInfo.getPageSize());
        //当前页的数量
        modelAndView.addObject("startPage", pageInfo.getSize());
        //总记录数
        modelAndView.addObject("countNumber", pageInfo.getTotal());
        //int sumPageNumber=countNumber%pageSize==0?(countNumber/pageSize):((countNumber/pageSize)+1);
        //总页数
        modelAndView.addObject("sumPageNumber", pageInfo.getPages());
        modelAndView.addObject("couts", couts);
        modelAndView.setViewName("views/pages/admin/user_manager");
        return modelAndView;
    }

    @RequestMapping(value = "/insertUser", method = POST)
    @ResponseBody
    @ApiOperation(value = "添加用户信息", httpMethod = "POST", notes = "添加用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true), @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object insertUser(DomainUser domainUser) {
        domainUser.setUserId(Integer.parseInt(UuidTools.getUuidNum()));
        domainUser.setUserPwd(this.checkStringIsEmpty(MD5Util.getMD5String(domainUser.getUserPwd())));
        domainUser.setUserAddress(this.checkStringIsEmpty(domainUser.getUserAddress()));
        domainUser.setUserDemand(this.checkStringIsEmpty(domainUser.getUserDemand()));
        domainUser.setUserDispatchAddress(this.checkStringIsEmpty(domainUser.getUserDispatchAddress()));
        domainUser.setUserName(this.checkStringIsEmpty(domainUser.getUserName()));
        domainUser.setUserOtherDesc(this.checkStringIsEmpty(domainUser.getUserOtherDesc()));
        domainUser.setUserRole(this.checkStringIsEmpty(domainUser.getUserRole()));
        domainUser.setUserTelnum(this.checkStringIsEmpty(domainUser.getUserTelnum()));
        domainUser.setUserUrgent(this.checkStringIsEmpty(domainUser.getUserUrgent()));
        if (userService.insert(domainUser)) {
            return ResultUtil.success("添加成功！");
        } else {
            return ResultUtil.success("添加失败！");
        }
    }


    @RequestMapping(value = "/updateUser", method = POST)
    @ResponseBody
    @ApiOperation(value = "修改用户信息", httpMethod = "POST", notes = "修改用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object updateUser(DomainUser domainUser) {
        domainUser.setUserId(domainUser.getUserId());
        if(domainUser.getUserPwd()!=null && !"".equals(domainUser.getUserPwd())){
            domainUser.setUserPwd(this.checkStringIsEmpty(MD5Util.getMD5String(domainUser.getUserPwd())));
        }
        domainUser.setUserAddress(this.checkStringIsEmpty(domainUser.getUserAddress()));
        domainUser.setUserDemand(this.checkStringIsEmpty(domainUser.getUserDemand()));
        domainUser.setUserDispatchAddress(this.checkStringIsEmpty(domainUser.getUserDispatchAddress()));
        domainUser.setUserName(this.checkStringIsEmpty(domainUser.getUserName()));
        domainUser.setUserOtherDesc(this.checkStringIsEmpty(domainUser.getUserOtherDesc()));
        domainUser.setUserRole(this.checkStringIsEmpty(domainUser.getUserRole()));
        domainUser.setUserTelnum(this.checkStringIsEmpty(domainUser.getUserTelnum()));
        domainUser.setUserUrgent(this.checkStringIsEmpty(domainUser.getUserUrgent()));
        if (userService.updateByPrimaryKeySelective(domainUser)) {
            return ResultUtil.success("修改成功！");
        } else {
            return ResultUtil.success("修改失败！");
        }

    }

    @RequestMapping(value = "/updateUserBefore", method = GET)
    @ResponseBody
    @ApiOperation(value = "修改用户信息跳转修改页", httpMethod = "GET", notes = "修改用户信息跳转修改页")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object updateUserBefore(@RequestParam String id, HttpSession session) {
        DomainUser domainUser = userService.selectByPrimaryKey(Integer.parseInt(id));
        session.setAttribute("domainUser", domainUser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("domainUser", domainUser);
        modelAndView.setViewName("views/pages/admin/updateUser.jsp");
        return ResultUtil.success(modelAndView);
    }


    @RequestMapping(value = "/deleteUser", method = POST)
    @ResponseBody
    @ApiOperation(value = "删除用户信息", httpMethod = "POST", notes = "删除用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true), @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object deleteUser(String id, String[] arrays) {
        Boolean index = null;
        if (id != null && !"".equals(id)) {
            index = userService.deleteByPrimaryKey(id);
            if (index) {
                return ResultUtil.success("删除成功！");
            }
        } else {
            for (String str : arrays) {
                index = userService.deleteByPrimaryKey(str);
                if (index) {
                    return ResultUtil.success("删除成功！");
                }
            }
        }
        return null;
    }


    private String checkStringIsEmpty(String param) {
        return param == null ? null : (param.equals("") ? null : param);
    }
    /* private String checkStringIsEmpty(String param) {
        return param == null ? null : (param.equals("") ? null : "%" + param + "%");
    }*/


}
