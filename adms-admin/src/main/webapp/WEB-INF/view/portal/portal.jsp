<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="decorator" content="portal" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0">
<meta name="keyword" content="">
<meta name="description" content="">
<title>小白世纪－首页</title>
<link type="image/x-icon" href="${ctx}/resources/img/icon-20160128.ico" rel="icon">
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="/css/lib/fontello-ie7.min.css?1426762594" /><![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/2.0/portal/style/index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/2.0/portal/style/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/2.0/portal/style/diapo.css" />

<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/resources/2.0/portal/script/jquery.easing.1.3.js"></script>
<script type='text/javascript' src='${ctx}/resources/2.0/portal/script/jquery.hoverIntent.minified.js'></script>
<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/jquery.mobile-1.0rc2.customized.min.js"></script>
<script type='text/javascript' src="${ctx}/resources/2.0/portal/script/diapo.js"></script>
<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/public.js"></script>

<header>
<script>
	$(function(){
		$('.pix_diapo').diapo();
	});
</script>

</header>
<body>
<!--header-->
<jsp:include page="top.jsp" />
<!--header end-->
<input type="hidden" id="columnNo" column="top_1" />
<!--container-->
	<div id="container">
		<div class="section active" id="section0">
			<div class="pix_diapo">
				<div class="banner0" str="核心技术" data-thumb="${ctx}/resources/2.0/portal/img/banner0.jpg">
					<img class="bg" src="${ctx}/resources/2.0/portal/img/banner0.jpg">
					<div class="caption elemHover fromLeft leftBox">
						<div class="d1 left"></div>
						<div class="d2 left">CBIR<span>/</span>SIFT<span>/</span>BOW<span>/</span>CNN</div>
					</div>
					<div class="caption elemHover fromRight rightBox">
						<div class="rightBox_top"></div>
						<div class="rightBox_middle clear">
							<img style='cursor:pointer;' onclick='window.open("${ctx}/product","_parent")' class="left" src="${ctx}/resources/2.0/portal/img/Video-search.png" alt="">
							<div class="left rightBox_font">
								<div class="znspss"><span class="fk"></span>智能视频搜索</div>
								<div>物件识别·场景识别</div>
							</div>
						</div>
						<div class="rightBox_bottom"></div>
					</div>
				</div>
				<div class="banner1" str="营销平台" data-thumb="${ctx}/resources/2.0/portal/img/banner1.jpg">
					<img class="bg" src="${ctx}/resources/2.0/portal/img/banner1.jpg">
					<div class=" elemHover fromTop banner1_top">
						<div class="banner1_title">
							<ul>
								<li class="l1">加米智能广告 <span style="font-weight: bold">互动营销平台</span></li>
								<li class="l2">从定制场景训练到场景匹配、广告投放、流量监控。提供全方位的完整闭环服务。</li>
							</ul>
						</div>
					</div>
					<div class="elemHover fromBottom banner1_bottom">
						<ul>
							<li class="l1 left">
								<img src="${ctx}/resources/2.0/portal/img/Video-content-search.png" alt="">
								<br>视频内容搜索
							</li>
							<li class="l2 left">
								<img src="${ctx}/resources/2.0/portal/img/SITUATION-icon2.png" alt="">
								<br>场景匹配
							</li>
							<li class="l3 left">
								<img src="${ctx}/resources/2.0/portal/img/Advertising-delivery.png" alt="">
								<br>广告投放
							</li>
							<li class="l5 left">
								<img src="${ctx}/resources/2.0/portal/img/Flow-statistics.png" alt="">
								<br>流量统计
							</li>
						</ul>
					</div>
				</div>
				<div class="banner2" str="定制服务" data-thumb="${ctx}/resources/2.0/portal/img/banner2.jpg">
					<img class="bg" src="${ctx}/resources/2.0/portal/img/banner2.jpg">
					<div class=" elemHover fadeIn banner2_top">
						<div class="banner2_top_title">一对一定制化服务</div>
					</div>
					<div class=" elemHover fadeIn banner2_bottom">
						<div class="banner2_bottom_font1">
							<ul>
								<li><span>内容审核</span>AUDITING</li>
								<li class="phone_none">对游戏直播、真人秀内容实时审核。</li>
							</ul>
						</div>
						<div class="banner2_bottom_font2">
							<ul>
								<li>SITUATION<span>情境广告</span></li>
								<li class="phone_none">精准投放，边看边买。</li>
							</ul>
						</div>
						<div class="banner2_bottom_font3">
							<ul>
								<li><span>版权监控</span>NO COPY RIGHT!</li>
								<li class="phone_none">全网监测，及时预警。</li>
							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="section" id="section1">
			<div class="content">
				<img class="situation" src="${ctx}/resources/2.0/portal/img/SITUATION.jpg" alt="">

				<div class="shoppingCart clear">
					<div class="shoppingCart_img_item left">
						<img class="shoppingCart_img" src="${ctx}/resources/2.0/portal/img/Shopping-Cart.png" alt="">
					</div>
					<div class="shoppingCart_font left">
						Hennessy轩尼诗
					</div>
				</div>
				<div class="situation_content">
					<img  src="${ctx}/resources/2.0/portal/img/SITUATION.png" alt=""><br>
					<ul class="situation_content_ul">
						<li class="l1"><span style="color:#3f3494;">情境广告</span><span style="color: #ffd701;">\</span>Contextual advertising</li>
						<li class="l2">精准投放，边看边买。</li>
						<li class="l3">基于视频内容搜索、挖掘相应广告场景、实现广告精准投放、多种广告互动形式、提升用户观看体验</li>
						<li class="l4"><a href="javascript:;"style='cursor:pointer;' onclick='window.open("${ctx}/product","_parent")'>马上体验</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="section" id="section2">
			<div class="content">
				<div class="Copyright clear">
					<img class="Copyright_img left" src="${ctx}/resources/2.0/portal/img/Copyright-monitoring.png" alt="">
					<ul class="Copyright_ul left">
						<li class="l1">版权监控</li>
						<li class="l2">Copyright-monitoring</li>
					</ul>
				</div>
				<div class="Copyright_content">
					<p>
						保护自主知识产权，维护社会公共秩序。对依法打击盗版视频、音影提供全方位的技术支持，完善的解决方案。
					</p>
					<img src="${ctx}/resources/2.0/portal/img/Copyright-monitoring-icon.png" alt="">
				</div>
			</div>
		</div>
		<div class="section" id="section3">
			<div class="content">
				<div class="content_audit clear">
					<div class="content_audit_content">
						<div class="content_audit_img left">
							<img src="${ctx}/resources/2.0/portal/img/Content-audit.png" alt="">
						</div>
						<div class="content_audit_font left">
							<ul>
								<li class="l1"><span	class="s1">内容审核</span><span class="s2">&nbsp;\&nbsp;</span>
									Content audit
								</li>
								<li class="l2">全网、24小时不间断、违规内容警报、</li>
								<li class="l3">准确定位、完整记录、及时通告、直播点播。</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="audit">
					<ul class="clear">
						<li class="l1 left"><img src="${ctx}/resources/2.0/portal/img/pornographic.png" alt=""><br>色情</li>
						<li class="l2 left"><img src="${ctx}/resources/2.0/portal/img/violence.png" alt=""><br>暴力</li>
						<li class="l3 left"><img src="${ctx}/resources/2.0/portal/img/Involved-in-terrorism.png" alt=""><br>反恐</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="section" id="section4">
			<div class="brand">
				<div class="them1">
					<div>他们正在使用加米互动技术</div>
				</div>
				<div class="brandItems">
					<ul class="ul1 clear">
						<li><img src="${ctx}/resources/2.0/portal/img/huasu.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/guoguang.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/hanhuo.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/tianhuyunshang2.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/ziyouwuxian.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/baishitong.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/gumi.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/cibn.jpg"></li>
					</ul>
				</div>
				<div class="them2">
					<div>合作伙伴<span style="color: #FECE00;">&nbsp;/&nbsp;</span><span style="color:#2e3192;">PARTNERS</span></div>
				</div>
				<div class="brandItems2">
					<ul class="ul2 clear">
						<li><img src="${ctx}/resources/2.0/portal/img/logo-dx.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/shenzhenwangluoguangbo.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/xinjiangguangdian.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/shanghaidongfang.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/shenzhenguangdian.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/zhongguoguangdian.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/logo-yd.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/stars-china.jpg"></li>
						<li><img src="${ctx}/resources/2.0/portal/img/PPTV.jpg" alt=""></li>
						<li><img src="${ctx}/resources/2.0/portal/img/centrin.png" alt=""></li>
					</ul>
				</div>
			</div>
			<!--bottom-->
			<div class="bottom_index">
				<jsp:include page="bottom.jsp" />
			</div>

			<!--bottom end-->
		</div>
	</div>
<!--container end-->
<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/pageswitch.js"></script>
<script type="text/javascript">
	$(function(){
		$("#container").switchPage({
			'keyboard' : true,
			'direction' : 'vertical',
			'easing':'ease',
		});

	});
</script>
</body>
</html>
