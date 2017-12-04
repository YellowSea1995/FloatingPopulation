package com.fp.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 生成验证码
 */
public class VerifyServlet extends ActionSupport {
	  
	   

  @Override
  public String execute() {
		//定义验证码随机数
	   String randomData = createRandomData();
	   //将生成的验证码存放session中
	   ServletActionContext.getRequest().getSession().setAttribute(OAContant.VCODE, randomData);
	   
	   //画验证码

	   //创建画布
	   BufferedImage image = new BufferedImage(70, 25, BufferedImage.TYPE_INT_RGB);
	   
	   //通过画布创建画笔
	   Graphics2D d2 = image.createGraphics();
	   
	   //给画布上颜色
	   d2.setColor(Color.white);
	   d2.fillRect(0, 0, 70, 25);
	   
	   //设置线宽
	   d2.setStroke(new BasicStroke(2f));
	   
	   Random random = new Random();
	   //画干扰线
	   for(int i=0;i<200;i++){
		   int r = random.nextInt(255);
		   int g = random.nextInt(255);
		   int b = random.nextInt(255);
		   
		   //重新给画笔上颜色
		   Color color = new Color(r, g, b);
		   d2.setColor(color);
		   int x = random.nextInt(70);
		   int y = random.nextInt(25);
		   d2.drawLine(x, y, x, y);
	   }
	   
	   //将文字画在画布上
	   d2.setColor(Color.red);
	   //设置字体
	   Font font = new Font("宋体", Font.BOLD, 24);
	   
	   d2.setFont(font);
	   
	   d2.drawString(randomData, 10, 22);
	   
	   
	   //将验证码响应至客户端
	   OutputStream out = null;
	try {
		 out = ServletActionContext.getResponse().getOutputStream();
		 ImageIO.write(image, "png", out);
		 //刷新
		 out.flush();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		 //关闭流
		 try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	   
	   return NONE;
	}

	//生成验证码
	private String createRandomData() {
		// TODO Auto-generated method stub
		StringBuffer  sbf = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<4;i++){
			int a = random.nextInt(10);
			sbf.append(a);
		}
		return sbf.toString();
	}

}
