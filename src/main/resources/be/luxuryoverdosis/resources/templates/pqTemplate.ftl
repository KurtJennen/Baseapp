<div id="${templateData.id}Grid"></div>

<script>
	$(document).ready(function () {
        var summary = "";
	
		var ${templateData.id}ColModel = [
			{ title: "", dataIndx: "selectedRow", maxWidth: 30, minWidth: 30, align: "center", resizable: false,
                type: 'checkBoxSelection', cls: 'ui-state-default', sortable: false, editable: false,
                cb: { all: true, header: true }
            },
            <#list templateData.pqGridColumnObjects as pqGridColumnObject>
            { title: "${pqGridColumnObject.title}"
            	, dataType: "${pqGridColumnObject.dataType}"
            	, dataIndx: "${pqGridColumnObject.dataIndx}"
            	, width: ${pqGridColumnObject.width}
            	, align: "${pqGridColumnObject.align}"
            	, sortable: ${pqGridColumnObject.sortable?string("true", "false")}
            	, resizable: ${pqGridColumnObject.resizable?string("true", "false")}
            	, filter: { type: "${pqGridColumnObject.filterType}", condition: "${pqGridColumnObject.filterCondition}", listeners: ['keyup'] }
            	, totalizable: ${pqGridColumnObject.totalizable?string("true", "false")}
            	<#if pqGridColumnObject.dataType = "float">
            		<#if pqGridColumnObject.currency = true>
            			, render: function formatCurrency(ui) {
            				return formatCurrencyPq(ui, "${templateData.locale}", "${templateData.currency}");
            			}
            		<#else>
            			, render: function format(ui) {
            				return formatPq(ui, "${templateData.locale}");
            			}
            		</#if>
            	</#if>
            },
            </#list>
		];
		
		var ${templateData.id}DataModel = {
            location: "remote",
            dataType: "json",
            method: "GET",
            url: "${templateData.url}",
            getData: function (dataJSON) {
                return { data: dataJSON };
            }
        };
        
        $("#${templateData.id}Grid").pqGrid({
        	width: "${templateData.width}",
            height: ${templateData.height},
            title: "${templateData.title}",
            freezeCols: ${templateData.freezeCols},
            scrollModel: {horizontal: true, autoFit: false},
            selectionModel: { type: null },
            dataModel: ${templateData.id}DataModel,
            colModel: ${templateData.id}ColModel,
            pageModel: { type: "local", rPP: ${templateData.rPP}, strRpp: "", rPPOptions: "" },
            sorting: "local",
            filterModel: { on: true, mode: "AND", header: true, type: "local" },
            check: function (evt, ui) {
            	checkPq(ui, "${templateData.id}Grid", "${templateData.selectedIds}");
            },
            unCheck: function (evt, ui) {
            	unCheckPq(ui, "${templateData.selectedIds}");
            },
            <#if templateData.rowClickMethod != "">
	            cellDblClick: function (evt, ui) {
	            	cellDblClickPq(ui, "${templateData.selectedIds}", "${templateData.rowClickMethod}");
	            },
            </#if>
            toolbar: {
                cls: 'pq-toolbar-export',
                items: [{
	                    type: 'button',
	                    label: "${templateData.exportLabelCsv}",
	                    icon: 'ui-icon-document',
	                    listeners: [{
	                        "click": function (evt) {
	                            $("#${templateData.id}Grid").pqGrid("exportCsv", { url: "${templateData.exportUrl}" });
	                        }
	                    }]
	            	},
                	{
                        type: 'button',
                        label: "${templateData.exportLabelExcel}",
                        icon: 'ui-icon-document',
                        listeners: [{
                            "click": function (evt) {
                                $("#${templateData.id}Grid").pqGrid("exportExcel", { url: "${templateData.exportUrl}", sheetName: "${templateData.title}" });
                            }
                        }]
                	}]
            },
            <#if templateData.summary = true>
	            render: function (evt, ui) {
	        		summary = $("<div class='pq-grid-summary'></div>").prependTo($(".pq-grid-bottom", this));
	        	},
	            refresh: function (evt, ui) {
	            	totals(ui, "${templateData.id}Grid", summary);
	        	}
	        </#if>
        });
        
        $("#${templateData.id}Grid").pqGrid("option", $.paramquery.pqGrid.regional['${templateData.language}']);
        $("#${templateData.id}Grid").find(".pq-pager").pqPager("option", $.paramquery.pqPager.regional['${templateData.language}']);
	});
</script>