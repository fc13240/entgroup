<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帮助中心 - 全球领先移动广告平台、手机广告专家 - 加米广告</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="decorator" content="help" />
<meta name="viewport" content="width=1010px,initial-scale=0.9,maximum-scale=1.0">
<link type="image/x-icon" href="${ctx}/resources/img/favicon.ico" rel="shortcut icon">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/include/xiaobai/css/fontello.min.css" />
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="/css/lib/fontello-ie7.min.css?1426762594" /><![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/include/xiaobai/css/xiaobai.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/include/xiaobai/css/zh-cn.min.css" />

<script type="text/javascript">
	var ADMS = {
		ctx : '${ctx}'
	};
	//         
	$(document).ready(function() {
		Youmi.backTop.init();
	});

	//
</script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-column.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-util.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/layer-v2.0/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/system/sdk/sdk.js"></script>
<body>
	<div id="container">
		<div id="header">
			<div id="header-wrapper" class="layout-center clearfix">
				<a id="logo" href="${ctx}/" class="pull-left sprite-ui icon-logo" style="background:url(${ctx}/resources/img/logo-portal.png)">加米</a>
				<ul id="header-nav" class="unstyled pull-right">
					<li><a href="${ctx}/">首页</a></li>
					<li><a href="${ctx}/product">产品介绍</a></li>
					<!-- 
					<li><a href="#">成功案例</a></li>
					 -->
					<li><a href="${ctx}/sdk">SDK下载</a></li>
					<li><a href="${ctx}/news">加米动态</a></li>
					<li class="current"><a href="${ctx}/help">帮助中心</a></li>
					<li><a href="${ctx}/login">登录/注册</a></li>
				</ul>
			</div>
		</div>

		<div id="content">
			<div class="layout-center" style="padding-top:40px;">
				<div class="news-index-slider">
					<div class="slider-border-top"></div>
					<div class="slider-pic"></div>
					<div class="slider-border-bottom"></div>
				</div>
				<div class="news-box">
					<div class="row-fluid">
						<div class="span10">
							<h1>
								<i class="title-square-middle"></i>平台常见问题
							</h1>
							<div class="help-body">
								<div class="help-box help-box-special">
									<h2>
										<i class="title-square-small"></i>小白平台介绍
									</h2>
									<ul class="faq-ul unstyled">
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">1</i>小白广告是什么？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">小白广告平台致力于为数以万计的企
												业广告主提供精准的产品营销和品牌推广服务，为应用开发者创造公正和优质的广告收益。在这里，广告主可以轻松地创建自己的精准广告，平台将会主动地将广告
												推送至成千上万安装在用户手机上的应用程序中；开发者则只需要提交自己的应用程序信息，安装平台提供的广告条插件，并努力将自己的应用程序推广出去，即可
												获得广告收益。</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">2</i>为什么选择小白？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">无论是广告主还是开发者，小白广告始终坚持“服务客户，用户第一，创造价值”的经营理念，努力为平台的用户服务并创造价值。</p>
										</li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">3</i>对广告主来说，“小白”的优势是什么？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<div class="hide">
												<ul>
													<li>独有的精准投放目标策略，提高广告的投放效果</li>
													<li>绚丽、轮放的广告展现形式，增强广告吸引力</li>
													<li>多项防作弊措施，有效降低投放成本</li>
													<li>众多优秀的合作应用，庞大的目标消费群</li>
												</ul>
											</div></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">4</i>对开发者来说，“小白”的优势是什么？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<div class="hide">
												<ul>
													<li>绚丽、轮放的广告展现形式，提升广告点击率</li>
													<li>丰富、优质的广告使得广告收入更多更稳定</li>
													<li>灵活、方便的结算机制能有效保障用户及时收获广告佣金</li>
													<li>公正、及时的广告效果监测，使开发者不再担心数据的可靠性</li>
													<li>独有的开发者社区支持，使得应用开发和推广更加简单有效</li>
												</ul>
											</div></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">5</i>对渠道来说，“小白”的优势是什么？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<div class="hide">
												<ul>
													<li>步骤简单，无需嵌入广告SDK，即可申请广告合作</li>
													<li>广告主覆盖各行各业，保障广告收益更多更稳定</li>
													<li>合作形式丰富，为渠道推荐关联性最强的广告，供灵活匹配</li>
													<li>渠道自主优化广告投放，兼顾自定义的用户体验，提高投产比</li>
													<li>稳健快捷的结算机制，保障受益，及时提现</li>
												</ul>
											</div></li>
									</ul>
								</div>
								<div class="help-box help-box-special">
									<h2>
										<i class="title-square-small"></i>注册与激活
									</h2>
									<ul class="faq-ul unstyled">
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">1</i>加入小白需要什么条件？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">对于广告主来说，要求是愿意在本平台付费投放广告的个人或企业；对于开发者来说，要求是愿意提供无任何违法、色情、反动内容的应用程序的个人或企业；对于渠道来说，要求是有自主经营的网站，并愿意在网站中接收来自小白的广告的个人或企业。</p>
										</li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">2</i>如何成为小白用户？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">
												进入“<a href="/">小白广告</a>”平台首页，点击进入<a href="/register">注册</a>页面，认真阅读平台的服务协议，按照相关提示信息完成资料录入后即可注册成为“小白”用户。
											</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">3</i>“广告主”，“渠道”，“开发者”的身份选错了怎么办？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">如果选择错了，您可以更换一个邮箱，重新注册。</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">4</i>收不到激活邮件怎么办？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">如果收件箱收不到激活邮件，可以查看下垃圾邮箱或者广告邮箱。注：网易的邮箱（以@163.com、@126.com和@yeah.net为后缀的邮箱）有可能会将自动发送的邮件归类在垃圾邮箱或者广告邮件夹。您将小白的邮箱号设为联系人下次就可以正常接收了。如果还是没找到，请将注册邮箱号告诉小白客服，客服会帮您处理。</p>
										</li>
									</ul>
								</div>
								<div class="help-box help-box-special">
									<h2 id="faq-login">
										<i class="title-square-small"></i>账户登录
									</h2>
									<ul class="faq-ul unstyled">
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">1</i>怎样登录小白广告？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">
												您可以在<a href="/">首页</a>的导航栏输入账号、密码登录，也可以在<a href="/login">登录</a>页面登录。
											</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">2</i>常见无法登录的原因？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<div class="hide">
												<p>首先，请检查您邮箱地址和密码是否被正确输入了，您可以清空邮箱和密码，然后尝试重新输入邮箱和密码进行再次登录；</p>
												<p>另外，密码输入需要区分大小写，请检查您是否开启了大写锁定（Caps Lock）；</p>
												<p>再者，如缓存文件过多、安全级别过高、网络繁忙、浏览器版本较低等原因也会导致邮箱无法登录，具体解决方法如下：</p>
												<ul>
													<li>1）打开IE浏览器点击“工具”－“Internet选项”</li>
													<li>2）选择“常规”－点击“删除cookies”后再点击“确定”</li>
													<li>3）点击“删除文件(F)”-在“删除所有脱机内容”前打勾，点击“确定”</li>
													<li>4）点击“安全”－将“安全级”调为中级</li>
													<li>5）点击“隐私”设置为“默认”级别，最后点击“确定”保存操作。完成以上操作后请再重新启动电脑</li>
												</ul>
											</div></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">3</i>忘记密码怎么办？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">
												进入“小白广告”平台首页，点击“<em>登录</em>”弹出登录窗口，点击“<em>忘记密码</em>”进入相应页面，填写注册邮箱后，系统将自动发送一个新的随机密码至该邮箱，用新密码登录后请到用户的“个人信息”中修改密码。
											</p></li>
									</ul>
								</div>
								<div class="help-box help-box-special">
									<h2>
										<i class="title-square-small"></i>身份验证
									</h2>
									<ul class="faq-ul unstyled">
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">1</i>身份验证需要提交什么材料？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">个人开发者需要提交身份证正反面；公司开发者需要提交公司营业执照、银行开户许可证；公司委托个人提款时需要提交个人的身份证正反面及公司委托函，以上材料必须清晰可见。</p>
										</li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">2</i>未满18岁怎样进行身份验证？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">如开发者未满18岁，可上传朋友或亲友的身份证正反面进行身份认证</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">3</i>港澳台开发者怎样进行身份验证？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">港澳台胞可上传港澳台身份证，或者可以提交大陆亲友的身份证正反面进行认证；如提交的是港澳台身份证，所扣税点为8.65%</p>
										</li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">4</i>身份验证审核需要多长时间？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">身份认证审核会在1至2个工作日内完成</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">5</i>为什么我的身份验证没有通过？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">请查看注册邮箱，根据邮件所示原因进行相应的修改，重新提交</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">6</i>身份验证不通过怎么办？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">可于提款设置界面，重新填写身份认证信息，提交审核</p></li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">7</i>通过身份验证后，我想重新进行验证要怎样的操作？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">请联系小白客服，修改身份认证状态，重新进行认证</p></li>
									</ul>
								</div>
								<div class="help-box help-box-special">
									<h2>
										<i class="title-square-small"></i>账户信息维护
									</h2>
									<ul class="faq-ul unstyled">
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">1</i>可以更改注册邮箱吗？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<p class="hide">不能更改注册邮箱，如果您需要换另一个邮箱作为账号，那么需要使用一个新邮箱进行注册。另外，如果想要将旧账号的应用转移到新账号，那么必须先在旧账号将应用删除，然后在新账号那里重新创建，按照流程提交审核。</p>
										</li>
										<li>
											<h5 class="question-title">
												<i class="sprite-ui icon-index align-middle">2</i>如果我要更改我的银行帐号等财务信息要怎么办？<a
													class="sprite-ui icon-drop-down" href="#">下拉</a>
											</h5>
											<div class="hide">
												<p>如果新的银行账户的开户人与通过身份认证的身份证姓名一致的话，用户登录平台后，可以在“控制面板”菜单中的和“账户信息”更改用户在平台上登记的账户信息则可；
													如果新的银行账户的开户人与通过身份认证的身份证姓名不一致，那您需要联系客服，提供身份证复印件，重新进行身份验证。</p>
												<p>注意：需要申请提款的话，新的银行账户的开户人与通过身份认证的身份证姓名必须一致。</p>
											</div></li>
									</ul>
								</div>
							</div>

						</div>
						<div class="span2" style="margin-left:0;width:17%;">
							<ul class="news-nav nav help-nav">
								<li><a href="/page/about">关于我们</a>
								</li>
								<li class="helpNavLv1"><a class="helpNavLv1_link"
									href="javascript:;">广告主服务</a>
									<ul class="news-sub-nav unstyled hide">
										<li class="dashed-bottom dashed-top"><a class=" "
											href="/page/help/ader/coopera">业务合作</a>
										</li>
										<li><a href="/page/ader" target="_blank">介绍页面</a>
										</li>
									</ul></li>
								<li class="helpNavLv1"><a class="helpNavLv1_link"
									href="javascript:;">开发者服务</a>
									<ul class="news-sub-nav unstyled hide">

										<li class="dashed-bottom dashed-top"><a class=" "
											href="/page/help/developer/guide">入门指南</a>
										</li>

										<li class="dashed-bottom"><a
											href="/page/help/developer/devprotocol">开发者协议</a>
										</li>

										<li class="dashed-bottom"><a
											href="/page/help/developer/weixin">微信服务</a>
										</li>

										<!-- <li class="dashed-bottom"><a  href="/page/help/developer/description">平台使用说明</a></li> -->

										<li class="dashed-bottom"><a
											href="/page/help/developer/promotion">小白渠道号</a>
										</li>

										<li><a href="/page/developer" target="_blank">介绍页面</a>
										</li>
									</ul></li>
								<li class="helpNavLv1"><a class="helpNavLv1_link"
									href="javascript:;">常见问题</a>
									<ul class="news-sub-nav unstyled ">
										<li class="dashed-bottom dashed-top"><a class="selected"
											href="/page/help/">平台常见问题</a>
										</li>

										<li class="dashed-bottom"><a href="/page/help/faq_ader">广告主常见问题</a>
										</li>

										<li class="dashed-bottom"><a
											href="/page/help/faq_developer">开发者常见问题</a>
										</li>

										<li><a href="/page/help/faq_siter">渠道常见问题</a>
										</li>
									</ul></li>
								<li class="helpNavLv1"><a class="helpNavLv1_link"
									href="javascript:;">规则介绍</a>
									<ul class="news-sub-nav unstyled hide">
										<li class="dashed-bottom dashed-top"><a class=" "
											href="/page/terms">服务条款</a>
										</li>

										<li class="dashed-bottom"><a href="/page/privacy">隐私政策</a>
										</li>

										<li><a href="/page/rules">应用准入规则</a>
										</li>
									</ul></li>
								<li class="helpNavLv1"><a class="helpNavLv1_link"
									href="javascript:;">合作伙伴</a>
									<ul class="news-sub-nav unstyled hide">
										<li class="dashed-bottom dashed-top"><a class=""
											href="/page/partner">知名伙伴</a>
										</li>

										<li><a class="" href="/page/impression">伙伴印象</a>
										</li>
									</ul></li>
								<li><a class="" href="/page/sitemap">网站地图</a>
								</li>
								<li class="helpNavLv1"><a class="helpNavLv1_link"
									href="javascript:;">加入我们</a>
									<ul class="news-sub-nav unstyled hide">
										<li class="dashed-bottom dashed-top"><a class=" "
											href="/page/join">小白招聘</a>
										</li>

										<li class="dashed-bottom"><a href="/page/join/tech">研发中心</a>
										</li>

										<li class="dashed-bottom"><a href="/page/join/adunit">广告事业部</a>
										</li>

										<li class="dashed-bottom"><a href="/page/join/gameunit">游戏事业部</a>
										</li>

										<li><a href="http://campus.youmi.net" target="_blank">校招专题</a>
										</li>
									</ul></li>
								<li class="helpNavLv1"><a class="helpNavLv1_link"
									href="javascript:;">联系反馈</a>
									<ul class="news-sub-nav unstyled hide">
										<li class="dashed-bottom dashed-top"><a class=" "
											href="/page/contact_us">联系我们</a>
										</li>

										<li><a href="/page/help/feedback">在线反馈</a>
										</li>
									</ul></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- include页脚 -->
		<jsp:include page="portal-footer.jsp" />

		<div id="switchold-modal" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="SwitcholdModalLabel"
			aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h5 style="margin:0;">小白温馨提示</h5>
			</div>
			<div class="modal-body">
				<h3 class="text-center text-success">
					亲爱的用户，新版是否有不足的地方？<br>希望您能留下宝贵的意见
				</h3>
				<ul class="inline text-center" style="margin:40px 0 20px 0;">
					<li style="margin-right:10px;"><a href="http://old.youmi.net"
						class="btn btn-large ymbtn" target="_blank">不，直接切到旧版</a>
					</li>
					<li><a href="/page/help/feedback"
						class="btn btn-large ymbtn btn-warning" target="_blank">好的，我要反馈</a>
					</li>
				</ul>
			</div>
		</div>

	</div>
	<script type="text/javascript" src="${ctx}/resources/include/xiaobai/js/xb.lib.min.js"></script>
	<!--<script type="text/javascript" src="${ctx}/resources/include/xiaobai/js/xb.qiantai.min.js"></script> -->
	<script type="text/javascript">
		//         
		$(document).ready(function() {
			Youmi.Faq.init();
		});

		//
	</script>
	<script type="text/javascript">
		//         
		$(document).ready(function() {
			Youmi.header.init();
		});

		//
	</script>
	<script type="text/javascript">
		//         
		$(document).ready(function() {
			Youmi.backTop.init();
		});

		//
	</script>
</body>
</html>
