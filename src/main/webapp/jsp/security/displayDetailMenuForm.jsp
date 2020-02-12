<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/detailMenu.do">
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.menu" /></i></h2>
	</div>
	<lo:button image="table_save.png" method="update" key="button.update"></lo:button>
	<table>
		<tr>
			<td><fmt:message key="security.username" />*:</td>
			<td>
				<html:select property="userId" tabindex="1" onchange="doActionChange('changeUser');">
					<html:optionsCollection name="userList" label="name" value="id" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td><fmt:message key="security.menu.full.name" /></td>
			<td><fmt:message key="security.menu.name" /></td>
			<td><fmt:message key="security.menu.title" /></td>
			<td><fmt:message key="security.menu.full.level" /></td>
			<td><fmt:message key="security.menu.level" /></td>
			<td><fmt:message key="security.menu.hidden" /></td>
			<td><fmt:message key="security.menu.disabled" /></td>
			<td><fmt:message key="security.menu.for.pay" /></td>
			<td><fmt:message key="security.menu.payed" /></td>
		</tr>
		<c:forEach var="item" items="${menuForm.menus}" varStatus="status">
			<tr>
				<td>
					<html:hidden property="ids" value="${item.id}"/>
					<c:out value="${item.fullName}"></c:out>
				</td>
				<td>
					<c:forEach begin="0" end="${item.level}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:forEach>
					<c:out value="${item.name}"></c:out>
				</td>
				<td>
					<c:out value="${item.title}"></c:out>
				</td>
				<td>
					<c:out value="${item.fullLevel}"></c:out>
				</td>
				<td>
					<c:out value="${item.level}"></c:out>
				</td>
				<td>
					<c:if test="${item.level != 0}">
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="2" property="hidden" value="${item.hidden.code}" method="getAllCodes" />
					</c:if>
					<c:if test="${item.level == 0}">
						<html:hidden property="hidden" value="${item.hidden.code}"/>
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="2" property="hidden" value="${item.hidden.code}" method="getAllCodes" disabled="true" />
					</c:if>
				</td>
				<td>
					<c:if test="${item.level != 0}">
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="3" property="disabled" value="${item.disabled.code}" method="getAllCodes" />
					</c:if>
					<c:if test="${item.level == 0}">
						<html:hidden property="disabled" value="${item.disabled.code}"/>
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="3" property="disabled" value="${item.disabled.code}" method="getAllCodes" disabled="true" />
					</c:if>
				</td>
				<td>
					<c:if test="${item.level != 0}">
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="4" property="forPay" value="${item.forPay.code}" method="getAllCodes" />
					</c:if>
					<c:if test="${item.level == 0}">
						<html:hidden property="forPay" value="${item.forPay.code}"/>
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="4" property="forPay" value="${item.forPay.code}" method="getAllCodes" disabled="true" />
					</c:if>
				</td>
				<td>
					<c:if test="${item.level != 0}">
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="5" property="payed" value="${item.payed.code}" method="getAllCodes" />
					</c:if>
					<c:if test="${item.level == 0}">
						<html:hidden property="payed" value="${item.payed}"/>
						<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum" tabindex="5" property="payed" value="${item.payed.code}" method="getAllCodes" disabled="true" />
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</html:form>