function doAjaxSelect(sProperty, sMethodAll, sMethodOne, aFields, sNoResult) {
	$('#' + sProperty + 'Button').click(function(e) {
		doAjaxSelectCommon(sProperty, sMethodAll, sNoResult);
	});
	
	$('#' + sProperty + 'Value').keyup(function(e) {
		if(e.keyCode == 40) {
			doAjaxSelectCommon(sProperty, sMethodAll, sNoResult);
		}
	});
	
	if ($('#' + sProperty).val() > 0) {
		doAjaxSelectClicked(sProperty, sMethodOne, $('#' + sProperty).val(), aFields);
	}
}

function doAjaxSelectCommon(sProperty, sMethodAll, sNoResult) {
	$('#' + sProperty + 'Result').text("");
	
	$.ajax({
		type: 'post',
		url: doComposeAction(sMethodAll),
		data: $('form').serialize(),
		success: function(response) {
			if (response.length == 0) {
				$("<tr><td onclick=javascript:doAjaxBlurNoResult('" + sProperty + "') onmouseover=this.style.background='#fdecae' onmouseout=this.style.background='#e3e3e3'>" + sNoResult + "</td></tr>").appendTo($('#' + sProperty + 'Result'));
			} else {
				var template = $('#' + sProperty + 'Tmpl');
				template.tmpl($.parseJSON(response)).appendTo('#' + sProperty + 'Result');
			}
			
		},
		error: function(e) {
			alert('Error: ' + e);
		}
	})
}

function doAjaxSelectClicked(sProperty, sMethodOne, sId, aFields) {
	$('#' + sProperty).val(sId);
	
	$.ajax({
		type: 'post',
		url: doComposeAction(sMethodOne),
		data: $('form').serialize(),
		success: function(response) {
			var data = $.parseJSON(response);
			var val = '';
			
			$.each(aFields, function(index, value) {
				val = val + data[0][value] + ' ';
			});
	
			$('#' + sProperty + 'Value').val(val);
		},
		error: function(e) {
			alert('Error: ' + e);
		}
	})
	
	$('#' + sProperty + 'Result').text("");
};

function doComposeAction(sMethod) {
	var action = $('form').attr('action');
	var actionsplit = action.split("/");
	
	return '/'+ actionsplit[1] + '/' + sMethod;
}

function doAjaxBlur(sProperty) {
	if ($('#' + sProperty + 'Value').val() == "") {
		$('#' + sProperty).val(-1);
	}
};

function doAjaxBlurNoResult(sProperty) {
	$('#' + sProperty + 'Result').text("");
	$('#' + sProperty + 'Value').val("");
	$('#' + sProperty).val(-1);
};

function doJQueryUiDatepicker(sProperty) {
	$( "#" + sProperty ).datepicker({ dateFormat: 'dd/mm/yy' });
};