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
      <title>Title</title>
   </head>
   <body>
      <body>
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
         <a href="/private/objects">
         <button>
         back
         </button>
         </a>
         <div>
            <h1>Objects</h1>
            <h2>/${bucket}</h2>
         </div>
         <table class="table">
            <thead>
               <tr>
                  <th scope="col">#</th>
                  <th scope="col">FileName</th>
                  <th scope="col">FileType</th>
                  <th scope="col">FileSize</th>
                  <th scope="col">FileDate</th>
                  <th scope="col">Action</th>
               </tr>
            </thead>
            <tbody>
               <c:set var="count" value="1"/>
               <c:forEach var="n" items="${object_list}">
                  <tr>
                     <th scope="row">${count}</th>
                     <td ><a href="/private/objects/${n.fileUri}/${n.fileName}">${n.fileName}</a></td>
                     <td>${n.fileType}</td>
                     <td>${n.fileSize}</td>
                     <td>${n.fileDate}</td>
                     <td>
                        <form action="/delete/{object}" method="post">
                           <input type="hidden" name="fileid" value=${n.fileId}>
                           <input type="hidden" name="path" value=${n.fileUri}>
                           <input type="submit" class="btn btn-danger" value="delete">
                        </form>
                     </td>
                  </tr>
                  <c:set var="count" value="${count + 1}"/>
               </c:forEach>
            </tbody>
         </table>
         <button class="openButton" onclick="openForm()"><strong>New Object</strong></button>
         <div id="popupForm">
            <form action="/private/objects/{bucket}" method="post" enctype="multipart/form-data">
               <h2>Create your object!</h2>
               <label for="bucket_name">Add more buckets path</label>
               <div>
                  <input type="text" id="bucket_path" name="bucket_path" placeholder="/example/buckets">
                  <input type="hidden" name="actual_path" value=${bucket}>
               </div>
               <label for="file" value=>Choose a file:</label>
               <div>
                  <input type="file" id="file" name="file" enctype="multipart/form-data">
               </div>
               <button type="submit" id="add_bucket">Send</button>
               <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
            </form>
         </div>
         <script><%@ include file="../js/form.js"%></script>
   </body>
</html>