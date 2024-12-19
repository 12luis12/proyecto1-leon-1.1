<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="intranetValida.jsp"/>
<div class="container">
 <div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    
    <div class="collapse navbar-collapse" >
    <ul class="nav navbar-nav navbar-left" >
       	<li><a href="verIntranetHome">Home</a></li>
    </ul>
    
    <ul class="nav navbar-nav">
   	    	<li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		           administrador <b class="caret"></b>
		        </a>
		        <ul class="dropdown-menu">
		        	<c:forEach var="x" items="${sessionScope.objMenus}">
		        	<c:if test="${x.tipo==1}">
					<li>
	        			<a href="${x.ruta}"> ${x.nombre} </a>
	        		</li>
	        	   </c:if>
		        	</c:forEach>
		        </ul>
	     	</li>
     </ul>
     <!--aqui hecho por lucio -->
     
     <ul class="nav navbar-nav">
    	<li class="dropdown">
	        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	           nogocio <b class="caret"></b>
	        </a>
	        <ul class="dropdown-menu">
	        	<c:forEach var="x" items="${sessionScope.objMenus}">
	        	  <c:if test="${x.tipo==2}">
					<li>
	        			<a href="${x.ruta}"> ${x.nombre} </a>
	        		</li>
	        	   </c:if>
	        	</c:forEach>
	        </ul>
  	</li>
</ul>
<ul class="nav navbar-nav">
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
       otros <b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
    	<c:forEach var="x" items="${sessionScope.objMenus}">
    	  <c:if test="${x.tipo==3}">
			<li>
    			<a href="${x.ruta}"> ${x.nombre} </a>
    		</li>
    	   </c:if>
    	</c:forEach>
    </ul>
</li>
</ul>
     
     <!--luciooooooooooo-->
     <ul class="nav navbar-nav navbar-right">
       	<li><a href="logout">Salir</a></li>
     </ul>
      
    </div>
  </div>
</div>  
</div>