<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Form</title>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
}

/* Add padding to container elements */
.container {
  padding: 16px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 90px) {
  .cancelbtn, .signupbtn {
     width: 90%;
  }
}
    </style>
</head>
<body>

   
        <form action="Signup.html" style="border:2px solid #ccc">
  <div class="container">
    <h1>Sign Up</h1>
    <%String success=(String)request.getAttribute("success");
 if(success!=null){%>
 <h2 class="success"><%=success%></h2>
 <%}%>
 <%String failure=(String)request.getAttribute("failure");
 if(failure!=null){%>
 <h2 class="failure"><%=failure%></h2>
 <%}%>
    <p>Please fill in this form to create an account.</p>
    <hr>
            <!-- User Name -->
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required>

            <!-- Phone Number -->
            <label for="phone">Phone Number</label>
            <input type="text" id="phone" name="phone" pattern="\d{10}" required>

            <!-- Email Address -->
            <label for="mail">Email</label>
            <input type="email" id="mail" name="mail" required>

            <!-- PIN -->
            <label for="pin">PIN</label>
            <input type="password" id="pin" name="pin" required>

            <!-- Confirm PIN -->
            <label for="confirmPin">Confirm PIN</label>
            <input type="password" id="confirmPin" name="confirmPin" required>
    
    <label>
      <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
    </label>
    
    <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

    <div class="clearfix">
      <button type="button" class="cancelbtn">Cancel</button>
      <button type="submit" class="signupbtn">Sign Up</button>
       <b>already have a account? </b>
 <a href="Login.html">Login</a>
    </div>
  </div>
</form>

</body>
</html>

        