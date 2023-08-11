<button title="${templateData.title}"
	<#if templateData.visible = true>
		onclick="javascript:doActionNavigation('${templateData.action}','${templateData.nameIds}');"
	</#if>
	<#if templateData.visible = false>
		disabled="disabled"
	</#if>
>
	<#if templateData.visible = true>
		<img src="images/${templateData.image}.png"/>
	</#if>
	<#if templateData.visible = false>
		<img src="images/${templateData.image}_disabled.png"/>
	</#if>
</button>
 