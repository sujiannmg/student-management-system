<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <title>SMS | Login</title>
  
  <link href="${contextPath}/commons/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/commons/font-awesome/css/font-awesome.css" rel="stylesheet">
  <link href="${contextPath}/commons/css/animate.css" rel="stylesheet">
  <link href="${contextPath}/commons/css/style.css" rel="stylesheet">
  <link href="${contextPath}/commons/css/bootstrapValidator.min.css" rel="stylesheet">
</head>

<body class="gray-bg">
  <div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
      <div>
        <h1 class="logo-name">MS</h1>
      </div>
      <h3>欢迎登录学生系统</h3>
      <!-- <p>更快，更便捷，更精准的为您服务</p> -->
      <!-- <p>登录. 了解一下自己吧.</p> -->
      <form id="loginForm" class="m-t" role="form" action="${contextPath}/scs/login" method = "POST">
        <div class="form-group">
            <input type="account" id="account" name = "account" class="form-control" placeholder="帐号" >
        </div>
        <div class="form-group">
            <input type="password" id="password" name = "password" class="form-control" placeholder="密码" >
        </div>
        <#if message ??>
        <p>${message}</p>
        </#if>
        <button type="submit" autocomplete="off" onclick="loag()" data-loading-text="登录中..." id="loginButton" class="btn btn-primary block full-width m-b">登录</button>
        <a href="#"><small>忘记密码?</small></a>
        <p class="text-muted text-center"><small>还没有帐号吗?</small></p>
        <a class="btn btn-sm btn-white btn-block" href="${contextPath}/scs/register">注册帐号</a>
        <#if errorMessage ??>
         <p class="text-muted text-center"><span class="text-danger">${errorMessage}</span></p>
        </#if>
      </form>
      <p class="m-t"> <small>xxx &copy; 2018</small> </p>
    </div>
  </div>

  <!-- Mainly scripts -->
  <script src="${contextPath}/commons/js/jquery-2.1.1.js"></script>
  <script src="${contextPath}/commons/js/bootstrap.min.js"></script>
  <script src="${contextPath}/commons/js/bootstrapValidator.min.js"></script>
  <script>
    $(function () {
      $('#loginForm').bootstrapValidator({
        feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
          account: {
            validators: {
              notEmpty: {
                  message: '帐号不能为空'
                  }
               }
        },
              password: {
                  validators: {
                      notEmpty: {
                          message: '密码不能为空'
                      }
                  }
              }
          }
      });
    });
    function loag() {
      var btn = $("#loginButton");
      btn.button('loading');
      if(document.getElementById("account").value == "" || document.getElementById("password").value == ""){
        btn.button('reset');
        }
    }
  </script>
</body>

</html>
