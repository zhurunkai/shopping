<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>乐购-商品列表</title>
    <link rel="stylesheet" href="style.css" />
    <link rel="stylesheet" href="addCom.css" />
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
        <div class="logo-line">
          <img
            class="logo-img"
            src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/logo.png"
            alt=""
          />
          <div class="title-text">添加商品</div>
        </div>
        <div class="line"></div>
        <div class="add-container">
          <form onsubmit="return false;">
            <div class="form-group">
              <label>商品名称</label>
              <input
                type="text"
                name="name"
                id="name-input"
                class="form-control"
              />
            </div>
            <div class="form-group">
              <label>商品价格</label>
              <input
                type="text"
                id="price-input"
                name="price"
                class="form-control"
              />
              <small id="emailHelp" class="form-text text-muted"
                >价格必须为数字。</small
              >
            </div>
            <div class="form-group">
              <label>商品描述</label>
              <textarea
                class="form-control"
                id="desc-input"
                name="desc"
                rows="3"
              ></textarea>
            </div>
            <div class="form-group">
              <label>上传封面</label>
              <input type="file" id="image" class="form-control-file" />
            </div>
            <div class="btn-box">
              <button class="btn btn-outline-danger" onclick="uploadCos()">
                上传
              </button>

              <button class="btn btn-outline-primary" onclick="send()">
                确定
              </button>
            </div>
          </form>
        </div>
      </div>
      <div class="order-box">
        <a href="getOrder.jsp">
          <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/orderitem.png" alt="">
        </a>
      </div>
      <div class="add-box">
        <a href="addCommodity.jsp">
          <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/add.png" alt="">
        </a>
      </div>

      <div class="mine-box">
        <a href="mycommodity.jsp">
          <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/mine.png" alt="">
        </a>
      </div>
      <div class="cart-box">
        <a href="cart.jsp">
          <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/cart.png" alt="">
        </a>
      </div>
    </div>
  </body>
  <script src="https://bookmanage-1258976754.cos.ap-shanghai.myqcloud.com/cos-js-sdk-v5.min.js"></script>
  <script>
    var i = false
    var url = ''
    var cos = new COS({
      SecretId: 'AKIDZ7a3StiVaXgprjJzFG1Wt2va2Phtb74r', //密钥id
      SecretKey: 'p9OURGwW0MJ1b4Oiw32s0ynSWUIGwa5T', //密钥的key
    })
    function GetTimeString() {
      var date = new Date()
      var yy = date.getFullYear().toString()
      var mm = (date.getMonth() + 1).toString()
      var dd = date.getDate().toString()
      var hh = date.getHours().toString()
      var nn = date.getMinutes().toString()
      var ss = date.getSeconds().toString()
      var mi = date.getMilliseconds().toString()

      var ret = yy + mm + dd + hh + nn + ss + mi
      return ret
    }
    function uploadCos() {
      var file = document.getElementById('image').files[0]
      var times = GetTimeString()
      var path =
        'https://bookmanage-1258976754.cos.ap-shanghai.myqcloud.com/' +
        times +
        '.jpg'
      if (file) {
        cos.putObject(
          {
            Bucket: 'bookmanage-1258976754', // 桶名-APPID  必须
            Region: 'ap-shanghai', //区域 必须
            Key: times + '.jpg', //文件名必须
            StorageClass: 'STANDARD',
            Body: file, // 上传文件对象
            onProgress: function (progressData) {
              console.log(JSON.stringify(progressData)) //上传成功的返回值
            },
          },
          function (err, data) {
            console.log(err || data) //上传失败的返回值
            console.log(data)
            url = 'https://' + data.Location
            sessionStorage.setItem('url',url)
            alert('上传成功')

          }
        )
      }
    }

    function send() {
      if ((url = '' && i == false)) {
        alert('请等待图片上传完成。')
      } else {
        $.ajax({
          method: 'POST',
          url: 'http://localhost:8080/shopping/commodity/add',
          data: {
            name: $('#name-input').val(),
            desc: $('#desc-input').val(),
            price: $('#price-input').val(),
            image: sessionStorage.getItem('url'),
          },
          success: function (res) {
            console.log(res)
            if (res == '200') {
              alert('添加成功')
              window.location.href =
                'http://localhost:8080/shopping/commodity/show'
            } else {
              alert('添加失败')
            }
          },
          error: function (err) {
            console.log(err)
            alert('添加失败')
          },
        })
      }
    }
  </script>
</html>
