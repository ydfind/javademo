<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<HTML>
<head>
    <LINK REL="StyleSheet" HREF="css/pk.css"/>
</head>
<TITLE>Login</TITLE>

<BODY>
<h2>Login</h2>
<%@include file="includes/menu.jsp" %>
<div class="content">

    <P>Welcome! Please enter your username to log in.</P>
    <html:form action="/Login">
        <html:errors/>

        <table>
            <tr>
                <td><b>username:</b></td>
                <td><html:text size="15" property="<%=Constants.USERNAME_PROPERTY %>"></html:text></td>
            </tr>
               <tr>
                <td><b>email:</b></td>
                <td><html:text size="32" property="<%=Constants.EMAIL_PROPERTY %>"></html:text></td>
            </tr>

        </table>

        <p><html:submit>Submit</html:submit></p>
    </html:form>

</div>
</BODY>
</HTML>
