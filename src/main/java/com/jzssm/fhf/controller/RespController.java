package com.jzssm.fhf.controller;

import com.github.pagehelper.PageInfo;
import com.jzssm.fhf.common.Params;
import com.jzssm.fhf.common.ResultUtil;
import com.jzssm.fhf.entity.DomainMsg;
import com.jzssm.fhf.entity.DomainMsgResp;
import com.jzssm.fhf.service.MsgRespService;
import com.jzssm.fhf.service.MsgService;
import com.jzssm.fhf.utils.UuidTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author ：Angular
 * @ProjectName: JZSSM
 * @Package: com.jzssm.fhf.controller
 * @ClassName: MsgController
 * @date ：Created in 2021/2/9 12:18
 * @description：留言回复管理
 * @modified By：
 * @version: v1.0.0$
 */
@Controller
@RequestMapping("respController")
@Api(value = "respController", tags = "留言回复操作操作接口", description = "留言回复操作操作接口")
public class RespController {

    @Autowired
    MsgRespService msgRespService;
    /**
     * 首页，并且分页查询
     *
     * @return
     */
    @RequestMapping(value = "/findAllMsgRespData")
    @ApiOperation(value = "分页查询留言回复列表", httpMethod = "GET", notes = "分页查询留言回复列表")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public ModelAndView index(@ApiIgnore Params params, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        //一开始第一页，查询10条
        params.setPageNo(1);
        params.setPageSize(10);

        PageInfo<DomainMsgResp> pageInfo = null;
        if(Integer.parseInt(session.getAttribute("role").toString())==3){
            pageInfo = msgRespService.finds(params,Integer.parseInt(session.getAttribute("loginId").toString()));
        }else if(Integer.parseInt(session.getAttribute("role").toString())==2){
            pageInfo = msgRespService.finds(params,Integer.parseInt(session.getAttribute("loginId").toString()));
        }else if(Integer.parseInt(session.getAttribute("role").toString())==1){
            pageInfo = msgRespService.finds(params,null);
        }
        List<DomainMsgResp> msgResplist = pageInfo.getList();
        //查询数量
        //long couts = msgService.counts();

        modelAndView.addObject("msgResplist", msgResplist);
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
        //modelAndView.addObject("couts", couts);
        modelAndView.setViewName("views/pages/admin/msg_resp_manager");
        return modelAndView;
    }

    @RequestMapping(value = "/insertMsgResp", method = POST)
    @ResponseBody
    @ApiOperation(value = "添加留言回复信息", httpMethod = "POST", notes = "添加留言回复信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true), @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object insertMsgResp(DomainMsgResp domainMsgResp, HttpSession session) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        domainMsgResp.setMsgId(Integer.parseInt(this.checkStringIsEmpty(domainMsgResp.getMsgId().toString())));
        domainMsgResp.setResUserId(Integer.parseInt(session.getAttribute("loginId").toString()));
        domainMsgResp.setResId(Integer.parseInt(UuidTools.getUuidNum()));
        domainMsgResp.setResRole(session.getAttribute("role").toString());
        domainMsgResp.setResMsg(this.checkStringIsEmpty(domainMsgResp.getResMsg()));
        domainMsgResp.setResTime(sdf.format(new Date()));
        if (msgRespService.insert(domainMsgResp)) {
            return ResultUtil.success("回复成功！");
        } else {
            return ResultUtil.success("回复失败！");
        }
    }


    @RequestMapping(value = "/updateMsgResp", method = POST)
    @ResponseBody
    @ApiOperation(value = "修改留言回复信息", httpMethod = "POST", notes = "修改留言回复信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object updateMsgResp(DomainMsgResp domainMsgResp,HttpSession session) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        domainMsgResp.setMsgId(Integer.parseInt(this.checkStringIsEmpty(domainMsgResp.getMsgId().toString())));
        domainMsgResp.setResUserId(Integer.parseInt(this.checkStringIsEmpty(domainMsgResp.getResUserId().toString())));
        domainMsgResp.setResId(Integer.parseInt(UuidTools.getUuidNum()));
        domainMsgResp.setResMsg(this.checkStringIsEmpty(domainMsgResp.getResMsg()));
        domainMsgResp.setResTime(sdf.format(new Date()));
        if (msgRespService.updateByPrimaryKey(domainMsgResp)) {
            return ResultUtil.success("修改成功！");
        } else {
            return ResultUtil.success("修改失败！");
        }

    }

    @RequestMapping(value = "/updateMsgRespBefore", method = GET)
    @ResponseBody
    @ApiOperation(value = "修改留言信息跳转修改页", httpMethod = "GET", notes = "修改留言信息跳转修改页")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object updateMsgBefore(@RequestParam String id, HttpSession session) {
        DomainMsgResp domainMsgResp = msgRespService.selectByPrimaryKey(Integer.parseInt(id));
        session.setAttribute("domainMsgResp", domainMsgResp);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("domainMsgResp", domainMsgResp);
        modelAndView.setViewName("views/pages/admin/updateMsgResp.jsp");
        return ResultUtil.success(modelAndView);
    }


    @RequestMapping(value = "/deleteMsgResp", method = POST)
    @ResponseBody
    @ApiOperation(value = "删除留言回复信息", httpMethod = "POST", notes = "删除留言回复信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token标记", required = true), @ApiImplicitParam(paramType = "query", dataType = "int", name = "loginId", value = "loginId标记", required = true)})
    public Object deleteMsgResp(String id, String[] arrays) {
        Boolean index = null;
        if (id != null && !"".equals(id)) {
            index = msgRespService.deleteByPrimaryKey(id);
            if (index) {
                return ResultUtil.success("删除成功！");
            }
        } else {
            for (String str : arrays) {
                index = msgRespService.deleteByPrimaryKey(str);
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
}
