<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Fonts -->
  <link rel="dns-prefetch" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

  <link rel="stylesheet" href="WEB-INF/css/style.css">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
    integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
    integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous" />
  <title>My objects</title>
</head>

<body>
  <h1>Welcome, welcome ${user}</h1>
  <div id="Logout">
    <a href="/logout">

      <button type="button">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-closed"
          viewBox="0 0 16 16">
          <path d="M3 2a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V2zm1 13h8V2H4v13z">
          </path>
          <path d="M9 9a1 1 0 1 0 2 0 1 1 0 0 0-2 0z"></path>
        </svg>
        Log out
      </button>
    </a>
  </div>
  <div>
    <h1>Buckets</h1>
  </div>

  <table class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Uri</th>
        <th scope="col">User</th>
        <!--<th scope="col">Metadates</th>-->
        <th scope="col">Date</th>
        <th scope="col">Action</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach var="n" items="${bucket}">
      <tr>
        <th scope="row">1</th>
        <td>/${n.uri}</td>
        <td>${n.user}</td>
        <td>${n.date}</td>
        <td>
          <form><input type="submit" class="btn btn-danger" value="delete"></form>
        </td>
      </tr>
          </c:forEach>
    </tbody>
  </table>

  <button class="openButton" onclick="openForm()"><strong>New Bucket</strong></button>
  <div id="popupForm">
    <form action="/private/objects" method="post">
      <h2>Create your bucket!</h2>
      <!-- Uri(Ruta del archivo), usuario -->
      <label for="text">Buckets</label>
      <div>
        <input type="text" id="bucket_name" name="bucket_name">
      </div>
      <button type="submit" id="add_bucket">Send</button>
      <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
    </form>

  </div>
    <script><%@ include file="../js/form.js"%></script>
</body>
</html>