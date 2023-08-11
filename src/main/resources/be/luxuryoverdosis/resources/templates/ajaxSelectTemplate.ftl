<script type="text/javascript">
	$(document).ready(function() {
		doAjaxSelect('${templateData.property}', 
			'${templateData.methodAll}', 
			'${templateData.methodOne}', 
			${templateData.concatenatedFields}, 
			'${templateData.searchNoResultKey}');
	});
</script>

<script id="${templateData.property}Tmpl" type="text/x-query-tmpl">
	<tr id="\${r"${id}"}" 
		onclick="javascript:doAjaxSelectClicked('${templateData.property}', 
			'${templateData.methodOne}', 
			${r"${id}"}, 
			${templateData.concatenatedFields}, 
			'${templateData.callbackActionMethodOne}');" 
		onmouseover="this.style.background='#fdecae'"
		onmouseout="this.style.background='#e3e3e3'">
	<#list templateData.seperatedArray as column>
		<td>${r"${"}${column}${r"}"}</td>
	</#list>
	</tr>
</script>

<input type="hidden" 
	name="${templateData.property}" 
	value="${templateData.value}" 
	id="${templateData.property}" />

<input type="text" 
	name="${templateData.property}Value" 
	maxlength="${templateData.maxLength}" 
	size="${templateData.size}" 
	tabindex="${templateData.tabindex}" 
	value="" 
	id="${templateData.property}Value" 
	oninput="javascript:doAjaxInput('${templateData.property}', 
		'${templateData.callbackActionMethodBlur}');" 
	autocomplete="off"
	
	<#if templateData.disabled = true>
		disabled="disabled"
	</#if>
	/>
<button id="${templateData.property}Button" 
	type="button" 
	title="${templateData.title}">
	<img src="images/${templateData.image}"/>
</button>

<div style="width: ${templateData.width}%; max-height: ${templateData.maxHeight}px; position: fixed; overflow: auto; z-index: 1;">
	<table id="${templateData.property}Result" style="background-color: #e3e3e3; width: 100%;" class=".ui-widget-content">
	</table>
</div>