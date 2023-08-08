<button
	<#if templateData.buttonType == "DE">
		onclick="javascript:doAction('${templateData.method}')"
	</#if>
	<#if templateData.buttonType == "DI">
		onclick="javascript:doDialogCopy('${templateData.dialogId}', '${templateData.method}')"
	</#if>
	<#if templateData.buttonType == "C">
		onclick="javascript:doConfirm('${templateData.method}')"
	</#if>

	title="${templateData.title}"
	type="${templateData.type}"
 >
 	<img src="images/${templateData.image}" />
 	
	<#if templateData.showKey = true>
		&nbsp; ${templateData.key}
	</#if>
</button>