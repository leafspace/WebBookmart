<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/11/26
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="IE=edge,chrome=1">
        <link rel="icon" type="image/x-ico" href="img/cloud-icon.png">
        <link href="css/normalize.css" rel="stylesheet"/>
        <link href="css/jquery-ui.css" rel="stylesheet"/>
        <link href="css/jquery.idealforms.min.css" rel="stylesheet" media="screen"/>
        <title>云收藏夹注册</title>

        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <![endif]-->

        <style type="text/css">
            body{font:normal 15px/1.5 Arial, Helvetica, Free Sans, sans-serif;color: #222;background:url(img/pattern.png);overflow-y:scroll;padding:60px 0 0 0;}
            #my-form{width:755px;margin:0 auto;border:1px solid #ccc;padding:3em;border-radius:3px;box-shadow:0 0 2px rgba(0,0,0,.2);}
            #comments{width:350px;height:100px;}
        </style>
        <script>
            function checkpassword(obj){
                var password1 = document.form.password.value;
                var password2 = document.form.c_pass.value;
                if(password1 == ""){
                    document.form.password.focus();
                    return false;
                }
                if(password1 != password2){
                    obj.style.borderColor="#d14848";
                }else{
                    obj.style.borderColor="#aaa";
                }
            }
        </script>
    </head>
    <body>
          <div class="row">
            <div class="eightcol last">

                <!-- Begin Form -->

                <form id="my-form" name="form" method="post" action="goRegister">

                    <section name="第一步">

                        <div><label>用户名:</label><input id="username" name="username" type="text"/></div>
                        <div><label>密码:</label><input id="pass" name="password" type="password"/></div>
                        <div><label>确认密码</label><input id="c_pass" name="c_pass" type="password" onkeyup="checkpassword(this)"/></div>
                        <div>
                            <label>是否为管理员</label>
                            <label><input type="radio" name="radio" value="否" checked/>否</label>
                            <label><input type="radio" name="radio" value="是" />是</label>
                            <label>管理员密钥&nbsp<br><input type="text" name="managerKey" /></label>
                        </div>
                    </section>

                    <section name="第二步">
                        <div id="kind">
                            <label>选择你喜欢的分类:</label>
                            <label><input type="checkbox" name="kinds" value="搜索引擎"/>搜索引擎</label>
                            <label><input type="checkbox" name="kinds" value="新闻网站"/>新闻网站</label>
                            <label><input type="checkbox" name="kinds" value="视频网站"/>视频网站</label>
                            <label><input type="checkbox" name="kinds" value="音乐网站"/>音乐网站</label>
                            <label><input type="checkbox" name="kinds" value="科技网站"/>科技网站</label>
                            <label><input type="checkbox" name="kinds" value="博客网站"/>博客网站</label>
                            <label><input type="checkbox" name="kinds" value="学习网站"/>学习网站</label>
                            <label><input type="checkbox" name="kinds" value="专业网站"/>专业网站</label>
                        </div>
                        <div><label>输入你喜欢的分类:</label>
                            <lable><input type="text" name="user-define" value="如果有多个，请使用'、'分隔"/></lable>
                        </div>
                    </section>

                    <section name="第三步">
                        <div><label>成为我们的会员吧！</label><lable><a href="index.jsp">点此获得会员码.</a></lable></div>
                        <div><label>输入您获得的会员码:</label><input type="text" name="vip-key" /></div>
                    </section>

                    <div><hr/></div>

                    <div>
                        <button type="submit">提交</button>
                        <button id="reset" type="button">重置</button>
                        <br />
                        点击提交表示您已同意本条款<a href="UserAgreement.jsp">阅读《用户注册协议》</a>
                    </div>

                </form>

                <!-- End Form -->

            </div>

        </div>


        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/jquery.idealforms.js"></script>
        <script type="text/javascript">
            var options = {

                onFail: function(){
                    alert( $myform.getInvalid().length +' invalid fields.' )
                },

                inputs: {
                    'password': {
                        filters: 'required pass',
                    },
                    'username': {
                        filters: 'required username',
                        data: {
                            //ajax: { url:'validate.php' }
                        }
                    },
                }

            };

            var $myform = $('#my-form').idealforms(options).data('idealforms');

            $('#reset').click(function(){
                $myform.reset().fresh().focusFirst()
            });

            $myform.focusFirst();
        </script>
    </body>
</html>