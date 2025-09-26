<select
	name="${templateData.property}"
	tabindex="${templateData.tabindex}"
	onchange="${templateData.onchange}"
	
	
	<#if templateData.multiple = true>
		size="${templateData.size}"
		multiple="multiple"
	</#if>
	
	<#if templateData.disabled = true>
		disabled="disabled"
	</#if>
 >	
	<#list templateData.options as option>
		<option 
			value="${option.key}"
			<#if option.selected = true>
				selected="selected"
			</#if>
		>
			${option.keyMessage}
		</option>
	</#list>
</select>

