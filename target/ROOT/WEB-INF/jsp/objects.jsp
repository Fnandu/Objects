<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
   <head>
      <!-- Fonts -->
      <link rel="dns-prefetch" href="https://fonts.gstatic.com">
      <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">
      <link rel="stylesheet" href="WEB-INF/css/style.css">
      <!-- Bootstrap CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
         integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
      <title>My objects</title>
   </head>
   <body>
   <nav class="navbar navbar-expand-lg navbar-light bg-light">
     <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
       <a class="navbar-brand" href="#">Buckets</a>
       <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
         <li class="nav-item active">
           <a class="nav-link" href="/private/objects">Home</a>
         </li>
         <li class="nav-item">
           <a class="nav-link" href="/profile">Profile</a>
         </li>
       </ul>
     </div>
     <div class="float-end">
            <a href="/logout" >
              <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-closed"
                                viewBox="0 0 16 16">
                                <path d="M3 2a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V2zm1 13h8V2H4v13z">
                                </path>
                                <path d="M9 9a1 1 0 1 0 2 0 1 1 0 0 0-2 0z"></path>
                             </svg>
                             Logout
                             </button>
            </a>
            </div>
   </nav>
      <h1>Welcome, welcome ${user}</h1>
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
            <c:set var="count" value="1"/>
            <c:forEach var="n" items="${bucket}">
               <tr>
                  <th scope="row">${count}</th>
                  <td><a href="/private/objects/${n.uri}">/${n.uri}</a></td>
                  <td>${n.user}</td>
                  <td>${n.date}</td>
                  <td>
                     <form action="/delete/{bucket}" method="post">
                        <input type="hidden" name="delete_bucket" value=${n.uri}>
                        <input type="submit" class="btn btn-danger" value="delete">
                     </form>
                  </td>
               </tr>
               <c:set var="count" value="${count + 1}"/>
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