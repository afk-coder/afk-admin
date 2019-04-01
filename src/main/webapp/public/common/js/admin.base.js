function searchParams(params) {
    var args = {};
    $('#myform').find('*[name]').each(function() {
        var value = $.trim($(this).val());
        var name = $.trim($(this).attr('name'));
        if(name && value){
            args[name] = value;
        }
    });
    var temp = {
        page: params.pageIndex,
        rows: params.pageSize,
        direct: params.order,
        filed: params.sort,
        args : args
    };
    return temp;
}

var layerId;
function layer_open(title, url, width, height) {
    layerId = layer.open({
        title: title,
        maxmin: false,
        type: 2,
        content: url,
        area: [width, height]
    });
}

function layer_close() {
    layer.close(layerId);
}

function date_format(value, pattern) {
    if(value == null || value == '') {
        return '';
    }
    return new Date(value).format(pattern);
}