<script>
	$(document).ready(function () {
      ${templateData.id}Dialog = $( "#${templateData.id}Dialog" ).dialog({
	    	title: "${templateData.title}",
	        autoOpen: ${templateData.autoOpen?string("true", "false")},
	        height: ${templateData.height},
	        width: ${templateData.width},
	        modal: ${templateData.modal?string("true", "false")},
	        buttons: {
	           "${templateData.saveLabel}": ${templateData.id}Add,
	           "${templateData.cancelLabel}": function() {
	          ${templateData.id}Dialog.dialog( "close" );
	          }
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