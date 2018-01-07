

function toDateString(date, showTime) {
    if (date) {
        if (!showTime)
            return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
        else
            return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours().toString().PadLeft(2, "0") + ":" + date.getMinutes().toString().PadLeft(2, "0");
    }
    else {
        return "";
    }
}

String.prototype.PadLeft = function (totalWidth, paddingChar) {
    if (paddingChar != null) {
        return this.PadHelper(totalWidth, paddingChar, false);
    } else {
        return this.PadHelper(totalWidth, ' ', false);
    }
}
String.prototype.PadRight = function (totalWidth, paddingChar) {
    if (paddingChar != null) {
        return this.PadHelper(totalWidth, paddingChar, true);
    } else {
        return this.PadHelper(totalWidth, ' ', true);
    }

}
String.prototype.PadHelper = function (totalWidth, paddingChar, isRightPadded) {

    if (this.length < totalWidth) {
        var paddingString = new String();
        for (i = 1; i <= (totalWidth - this.length) ; i++) {
            paddingString += paddingChar;
        }

        if (isRightPadded) {
            return (this + paddingString);
        } else {
            return (paddingString + this);
        }
    } else {
        return this;
    }
}

var msgdlg = {
    alert: function (title, msg, okFunc, color, draggable) {
        var close = function (element) {
            $(element).dialog('close');
        }
        if (color == null || color == undefined) {
            color = 'green';
        }
        if (draggable == null || draggable == undefined)
            draggable = true;
        $("<div>").appendTo($("body")).dialog({
            dialogClass: 'ui-dialog-' + color,
            title: title,
            width: 600,
            draggable: draggable,
            buttons: [
                {
                    'class': 'btn ' + color,
                    text: "确定",
                    click: function () {
                        close(this);
                    }
                }
            ],
            modal: true,
            close: function () {
                if (okFunc != null || okFunc != undefined)
                    okFunc();
                $(this).dialog('destroy');
                $(this).detach();
            }
        }).append(msg);
    },
    confirm: function (title, msg, okFunc, cancelFunc, color, draggable) {
        var close = function (element) {
            $(element).dialog('close');
        }
        if (color == null || color == undefined) {
            color = 'green';
        }
        if (draggable == null || draggable == undefined)
            draggable = true;
        var okClicked = false;
        $("<div>").appendTo($("body")).dialog({
            dialogClass: 'ui-dialog-' + color,
            title: title,
            width: 600,
            draggable: draggable,
            zIndex: 1500,
            buttons: [
                {
                    'class': 'btn ' + color,
                    text: "确定",
                    click: function () {
                        if (okFunc != null || okFunc != undefined)
                            okFunc();
                        okClicked = true;
                        close(this);
                    }
                },
                {
                    'class': 'btn',
                    text: "取消",
                    click: function () {
                        close(this);
                    }
                }
            ],
            modal: true,
            close: function () {
                if (!okClicked) {
                    if (cancelFunc != null || cancelFunc != undefined) {
                        cancelFunc();
                        return false;
                    }
                }
                $(this).dialog('destroy');
                $(this).detach();
            }
        }).append(msg);
    }
}

var SearchBuilder = {
    buildCondition: function (aoData, container) {
        var searchElements = $("[list-search-element='1']", container);
        for (var iLoop = 0; iLoop < searchElements.length; iLoop++) {
            var searchElement = searchElements[iLoop];
            aoData.push({ "name": "filters[" + iLoop + "].property", "value": $(searchElement).attr("list-search-prop") });
            aoData.push({ "name": "filters[" + iLoop + "].operator", "value": $(searchElement).attr("list-search-oper") });
            aoData.push({ "name": "filters[" + iLoop + "].value", "value": $(searchElement).val() });
        }
    },
    buildSort: function (aoData, oSettings) {
        var aoColumns = oSettings.aoColumns;
        var aaSorting = oSettings.aaSorting;
        for (var iLoop = 0; iLoop < aaSorting.length; iLoop++) {
            aoData.push({ "name": "sorts[" + iLoop + "].property", "value": aoColumns[aaSorting[iLoop][0]].mData });
            aoData.push({ "name": "sorts[" + iLoop + "].direction", "value": aaSorting[iLoop][1] == 'asc' ? "Ascending" : "Descending" });
        }
    },
    buildPageParam: function (aoData, oSettings) {
        aoData.push({ "name": "pageIndex", "value": parseInt(oSettings._iDisplayStart / oSettings._iDisplayLength) });
        aoData.push({ "name": "pageSize", "value": oSettings._iDisplayLength });
    },
    buildCSRFParam: function (aoData, oSettings) {
        aoData.push({ "name": "_csrf", "value": $("[name='_csrf']").val()});
    },
    buildAll: function (aoData, searchContainer, oSettings) {
        SearchBuilder.buildCondition(aoData, searchContainer);
        SearchBuilder.buildSort(aoData, oSettings);
        SearchBuilder.buildPageParam(aoData, oSettings);
        SearchBuilder.buildCSRFParam(aoData, oSettings);
    }
}

function initDataTable(table, options) {
    var optionOriginal = {
        "bProcessing": true,
        "bServerSide": true,
        "bSort": true,
        "aaSorting": [[0, "desc"]],
        "bFilter": false,
        "sAjaxDataProp": "list",
        "sServerMethod": "post",
        "searchPanel": $("[list-search='1']"),
        "aLengthMenu": [
            [10, 20],
            [10, 20] // change per page values here
        ],
        "sPaginationType": "bootstrap_full_number",
        "oLanguage": {
            "sSearch": "申请人：",
            "sLengthMenu": "每页_MENU_条记录",
            "sEmptyTable": "暂无符合条件的数据",
            "sInfoEmpty": "暂无符合条件的数据",
            "sInfo": "总数：_TOTAL_ 当前显示第 _START_ 到 _END_条",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页",
            }
        },
        "fnServerData": function (sUrl, aoData, fnCallback, oSettings) {
            App.blockUI({
                target: table,
                boxed: true,
                message: '正在加载数据...'
            });
            SearchBuilder.buildAll(aoData, optionOriginal.searchPanel, oSettings);
            oSettings.jqXHR = $.ajax({
                "dataType": 'json',
                "type": oSettings.sServerMethod,
                "url": sUrl,
                "data": aoData,
                "success": function (json) {
                    json.iTotalDisplayRecords = json.iTotalRecords = json.totalRecords;
                    fnCallback(json);
                    $("input", oTable).uniform();
                    App.unblockUI(table);
                },
                error: function (data) {
                    bootbox.alert("加载列表时出现异常");
                    App.unblockUI(table);
                }
            });
        }
    }

    for (var property in options) {
        optionOriginal[property] = options[property];
    }
    var oTable = $(table).dataTable(optionOriginal);

    // 绑定列头CheckBox改变事件，点击列头，选中所有行的Checkbox
    $("body").on("change", "[dt-chk-head='1']", function () {
        var randomId = $(this).attr("dt-chk-group");
        var checked = this.checked;

        $("[dt-chk-row='1'][dt-chk-group='" + randomId + "']").each(function () {
            this.checked = checked;
        });
        // 重新绑定下uniform样式，避免改了Check状态但是uniform样式未刷新
        $("[dt-chk-row='1'][dt-chk-group='" + randomId + "']").uniform();
    });

    // 每个行的checkbox被改变时，判断是不是所有同组的checkbox都为选中，是则把列头的checkbox选中，否则不选中
    $("body").on("change", "[dt-chk-row='1']", function () {
        var randomId = $(this).attr("dt-chk-group");
        var allCheck = 1;
        var allChks = $("[dt-chk-row='1'][dt-chk-group='" + randomId + "']");
        for (var iLoop = 0; iLoop < allChks.length; iLoop++) {
            allCheck &= allChks[iLoop].checked;
        }
        $("[dt-chk-head='1'][dt-chk-group='" + randomId + "']")[0].checked = allCheck == 1;
        // 重新绑定下uniform样式，避免改了Check状态但是uniform样式未刷新
        $("[dt-chk-head='1'][dt-chk-group='" + randomId + "']").uniform();
    });

    oTable.GetCheckedItems = function (index) {
        var headCheckBox = $("[dt-chk-head='1']");
        if (headCheckBox.length == 0)
            return null;
        if (index == null || index == undefined) {
            index = 0;
        }
        var dataList = new Array();
        var checkBox = headCheckBox[index];
        var randomId = $(checkBox).attr("dt-chk-group");
        var aData = oTable.fnGetData();
        var checkBoxes = $("[dt-chk-row='1'][dt-chk-group='" + randomId + "']:checked");
        for (var iLoop = 0; iLoop < checkBoxes.length; iLoop++) {
            var prop = $(checkBoxes[iLoop]).attr("dt-chk-prop");
            var value = $(checkBoxes[iLoop]).attr("value");
            for (var jLoop = 0; jLoop < aData.length; jLoop++) {
                if (aData[jLoop][prop] == value)
                    dataList.push(aData[jLoop]);
            }
        }
        return dataList;
    }
    oTable.defineOptions = options;
    // 详细信息
    oTable.on('click', ' tbody td .row-details', function () {
        var nTr = $(this).parents('tr')[0];
        if (oTable.fnIsOpen(nTr)) {
            // 已打开，则收起
            $(this).addClass("row-details-close").removeClass("row-details-open");
            oTable.fnClose(nTr);
        }
        else {
            // 已收起，则打开
            var fnGetDetail = oTable.defineOptions.aoColumns[oTable.defineOptions.detailIndex].fnGetDetail;
            $(this).addClass("row-details-open").removeClass("row-details-close");
            var colData = oTable.fnGetData(nTr);
            var detail = fnGetDetail(oTable, nTr, colData);
            oTable.fnOpen(nTr, detail, 'details');
        }
    });

    // 回车可触发查询
    optionOriginal.searchPanel.on('keydown', "input[type='text']", function (e) {
        if (e.keyCode == 13) {
            oTable.fnDraw();
        }
    })

    oTable.Export = function () {
        var postData = new Object();
        var searchElements = $("[list-search-element='1']", optionOriginal.searchPanel);
        var query = "?exp=1";
        postData.Filter = new Object();
        for (var iLoop = 0; iLoop < searchElements.length; iLoop++) {
            var searchElement = searchElements[iLoop];
            //postData.Filter[iLoop] = new Object();
            //postData.Filter[iLoop].Property = $(searchElement).attr("list-search-prop");
            //postData.Filter[iLoop].Operator = $(searchElement).attr("list-search-oper");
            //postData.Filter[iLoop].Value = $(searchElement).val();
            query += "&filters[" + iLoop + "].property=" + $(searchElement).attr("list-search-prop");
            query += "&filters[" + iLoop + "].operator=" + $(searchElement).attr("list-search-oper");
            query += "&filters[" + iLoop + "].value=" + $(searchElement).val();

        }
        postData.Sort = new Object();
        var oSettings = oTable.dataTableSettings[0];
        var aoColumns = oSettings.aoColumns;
        var aaSorting = oSettings.aaSorting;
        for (var iLoop = 0; iLoop < aaSorting.length; iLoop++) {
            //postData.Sort[iLoop] = new Object();
            //postData.Sort[iLoop].OrderField = aoColumns[aaSorting[iLoop][0]].mData;
            //postData.Sort[iLoop].Direction = aaSorting[iLoop][1];
            query += "&sorts[" + iLoop + "].property=" + aoColumns[aaSorting[iLoop][0]].mData;
            query += "&sorts[" + iLoop + "].direction=" + (aaSorting[iLoop][1] == 'asc' ? "Ascending" : "Descending");
        }
        location.href = optionOriginal.exportSource + query;
        return postData;
    }

    return oTable;
}

function renderDateTimeColumn(data, type, full, showTime) {
    if (data) {
        data = data.replace(/\//g, "-").replace("T", " ");
        var ps = data.split(" ");
        var pd = ps[0].split("-");
        var pt = ps.length > 1 ? (ps[1].length > 11 ? ps[1].substr(0, 11) : ps[1]).split(":") : [0, 0, 0];
        var d = new Date(pd[0], pd[1] - 1, pd[2], pt[0], pt[1], pt[2]);
        return toDateString(d, showTime);
    }
    else
        return "";
}


//Ajax提交请求,返回结果
var ajaxRequest = function (url, datas, reBindFun, needLoad) {
    var loadObj = $(".page-header-fixed");
    if (loadObj != null && needLoad != null && needLoad != undefined && needLoad) {
        $(".page-header-fixed").showLoading();
    }
    var obj = new Object();
    obj.isSuccess = false;
    obj.result = null;
    $.ajax({
        async: true, //取消异步
        type: "post",
        url: url, //请求页面
        traditional: true,//需要把 traditional 设置为 true 
        data: datas,
        success: function (data) { //回调函数,获取结果
            if (loadObj != null && needLoad != undefined && needLoad) {
                $(".page-header-fixed").hideLoading();
            }
            var result = data.Result;
            if (result != null && result != "") {
                obj.isSuccess = true;
                obj.result = result;
            }
            if (reBindFun != null || reBindFun != undefined && needLoad) {
                reBindFun(data);
            }
        }, error: function () {
            if (loadObj != null && needLoad != undefined && needLoad) {
                $(".page-header-fixed").hideLoading();
            }
            //alert("提示：Ajax请求失败，请刷新页面重试");
        }
    });
}


var bindCheckEvent = function (btn, bindName) {
    $("input[name='" + btn + "']").bind({
        change: function () {
            var checkValue = $("input[name='" + btn + "']:checked").val();
            if (checkValue != "1") {
                $("#" + bindName).val("0");
            } else {
                $("#" + bindName).val("1");
            }
        }
    });
}


$(document).ready(function () {
    if ($.minicolors) {
        $('.color-picker').each(function () {
            $(this).minicolors({
                control: $(this).attr('data-control') || 'hue',
                defaultValue: $(this).attr('data-defaultValue') || '',
                inline: $(this).attr('data-inline') === 'true',
                letterCase: $(this).attr('data-letterCase') || 'lowercase',
                opacity: $(this).attr('data-opacity'),
                position: $(this).attr('data-position') || 'bottom left',
                change: function (hex, opacity) {
                    if (!hex) return;
                    if (opacity) hex += ', ' + opacity;
                    if (typeof console === 'object') {
                        console.log(hex);
                    }
                },
                theme: 'bootstrap'
            });
        });
    }
    if (jQuery().datepicker) {
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            orientation: "left",
            autoclose: true
        });
    }
    bootbox.setLocale("zh_CN");
});