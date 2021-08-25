<%--
  Created by IntelliJ IDEA.
  User: heojae
  Date: 2021/08/25
  Time: 10:50 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hubtwork.study.springmvc.data.Member" %>
<%@ page import="hubtwork.study.springmvc.repository.MemberRepository" %>
<%
  //request, response 사용 가능
  MemberRepository memberRepository = MemberRepository.Companion.getInstance();

  System.out.println("save.jsp");
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(null, username, age);
  memberRepository.save(member);

%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    성공
    <ul>
      <li>id=<%=member.getId()%></li>
      <li>username=<%=member.getUserName()%></li>
      <li>age=<%=member.getAge()%></li>
    </ul>
    <a href="/jsp/members.jsp"> 회원 등록 목록으로</a>
  </body>
</html>
