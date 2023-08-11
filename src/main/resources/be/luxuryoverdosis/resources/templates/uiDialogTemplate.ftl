<script>
	$(document).ready(function () {
      ${templateData.id}Dialog = $( "#${templateData.id}Dialog" ).dialog({
	    	title: "${templateData.title}",
	        autoOpen: ${templateData.autoOpen?string("true", "false")},
	        height: ${templateData.height},
	        width: ${templateData.width},
	        modal: ${templateData.modal?string("true", "false")},
	        buttons: {
	        	<#if templateData.defaultYesButton = true>
	           		"${templateData.yesLabel}": ${templateData.id}Add,
	        	</#if>
	        	<#if templateData.defaultNoButton = true>
	        		"${templateData.noLabel}": function() {
			          ${templateData.id}Dialog.dialog( "close" );
			          }
	        	</#if>
	        },
	        close: function() {
	       ${templateData.id}Dialog.dialog( "close" );
	        }
	      });
	    
	    function ${templateData.id}Add() {
	    	javascript:doDialogCopy('${templateData.id}Dialog', '${templateData.method}');
	    }
	});
</script>