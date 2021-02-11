<%@ page import="com.jzssm.fhf.entity.DomainUser" %><%--
  Created by IntelliJ IDEA.
  User: Angular
  Date: 2021/1/10
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<%
    String param = (String)session.getAttribute("token");
    String userName = (String)session.getAttribute("userName");
    String role = (String)session.getAttribute("role");
    Integer loginId = Integer.parseInt(session.getAttribute("loginId").toString());
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>views/assets/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>views/assets/css/admin.css">
    <link rel="icon" href="<%=basePath%>views/favicon.ico">
    <title>基于SSM的家政服务管理平台</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header custom-header">
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item slide-sidebar" lay-unselect>
                <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
            </li>
        </ul>
        <p class="datexians">基于SSM的家政服务管理平台</p>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;"><%=role%> | <%=userName%></a>
                <dl class="layui-nav-child">
                    <dd><a href="">帮助中心</a></dd>
                    <dd><a href="views/login.jsp">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side custom-admin">
        <div class="layui-side-scroll">
            <div class="custom-logo">
                <img src="<%=basePath%>views/assets/images/logo.png" alt=""/>
                <h1>管理系统</h1>
            </div>
            <ul id="Nav" class="layui-nav layui-nav-tree">
                <li class="layui-nav-item" >
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe665;</i>
                        <em>首页管理</em>
                    </a>
                   <%-- <dl class="layui-nav-child">
                        <dd><a href="<%=basePath%>views/pages/01轮播图.jsp">轮播图</a></dd>
                        <dd><a href="<%=basePath%>views/pages/02任务发布.html">任务发布</a></dd>
                        <dd><a href="<%=basePath%>views/pages/03消息发布.html">消息发布</a></dd>
                    </dl>--%>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe857;</i>
                        <em>用户管理</em>
                    </a>
                    <dl class="layui-nav-child">
<%--                        <dd><a href="<%=basePath%>adminController/findAllUserData?loginId=<%=loginId%>&token=<%=param%>">用户列表</a></dd>--%>
                        <dd><a href="<%=basePath%>adminController/findAllUserData?loginId=<%=loginId%>&token=<%=param%>">用户列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe663;</i>
                        <em>留言管理</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="<%=basePath%>msgController/findAllMsgData?loginId=<%=loginId%>&token=<%=param%>">留言列表</a></dd>
                        <dd><a href="<%=basePath%>respController/findAllMsgRespData?loginId=<%=loginId%>&token=<%=param%>">回复列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe653;</i>
                        <em>评星管理</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><dd><a href="<%=basePath%>pkstarController/findAllPkstarData?loginId=<%=loginId%>&token=<%=param%>">评星列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe631;</i>
                        <em>工作职位管理</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="<%=basePath%>workController/findAllWorkData?loginId=<%=loginId%>&token=<%=param%>">职位列表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
            <ul id="appTabs" class="layui-tab-title custom-tab" style="display: none;"></ul>
            <div id="appTabPage" class="layui-tab-content"></div>
        </div>
    </div>
    <div class="layui-footer">
        <p>网络工程专业：<a href="http://www.mycodes.net/" target="_blank">冯贺飞</a></p>
    </div>
    <div class="mobile-mask"></div>
</div>
<script src="<%=basePath%>views/assets/layui.js"></script>
<script src="<%=basePath%>views/js/home.js" data-main="home"></script>
<script src="<%=basePath%>views/js/pageJs.js"></script>
</body>
</html>