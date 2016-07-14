<html>
<head>
<title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<h1><p>Charter: ${charterName} </p></h1>
<ul>
<h2><p>${label0}: </p></h2>
</ul>
<#if labels0??>
<#list labels0 as label00>
<ul>
<p>- ${label00.text} </p>
</ul>
</#list>
</#if>

<#if labels1??>
<ul>
<h2><p>${label1}:</p></h2>
</ul>
<#list labels1 as label01>
<ul>
<p>- ${label01.text} </p>
</ul>
</#list>
</#if>

<#if labels2??>
<ul>
<h2><p>${label2}: </p></h2>
</ul>
 <#list labels2 as label02>
  <ul>
 <p>- ${label02.text} </p>
 </ul>
</#list>
</#if>

<#if labels3??>
<ul>
<h2><p>${label3}: </p></h2>
</ul>
 <#list labels3 as label03>
  <ul>
 <p>- ${label03.text} </p>
 </ul>
</#list>
</#if>

<#if labels4??>
<ul>
<h2><p>${label4}: </p></h2>
</ul>
 <#list labels4 as label04>
  <ul>
 <p>- ${label04.text} </p>
 </ul>
</#list>
</#if>
</ul>
 <#if screenprints??>	
<ul> 
<h2><p>List of screenprints: </p></h2>
</ul>
<#list screenprints as print>
<ul>

<a href=ScreenShots/${print.text}>
  <img src=ScreenShots/${print.text} alt="HTML tutorial" style="width:42px;height:42px;border:0;">
</a>
</ul>
</#list>
</#if>

 
 
<#assign var_link = "http://blog.abstracta.com.uy/2016/06/testing-exploratorio-proyecto-de-grado.html">
<#assign var_link_code = "https://github.com/abstracta/relytest">
<a href="${var_link}">About this project.</a> 
<a href="${var_link_code}">Get the latest version of the code.</a> 

</body>
</html>