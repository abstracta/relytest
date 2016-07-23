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
  <div class="container">
  <!-- Trigger the modal with a button -->
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

<#if logNotes??>
<div class="well"><H2>Charter Log:</H2>
<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#logNote">+</button>
</div>
<div id="logNote" class="collapse">
<ul class="list-group">
<#list logNotes as logNote>

<li class="list-group-item">${logNote.timeStamp}- <strong>[${logNote.label}]</strong>- ${logNote.text} 
<#if logNote.label == "Picture Taken">
<img class="img-responsive" src=ScreenShots/${logNote.text} alt=ScreenShots/${logNote.text}>
</#if>
</li>
</#list>
</ul>
</div>
</#if>

<div class="panel panel-default">
  <div class="panel-heading"><H2>Group by categories:</H2>
 <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#categories">+</button>
</div>
 <div class="panel-body">
<div id="categories" class="collapse">
<ul class="list-group">

<#if labels0??>
<li class="list-group-item"><span class="badge">${label0badge}</span>
<div class="well"><H3>${label0}:</H3>
<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#label0">+</button>
</div>
<div id="label0" class="collapse">
<#list labels0 as label00>
<ul>
<p>${label00.id}- ${label00.text} </p>
</ul>
</#list>
</div>
</li>
</#if>

<#if labels1??>
<li class="list-group-item"><span class="badge">${label1badge}</span>
<div class="well"><H3>${label1}:</H3>
<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#label1">+</button>
</div>
<div id="label1" class="collapse">
<#list labels1 as label01>
<ul>
<p>${label01.id}- ${label01.text} </p>
</ul>
</#list>
</div>
</li>
</#if>

<#if labels2??>
<li class="list-group-item"><span class="badge">${label2badge}</span>
<div class="well"><H3>${label2}:</H3>
<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#label2">+</button>
</div>
<div id="label2" class="collapse">
 <#list labels2 as label02>
  <ul>
 <p>${label02.id}- ${label02.text} </p>
 </ul>
</#list>
</div>
</li>
</#if>

<#if labels3??>
<li class="list-group-item"><span class="badge">${label3badge}</span>
<div class="well"><H3>${label3}:</H3>
<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#label3">+</button>
</div>
<div id="label3" class="collapse">
 <#list labels3 as label03>
  <ul>
 <p>${label03.id}- ${label03.text} </p>
 </ul>
</#list>
</div>
</li>
</#if>

<#if labels4??>
<li class="list-group-item"><span class="badge">${label4badge}</span>
<div class="well"><H3>${label4}:</H3>
<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#label04">+</button>
</div>
<div id="label04" class="collapse">
 <#list labels4 as label04>
  <ul>
 <p>${label04.id}- ${label04.text} </p>
 </ul>
</#list>
</div>
</li>
</#if>
 <#if screenprints??>	
<li class="list-group-item"><span class="badge">${label5badge}</span>
<div class="well"><H3>List of screenprints:</H3>
<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#screenPrints">+</button>
</div>
<div id="screenPrints" class="collapse">
<ul>
<#list screenprints as print>
<a href=ScreenShots/${print.text}>
 <img src=ScreenShots/${print.text} class="img-rounded" alt=ScreenShots/${print.text} width="304" height="236">
</a>
</#list>
</ul>
</div>
</li>
</#if>
</ul>
</div>
</div>
</div>
<style>
.bg-4 { 
    background-color: #2f2f2f;
    color: #ffffff;
}
</style>
</body>
<footer class="container-fluid bg-4 text-center">
  <p>RelyTest Made By <a href="http://abstracta.us">Abstracta.us</a></p> 
<p> 
 <a href="https://github.com/abstracta/relytest">Code at Github</a>
<a href="http://blog.abstracta.com.uy/2016/06/testing-exploratorio-proyecto-de-grado.html">Blog Abstracta</a>
</p> 
</footer>
</html>