<#list templateData.inputs as input>
	<input
		type="radio"
		name="${input.property}"
		tabindex="${input.tabindex}"
		
		<#if input.checked = true>
			checked="checked"
		</#if>
		<#if input.disabled = true>
			disabled="disableds"
		</#if>
	>
		${input.keyMessage}
	</input>
</#list>
