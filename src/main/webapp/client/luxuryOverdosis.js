function doPositionForm(sMethod, oFormIndex) {
	var iPosition =  window.document.forms[oFormIndex].action.indexOf("?method=");
	if(iPosition != -1 ) {
		window.document.forms[oFormIndex].action = window.document.forms[0].action.substr(0, iPosition) + '?method=' + sMethod;
	} else {
		window.document.forms[oFormIndex].action += '?method=' + sMethod;
	}
}

function doPosition(sMethod) {
	doPositionForm(sMethod, 0);
}

function doActionForm(sMethod, oFormIndex) {
	doPositionForm(sMethod, oFormIndex);
	doSubmit();
}

function doAction(sMethod) {
	doActionForm(sMethod, 0);
}

function doActionDetailForm(sMethod, oFormIndex) {
	doPositionForm(sMethod, oFormIndex);
	window.document.forms[oFormIndex].submit();
}

function doActionDetail(sMethod) {
	doActionDetailForm(sMethod, 0);
}

function doActionChangeForm(sMethod, oFormIndex) {
	doPositionForm(sMethod, oFormIndex);
	window.document.forms[oFormIndex].submit();
}

function doActionChange(sMethod) {
	doActionChangeForm(sMethod, 0);
}

function doActionId(sMethod, oObject) {
	var oTag = oObject.parentNode.parentNode;
	var sId;
	if(window.navigator.appName.indexOf("Microsoft Internet Explorer") != -1 ) {
		sId = oTag.childNodes[0].childNodes[0].value;
	} else {
		sId = oTag.firstElementChild.firstElementChild.value;
	}
	
	window.document.forms[0].action += '?method=' + sMethod + '&id=' + sId;
	doSubmit();
}

function doActionNavigation(sMethod, sNameIds) {
	window.document.forms[0].action += '?method=' + sMethod + '&nameIds=' + sNameIds;
	doSubmit();
}

function doMenuAction(sAction, sMethod) {
	var sActionOld = window.document.forms[0].action;
	var iPosition = sActionOld.lastIndexOf("/");
	var sActionNew = sActionOld.substr(0, iPosition + 1);
	window.document.forms[0].action = sActionNew + sAction + '.do?method=' + sMethod;
	doSubmit();
}

function doSubmit() {
	if(window.navigator.appVersion.indexOf("IE 7.0")!= -1) {
		window.document.forms[0].submit();
	}
}

function colorTableRow(sId, sColumnNumber, sValue, sColor) {
	var table = document.getElementById(sId);
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    
    for(i=0; i < rows.length; i++) {
        var value = rows[i].getElementsByTagName("td")[sColumnNumber].firstChild.nodeValue;
        if (value == sValue) {
            rows[i].style.backgroundColor = sColor;
        }
    }
}

function onInvokeAction(id) {
	setExportToLimit(id, '');
	var parameterString = createParameterStringForLimit(id);
	var iPosition =  window.document.forms[0].action.indexOf("?method=");
	if(iPosition != -1 ) {
		location.href = window.document.forms[0].action.substr(0, iPosition) + '?method=listJmesa&' + parameterString;
	} else {
		location.href = window.document.forms[0].action + '?method=listJmesa&' + parameterString;
	}
}
	
function onInvokeExportAction(id) {
	var parameterString = createParameterStringForLimit(id);
	var iPosition =  window.document.forms[0].action.indexOf("?method=");
	if(iPosition != -1 ) {
		location.href = window.document.forms[0].action.substr(0, iPosition) + '?method=listJmesa&' + parameterString;
	} else {
		location.href = window.document.forms[0].action + '?method=listJmesa&' + parameterString;
	}
}