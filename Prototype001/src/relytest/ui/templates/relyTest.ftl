<!DOCTYPE html>
<html lang="en">
<head>
  <title>${title} - ${charterName}</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
<div class="jumbotron text-center">
  <h1>Charter: ${charterName}</h1>
  <p>The content of the charter.</p> 
  
  <div class="container">
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Exploratory Testing Tip</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Exploratory Testing Tip</h4>
        </div>
        <div class="modal-body">
          <p>Exploratory software testing is a powerful and fun approach to testing.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>      
    </div>
  </div>  
</div>
  
  
</div>
<div class="alert alert-success fade in">
<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  <strong>Success!</strong> Your charter was great!
</div>

<#if logNotes??>
<div class="well">Charter Log:</div>
<p><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#logNote">Show Charter Log</button> </p>
<div id="logNote" class="collapse">
<#list logNotes as logNote>
<ul>
<p>${logNote.timeStamp}- [${logNote.label}]- ${logNote.text} </p>
<#if logNote.label == "Picture Taken">
<img class="img-responsive" src=ScreenShots/${logNote.text} alt="Chania">
</#if>
</ul>
</#list>
</div>
</#if>

<div class="well">Summary:</div>
<p><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#Summary">Show Summary</button></p>
<div id="Summary" class="collapse">

<#if labels0??>
<div class="well">${label0}:</div>
<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#label0">Show</button>
<div id="label0" class="collapse">
<#list labels0 as label00>
<ul>
<p>${label00.id}- ${label00.text} </p>
</ul>
</#list>
</div>
</#if>

<#if labels1??>
<div class="well">${label1}:</div>
<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#label1">Show</button>
<div id="label1" class="collapse">
<#list labels1 as label01>
<ul>
<p>${label01.id}- ${label01.text} </p>
</ul>
</#list>
</div>
</#if>

<#if labels2??>
<div class="well">${label2}:</div>
<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#label2">Show</button>
<div id="label2" class="collapse">
 <#list labels2 as label02>
  <ul>
 <p>${label02.id}- ${label02.text} </p>
 </ul>
</#list>
</div>
</#if>

<#if labels3??>
<div class="well">${label3}:</div>
<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#label3">Show</button>
<div id="label3" class="collapse">
 <#list labels3 as label03>
  <ul>
 <p>${label03.id}- ${label03.text} </p>
 </ul>
</#list>
</div>
</#if>

<#if labels4??>
<div class="well">${label4}:</div>
<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#label04">Show</button>
<div id="label04" class="collapse">
 <#list labels4 as label04>
  <ul>
 <p>${label04.id}- ${label04.text} </p>
 </ul>
</#list>
</div>
</#if>
</ul>
 <#if screenprints??>	
<div class="well">List of screenprints:</div>
<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#screenPrints">Show the images</button>
<div id="screenPrints" class="collapse">
<ul>
<#list screenprints as print>
<a href=ScreenShots/${print.text}>
 <!-- <img src=ScreenShots/${print.text} class="img-rounded" alt="Cinque Terre" width="304" height="236">
  <img class="img-responsive" src=ScreenShots/${print.text} alt="Chania">
 -->

 <img src=ScreenShots/${print.text} class="img-rounded" alt="Cinque Terre" width="304" height="236">
</a>
</#list>
</ul>
</div>
</#if>
</div>

<!-- 
<div class="btn-group btn-group-justified">
  <a href="http://blog.abstracta.com.uy/2016/06/testing-exploratorio-proyecto-de-grado.html" class="btn btn-primary">Blog Abstracta</a>
  <a href="https://github.com/abstracta/relytest" class="btn btn-primary">Code at Github</a>
</div>
-->
<style>
.bg-4 { 
    background-color: #2f2f2f;
    color: #ffffff;
}
</style>
</body>
<footer class="container-fluid bg-4 text-center">
  <p>RelyTest Made By <a href="http://abstracta.us">Abstracta.us</a></p> 
<p> <a href="http://blog.abstracta.com.uy/2016/06/testing-exploratorio-proyecto-de-grado.html">Blog Abstracta</a>
 <a href="https://github.com/abstracta/relytest">Code at Github</a></p> 
</footer>
</html>