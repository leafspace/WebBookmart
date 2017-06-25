function disp_prompt(){
    websiteName = prompt("请输入书签名","");
    if(websiteName == ""){
        alert("书签名不可为空");
        websiteName = prompt("请输入书签名","");
    }
    website = prompt("请输入书签地址","");
    if(website == ""){
        alert("书签地址不可为空");
        website = prompt("请输入书签地址","");
    }
}

function disp2_prompt() {
    classificationName = prompt("请输入分类名","");
    if(classificationName == ""){
        alert("分类名不可为空");
        classificationName = prompt("请输入分类名","");
    }
}

function getEvent() {
    if (document.all) {
        return window.event; //如果是ie
    }
    func = getEvent.caller;
    while (func != null) {
        var arg0 = func.arguments[0];
        if (arg0) {
            if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
                return arg0;
            }
        }
        func = func.caller;
    }
    return null;
}
function stopevt() {
    var ev = getEvent();
    if (ev.stopPropagation) {
        ev.stopPropagation();
    } else if (window.ev) {
        window.ev.cancelBubble = true;
    }
}