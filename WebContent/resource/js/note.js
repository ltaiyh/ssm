/**
 * 信息提示框
 * @param title
 * @param content
 * @param height
 * @param weight
 */
function note_init(title,content,height,weight) {  
    //取当前浏览器窗口大小  
    var windowWidth=$(document).width();  
    var windowHeight=$(document).height();  
    
    $("body").append(  
	    "<div onmouseover='note_show()' id='note_div' style='width:"+weight+"px;height:"+height+"px;top:"+(windowHeight-height-10)+"px;left:"+(windowWidth-weight-10)+"px'>"+  
	        "<div class='title'>" +  
	            "<div class='left'><b>"+title+"</b></div>"+
	            "<div class='close'><b onclick='note_close()'>×</b></div>" +  
	            "<div class='clear'></div>"+  
	        "</div>" +  
	        "<div>" +  
	             content+  
	        "</div>"+  
	    "</div>"  
    ); 
    
    $('#note_div').fadeIn(1000);  
}  

/**
 * 关闭提示框
 */
function note_close(){  
    $('#note_div').fadeOut(1000); 
}  

/**
 * 设置自动消失时间，默认5秒
 */
setInterval("note_close()", 5000);

/**
 * 显示提示框
 */
function note_show(){
	$('#note_div').fadeIn(1000);  
}

