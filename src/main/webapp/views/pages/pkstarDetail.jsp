<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<%
	String s2 = (String)request.getParameter("pageInfo");//获取后台参数给s2变量，<%%>表示这范围内是服务器解析的语言
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>职位管理</title>
    <link rel="stylesheet" href="<%=basePath%>../assets/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>../assets/css/view.css"/>
    <link rel="stylesheet" href="<%=basePath%>../assets/css/admin.css"/>
    <link rel="stylesheet" href="<%=basePath%>../assets/css/style.css"/>
    <style type="text/css">
    	.layui-elem-quote{ font-size: 18px; margin-bottom: 20px;}
    	.layui-input-block img{ max-height: 160px; width: auto;}
    </style>
</head>
<body class="layui-view-body">
    <div class="layui-content">
    	<!--面包屑导航-->
    	<div class="mianb">
    		<div class="mblf layui-col-xs6 layui-col-md6">
    			 <i class="layui-icon">&#xe656;</i>
    			 <p>职位管理 > 职位管理 ><span>职位详情</span></p>
    		</div>    		
    	</div>
    	<!--面包屑导航-->
    	<blockquote class="layui-elem-quote">
		   人事专员
		  <a class="layui-btn layui-btn-normal float-right">修改</a>
		</blockquote> 
		<!--岗位标题-->
        <div class="layui-row">
            <div class="layui-card positionbox">
            	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>基本信息</legend>
					</fieldset>
					<form class="layui-form" action="">
						 <div class="layui-form-item">
							    <label class="layui-form-label"><span class="col-red">*</span>招聘岗位</label>
							    <div class="layui-input-block">
							        人事专员
							    </div>
						  </div>
						  <div class="layui-form-item">
							    <label class="layui-form-label">月薪范围</label>
							    <div class="layui-input-inline">
							        4000-6000
							    </div>							    
						  </div>
						  <div class="layui-form-item">
							    <label class="layui-form-label">最低学历</label>
							    <div class="layui-input-block">
							       大专 
							    </div>						    
						  </div>
						  <div class="layui-form-item">
							    <label class="layui-form-label">工作区域</label>
							    <div class="layui-input-inline">
							      河南省-郑州市-二七区
							    </div>							    			   
							</div>
						  <div class="layui-form-item layui-form-text">
							    <label class="layui-form-label">职位描述</label>
							    <div class="layui-input-block">
							    	1、在主管指导下通过各种方式（人才库，网站，cold call等）完成候选人的搜寻工作；</br>
									2、充分分析客户需求，快速学习行业，锁定目标人才，定制寻访方案，寻找合适的候选人，并出具人才推荐报告；</br>
									3、初步甄别筛选候选人，安排候选人与主管/客户面试；</br>
									4、整理候选人的推荐报告，负责数据的编辑和录入工作；</br>
									5、把所有的客户信息，职位信息，猎取总结与候选人简历等资料整理进入公司系统;</br>
								</div>							    
							</div>
					</form>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>职位设置</legend>
					</fieldset>
					<form  class="layui-form" action="">
						 <div class="layui-form-item">
							    <label class="layui-form-label"><span class="col-red">*</span>招聘人数</label>
							    <div class="layui-input-block">
							      5人
							    </div>
						  </div>
						  <div class="layui-form-item">							    
							    <div class="layui-inline">
							      <label class="layui-form-label">年龄要求</label>
							      <div class="layui-input-inline" style="width: 100px;">
							        18-32岁
							      </div>							      
							    </div>
						  </div>
						  <div class="layui-form-item">
						    <label class="layui-form-label">性别要求</label>
						    <div class="layui-input-block">
						      不限					      
						    </div>
						  </div>
					</form>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>福利待遇</legend>
					</fieldset>
					<div class="fuldy">
						<a href="javascript:;" class="layui-btn">社保</a>
						<a href="javascript:;" class="layui-btn">公积金</a>
						<a href="javascript:;" class="layui-btn">奖金</a>
						<a href="javascript:;" class="layui-btn">绩效</a>
						<a href="javascript:;" class="layui-btn">提成</a>
						<a href="javascript:;" class="layui-btn">年终奖</a>
						<a href="javascript:;" class="layui-btn">带薪年假</a>						
						<a href="javascript:;" class="layui-btn">通讯补助</a>
						<a href="javascript:;" class="layui-btn">交通补助</a>
						<a href="javascript:;" class="layui-btn">其他</a>
					</div>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>补充说明</legend>
					</fieldset>
					<form  class="layui-form" action="">
						 <div class="layui-form-item layui-form-text">
							    <label class="layui-form-label">补充说明</label>
							    <div class="layui-input-block">							    	
							       倾向于有工作经验的
							    </div>							    
							</div>
					</form>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>积分设置</legend>
					</fieldset>
					<form  class="layui-form" action="">
						 <div class="layui-form-mid layui-word-aux" style="padding: 0 0 10px 110px!important;">请根据公司招聘预算与职位紧急程度合理设定悬赏金额</div>
						 <div class="layui-form-item">						 	
						 	    <label class="layui-form-label"><span class="col-red">*</span>积分设置</label>
							    <div class="layui-input-inline">
							      10
							    </div>
							    <div class="layui-inline">
								      <label class="layui-form-label">悬赏类型</label>
								      <div class="layui-input-inline">
								        审核通过
								      </div>
								</div>
						  </div>
						  <div class="layui-form-item">
							    <label class="layui-form-label"><span class="col-red">*</span>积分设置</label>
							    <div class="layui-input-inline">
							      2000
							    </div>
							    <div class="layui-inline">
								      <label class="layui-form-label">悬赏类型</label>
								      <div class="layui-input-inline">
								        面试通过
								      </div>
								</div>								
						  </div>
					</form>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>企业信息</legend>
					</fieldset>
					<form  class="layui-form" action="">
						 <div class="layui-form-item">
							    <label class="layui-form-label"><span class="col-red">*</span>企业全称</label>
							    <div class="layui-input-block">
							      河南卓聘企业信息咨询有限公司
							    </div>
						  </div>
						  <div class="layui-form-item">							    
							    <div class="layui-inline">
							        <label class="layui-form-label">企业logo</label>
							        <div class="layui-input-block">
							      		  <img src="<%=basePath%>../assets/images/banner01.png"/>
							        </div>
							      </div>
						  </div>
						  <div class="layui-form-item">
							    <label class="layui-form-label">企业性质</label>
							    <div class="layui-input-block">
							      国有企业
							    </div>							    
						  </div>
						  <div class="layui-form-item">
							    <label class="layui-form-label">招聘地址</label>
							    <div class="layui-input-block">
							      河南省-郑州市-二七区
							    </div>							    				   
							</div>
							<div class="layui-form-item">
							    <label class="layui-form-label"><span class="col-red">*</span>详细地址</label>
							    <div class="layui-input-block">
							       郑州市二七区解放路正弘凯宾城2号楼40楼
							    </div>
						   </div>						  
						  <div class="layui-form-item">							    
							    <div class="layui-inline">
							      <label class="layui-form-label">企业规模</label>
							      <div class="layui-input-block">
							       50-200人
							      </div>							      
							    </div>
						  </div>						  
					</form>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>公司介绍</legend>
					</fieldset>
					<form  class="layui-form" action="">
						 <div class="layui-form-item layui-form-text">
							    <label class="layui-form-label">公司介绍</label>
							    <div class="layui-input-block">							    	
							        召π(召派) 是百姓网RPO的重装升级，原班人马只为更好的服务，更好的客户体验，本土"互联网＋人力资源"专业领先的服务供应商业务范围涵盖线上招聘广告、线下代理招聘、猎头、校园等人力资源服务，公司致力于通过AI人工智能系统的不断创新和运用，努力创建业内更加透明和高效的中基层人才搜寻机构，已有超过百余家省内外知名企业选择召π招募精英，有30余万的精英人才进入中端人才储备库。 召π(召派)为各大河南国企事业单位提供专业人力资源服务，帮助客户有效规避用人风险，降低经营成本，提高管理效率，增强核心竞争力，为客户提供专业性最强、时效性最快、服务链条全的河南人力资源解决方案，使服务质量达到新的高度。
							    </div>							    
							</div>
					</form>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					    <legend>公司环境</legend>
					</fieldset>
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">上传图片</label>
					    <div class="layui-form-mid layui-word-aux">可选择多张图片上传</div>
					    <div class="layui-input-block">						    	
					       <div class="layui-upload">
								  <img src="<%=basePath%>../assets/images/banner01.png" />
								  <img src="<%=basePath%>../assets/images/banner02.png" />
							</div>
					    </div>							    
					</div>				
			</div>           
        </div>
    </div>
    <script src="<%=basePath%>../assets/jquery.min.js"></script>
    <script src="<%=basePath%>../assets/layui.all.js"></script>
    <script>   	
		layui.use('form', function(){
		  var form = layui.form; 
		  form.render();
		}); 
	</script>
	
</body>
</html>