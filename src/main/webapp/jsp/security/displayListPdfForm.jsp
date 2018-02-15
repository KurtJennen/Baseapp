<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>

<script type="text/javascript">
    function PreviewImage() {
        pdffile=document.getElementById("formFileId").files[0];
        pdffile_url=URL.createObjectURL(pdffile);
        $('#viewer').attr('src',pdffile_url);
    }
</script>

<html:form action="/detailDocument.do" enctype="multipart/form-data">
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.pdf" /></i></h2>
	</div>
	<hr />
	
	<table>
		<tr>
			<td><fmt:message key="file" />*:</td>
			<td><html:file styleId="formFileId" property="formFile" size="100" maxlength="256" tabindex="2" onchange="PreviewImage();"></html:file></td>
		</tr>
	</table>
	
	<div style="clear:both;">
		<iframe id="viewer" style="border: none;" width="90%" height="500"></iframe>
	</div>
</html:form>