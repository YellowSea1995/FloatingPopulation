(function($){
	
	//给jQuery添加类的方法以及属性
	$.extend({
		week:["星期天","星期一","星期二","星期三","星期四","星期五","星期六"],
		calcTime:function(time){
			
			return time < 10?"0"+time:time;
		}

	})
	
	
	
	//给jQuery添加对象的方法
	$.fn.extend({
		timeRun:function(){
			  //获取当前事件
			  var date = new Date();
			  //获取年
			  var year = date.getFullYear();
			  //获取月
			  var month = date.getMonth()+1;
			  //获取天
			  var day = date.getDate();

			 
			  //获取星期
			  var week = date.getDay();//0-6
			  //获取时
			  var hour = date.getHours();
			  //获取分钟
			  var minute = date.getMinutes();
			  //获取秒
			  var second = date.getSeconds();

			  var timeStr = year + "年"+month+"月"+day+"日"+"&nbsp;&nbsp;"+$.week[week]+"&nbsp;&nbsp;"
              +$.calcTime(hour)+":"+$.calcTime(minute)+":"+$.calcTime(second); 
			
              this.html(timeStr);
              
              var t = this;
              
              //启动定时器
              window.setTimeout(function(){
            	t.timeRun();
            	 
              },1000);

		}
		
		
	})
	
	
})(jQuery)