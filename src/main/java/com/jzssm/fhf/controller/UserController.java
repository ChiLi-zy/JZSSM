package com.jzssm.fhf.controller;


import com.github.pagehelper.PageInfo;
import com.jzssm.fhf.common.Params;
import com.jzssm.fhf.common.ResultUtil;
import com.jzssm.fhf.entity.DomainPkStar;
import com.jzssm.fhf.entity.DomainUser;
import com.jzssm.fhf.service.PkstarService;
import com.jzssm.fhf.service.UserService;
import com.jzssm.fhf.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author ：Angular
 * @ProjectName: JZSSM
 * @Package: com.jzssm.fhf.controller
 * @ClassName: UserController
 * @date ：Created in 2021/1/30 15:42
 * @description：用户
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping("userController")
@Api(value = "/userController", tags = "111", description = "用户接口")
public class UserController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private PkstarService pkstarService;

    /**
     * @Title: getHeader @Description: TODO @param @return @return String @throws
     */
    @RequestMapping(value = "/header")
    @ApiOperation(value = "根据header户信息", httpMethod = "GET", notes = "get user by id")
    public Map<String, Object> getHeader() {
        Map<String, Object> map = new HashMap<>();
        map.put("header", getHeadersInfo());
        map.put("user-agent", getUserAgent());
        return map;
    }


    /**
     * 分页查询用户信息列表
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
        modelAndView.addObject("pageNum", pageInfo.getPageNum());
        //每页的数量
        modelAndView.addObject("pageSize", pageInfo.getPageSize());
        //当前页的数量
        modelAndView.addObject("size", pageInfo.getSize());
        //总记录数
        modelAndView.addObject("total", pageInfo.getTotal());
        //总页数
        modelAndView.addObject("pages", pageInfo.getPages());
        modelAndView.addObject("couts", couts);
        modelAndView.setViewName("views/pages/admin/user_manager");
        return modelAndView;
    }


    /**
     * 根据登录ID查询用户信息
     *
     * @return
     */
    @RequestMapping(value = "/findUserByLoginId")
    @ApiOperation(value = "根据登录ID查询用户信息", httpMethod = "GET", notes = "根据登录ID查询用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public ModelAndView findUserByLoginId(@RequestParam String loginId, @ApiIgnore Params params, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        DomainUser pageInfo = userService.selectByPrimaryKey(Integer.parseInt(loginId));
        DomainPkStar pkInfo = pkstarService.selectByPrimaryKey(null, Integer.parseInt(loginId));

        modelAndView.addObject("pkstar", pkInfo);
        modelAndView.addObject("user", pageInfo);
        modelAndView.setViewName("views/pages/user/userDetail");
        return modelAndView;
    }



/*    private Map<String, Object> CommoditiesToMap(DomainUser domainUser) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name", checkStringIsEmpty(domainUser.getName()));
        map.put("locality", checkStringIsEmpty(domainUser.getLocality()));
        map.put("createTime",checkStringIsEmpty(domainUser.getCreateTime()));
        map.put("startPage", commodities.getStartPage());
        map.put("pageSize", commodities.getPageSize());
        return map;
    }*/

    private String checkStringIsEmpty(String param) {
        return param == null ? null : (param.equals("") ? null :  param );
    }

    @RequestMapping(value = "/updateUserBefore", method = GET)
    @ApiOperation(value = "修改用户信息跳转修改页", httpMethod = "GET", notes = "修改用户信息跳转修改页")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object updateUserBefore(@RequestParam String id, HttpSession session) {
        DomainUser domainUser = userService.selectByPrimaryKey(Integer.parseInt(id));
        session.setAttribute("domainUser", domainUser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("domainUser", domainUser);
        modelAndView.setViewName("views/pages/user/updateUser");
        return ResultUtil.success(modelAndView);
    }



    @RequestMapping(value = "/updateUser", method = POST)
    @ResponseBody
    @ApiOperation(value = "修改用户信息", httpMethod = "POST", notes = "修改用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object updateUser(DomainUser domainUser) {
        domainUser.setUserId(domainUser.getUserId());
        domainUser.setUserPwd(this.checkStringIsEmpty(MD5Util.getMD5String(domainUser.getUserPwd())));
        domainUser.setUserAddress(this.checkStringIsEmpty(domainUser.getUserAddress()));
        domainUser.setUserDemand(this.checkStringIsEmpty(domainUser.getUserDemand()));
        domainUser.setUserDispatchAddress(this.checkStringIsEmpty(domainUser.getUserDispatchAddress()));
        domainUser.setUserName(this.checkStringIsEmpty(domainUser.getUserName()));
        domainUser.setUserOtherDesc(this.checkStringIsEmpty(domainUser.getUserOtherDesc()));
        domainUser.setUserRole(this.checkStringIsEmpty(domainUser.getUserRole()));
        domainUser.setUserTelnum(this.checkStringIsEmpty(domainUser.getUserTelnum()));
        domainUser.setUserUrgent(this.checkStringIsEmpty(domainUser.getUserUrgent()));
        if (userService.updateByPrimaryKey(domainUser)) {
            return ResultUtil.success("修改成功！");
        } else {
            return ResultUtil.success("修改失败！");
        }

    }


    /**
     * ajax请求 的 分页查询
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadData")
    public HashMap<String, Object> loadData(Params params, @RequestBody DomainUser domainUser) {


        HashMap<String, Object> map = new HashMap<String, Object>();
        PageInfo<DomainUser> pageInfo = userService.finds(params);
        List<DomainUser> clist = pageInfo.getList();
        map.put("clist", clist);

        return map;
    }

    // get user agent
    private String getUserAgent() {
        return request.getHeader("user-agent");
    }

    // get request headers
    private Map<String, String> getHeadersInfo() {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private String getToken() {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if ("token".equals(key)) {
                String value = request.getHeader(key);
                return value;

            }
        }
        return null;
    }


}
