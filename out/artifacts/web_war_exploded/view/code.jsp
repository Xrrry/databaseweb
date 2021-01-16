<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/11/11
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="java.awt.image.BufferedImage" import="java.util.*"
    import="javax.imageio.ImageIO" pageEncoding="utf-8" %>
<%@ page import="java.awt.*" %>
<html>
<% response.setHeader("Cache-Control","no-cache");
    int width = 70,height=24;
    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
    Graphics g = image.getGraphics();
    g.setColor(new Color(200,200,200));
    g.fillRect(0,0,width,height);
    String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
    String str = "";
    for (int i=0;i<5;i++) {
        str = str+chars.charAt((int)(Math.random() *36));
    }
    session.setAttribute("randStr",str);
    Random rnd = new Random();
    int randNum = rnd.nextInt(8999) + 1000;
    String randStr = String.valueOf(randNum);
    g.setColor(Color.BLACK);
    g.setFont(new Font("",Font.PLAIN, 20));
    g.drawString(str,7,17);
    for (int i=0;i<200;i++){
        int x = rnd.nextInt(width);
        int y = rnd.nextInt(height);
        g.drawOval(x,y,1,1);
    }
    ImageIO.write(image, "JPEG", response.getOutputStream());
    out.clear();
    out = pageContext.pushBody();
%>
<body>

</body>
</html>
