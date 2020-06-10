<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>乐购-登录</title>
    <link rel="stylesheet" href="style.css" />
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <link
      href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.css"
      rel="stylesheet"
    />
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.js"></script>
  </head>
  <body>
    <div class="page-container">
      <div class="window-box">
        <div class="logo-title-line">
          <img
            class="logo-img"
            src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/logo.png"
            alt=""
          />
          <div class="title-text">乐购-用户登录</div>
        </div>
        <div class="form-box">
          <form onsubmit="return false">
            <div class="form-group">
              <label>用户名</label>
              <input
                type="text"
                class="form-control"
                id="name-input"
                placeholder="请输入用户名"
              />
            </div>
            <div class="form-group">
              <label>密码</label>
              <input
                type="password"
                class="form-control"
                id="password-input"
                placeholder="请输入密码"
              />
            </div>
            <div class="btn-box">

              <button class="btn btn-outline-primary">
                <a href="register.jsp">去注册？</a>
              </button>

              <button class="btn btn-outline-primary" onClick="login()">
                登录
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
  <script>
    function login() {
      if ($('#name-input').val() == '') {
        alert('用户名不得为空！')
      } else {
        if ($('#password-input').val() == '') {
          alert('密码不得为空！')
        } else {
          // 发送登录请求
          $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/shopping/user/login',
            data: {
              userName: $('#name-input').val(),
              password: $('#password-input').val(),
            },
            success: function (res) {
              console.log(res)
              if (res == '200') {
                alert('登录成功')
                window.location.href =
                  'http://localhost:8080/shopping/commodity/show'
              } else {
                alert('登录失败')
              }
            },
            error: function (err) {
              console.log(err)
              alert('登录失败')
            },
          })
        }
      }
    }
  </script>
</html>
