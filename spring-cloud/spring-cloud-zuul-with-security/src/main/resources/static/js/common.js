function Map() {
    var struct = function (key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function (key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    };

    var get = function (key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var remove = function (key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
        }
    };

    var clear = function () {
        for (var i = 0; i < this.arr.length; i++) {
            this.arr.pop();
        }
    };

    var size = function () {
        return this.arr.length;
    };

    var isEmpty = function () {
        return this.arr.length <= 0;
    };

    var containsKey = function (key) {
        if (this.get(key)) {
            return true;
        }
        return false;
    };

    var keySet = function () {
        var keys = [];
        for (var i = 0; i < this.arr.length; i++) {
            keys.push(this.arr[i].key);
        }
        return keys;
    };

    var values = function (){
        var valueArray = [];
        var keys = this.keySet();
        for (var i = 0; i < keys.length; i++) {
            valueArray.push(this.get(keys[i]));
        }
        return valueArray;
    };

    this.arr = [];
    this.get = get;
    this.put = put;
    this.containsKey = containsKey;
    this.remove = remove;
    this.clear = clear;
    this.size = size;
    this.isEmpty = isEmpty;
    this.keySet = keySet;
    this.values = values;
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,// 月份
        "d+": this.getDate(),// 日
        "h+": this.getHours(),// 小时
        "m+": this.getMinutes(),// 分
        "s+": this.getSeconds(),// 秒
        "q+": Math.floor((this.getMonth() + 3) / 3),// 季度
        "S": this.getMilliseconds()// 毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

var LASender = function(){
    var send = function(bizName, bizDesc, username, sessionId){
        LA.sysCode = '67';
        LA.log(bizName, bizDesc, username, sessionId);
    };

    return {
        send: function (bizName, bizDesc, username, sessionId) {
            send(bizName, bizDesc, username, sessionId);
        }
    };
};