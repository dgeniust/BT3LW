<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
</head>
<style>
#feedback-form {
  width: 280px;
  margin: 0 auto;
  background-color: #fcfcfc;
  padding: 20px 50px 40px;
  box-shadow: 1px 4px 10px 1px #aaa;
  font-family: sans-serif;
}

#feedback-form * {
    box-sizing: border-box;
}

#feedback-form h2{
  text-align: center;
  margin-bottom: 30px;
}

#feedback-form input {
  margin-bottom: 15px;
}

#feedback-form input {
  display: block;
  height: 32px;
  padding: 6px 16px;
  width: 100%;
  border: none;
  background-color: #f3f3f3;
}

#feedback-form label {
  color: #777;
  font-size: 0.8em;
}


#feedback-form input:not(:checked) + #feedback-phone {
  height: 0;
  padding-top: 0;
  padding-bottom: 0;
}

#feedback-form #feedback-phone {
  transition: .3s;
}

#feedback-form button[type=submit] {
  display: block;
  margin: 20px auto 0;
  width: 150px;
  height: 40px;
  border-radius: 25px;
  border: none;
  color: #eee;
  font-weight: 700;
  box-shadow: 1px 4px 10px 1px #aaa;
  
  background: #207cca; /* Old browsers */
  background: -moz-linear-gradient(left, #207cca 0%, #9f58a3 100%); /* FF3.6-15 */
  background: -webkit-linear-gradient(left, #207cca 0%,#9f58a3 100%); /* Chrome10-25,Safari5.1-6 */
  background: linear-gradient(to right, #207cca 0%,#9f58a3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#207cca', endColorstr='#9f58a3',GradientType=1 ); /* IE6-9 */
}
</style>
<body>
<div id="feedback-form">
	  <h2 class="header">Quên mật khẩu</h2>
	  <div>
	    <form action="forgetpw" method ="post">
	    	<label for="text"><b>Email</b></label>
	    	<input type="email" placeholder="Enter your email" name="email" id="email" required>
			<label for="password"><b>Mật khẩu mới</b></label>
			<input type="password" name="pword" id="pword" placeholder="Enter your new password" required>
			<p class="notification" style="color: blue">${msg}</p>
			<button type="submit">Xác nhận</button>
	    </form>
	  </div>
	</div>
</body>
</html>