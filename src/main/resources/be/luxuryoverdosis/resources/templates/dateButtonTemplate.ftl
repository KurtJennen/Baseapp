<input
	type="text"
	name="${templateData.property}"
	tabindex="${templateData.tabindex}"
	value="${templateData.value}"
	id="${templateData.property}"
	onchange="${templateData.onchange}"
 	
	<#if templateData.disabled = true>
		disabled="disabled"
	</#if>
</input>

<script>
	javascript:doJQueryUiDatepicker('${templateData.property}');
</script>
